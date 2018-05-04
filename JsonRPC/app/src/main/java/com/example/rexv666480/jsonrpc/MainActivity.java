package com.example.rexv666480.jsonrpc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.rexv666480.jsonrpc.Async.AsyncSesion;
import com.example.rexv666480.jsonrpc.XmlRpc.XMLRPCClient;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;



import org.alexd.jsonrpc.JSONRPCClient;
import org.alexd.jsonrpc.JSONRPCException;
import org.alexd.jsonrpc.JSONRPCParams;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String url = "https://demo.odoo.com/start";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                   XMLRPCClient client = new XMLRPCClient(new URL(url));
                    client.call()

                    new AsyncSesion().execute();
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }
}

//https://stackoverflow.com/questions/46067346/is-there-anyone-integratted-odoo-with-android
//https://github.com/gturri/aXMLRPC
//https://github.com/oogbox/odoo-mobile-api#getting-started