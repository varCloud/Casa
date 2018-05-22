package com.example.rexv666480.oddoventas.Odoo;

import android.util.Log;

import com.example.rexv666480.oddoventas.Entidades.Cliente;
import com.example.rexv666480.oddoventas.Entidades.Impuesto;
import com.example.rexv666480.oddoventas.Entidades.Producto;
import com.example.rexv666480.oddoventas.Entidades.TemplateProducto;
import com.example.rexv666480.oddoventas.Entidades.UnidadMedida;

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

    public  static List<Producto> ObtenerProductos(Object result)
    {
/*
        private String default_code;
        private String name;
        private int id;
        private Object[] attribute_value_ids;
        private Double lst_price;
        private Double price;
        private Double qty_available;
        private Double virtual_available;
        private Object[] uom_id;
        private Boolean barcode;
        private Object[] product_tmpl_id;
        private Boolean active;
*/
        List<Producto> productos = null;
        try{
            Object[] objects = (Object[]) result;
            if (objects.length > 0) {
                productos = new ArrayList<>();
                Producto producto;
                for (Object object : objects) {
                    producto = new Producto();
                    producto.setName(OdooUtil.getString((Map<String, Object>) object, "name"));
                    producto.setDefault_code(OdooUtil.getString((Map<String, Object>) object, "default_code"));
                    producto.setId(OdooUtil.getInteger((Map<String, Object>) object, "id"));
                    producto.setAttribute_value_ids(OdooUtil.getTupla((Map<String, Object>) object, "attribute_value_ids"));
                    producto.setLst_price(OdooUtil.getDouble((Map<String, Object>) object, "lst_price"));
                    producto.setPrice(OdooUtil.getDouble((Map<String, Object>) object, "price"));
                    producto.setQty_available(OdooUtil.getDouble((Map<String, Object>) object, "qty_available"));
                    producto.setVirtual_available(OdooUtil.getDouble((Map<String, Object>) object, "virtual_available"));
                    producto.setUom_id(OdooUtil.getTupla((Map<String, Object>) object, "uom_id"));
                    producto.setBarcode(OdooUtil.getBoolean((Map<String, Object>) object, "barcode"));
                    producto.setProduct_tmpl_id(OdooUtil.getTupla((Map<String, Object>) object, "product_tmpl_id"));
                    producto.setActive(OdooUtil.getBoolean((Map<String,Object>) object,"active"));
                    productos.add(producto);
                }
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  productos;
    }

    public  static List<TemplateProducto> ObtenerTemplateProductos(Object result)
    {
/*
    private String name;
    private Object[] currency_id;
    private String product_variant_count;
    private Double lst_price;
    private Double qty_available;
    private String type;
    private String product_variant_ids;
    private String image_small;
    private Object[] uom_id;
    private String default_code;
    private String __last_update;

*/
        List<TemplateProducto> productos = null;
        try{
            Object[] objects = (Object[]) result;
            if (objects.length > 0) {
                productos = new ArrayList<>();
                TemplateProducto producto;
                for (Object object : objects) {
                    producto = new TemplateProducto();
                    producto.setName(OdooUtil.getString((Map<String, Object>) object, "name"));
                    producto.setDefault_code(OdooUtil.getString((Map<String, Object>) object, "default_code"));
                    //producto.setId(OdooUtil.getInteger((Map<String, Object>) object, "id"));
                    producto.setCurrency_id(OdooUtil.getTupla((Map<String, Object>) object, "currency_id"));
                    producto.setLst_price(OdooUtil.getDouble((Map<String, Object>) object, "lst_price"));
                    producto.setQty_available(OdooUtil.getDouble((Map<String, Object>) object, "qty_available"));
                    producto.setType(OdooUtil.getString((Map<String, Object>) object, "type"));
                    producto.setProduct_variant_ids(OdooUtil.getString((Map<String, Object>) object, "product_variant_ids"));
                    producto.setImage_small(OdooUtil.getString((Map<String, Object>) object, "image_small"));
                    producto.setUom_id(OdooUtil.getTupla((Map<String, Object>) object, "uom_id"));
                    producto.setDefault_code(OdooUtil.getString((Map<String, Object>) object, "default_code"));
                    producto.set__last_update(OdooUtil.getString((Map<String,Object>) object,"__last_update"));
                    productos.add(producto);
                }
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  productos;
    }

    public  static List<UnidadMedida> ObtenerUnidadesMedida(Object result)
    {
        /*
            private String display_name;
            private Integer id;
            private Object[]  category_id;
            private String name;

        */
        List<UnidadMedida> unidadesMedida = null;
        try{
            Object[] objects = (Object[]) result;
            if (objects.length > 0) {
                unidadesMedida = new ArrayList<>();
                UnidadMedida unidadMedida;
                for (Object object : objects) {
                    unidadMedida = new UnidadMedida();
                    unidadMedida.setId(OdooUtil.getInteger((Map<String, Object>) object, "id"));
                    unidadMedida.setCategory_id(OdooUtil.getTupla((Map<String, Object>) object, "category_id"));
                    unidadMedida.setDisplay_name(OdooUtil.getString((Map<String, Object>) object, "display_name"));
                    unidadMedida.setName(OdooUtil.getString((Map<String, Object>) object, "name"));
                    unidadesMedida.add(unidadMedida);
                }
            }
        }catch (Exception ex)
        {
            Log.d("errores: ",ex.getMessage());
        }
        return  unidadesMedida;
    }


    public  static List<Impuesto> ObtenerImpuestos(Object result)
    {
        /*
            private  Integer id;
            private String display_name;
            private String description;
            private Double amount;
            private String amount_type;

        */
        List<Impuesto> impuestos = null;
        try{
            Object[] objects = (Object[]) result;
            if (objects.length > 0) {
                impuestos = new ArrayList<>();
                Impuesto impuesto;
                for (Object object : objects) {
                    impuesto = new Impuesto();
                    impuesto.setId(OdooUtil.getInteger((Map<String, Object>) object, "id"));
                    impuesto.setAmount(OdooUtil.getDouble((Map<String, Object>) object, "amount"));
                    impuesto.setAmount_type(OdooUtil.getString((Map<String, Object>) object, "amount_type"));
                    impuesto.setDisplay_name(OdooUtil.getString((Map<String, Object>) object, "display_name"));
                    impuesto.setDescription(OdooUtil.getString((Map<String, Object>) object, "description"));
                    impuestos.add(impuesto);
                }
            }
        }catch (Exception ex)
        {
            Log.d("errores: ",ex.getMessage());
        }
        return  impuestos;
    }
}