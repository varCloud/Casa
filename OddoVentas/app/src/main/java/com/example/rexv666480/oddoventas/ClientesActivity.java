package com.example.rexv666480.oddoventas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.rexv666480.oddoventas.Adapters.AdapterClientes;
import com.example.rexv666480.oddoventas.Adapters.AdapterPedidoVenta;
import com.example.rexv666480.oddoventas.Entidades.Cliente;
import com.example.rexv666480.oddoventas.Entidades.PedidoVenta;
import com.example.rexv666480.oddoventas.Odoo.OdooConect;
import com.example.rexv666480.oddoventas.Odoo.OdooUtil;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCCallback;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCException;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCServerException;
import com.example.rexv666480.oddoventas.Utilerias.Loading;
import com.example.rexv666480.oddoventas.Utilerias.PreferencesManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Arrays.asList;

public class ClientesActivity extends AppCompatActivity {
    private OdooConect odoo;
    private Loading loading;
    private Context context;
    private int user_id = 0;
    private String TAG = "ClientesActivity";
    private ListView lvClientes;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        try {
            activity = this;
            context = this;
            odoo = new OdooConect();
            loading = new Loading(context);
            user_id = Integer.parseInt(PreferencesManager.loadString(context, "usID", "0"));
            lvClientes = (ListView) findViewById(R.id.LvClientes);
            ObtenerClientes();



        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
                if (result != null) {
                    List<Cliente> clientes = ObtenerClientes(result);
                    if (clientes != null) {
                        AdapterClientes adapter = new AdapterClientes(activity, clientes);
                        lvClientes.setAdapter(adapter);
                    }
                }
                loading.CerrarLoading();
            }
        };

        asyncTask.execute();
    }

    public List<Cliente> ObtenerClientes(Object result)
    {

        List<Cliente> clientes = null;
        try{
            Object[] objects = (Object[]) result;
            if (objects.length > 0) {
                clientes = new ArrayList<>();
                Cliente c;
                for (Object object : objects) {
                    c = new Cliente();
                    c.setId(OdooUtil.getInteger((Map<String, Object>) object, "id"));
                    c.setDisplay_name(OdooUtil.getString((Map<String, Object>) object, "display_name"));
                    c.setStreet(OdooUtil.getString((Map<String, Object>) object, "street"));
                    c.setEmail(OdooUtil.getString((Map<String, Object>) object, "email"));
                    c.setCity(OdooUtil.getString((Map<String, Object>) object, "city"));
                    c.setImage_small(OdooUtil.getString((Map<String, Object>) object, "image_small"));
                    c.setCountry_id(OdooUtil.getTupla((Map<String, Object>) object, "country_id"));
                    clientes.add(c);
                }
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  clientes;
    }
}
