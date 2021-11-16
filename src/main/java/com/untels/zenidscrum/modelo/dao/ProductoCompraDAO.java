package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.modelo.bean.ProductoCompra;
import java.util.List;

public interface ProductoCompraDAO {

    public List<ProductoCompra> listarPorIdPedido(int idPedido);

    public boolean crear(ProductoCompra pc);
}
