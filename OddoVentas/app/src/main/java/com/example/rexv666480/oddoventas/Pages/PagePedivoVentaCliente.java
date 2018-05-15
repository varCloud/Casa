package com.example.rexv666480.oddoventas.Pages;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.rexv666480.oddoventas.Adapters.AdapterClientes;
import com.example.rexv666480.oddoventas.Entidades.Cliente;
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
public class PagePedivoVentaCliente extends Fragment {


    @BindView(R.id.cbClientes)
    Spinner cbClientes;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OdooConect odoo;
    private Loading loading;
    private int user_id = 0;

    public PagePedivoVentaCliente() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PagePedivoVentaCliente.
     */
    // TODO: Rename and change types and number of parameters
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

        }catch (Exception ex)
        {
             ex.printStackTrace();
        }
        return v;
    }

    public void ObtenerClientes()
    {
        loading.ShowLoading("Cargando...");

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
                        List<Cliente> clientes = OdooUtil.ObtenerCllientes(result);
                        if (clientes != null) {
                            ArrayAdapter<Cliente> myAdapter = new ArrayAdapter<Cliente>(getContext(), android.R.layout.simple_spinner_item, clientes);
                            cbClientes.setAdapter(myAdapter);
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

    public void ObtenerProductos()
    {
        loading.ShowLoading("Cargando...");

        AsyncTask asyncTask = new AsyncTask<Object,Object,Object>() {

            @Override
            protected Object doInBackground(Object... params) {
                Object result=null;
                try {
                    List conditions = asList(asList(
                            asList("sale_ok", "=", true),
                            asList("customer", "=", true)
                    ));

                    Map<String, List> filtros = new HashMap() {{
                        put("fields", asList("email","id","image_small","phone","city","display_name","country_id", "street", "comment"));
                        /*put("limit", 5);*/
                    }};

                    result=  odoo.getXmlClienteObject().call("execute_kw", odoo.getDb(), user_id, odoo.getPassword(), "product.product", "name_search", conditions, filtros);
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
                        List<Cliente> clientes = OdooUtil.ObtenerCllientes(result);
                        if (clientes != null) {
                            ArrayAdapter<Cliente> myAdapter = new ArrayAdapter<Cliente>(getContext(), android.R.layout.simple_spinner_item, clientes);
                            cbClientes.setAdapter(myAdapter);
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
}
