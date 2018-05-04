package com.example.rexv666480.jsonrpc.Async;

import android.os.AsyncTask;
import android.util.Log;

import org.alexd.jsonrpc.JSONRPCClient;
import org.alexd.jsonrpc.JSONRPCException;
import org.alexd.jsonrpc.JSONRPCParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;

/**
 * Created by rexv666480 on 02/05/2018.
 */

public class AsyncSesion extends AsyncTask<Void, Void, Void> {
    String url = "https://demo.odoo.com";
    @Override
    protected Void doInBackground(Void... p) {
     /*   JSONRPCClient client = JSONRPCClient.create(
                url+"/start",
                JSONRPCParams.Versions.VERSION_2);
        client.setConnectionTimeout(3000);
        client.setSoTimeout(3000);

        // enable debug to inspect the raw request & response in your logcat output
        client.setDebug(true);
        JSONObject params = new JSONObject();
        //info['host'], info['database'], info['user'], info['password']
        try {
            params.put("host", "45.58.46.20:8068");
            params.put("database", "demofe");
            params.put("user", "userdemo");
            params.put("password", "$demo123*");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try
        {
            Object ret = client.call("start", params);
            Log.d("blue RPC",ret.toString());
            client = JSONRPCClient.create(
                    url,
                    JSONRPCParams.Versions.VERSION_2);
            ret = client.call(url+"/xmlrpc/2/common");
            Log.d("blue RPC",ret.toString());

        } catch (JSONRPCException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        */
        return null;

    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}
