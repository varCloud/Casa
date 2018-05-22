package com.example.rexv666480.oddoventas.Entidades.AgregarPedidoVenta;

import com.example.rexv666480.oddoventas.Entidades.Producto;

/**
 * Created by rexv666480 on 22/05/2018.
 */

public class NuevoProducto {

    private Double cantidadPedido;
    private Double subtotal;
    private Double descuento;
    private Double precioUnitario;
    private Integer idProducto;
    private String descripcionProducto;
    private Integer unidadDeMedida;
    private  String unidadDeMedidaDescripcion;
    private Integer impuestos;
    private String impuestosDescripcion;
    private String image_small;

    public String getImage_small() {
        return image_small;
    }

    public void setImage_small(String image_small) {
        this.image_small = image_small;
    }

    public NuevoProducto() {
    }

    public Double getCantidadPedido() {
        return cantidadPedido;
    }

    public void setCantidadPedido(Double cantidadPedido) {
        this.cantidadPedido = cantidadPedido;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Integer getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(Integer unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public String getUnidadDeMedidaDescripcion() {
        return unidadDeMedidaDescripcion;
    }

    public void setUnidadDeMedidaDescripcion(String unidadDeMedidaDescripcion) {
        this.unidadDeMedidaDescripcion = unidadDeMedidaDescripcion;
    }

    public Integer getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Integer impuestos) {
        this.impuestos = impuestos;
    }

    public String getImpuestosDescripcion() {
        return impuestosDescripcion;
    }

    public void setImpuestosDescripcion(String impuestosDescripcion) {
        this.impuestosDescripcion = impuestosDescripcion;
    }
}
