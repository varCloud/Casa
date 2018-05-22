package com.example.rexv666480.oddoventas.Entidades;

import org.w3c.dom.DOMError;

/**
 * Created by rexv666480 on 16/05/2018.
 */

public class Producto {

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

    private  Impuesto impuesto;
    private UnidadMedida unidadMedida;

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Producto() {
    }

    public String getDefault_code() {
        return default_code;
    }

    public void setDefault_code(String default_code) {
        this.default_code = default_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object[] getAttribute_value_ids() {
        return attribute_value_ids;
    }

    public void setAttribute_value_ids(Object[] attribute_value_ids) {
        this.attribute_value_ids = attribute_value_ids;
    }

    public Double getLst_price() {
        return lst_price;
    }

    public void setLst_price(Double lst_price) {
        this.lst_price = lst_price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQty_available() {
        return qty_available;
    }

    public void setQty_available(Double qty_available) {
        this.qty_available = qty_available;
    }

    public Double getVirtual_available() {
        return virtual_available;
    }

    public void setVirtual_available(Double virtual_available) {
        this.virtual_available = virtual_available;
    }

    public Object[] getUom_id() {
        return uom_id;
    }

    public void setUom_id(Object[] uom_id) {
        this.uom_id = uom_id;
    }

    public Boolean getBarcode() {
        return barcode;
    }

    public void setBarcode(Boolean barcode) {
        this.barcode = barcode;
    }

    public Object[] getProduct_tmpl_id() {
        return product_tmpl_id;
    }

    public void setProduct_tmpl_id(Object[] product_tmpl_id) {
        this.product_tmpl_id = product_tmpl_id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
