package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.modelo.bean.Venta;
import java.util.List;

public interface VentaDAO {

    public boolean Crear(Venta v);

    public Venta obtenerPorId(int idVenta);

    public int obtenerIdProformaPorIdVenta(int idVenta);

    public List<Venta> listarTodos();

    public List<Venta> listarPorTerminoBoletaFactura(String termino, boolean boleta, boolean factura);

    public boolean despachar(int idVenta);
}
