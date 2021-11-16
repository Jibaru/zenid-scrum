package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.ProductoCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLProductoCompraDAO implements ProductoCompraDAO {

    private final Conexion conexion;

    public SQLProductoCompraDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean crear(ProductoCompra pc) {
        String sql = "INSERT INTO productos_compras ("
                + "nombre, "
                + "cod_barras,"
                + "descripcion, "
                + "marca, "
                + "igv, "
                + "precio_compra_unitario, "
                + "stock_minimo, "
                + "cantidad, "
                + "stock_actual, "
                + "id_pedido,"
                + "id_producto) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pc.getNombre());
            ps.setString(2, pc.getCodBarras());
            ps.setString(3, pc.getDescripcion());
            ps.setString(4, pc.getMarca());
            ps.setFloat(5, pc.getIgv());
            ps.setFloat(6, pc.getPrecioCompraUnitario());
            ps.setInt(7, pc.getStockMinimo());
            ps.setInt(8, pc.getCantidad());
            ps.setInt(9, pc.getStockActual());
            ps.setInt(10, pc.getIdPedido());
            ps.setInt(11, pc.getIdProducto());

            System.out.println(ps);

            filasModificadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                pc.setIdProductoCompra(rs.getInt(1));
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
    public List<ProductoCompra> listarPorIdPedido(int idPedido) {
        String sql = "SELECT * FROM productos_compras WHERE id_pedido = ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProductoCompra> productosCompra = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPedido);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductoCompra pc = new ProductoCompra();
                pc.setIdProductoCompra(rs.getInt("id_producto_compra"));
                pc.setIdProducto(rs.getInt("id_producto"));
                pc.setIdPedido(rs.getInt("id_pedido"));
                pc.setNombre(rs.getString("nombre"));
                pc.setDescripcion(rs.getString("descripcion"));
                pc.setCantidad(rs.getInt("cantidad"));
                pc.setCodBarras(rs.getString("cod_barras"));
                pc.setIgv(rs.getFloat("igv"));
                pc.setPrecioCompraUnitario(rs.getFloat("precio_compra_unitario"));
                pc.setMarca(rs.getString("marca"));
                pc.setStockActual(rs.getInt("stock_actual"));
                pc.setStockMinimo(rs.getInt("stock_minimo"));

                productosCompra.add(pc);
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

        return productosCompra;
    }

}
