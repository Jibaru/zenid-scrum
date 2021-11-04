package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.modelo.bean.Usuario;
import java.util.List;

public interface UsuarioDAO {

    public Usuario obtenerUsuarioPorCorreoElectronico(String correoElectronico);

    public List<Usuario> listarTodos();

    public Usuario obtenerPorId(int idUsuario);

    public boolean crear(Usuario usuario);

    public boolean modificar(Usuario usuario);

    public boolean inhabilitar(int idUsuario);

    public boolean habilitar(int idUsuario);
}
