package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLMarcaDAO implements MarcaDAO {

    private final Conexion conexion;

    public SQLMarcaDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<String> listarTodos() {
        String sql = "SELECT DISTINCT marca FROM `productos`";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<String> marca = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                marca.add(rs.getString("marca"));
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

        return marca;

    }
}
