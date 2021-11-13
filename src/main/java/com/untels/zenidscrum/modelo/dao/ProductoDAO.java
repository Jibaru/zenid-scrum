package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.modelo.bean.Producto;
import java.util.List;

public interface ProductoDAO {

    public List<Producto> buscar(String termino, String marca, Integer idProveedor);
}
