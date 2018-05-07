package com.example.rexv666480.oddoventas.Odoo.Async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCClient;

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
          List conditions =  asList(asList(
                    asList("is_company", "=", false),
                    asList("customer", "=", false)));

            Map m = new HashMap();


            XMLRPCClient client = new XMLRPCClient(new URL(url+common),FLAGS_FORWARD);

            int user_id = (int) client.call("login", db, username, password);

             //user_id =(int) client.call("execute", new URL(url+common),"authenticate",asList(
             //       db, username, password, Collections.emptySet()));

            client = new XMLRPCClient(new URL(url+Objetos),FLAGS_FORWARD);
            Object result = client.call("execute_kw", db, user_id, password, "res.partner", "search", conditions);
            Log.d("Resultado", (String) result.toString());

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
