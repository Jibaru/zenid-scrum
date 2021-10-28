package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.modelo.bean.Usuario;

public interface UsuarioDAO {

    public Usuario obtenerUsuarioPorCorreoElectronico(String correoElectronico);
}
