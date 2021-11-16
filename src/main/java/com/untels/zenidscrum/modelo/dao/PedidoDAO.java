package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.modelo.bean.Pedido;
import java.util.List;

public interface PedidoDAO {

    public boolean crear(Pedido p);

    public Pedido obtenerPorId(int idPedido);

    public int obtenerSiguienteCodigoCompra();

    public List<Pedido> listarTodos();

    public List<Pedido> listarPorCodigoCompra(int codigoCompra);

    public boolean eliminar(int idPedido);
}
