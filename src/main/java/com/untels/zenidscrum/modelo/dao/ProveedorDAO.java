package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.modelo.bean.Proveedor;
import java.util.List;

public interface ProveedorDAO {

    public List<Proveedor> listarTodos();
    
     public Proveedor obtenerPorId(int idProveedor);

    public boolean crear(Proveedor proveedor);

    public boolean modificar(Proveedor proveedor);
       
}
