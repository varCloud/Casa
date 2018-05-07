package com.example.rexv666480.oddoventas.Odoo;

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
}