package com.example.rexv666480.oddoventas.Entidades;

import com.example.rexv666480.oddoventas.Odoo.XmlRpc.serializer.DateTimeSerializer;

import java.util.Date;

/**
 * Created by victor on 07/05/18.
 */

public class PedidoVenta {

    private String NoPedido;
    private Date FechaPedido;
    private String Cliente;
    private String Comercial;
    private String Total;
    private String EstadoFactura;

    public String getNoPedido() {
        return NoPedido;
    }

    public void setNoPedido(String noPedido) {
        NoPedido = noPedido;
    }

    public Date getFechaPedido() {
        return FechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        FechaPedido = fechaPedido;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getComercial() {
        return Comercial;
    }

    public void setComercial(String comercial) {
        Comercial = comercial;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getEstadoFactura() {
        return EstadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        EstadoFactura = estadoFactura;
    }
}
