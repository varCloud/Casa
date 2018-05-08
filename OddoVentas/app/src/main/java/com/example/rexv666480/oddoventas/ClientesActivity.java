package com.example.rexv666480.oddoventas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.rexv666480.oddoventas.Entidades.Cliente;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        try {
            context = this;
            odoo = new OdooConect();
            loading = new Loading(context);
            user_id = Integer.parseInt(PreferencesManager.loadString(context, "usID", "0"));
            loading.ShowLoading("Cargando ...");
            XMLRPCCallback listener = new XMLRPCCallback() {
                public void onResponse(long id, Object result) {
                    ObtenerClientes(result);
                    loading.CerrarLoading();

                }

                public void onError(long id, XMLRPCException error) {
                    error.printStackTrace();
                    loading.CerrarLoading();
                }

                @Override
                public void onServerError(long id, XMLRPCServerException error) {
                    error.printStackTrace();
                    loading.CerrarLoading();
                }

            };

            List conditions = asList(asList(
                    asList("is_company", "=", false),
                    asList("customer", "=", false)));

            Map<String, List> filtros = new HashMap() {{
                put("fields", asList("name", "country_id","street", "comment"));
                put("limit", 5);
            }};

            odoo.getXmlClienteObject().callAsync(listener, "execute_kw", odoo.getDb(), user_id, odoo.getPassword(), "res.partner", "search_read", conditions, filtros);



        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
                    String name= OdooUtil.getString((Map<String, Object>) object, "name");
                    boolean is_company= OdooUtil.getBoolean((Map<String, Object>) object, "is_company");
                    String street = OdooUtil.getString((Map<String, Object>) object, "street");
                    int id= OdooUtil.getInteger((Map<String, Object>) object, "id");
                    Object[] tuple =  OdooUtil.getTupla((Map<String, Object>) object, "country_id");
                    c.setId(String.valueOf(id));
                    c.setName(name);
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
