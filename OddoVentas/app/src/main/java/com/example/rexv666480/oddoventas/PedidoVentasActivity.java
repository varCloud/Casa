package com.example.rexv666480.oddoventas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rexv666480.oddoventas.Entidades.Cliente;
import com.example.rexv666480.oddoventas.Entidades.PedidoVenta;
import com.example.rexv666480.oddoventas.Odoo.Async.AsyncSesion;
import com.example.rexv666480.oddoventas.Odoo.OdooConect;
import com.example.rexv666480.oddoventas.Odoo.OdooUtil;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCCallback;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCException;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCServerException;
import com.example.rexv666480.oddoventas.Utilerias.Loading;
import com.example.rexv666480.oddoventas.Utilerias.PreferencesManager;

import org.alexd.jsonrpc.JSONRPCClient;
import org.alexd.jsonrpc.JSONRPCException;
import org.alexd.jsonrpc.JSONRPCParams;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class PedidoVentasActivity extends AppCompatActivity {

    private OdooConect odoo;
    private Loading loading;
    private Context context;
    private int user_id = 0;
    private String TAG = "PedidoVentasActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_ventas);
        context = this;
        try {
            odoo = new OdooConect();

        loading = new Loading(context);
        user_id = Integer.parseInt(PreferencesManager.loadString(context, "usID", "0"));
        loading.ShowLoading("Cargando ...");
        XMLRPCCallback listener = new XMLRPCCallback() {
            public void onResponse(long id, Object result) {
                ObtenerPedidos(result);
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
                asList("state", "=", "draft")
                //asList("customer", "=", true)
        ));


        Map<String, List> filtros = new HashMap() {{
            put("fields", asList("message_needaction", "name", "date_order", "partner_id", "user_id", "amount_total", "currency_id", "invoice_status", "state"));
            put("limit", 5);
        }};

            odoo.getXmlClienteObject().callAsync(listener, "execute_kw", odoo.getDb(), user_id, odoo.getPassword(), "sale.order", "search_read",conditions,filtros);
            new AsyncSesion().execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public List<PedidoVenta> ObtenerPedidos(Object result)
    {
        List<PedidoVenta> pedidos = null;
        try{
            Object[] objects = (Object[]) result;
            if (objects.length > 0) {
                pedidos = new ArrayList<>();
                PedidoVenta pedido;
                for (Object object : objects) {
                    pedido = new PedidoVenta();
                    String partner_id= OdooUtil.getString((Map<String, Object>) object, "message_needaction");
                    String name= OdooUtil.getString((Map<String, Object>) object, "name");
                    String invoice_status = OdooUtil.getString((Map<String, Object>) object, "date_order");
                    String create_date= OdooUtil.getString((Map<String, Object>) object, "partner_id");
                    String amount_total= OdooUtil.getString((Map<String, Object>) object, "amount_total");
                    String currency_id= OdooUtil.getString((Map<String, Object>) object, "currency_id");

                }
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  pedidos;
    }

    public  void  Pedidos()
    {
       /* JSONRPCClient client = JSONRPCClient.create(
                "http://45.58.40.30:8068/xmlrpc/2/object",
                JSONRPCParams.Versions.VERSION_2);
        client.setConnectionTimeout(3000);
        client.setSoTimeout(3000);
        // enable debug to inspect the raw request & response in your logcat output
        client.setDebug(true);

        try
        {
            int ret = client.("execute_kw",)
        } catch (JSONRPCException e) {
            e.printStackTrace();
        }*/
    }

}

