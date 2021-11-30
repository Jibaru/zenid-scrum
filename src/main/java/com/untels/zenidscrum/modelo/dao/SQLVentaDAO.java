package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.ProductoProformado;
import com.untels.zenidscrum.modelo.bean.Proforma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLVentaDAO {

    private final Conexion conexion;

    public SQLVentaDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    public Proforma obtenerProductosProformadosId(int idProductoProformado) {
        String sql = "SELECT FROM productos_proformados WHERE id_producto_proformado = ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proforma p = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idProductoProformado);

            rs = ps.executeQuery();

            List<ProductoProformado> productos = new ArrayList<>();

            while (rs.next()) {

                if (p == null) {
                    p = new Proforma();
                    p.setIdProforma(rs.getInt("id_proforma"));
                    p.setNombreReferencial(rs.getString("nombre_referencial"));
                    p.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
                }

                ProductoProformado prod = new ProductoProformado();
                prod.setIdProductoProformado(rs.getInt("id_producto_proformado"));
                prod.setIdProducto(rs.getInt("id_producto"));
                prod.setIdProforma(rs.getInt("id_proforma"));
                prod.setNombre(rs.getString("nombre"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setCantidad(rs.getInt("cantidad"));
                prod.setCodBarras(rs.getString("cod_barras"));
                prod.setMarca(rs.getString("marca"));
                prod.setIgv(rs.getFloat("igv"));
                prod.setPrecioUnitario(rs.getFloat("precio_unitario"));
                prod.setFactor(rs.getInt("factor"));
                prod.setUnidad(rs.getString("unidad"));
                productos.add(prod);
            }

            if (p != null) {
                p.setProductosProformados(productos);
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
}
