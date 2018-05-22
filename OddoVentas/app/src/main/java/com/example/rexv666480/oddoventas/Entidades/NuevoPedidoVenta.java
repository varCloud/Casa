package com.example.rexv666480.oddoventas.Entidades;

import java.util.List;

/**
 * Created by rexv666480 on 21/05/2018.
 */

public class NuevoPedidoVenta {

    private Cliente cliente;
    private List<Producto> productos;

    public NuevoPedidoVenta() {
    }

    public NuevoPedidoVenta(Cliente cliente, List<Producto> productos) {
        this.cliente = cliente;
        this.productos = productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
