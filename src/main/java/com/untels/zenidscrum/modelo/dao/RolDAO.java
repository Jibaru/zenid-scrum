package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.modelo.bean.Rol;
import java.util.List;

public interface RolDAO {

    public List<Rol> listarTodos();

    public Rol obtenerPorId(int idRol);

    public boolean crear(Rol rol);

    public boolean modificar(Rol rol);

    public boolean inhabilitar(int idRol);

    public boolean habilitar(int idRol);
}
