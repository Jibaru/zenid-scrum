package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUsuarioDAO implements UsuarioDAO {

    private final Conexion conexion;

    public SQLUsuarioDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public Usuario obtenerUsuarioPorCorreoElectronico(String correoElectronico) {
        String sql = "SELECT * FROM usuarios WHERE correo_electronico = ?";
        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario us = null;

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, correoElectronico);

            rs = ps.executeQuery();

            while (rs.next()) {
                us = new Usuario();

                us.setIdUsuario(rs.getInt("id_usuario"));
                us.setCorreoElectronico(rs.getString("correo_electronico"));
                us.setContrasenia(rs.getString("contrasenia"));
                us.setNombre(rs.getString("nombre"));
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
