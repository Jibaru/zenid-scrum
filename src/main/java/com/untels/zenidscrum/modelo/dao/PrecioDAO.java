package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.modelo.bean.Precio;

public interface PrecioDAO {

    public Precio obtenerPorId(int precioId);

    public boolean crear(Precio p);

    public boolean eliminar(int idPrecio);

    public boolean modificar(int idPrecio);
}
