package com.example.rexv666480.oddoventas.Odoo.Async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCClient;

import org.alexd.jsonrpc.JSONRPCClient;
import org.alexd.jsonrpc.JSONRPCException;
import org.alexd.jsonrpc.JSONRPCParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCClient.FLAGS_FORWARD;
import static java.util.Arrays.asList;

/**
 * Created by rexv666480 on 02/05/2018.
 */

public class AsyncSesion extends AsyncTask<Void, Void, Void> {
    String url = "http://45.58.40.30:8068/";
    String Objetos ="xmlrpc/2/object";
    String common = "xmlrpc/2/common";
    //String url="http://demo.odoo.com/xmlrpc/2/object";
    String db = "demofe";
    String username = "demo";
    String password = "$demo123*";
    @Override
    protected Void doInBackground(Void... p) {
        try
        {

            JSONRPCClient client = JSONRPCClient.create(
                    "http://45.58.40.30:8068/xmlrpc/2/object",
                    JSONRPCParams.Versions.VERSION_2);
            client.setConnectionTimeout(3000);
            client.setSoTimeout(3000);
            // enable debug to inspect the raw request & response in your logcat output
            client.setDebug(true);

            try
            {
                List conditions = asList(asList(
                        asList("state", "=", "draft")
                        //asList("customer", "=", true)
                ));


                Map<String, List> filtros = new HashMap() {{
                    put("fields", asList("message_needaction", "name", "date_order", "partner_id", "user_id", "amount_total", "currency_id", "invoice_status", "state"));
                    put("limit", 5);
                }};

                Object result = client.call("call", db, 5,password, "sale.order", "search_read",conditions,filtros);
                Log.d("Resultado",  result.toString());
            } catch (JSONRPCException e) {
                e.printStackTrace();
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}
