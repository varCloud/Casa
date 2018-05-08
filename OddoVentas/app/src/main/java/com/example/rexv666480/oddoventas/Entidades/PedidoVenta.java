package com.example.rexv666480.oddoventas.Entidades;

import com.example.rexv666480.oddoventas.Odoo.XmlRpc.serializer.DateTimeSerializer;

import java.util.Date;

/**
 * Created by victor on 07/05/18.
 */

public class PedidoVenta {

    private String name;
    private String date_order;
    private Object[] partner_id;
    private Double amount_total;
    private Object[] currency_id;
    private String invoice_status;
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_order() {
        return date_order;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }

    public Object[] getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(Object[] partner_id) {
        this.partner_id = partner_id;
    }

    public Double getAmount_total() {
        return amount_total;
    }

    public void setAmount_total(Double amount_total) {
        this.amount_total = amount_total;
    }

    public Object[] getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(Object[] currency_id) {
        this.currency_id = currency_id;
    }

    public String getInvoice_status() {
        return invoice_status;
    }

    public void setInvoice_status(String invoice_status) {
        this.invoice_status = invoice_status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



}
