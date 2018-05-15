package com.example.rexv666480.oddoventas.Entidades;

/**
 * Created by rexv666480 on 15/05/2018.
 */

public class Producto {

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

    public Producto(String name, Object[] currency_id, String product_variant_count, Double lst_price, Double qty_available, String type, String product_variant_ids, String image_small, Object[] uom_id, String default_code, String __last_update) {
        this.name = name;
        this.currency_id = currency_id;
        this.product_variant_count = product_variant_count;
        this.lst_price = lst_price;
        this.qty_available = qty_available;
        this.type = type;
        this.product_variant_ids = product_variant_ids;
        this.image_small = image_small;
        this.uom_id = uom_id;
        this.default_code = default_code;
        this.__last_update = __last_update;
    }

    public Producto() {

        this.name = "";
        this.currency_id = new Object[0];
        this.product_variant_count = "";
        this.lst_price = 0.0;
        this.qty_available = 0.0;
        this.type = "";
        this.product_variant_ids = "";
        this.image_small = "";
        this.uom_id = "";
        this.default_code = "";
        this.__last_update = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object[] getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(Object[] currency_id) {
        this.currency_id = currency_id;
    }

    public String getProduct_variant_count() {
        return product_variant_count;
    }

    public void setProduct_variant_count(String product_variant_count) {
        this.product_variant_count = product_variant_count;
    }

    public Double getLst_price() {
        return lst_price;
    }

    public void setLst_price(Double lst_price) {
        this.lst_price = lst_price;
    }

    public Double getQty_available() {
        return qty_available;
    }

    public void setQty_available(Double qty_available) {
        this.qty_available = qty_available;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduct_variant_ids() {
        return product_variant_ids;
    }

    public void setProduct_variant_ids(String product_variant_ids) {
        this.product_variant_ids = product_variant_ids;
    }

    public String getImage_small() {
        return image_small;
    }

    public void setImage_small(String image_small) {
        this.image_small = image_small;
    }

    public Object[] getUom_id() {
        return uom_id;
    }

    public void setUom_id(Object[] uom_id) {
        this.uom_id = uom_id;
    }

    public String getDefault_code() {
        return default_code;
    }

    public void setDefault_code(String default_code) {
        this.default_code = default_code;
    }

    public String get__last_update() {
        return __last_update;
    }

    public void set__last_update(String __last_update) {
        this.__last_update = __last_update;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
