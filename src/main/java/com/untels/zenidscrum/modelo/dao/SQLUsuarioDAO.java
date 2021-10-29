package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Privilegio;
import com.untels.zenidscrum.modelo.bean.Rol;
import com.untels.zenidscrum.modelo.bean.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUsuarioDAO implements UsuarioDAO {

    private final Conexion conexion;

    public SQLUsuarioDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public Usuario obtenerUsuarioPorCorreoElectronico(String correoElectronico) {
        String sql = "SELECT u.id_usuario, "
                + "u.nombre as nombre_usuario, "
                + "u.correo_electronico, "
                + "u.contrasenia, "
                + "u.estado as estado_usuario, "
                + "u.id_rol, "
                + "r.id_rol, "
                + "r.nombre as nombre_rol, "
                + "r.descripcion, "
                + "p.id_privilegio, "
                + "p.nombre as nombre_privilegio, "
                + "p.crear, "
                + "p.actualizar, "
                + "p.listar, "
                + "p.eliminar "
                + "FROM usuarios u "
                + "INNER JOIN roles r "
                + "ON u.id_rol = r.id_rol "
                + "INNER JOIN privilegios p "
                + "ON r.id_rol = p.id_rol "
                + "WHERE u.correo_electronico = ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario us = null;
        Rol rol = null;
        List<Privilegio> privilegios = null;

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, correoElectronico);

            rs = ps.executeQuery();

            while (rs.next()) {

                if (us == null) {
                    us = new Usuario();
                    rol = new Rol();
                    us.setIdUsuario(rs.getInt("id_usuario"));
                    us.setCorreoElectronico(rs.getString("correo_electronico"));
                    us.setContrasenia(rs.getString("contrasenia"));
                    us.setNombre(rs.getString("nombre_usuario"));
                    rol.setIdRol(rs.getInt("id_rol"));
                    rol.setNombre(rs.getString("nombre_rol"));
                    rol.setDescripcion(rs.getString("descripcion"));

                    privilegios = new ArrayList<>();
                }

                Privilegio pr = new Privilegio();
                pr.setIdPrivilegio(rs.getInt("id_privilegio"));
                pr.setNombre(rs.getString("nombre_privilegio"));
                pr.setCrear(rs.getBoolean("crear"));
                pr.setActualizar(rs.getBoolean("actualizar"));
                pr.setEliminar(rs.getBoolean("eliminar"));
                pr.setIdRol(rs.getInt("id_rol"));
                privilegios.add(pr);
            }

            if (rol != null) {
                rol.setPrivilegios(privilegios);
            }
            if (us != null) {
                us.setRol(rol);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }

        return us;
    }
}
