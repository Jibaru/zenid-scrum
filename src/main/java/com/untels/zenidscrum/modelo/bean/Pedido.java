package com.untels.zenidscrum.modelo.bean;

import java.time.LocalDate;
import java.util.List;

public class Pedido {

    private int idPedido;
    private String observaciones;
    private LocalDate fechaCreacion;
    private Proveedor proveedor;
    private int codigoCompra;
    private List<ProductoCompra> productoCompra;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public int getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(int codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    public List<ProductoCompra> getProductoCompra() {
        return productoCompra;
    }

    public void setProductoCompra(List<ProductoCompra> productoCompra) {
        this.productoCompra = productoCompra;
    }

}
