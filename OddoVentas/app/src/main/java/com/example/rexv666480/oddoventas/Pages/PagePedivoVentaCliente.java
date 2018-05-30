package com.example.rexv666480.oddoventas.Pages;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rexv666480.oddoventas.Adapters.AdapterClientes;
import com.example.rexv666480.oddoventas.Entidades.AgregarPedidoVenta.NuevoPedidoVenta;
import com.example.rexv666480.oddoventas.Entidades.AgregarPedidoVenta.NuevoProducto;
import com.example.rexv666480.oddoventas.Entidades.Cliente;
import com.example.rexv666480.oddoventas.Entidades.Impuesto;
import com.example.rexv666480.oddoventas.Entidades.Producto;
import com.example.rexv666480.oddoventas.Entidades.UnidadMedida;
import com.example.rexv666480.oddoventas.Odoo.OdooConect;
import com.example.rexv666480.oddoventas.Odoo.OdooUtil;
import com.example.rexv666480.oddoventas.R;
import com.example.rexv666480.oddoventas.Utilerias.Loading;
import com.example.rexv666480.oddoventas.Utilerias.PreferencesManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Arrays.asList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PagePedivoVentaCliente.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PagePedivoVentaCliente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PagePedivoVentaCliente extends Fragment  {


    @BindView(R.id.cbClientes)
    Spinner cbClientes;

    @BindView(R.id.cbProductos)
    Spinner cbProductos;

    @BindView(R.id.cbUnidadMedida)
    Spinner cbUnidadMedida;

    @BindView(R.id.cbImpuestos)
    Spinner cbImpuestos;

    @BindView(R.id.btnAgregarProducto)
    Button btnAgregarProducto;

    @BindView(R.id.txtPrecioUnitario)
    EditText txtPrecioUnitario;

    @BindView(R.id.txtDescuento)
    EditText txtDescuento;

    @BindView(R.id.txtSubTotal)
    EditText txtSubTotal;

    @BindView(R.id.txtCantidadPedido)
    EditText txtCantidadPedido;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public List<Producto> productos;
    public List<UnidadMedida> unidadesMedida;
    public List<Cliente> clientes;
    public List<Impuesto> impuestos;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OdooConect odoo;
    private Loading loading;
    private int user_id = 0;
    public NuevoProducto nuevoProducto;

    public PagePedivoVentaCliente() {
        // Required empty public constructor
    }

    public Spinner getCbClientes() {
        return cbClientes;
    }

    public Spinner getCbProductos() {
        return cbProductos;
    }

    public Spinner getCbUnidadMedida() {
        return cbUnidadMedida;
    }

    public Spinner getCbImpuestos() {
        return cbImpuestos;
    }

    public EditText getTxtPrecioUnitario() {
        return txtPrecioUnitario;
    }

    public EditText getTxtDescuento() {
        return txtDescuento;
    }

    public EditText getTxtSubTotal() {
        return txtSubTotal;
    }

    public EditText getTxtCantidadPedido() {
        return txtCantidadPedido;
    }


    public static PagePedivoVentaCliente newInstance(String param1, String param2) {
        PagePedivoVentaCliente fragment = new PagePedivoVentaCliente();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page_pedivo_venta_cliente, container, false);
        ButterKnife.bind(this,v);


        try{

            user_id = Integer.parseInt(PreferencesManager.loadString(getContext(), "usID", "0"));
            odoo = new OdooConect();
            loading = new Loading(getContext());
            ObtenerClientes();
            InitTextChange();

            btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Click","Click en la pagina");
                }
            });

            cbProductos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //ObtenerUnidadMedidas(productos.get(position).getUom_id()[0]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }catch (Exception ex)
        {
             ex.printStackTrace();
        }
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        try {
            inflater.inflate(R.menu.main, menu);
            menu.findItem(R.id.action_delete).setVisible(false);

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    ///////////////////////////////////////////////////CARGA DE INFORMACION POR DEFAULT//////////////////////////////////////////////////////////////////////////
    private void ObtenerClientes()
    {
        loading.ShowLoading("Cargando Clientes...");

        AsyncTask asyncTask = new AsyncTask<Object,Object,Object>() {

            @Override
            protected Object doInBackground(Object... params) {
                Object result=null;
                try {
                    List conditions = asList(asList(
                            asList("parent_id", "=", false),
                            asList("customer", "=", true)));

                    Map<String, List> filtros = new HashMap() {{
                        put("fields", asList("email","id","image_small","phone","city","display_name","country_id", "street", "comment"));
                        /*put("limit", 5);*/
                    }};

                    result=  odoo.getXmlClienteObject().call("execute_kw", odoo.getDb(), user_id, odoo.getPassword(), "res.partner", "search_read", conditions, filtros);
                }catch (Exception ex)
                {
                    loading.CerrarLoading();
                    ex.printStackTrace();
                }
                return  result;
            }

            @Override
            protected void onPostExecute(Object result) {
                try {
                    if (result != null) {
                        clientes = OdooUtil.ObtenerCllientes(result);
                        if (clientes != null) {
                            ArrayAdapter<Cliente> myAdapter = new ArrayAdapter<Cliente>(getContext(), android.R.layout.simple_spinner_item, clientes);
                            cbClientes.setAdapter(myAdapter);
                            ObtenerProductos();
                        }
                    }
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        };

        asyncTask.execute();
    }

    private void ObtenerProductos()
    {
        loading.ShowLoading("Cargando Productos...");

        AsyncTask asyncTask = new AsyncTask<Object,Object,Object>() {

            @Override
            protected Object doInBackground(Object... params) {
                Object result=null;
                try {
                    List conditions = asList(asList(
                            asList("sale_ok", "=", true)
                    ));

                    Map<String, List> filtros = new HashMap() {{
                        put("fields", asList("default_code",
                                "name",
                                "attribute_value_ids",
                                "lst_price",
                                "price",
                                "qty_available",
                                "virtual_available",
                                "uom_id",
                                "barcode",
                                "product_tmpl_id",
                                "active",
                                "image_small"));
                        /*put("limit", 5);*/
                    }};

                    result=  odoo.getXmlClienteObject().call("execute_kw", odoo.getDb(), user_id, odoo.getPassword(), "product.product", "search_read", conditions, filtros);
                }catch (Exception ex)
                {
                    loading.CerrarLoading();
                    ex.printStackTrace();
                }
                return  result;
            }

            @Override
            protected void onPostExecute(Object result) {
                try {
                    if (result != null) {
                        productos = OdooUtil.ObtenerProductos(result);
                        if (productos != null) {
                            ArrayAdapter<Producto> myAdapter = new ArrayAdapter<Producto>(getContext(), android.R.layout.simple_spinner_item, productos);
                            cbProductos.setAdapter(myAdapter);
                            ObtenerUnidadMedidas();
                        }
                    }
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                loading.CerrarLoading();
            }
        };

        asyncTask.execute();
    }

    private void ObtenerUnidadMedidas()
    {
        loading.ShowLoading("Cargando Unidade de Medida...");

        AsyncTask asyncTask = new AsyncTask<Object,Object,Object>() {

            @Override
            protected Object doInBackground(Object... params) {
                Object result=null;
                try {
                    List conditions = asList(asList(
                            asList("category_id", "=", 1)
                    ));

                    Map<String, List> filtros = new HashMap() {{
                        put("fields", asList("display_name","id","category_id","name"
                               ));
                        /*put("limit", 5);*/
                    }};

                    result=  odoo.getXmlClienteObject().call("execute_kw", odoo.getDb(), user_id, odoo.getPassword(), "product.uom", "search_read", conditions, filtros);
                }catch (Exception ex)
                {
                    loading.CerrarLoading();
                    ex.printStackTrace();
                }
                return  result;
            }

            @Override
            protected void onPostExecute(Object result) {
                try {
                    if (result != null) {
                        unidadesMedida = OdooUtil.ObtenerUnidadesMedida(result);
                        if (unidadesMedida != null) {
                            ArrayAdapter<UnidadMedida> myAdapter = new ArrayAdapter<UnidadMedida>(getContext(), android.R.layout.simple_spinner_item, unidadesMedida);
                            cbUnidadMedida.setAdapter(myAdapter);
                            ObtenerImpuestos();
                        }
                    }
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                loading.CerrarLoading();
            }
        };

        asyncTask.execute();
    }

    private void ObtenerImpuestos()
    {
        loading.ShowLoading("Cargando Impuestos...");

        AsyncTask asyncTask = new AsyncTask<Object,Object,Object>() {

            @Override
            protected Object doInBackground(Object... params) {
                Object result=null;
                try {
                    List conditions = asList(asList(
                            asList("company_id", "=", 1),
                            asList("type_tax_use", "=", "sale")
                    ));

                    Map<String, List> filtros = new HashMap() {{
                        /*put("fields", asList("display_name","id","category_id","name"
                        ));*/
                        /*put("limit", 5);*/
                    }};

                    result=  odoo.getXmlClienteObject().call("execute_kw", odoo.getDb(), user_id, odoo.getPassword(), "account.tax", "search_read", conditions, filtros);
                }catch (Exception ex)
                {
                    loading.CerrarLoading();
                    ex.printStackTrace();
                }
                return  result;
            }

            @Override
            protected void onPostExecute(Object result) {
                try {
                    if (result != null) {
                        impuestos = OdooUtil.ObtenerImpuestos(result);
                        if (impuestos != null) {
                            ArrayAdapter<Impuesto> myAdapter = new ArrayAdapter<Impuesto>(getContext(), android.R.layout.simple_spinner_item, impuestos);
                            cbImpuestos.setAdapter(myAdapter);
                        }
                    }
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                loading.CerrarLoading();
            }
        };

        asyncTask.execute();
    }

    private  void InitTextChange()
    {
        txtDescuento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalcularSubtotal();

            }
        });

        txtPrecioUnitario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalcularSubtotal();

            }
        });

        txtCantidadPedido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalcularSubtotal();

            }
        });
    }

    private  void CalcularSubtotal()
    {
        try {
            if (! TextUtils.isEmpty(txtCantidadPedido.getText()) &&
                    ! TextUtils.isEmpty(txtPrecioUnitario.getText()) &&
                    ! TextUtils.isEmpty(txtDescuento.getText())) {

                Double unidadMedida  =1D;
                if (((UnidadMedida) (cbUnidadMedida.getSelectedItem())).getId() == 2)
                    unidadMedida = 12D;


                Double canitdadXpedido =unidadMedida * Double.parseDouble(txtCantidadPedido.getText().toString()) * Double.parseDouble(txtPrecioUnitario.getText().toString());
                if (canitdadXpedido > 0) {
                    Double descuento = canitdadXpedido * (Double.parseDouble(txtDescuento.getText().toString()) / 100);
                    canitdadXpedido = canitdadXpedido - descuento;
                    txtSubTotal.setText(canitdadXpedido.toString());

                }

            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public  void ResetFormulario()
    {
        txtCantidadPedido.setText("");
        txtPrecioUnitario.setText("");
        txtDescuento.setText("");
        txtSubTotal.setText("");
    }

}
