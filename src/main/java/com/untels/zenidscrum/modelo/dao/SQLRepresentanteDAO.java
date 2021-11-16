package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Representante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLRepresentanteDAO implements RepresentanteDAO {

    public Conexion conexion;

    public SQLRepresentanteDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean crear(Representante r) {
        String sql = "INSERT INTO representantes ("
                + "nombre, "
                + "correo_electronico, "
                + "telefono) "
                + "VALUES (?, ?, ?)";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, r.getNombre());
            ps.setString(2, r.getCorreoElectronico());
            ps.setString(3, r.getTelefono());

            filasModificadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                r.setIdRepresentante(rs.getInt(1));
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
    public boolean modificar(Representante r) {
        String sql = "UPDATE representantes SET "
                + "nombre=?, "
                + "correo_electronico=?, "
                + "telefono=? "
                + "WHERE id_representante = ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, r.getNombre());
            ps.setString(2, r.getCorreoElectronico());
            ps.setString(3, r.getTelefono());
            ps.setInt(4, r.getIdRepresentante());

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
