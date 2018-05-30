package com.example.rexv666480.oddoventas;

import android.app.Activity;
import android.content.Context;
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
import com.example.rexv666480.oddoventas.Pages.PagePedidoVentaProducto;
import com.example.rexv666480.oddoventas.Pages.PagePedivoVentaCliente;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgregarPedidoVentaActivity extends AppCompatActivity {



    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private  ViewPagerAdapter adapter;
    private NuevoPedidoVenta nuevoPedidoVenta;

    private Context context;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pedido_venta);
        ButterKnife.bind(this);
        try {
            context = this;
            activity = this;
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            nuevoPedidoVenta = new NuevoPedidoVenta();
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }



    private void setupViewPager(final ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PagePedivoVentaCliente(), "Infomación Cliente");
        adapter.addFragment(new PagePedidoVentaProducto(), "Producto Agregados");
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Fragment f  = adapter.getItem(position);
                if(f.getView() != null)
                {
                    if(position == 0)
                    {
                    Button btnAgregar = (Button) f.getView().findViewById(R.id.btnAgregarProducto);
                    if (btnAgregar != null) {

                        btnAgregar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PagePedivoVentaCliente fp = (PagePedivoVentaCliente) adapter.getItem(viewPager.getCurrentItem());
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

                                if(AgregarProducto(fp)) {
                                    Snackbar snakc = Snackbar.make(v, "Producto Agregado ", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null);
                                    View sbView = snakc.getView();
                                    sbView.setBackgroundColor(getResources().getColor(R.color.colorMoradoOddo));
                                    snakc.show();
                                    fp.ResetFormulario();
                                    fp.getCbClientes().setEnabled(false);
                                }

                            }
                        });

                    }
                }else
                    {
                        PagePedidoVentaProducto fp = (PagePedidoVentaProducto) adapter.getItem(viewPager.getCurrentItem());
                        if(fp.getView() !=null)
                        {
                            Button btnGuadarPV = (Button) f.getView().findViewById(R.id.btnGuadarPV);
                            btnGuadarPV.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    
                                }
                            });

                            if(nuevoPedidoVenta.getProductos().size() > 0) {
                                fp.productoList = nuevoPedidoVenta.getProductos();
                                adapter.notifyDataSetChanged();
                                viewPager.setCurrentItem(position);
                            }else
                            {
                                adapter.notifyDataSetChanged();
                                viewPager.setCurrentItem(position);
                            }
                        }
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("onPageSelected","onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("onPageScrollStateChange","onPageScrollStateChanged");
            }
        });
    }

    public  boolean AgregarProducto(PagePedivoVentaCliente fp)
    {
        try {

            NuevoProducto nuevoProducto = new NuevoProducto();
            nuevoProducto.setCantidadPedido(Double.parseDouble(fp.getTxtCantidadPedido().getText().toString()));
            nuevoProducto.setDescuento(Double.parseDouble(fp.getTxtDescuento().getText().toString()));
            nuevoProducto.setPrecioUnitario(Double.parseDouble(fp.getTxtPrecioUnitario().getText().toString()));
            nuevoProducto.setSubtotal(Double.parseDouble(fp.getTxtSubTotal().getText().toString()));
            nuevoProducto.setIdProducto(((Producto)(fp.getCbProductos().getSelectedItem())).getId());
            nuevoProducto.setDescripcionProducto(((Producto)(fp.getCbProductos().getSelectedItem())).getName());
            nuevoProducto.setImage_small(((Producto)(fp.getCbProductos().getSelectedItem())).getImage_small());
            this.nuevoPedidoVenta.getProductos().add(nuevoProducto);
            return  true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
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
                PagePedidoVentaProducto f = (PagePedidoVentaProducto) object;
                if (f != null) {
                    f.update();
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
