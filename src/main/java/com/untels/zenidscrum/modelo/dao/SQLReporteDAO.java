package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLReporteDAO implements ReporteDAO {

    private final Conexion conexion;

    public SQLReporteDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Venta> listarPorFecha(String inicio, String fin) {
        String sql = "SELECT * FROM ventas";

        if (inicio != null) {
            sql += " WHERE fecha_emision BETWEEN '" + inicio + "' AND '" + fin + "'";
        }
        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Venta> ventas = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Venta v = new Venta();
                v.setIdVenta(rs.getInt("id_venta"));
                v.setNombres(rs.getString("nombres"));
                v.setApePaterno(rs.getNString("ape_paterno"));
                v.setApeMaterno(rs.getNString("ape_materno"));
                v.setTipoComprobante(rs.getString("tipo_comprobante"));
                v.setFechaEmision(rs.getDate("fecha_emision").toLocalDate());

                ventas.add(v);
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

        return ventas;

    }

}
