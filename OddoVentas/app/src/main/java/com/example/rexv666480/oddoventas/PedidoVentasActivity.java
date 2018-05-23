package com.example.rexv666480.oddoventas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rexv666480.oddoventas.Adapters.AdapterPedidoVenta;
import com.example.rexv666480.oddoventas.Entidades.Cliente;
import com.example.rexv666480.oddoventas.Entidades.PedidoVenta;
import com.example.rexv666480.oddoventas.Entidades.UnidadMedida;
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
    private Activity activity;
    private ListView lv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_ventas);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAgregarPedidoVenta);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PedidoVentasActivity.this, AgregarPedidoVentaActivity.class);
                startActivity(intent);

            }
        });
        context = this;
        activity = this;
        try {
            odoo = new OdooConect();
            loading = new Loading(context);
            user_id = Integer.parseInt(PreferencesManager.loadString(context, "usID", "0"));
            loading.ShowLoading("Cargando ...");
            lv = (ListView) findViewById(R.id.LvPedidoVenta);
            ObtenerPedidos();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   try{
                       PedidoVenta p = (PedidoVenta) lv.getItemAtPosition(i);
                       Log.d(TAG,p.getName());
                   } catch(Exception ex) {
                       ex.printStackTrace();
                   }
                }
            });

        } catch (MalformedURLException e) {
            loading.CerrarLoading();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ObtenerPedidos() {
        AsyncTask asyncTask = new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object... params) {
                Object result = null;
                try {

                    List conditions = asList(asList(
                            asList("state", "=", "draft")
                    ));

                    Map<String, List> filtros = new HashMap() {{
                        put("fields", asList("message_needaction", "name", "date_order", "partner_id", "user_id", "amount_total", "currency_id", "invoice_status", "state"));
                        put("limit", 5);
                    }};
                    result = odoo.getXmlClienteObject().call("execute_kw", odoo.getDb(), user_id, odoo.getPassword(), "sale.order", "search_read", conditions, filtros);

                } catch (XMLRPCException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(Object result) {
                super.onPostExecute(result);
                if (result != null) {
                    List<PedidoVenta> pedidosVentas = ObtenerPedidos(result);
                    if (pedidosVentas != null) {
                        AdapterPedidoVenta adapter = new AdapterPedidoVenta(activity, pedidosVentas);
                        lv.setAdapter(adapter);

                    }
                }
                loading.CerrarLoading();
            }
        };
        asyncTask.execute();
    }


    public List<PedidoVenta> ObtenerPedidos(Object result) {
        List<PedidoVenta> pedidos = null;
        try {
            Object[] objects = (Object[]) result;
            if (objects.length > 0) {
                pedidos = new ArrayList<>();
                PedidoVenta pedido;
                for (Object object : objects) {
                    pedido = new PedidoVenta();
                    String message_needaction = OdooUtil.getString((Map<String, Object>) object, "message_needaction");
                    pedido.setName(OdooUtil.getString((Map<String, Object>) object, "name"));
                    pedido.setDate_order(OdooUtil.getString((Map<String, Object>) object, "date_order"));
                    pedido.setPartner_id(OdooUtil.getTupla((Map<String, Object>) object, "partner_id"));
                    pedido.setAmount_total(OdooUtil.getDouble((Map<String, Object>) object, "amount_total"));
                    pedido.setCurrency_id(OdooUtil.getTupla((Map<String, Object>) object, "currency_id"));
                    pedido.setInvoice_status(OdooUtil.getString((Map<String, Object>) object, "invoice_status"));
                    pedido.setState(OdooUtil.getString((Map<String, Object>) object, "state"));
                    pedidos.add(pedido);
                }
            }
        } catch (Exception ex) {
            loading.CerrarLoading();
            ex.printStackTrace();
        }
        return pedidos;
    }



}

