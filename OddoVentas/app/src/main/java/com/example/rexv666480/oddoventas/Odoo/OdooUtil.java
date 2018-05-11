package com.example.rexv666480.oddoventas.Odoo;

import com.example.rexv666480.oddoventas.Entidades.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rexv666480 on 07/05/2018.
 */

public class OdooUtil {

    public static String getString(Map<String, Object> map, String fieldName) {
        String res = "";
        if (map.get(fieldName) instanceof String) {
            res = (String) map.get(fieldName);
        }
        return res;
    }

    public static Integer getInteger(Map<String, Object> map, String fieldName) {
        Integer res = 0;
        if (map.get(fieldName) instanceof Integer) {
            res = (Integer) map.get(fieldName);
        }
        return res;
    }

    public static Double getDouble(Map<String, Object> map, String fieldName) {
        Double res = 0.0;
        if (map.get(fieldName) instanceof Double) {
            res = (Double) map.get(fieldName);
        }
        return res;
    }

    public static Boolean getBoolean(Map<String, Object> map, String fieldName) {
        Boolean res = false;
        if (map.get(fieldName) instanceof Boolean) {
            res = (Boolean) map.get(fieldName);
        }
        return res;
    }


    public static Float getFloat(Map<String, Object> map, String fieldName) {
        Float res = 0f;
        if (map.get(fieldName) instanceof Float) {
            res = (Float) map.get(fieldName);
        }
        return res;
    }

    public static Object[] getTupla(Map<String, Object> map, String fieldName) {
        Object[] res = null;
        if (map.get(fieldName) instanceof Object[]) {
            res = (Object[]) map.get(fieldName);
        }
        return res;
    }

    public  static List<Cliente> ObtenerCllientes(Object result)
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