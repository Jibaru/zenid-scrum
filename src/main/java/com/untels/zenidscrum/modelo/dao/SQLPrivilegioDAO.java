package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Privilegio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLPrivilegioDAO implements PrivilegioDAO {

    private final Conexion conexion;

    public SQLPrivilegioDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean crear(Privilegio privilegio) {
        String sql = "INSERT INTO privilegios "
                + "(nombre, crear, actualizar, listar, eliminar, id_rol) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, privilegio.getNombre());
            ps.setBoolean(2, privilegio.isCrear());
            ps.setBoolean(3, privilegio.isActualizar());
            ps.setBoolean(4, privilegio.isListar());
            ps.setBoolean(5, privilegio.isEliminar());
            ps.setInt(6, privilegio.getIdRol());

            filasModificadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                privilegio.setIdPrivilegio(rs.getInt(1));
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

        return filasModificadas > 0;
    }

    @Override
    public boolean modificar(Privilegio privilegio) {
        String sql = "UPDATE privilegios "
                + "SET nombre=?, crear=?, actualizar=?, listar=?, eliminar=?, id_rol=? "
                + "WHERE id_privilegio=?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, privilegio.getNombre());
            ps.setBoolean(2, privilegio.isCrear());
            ps.setBoolean(3, privilegio.isActualizar());
            ps.setBoolean(4, privilegio.isListar());
            ps.setBoolean(5, privilegio.isEliminar());
            ps.setInt(6, privilegio.getIdRol());
            ps.setInt(7, privilegio.getIdPrivilegio());

            filasModificadas = ps.executeUpdate();

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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }

        return filasModificadas > 0;
    }

}
