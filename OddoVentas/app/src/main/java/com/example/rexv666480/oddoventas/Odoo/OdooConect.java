package com.example.rexv666480.oddoventas.Odoo;

import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCClient;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCClient.FLAGS_FORWARD;

/**
 * Created by rexv666480 on 07/05/2018.
 */

public class OdooConect {

    String url = "http://45.58.40.30:8068/";
    String Objetos ="xmlrpc/2/object";
    String common = "xmlrpc/2/common";
    String db = "demofe";
    String username = "demo";
    String password = "$demo123*";
    public XMLRPCClient client;


    public String getDb() throws MalformedURLException {
        return db;
    }

    public URL getUrlCommon() throws MalformedURLException {
        return new URL(url+common);
    }

    public URL getUrlObject() throws MalformedURLException {
        return new URL(url+Objetos);
    }

    public  XMLRPCClient getXmlClienteCommon() throws MalformedURLException {
        return  new XMLRPCClient(getUrlCommon() , FLAGS_FORWARD);
    }

    public  XMLRPCClient getXmlClienteObject() throws MalformedURLException {
        return  new XMLRPCClient(getUrlObject() , FLAGS_FORWARD);
    }
    public OdooConect() throws MalformedURLException {



    }

    /*
    public int login( String username, String password) {
        int usId=0;
        try {
            client = new XMLRPCClient(new URL(url+common),FLAGS_FORWARD);
            usId =(int) client.call("login", db, username, password);
            return usId;
        } catch (XMLRPCException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return usId;
    }

    public Object checkServer() {
        Object object;
        try {
            object = client.call("list", new Object[]{});
            return object;
        } catch (XMLRPCException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object search_read(String db, int user_id, String password, String object, List conditions, Map<String, List> fields) {
        Object result = null;
        try {

            result = client.call("execute_kw", db, user_id, password, object, "search_read", conditions, fields);
        } catch (XMLRPCException e) {
            e.printStackTrace();
        }
        return result;
    }
    */

}
