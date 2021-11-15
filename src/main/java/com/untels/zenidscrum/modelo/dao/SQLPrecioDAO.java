package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Precio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLPrecioDAO implements PrecioDAO {

    private final Conexion conexion;

    public SQLPrecioDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public Precio obtenerPorId(int idPrecio) {
        String sql = "SELECT * FROM precios WHERE id_precio = ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Precio p = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPrecio);

            rs = ps.executeQuery();

            if (rs.next()) {
                p = new Precio();
                p.setIdPrecio(rs.getInt("id_precio"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setFactor(rs.getInt("factor"));
                p.setPrecioUnitario(rs.getFloat("precio_unitario"));
                p.setUnidad(rs.getString("unidad"));
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

        return p;
    }

    @Override
    public boolean crear(Precio arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(int arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
