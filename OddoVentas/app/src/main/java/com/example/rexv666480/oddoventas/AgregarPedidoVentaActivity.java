package com.example.rexv666480.oddoventas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rexv666480.oddoventas.Adapters.AdapterClientes;
import com.example.rexv666480.oddoventas.Adapters.AdapterNuevoPedidoVenta;
import com.example.rexv666480.oddoventas.Entidades.AgregarPedidoVenta.NuevoPedidoVenta;
import com.example.rexv666480.oddoventas.Entidades.AgregarPedidoVenta.NuevoProducto;
import com.example.rexv666480.oddoventas.Entidades.Cliente;
import com.example.rexv666480.oddoventas.Entidades.Producto;
import com.example.rexv666480.oddoventas.Entidades.UnidadMedida;
import com.example.rexv666480.oddoventas.Odoo.OdooConect;
import com.example.rexv666480.oddoventas.Odoo.OdooUtil;
import com.example.rexv666480.oddoventas.Pages.PagePedidoVentaProducto;
import com.example.rexv666480.oddoventas.Pages.PagePedivoVentaCliente;
import com.example.rexv666480.oddoventas.Utilerias.Loading;
import com.example.rexv666480.oddoventas.Utilerias.PreferencesManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Arrays.asList;

public class AgregarPedidoVentaActivity extends AppCompatActivity {


    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ViewPagerAdapter viewPagerAdapter;
    private NuevoPedidoVenta nuevoPedidoVenta;
    private Loading loading;
    private OdooConect odoo;

    private Context context;
    private Activity activity;
    private int user_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pedido_venta);
        ButterKnife.bind(this);
        try {
            context = this;
            activity = this;
            loading = new Loading(context);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            odoo = new OdooConect();
            loading = new Loading(context);
            user_id = Integer.parseInt(PreferencesManager.loadString(context, "usID", "0"));

            nuevoPedidoVenta = new NuevoPedidoVenta();
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    private void setupViewPager(final ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new PagePedivoVentaCliente(), "Infomación Cliente");
        viewPagerAdapter.addFragment(new PagePedidoVentaProducto(), "Producto Agregados");
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Fragment f = viewPagerAdapter.getItem(position);
                try {
                    if (f.getView() != null) {
                        if (position == 0) {
                            final PagePedivoVentaCliente fp = (PagePedivoVentaCliente) viewPagerAdapter.getItem(0);
                            if (nuevoPedidoVenta.getCliente() == null) {
                                fp.ResetFormulario();
                                fp.getCbClientes().setEnabled(true);
                                fp.getCbClientes().setSelection(0);
                                fp.getCbProductos().setSelection(0);
                            }

                            Button btnAgregar = (Button) f.getView().findViewById(R.id.btnAgregarProducto);
                            if (btnAgregar != null) {

                                btnAgregar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        if (((Cliente) (fp.getCbClientes().getSelectedItem())).getId() == -1) {
                                            Snackbar.make(v, "Seleccione un cliente", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                            return;
                                        }

                                        if (((Producto) (fp.getCbProductos().getSelectedItem())).getId() == -1) {
                                            Snackbar.make(v, "Seleccione un producto", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                            return;
                                        }

                                        if (TextUtils.isEmpty(fp.getTxtCantidadPedido().getText().toString())) {
                                            Snackbar.make(v, "Especifique una cantidad.", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                            return;
                                        }

                                        if (TextUtils.isEmpty(fp.getTxtPrecioUnitario().getText().toString())) {
                                            Snackbar.make(v, "Especifique un precio.", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                            return;
                                        }

                                        if (TextUtils.isEmpty(fp.getTxtDescuento().getText().toString())) {
                                            Snackbar.make(v, "Especifique un descuento.", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                            return;
                                        }

                                        if (AgregarProducto(fp)) {
                                            Snackbar snakc = Snackbar.make(v, "Producto Agregado ", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null);
                                            View sbView = snakc.getView();
                                            sbView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                            snakc.show();
                                            fp.ResetFormulario();
                                            fp.getCbClientes().setEnabled(false);
                                            fp.getCbProductos().setSelection(0);
                                        }
                                    }
                                });

                            }
                        } else {

                            PagePedidoVentaProducto fp = (PagePedidoVentaProducto) viewPagerAdapter.getItem(viewPager.getCurrentItem());
                            if (fp.getView() != null) {
                                Button btnGuadarPV = (Button) f.getView().findViewById(R.id.btnGuadarPV);
                                btnGuadarPV.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (nuevoPedidoVenta.getProductos().size() > 0)
                                            GurdarPedidoVenta();
                                        else {
                                            Snackbar snakc = Snackbar.make(view, "No existen productos ", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null);
                                            View sbView = snakc.getView();
                                            sbView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                            snakc.show();
                                        }

                                    }
                                });
                                if(nuevoPedidoVenta != null) {
                                    if (nuevoPedidoVenta.getProductos().size() > 0) {
                                        fp.productoList = nuevoPedidoVenta.getProductos();
                                        viewPagerAdapter.notifyDataSetChanged();
                                        viewPager.setCurrentItem(position);
                                    }
                                }
//                                else { //Limpia el listView Actual de AdapaterNuevoPedidoVenta
//                                    fp.productoList = null;
//                                    viewPagerAdapter.notifyDataSetChanged();
//                                    viewPager.setCurrentItem(position);
//                                }
                            }
                        }
                    }
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("onPageSelected", "onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("onPageScrollStateChange", "onPageScrollStateChanged");
            }
        });
    }

    public boolean AgregarProducto(PagePedivoVentaCliente fp) {
        try {
            if (this.nuevoPedidoVenta.getCliente() == null) {
                Cliente c = new Cliente();
                c.setId(((Cliente) (fp.getCbClientes().getSelectedItem())).getId());
                this.nuevoPedidoVenta.setCliente(c);
            }

            NuevoProducto nuevoProducto = new NuevoProducto();
            nuevoProducto.setCantidadPedido(Double.parseDouble(fp.getTxtCantidadPedido().getText().toString()));
            nuevoProducto.setDescuento(Double.parseDouble(fp.getTxtDescuento().getText().toString()));
            nuevoProducto.setPrecioUnitario(Double.parseDouble(fp.getTxtPrecioUnitario().getText().toString()));
            nuevoProducto.setSubtotal(Double.parseDouble(fp.getTxtSubTotal().getText().toString()));
            nuevoProducto.setIdProducto(((Producto) (fp.getCbProductos().getSelectedItem())).getId());
            nuevoProducto.setUnidadDeMedida(((UnidadMedida) (fp.getCbUnidadMedida().getSelectedItem())).getId());
            nuevoProducto.setDescripcionProducto(((Producto) (fp.getCbProductos().getSelectedItem())).getName());
            nuevoProducto.setImage_small(((Producto) (fp.getCbProductos().getSelectedItem())).getImage_small());
            nuevoProducto.setUnidadDeMedidaDescripcion(((UnidadMedida) fp.getCbUnidadMedida().getSelectedItem()).getDisplay_name());
            this.nuevoPedidoVenta.getProductos().add(nuevoProducto);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void GurdarPedidoVenta() {
        loading.ShowLoading("Guardando pedido ...");
        AsyncTask asyncTask = new AsyncTask<Object, Object, Object>() {

            @Override
            protected Object doInBackground(Object... params) {
                Object result = null;
                try {
                    NuevoPedidoVenta nuevoPedidoVenta = null;
                    if (params != null)
                        nuevoPedidoVenta = (NuevoPedidoVenta) params[0];
                    else
                        return null;

                    final NuevoPedidoVenta finalNuevoPedidoVenta = nuevoPedidoVenta;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
                    Date date = new Date();
                    final String fecha = dateFormat.format(date);

                    List crear = asList(new HashMap() {{
                        put("partner_id", finalNuevoPedidoVenta.getCliente().getId());
                        put("date_order", fecha);
                    }});

                    result = odoo.getXmlClienteObject().call("execute_kw", odoo.getDb(), user_id, odoo.getPassword(), "sale.order", "create", crear);
                } catch (Exception ex) {
                    loading.CerrarLoading();
                    ex.printStackTrace();
                }
                return result;
            }


            @Override
            protected void onPostExecute(Object result) {
                try {
                    if (result != null) {
                        Log.d("result", result.toString());
                        GuardarProductosVenta(Integer.parseInt(result.toString()));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        };
        asyncTask.execute(nuevoPedidoVenta);
    }

    public void GuardarProductosVenta(final int idOrden) {
        loading.ShowLoading("Guardando productos...");
        AsyncTask asyncTask = new AsyncTask<Object, Object, Object>() {

            @Override
            protected Object doInBackground(Object... params) {
                Object result = null;
                Object idProductos = null;
                try {
                    NuevoPedidoVenta nuevoPedidoVenta = null;
                    if (params != null)
                        nuevoPedidoVenta = (NuevoPedidoVenta) params[0];
                    else
                        return null;

                    final NuevoPedidoVenta finalNuevoPedidoVenta = nuevoPedidoVenta;
                    for (final NuevoProducto item : finalNuevoPedidoVenta.getProductos()) {
                        List crear = asList(new HashMap() {{
                            put("product_id", item.getIdProducto());
                            put("order_id", idOrden);
                            put("product_uom_qty", item.getCantidadPedido());
                            put("price_unit", item.getPrecioUnitario());
                            put("product_uom", item.getUnidadDeMedida());
                        }});
                        result = odoo.getXmlClienteObject().call("execute_kw", odoo.getDb(), user_id, odoo.getPassword(), "sale.order.line", "create", crear);
                        idProductos += result + ",";
                        Log.d("reault", result.toString());
                    }

                } catch (Exception ex) {
                    loading.CerrarLoading();
                    ex.printStackTrace();
                }
                return idProductos;
            }

            @Override
            protected void onPostExecute(Object result) {
                try {
                    if (result != null) {
                        final AlertDialog alertDialog = new AlertDialog.Builder(
                                AgregarPedidoVentaActivity.this).create();

                        alertDialog.setMessage("Pedido guardado exitosamente");
                        alertDialog.setIcon(R.drawable.logo_odoo72);
                        alertDialog.setCancelable(false);
                        alertDialog.setButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                nuevoPedidoVenta = new NuevoPedidoVenta();
                                PagePedidoVentaProducto fp = (PagePedidoVentaProducto) viewPagerAdapter.getItem(viewPager.getCurrentItem());
                                fp.productoList = null;
                                viewPagerAdapter.notifyDataSetChanged();
                                viewPager.setCurrentItem(0);
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                loading.CerrarLoading();
            }
        };
        asyncTask.execute(nuevoPedidoVenta);

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public int getItemPosition(Object object) {
            if (object instanceof PagePedidoVentaProducto) {
                PagePedidoVentaProducto fPagePedidoVentaProducto = (PagePedidoVentaProducto) object;
                if (fPagePedidoVentaProducto != null) {
                    fPagePedidoVentaProducto.update();
                }
            }
            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
