package com.example.rexv666480.oddoventas.Entidades.AgregarPedidoVenta;

import android.widget.ListView;

import com.example.rexv666480.oddoventas.Entidades.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rexv666480 on 21/05/2018.
 */

public class NuevoPedidoVenta {

    private Cliente cliente;
    private List<NuevoProducto> productos;

    public NuevoPedidoVenta() {
        this.productos = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<NuevoProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<NuevoProducto> productos) {
        this.productos = productos;
    }
}
