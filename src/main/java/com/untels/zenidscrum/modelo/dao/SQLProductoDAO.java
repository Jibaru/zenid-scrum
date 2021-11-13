package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLProductoDAO implements ProductoDAO {

    private final Conexion conexion;

    public SQLProductoDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Producto> buscar(String termino, String marca, Integer idProveedor) {
        String sql = "SELECT * FROM producto p "
                + "WHERE p.nombre LIKE '"
                + termino + "%'"
                + "AND p.marca LIKE '"
                + marca + "%'";

        if (idProveedor != null) {
            sql += " AND p.id_proveedor = " + idProveedor.toString();
        }

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Producto> productos = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            //ps.setString(1, termino + "%");
            //ps.setString(2, marca + "%");
/*
            if (idProveedor != null) {
                ps.setString(1, idProveedor.toString());
            }*/

            System.out.println(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setCodBarras(rs.getString("cod_barras"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setMarca(rs.getString("marca"));
                p.setFamilia(rs.getString("familia"));
                p.setLinea(rs.getString("linea"));
                p.setStock(rs.getInt("stock"));
                p.setIgv(rs.getFloat("igv"));
                p.setStockMinimo(rs.getInt("stock_minimo"));
                p.setHabilitado(rs.getBoolean("habilitado"));

                productos.add(p);
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

        return productos;
    }

}
