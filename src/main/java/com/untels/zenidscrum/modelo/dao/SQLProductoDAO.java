package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Precio;
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
        String sql = "SELECT * FROM productos p ";
        List<String> partes = new ArrayList<>();

        if (termino != null) {
            partes.add("p.nombre LIKE '" + termino + "%'");
        }

        if (marca != null) {
            partes.add("p.marca LIKE '" + marca + "%'");
        }

        if (idProveedor != null) {
            partes.add("p.id_proveedor = " + idProveedor.toString());
        }

        if (partes.size() > 0) {
            sql += " WHERE ";
            for (int i = 0; i < partes.size(); i++) {
                sql += partes.get(i);
                if (i != partes.size() - 1) {
                    sql += " AND ";
                }
            }
        }

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null, ps2 = null;
        ResultSet rs = null, rs2 = null;
        List<Producto> productos = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            //ps.setString(1, termino + "%");
            //ps.setString(2, marca + "%");
            /*
            if (idProveedor != null) {
                ps.setString(1, idProveedor.toString());
            }*/
            rs = ps.executeQuery();

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

                String sql2 = "SELECT * FROM precios WHERE id_precio = ?";
                ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1, p.getIdProducto());

                rs2 = ps2.executeQuery();
                List<Precio> precios = new ArrayList<>();
                while (rs2.next()) {
                    Precio precio = new Precio();
                    precio.setIdPrecio(rs2.getInt("id_precio"));
                    precio.setCantidad(rs2.getInt("cantidad"));
                    precio.setFactor(rs2.getInt("factor"));
                    precio.setPrecioUnitario(rs2.getFloat("precio_unitario"));
                    precio.setUnidad(rs2.getString("unidad"));
                    precios.add(precio);
                }
                p.setPrecios(precios);

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
            if (ps2 != null) {
                try {
                    ps2.close();
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
            if (rs2 != null) {
                try {
                    rs2.close();
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

    @Override
    public List<Producto> buscarEquivalentes(String termino, String marca, Integer idProveedor) {
        String sql = "SELECT * FROM productos p ";
        List<String> partes = new ArrayList<>();

        if (termino != null) {
            partes.add("p.nombre LIKE '" + termino + "%'");
        }

        if (marca != null) {
            partes.add("p.marca NOT LIKE '" + marca + "%'");
        }

        if (idProveedor != null) {
            partes.add("p.id_proveedor = " + idProveedor.toString());
        }

        if (partes.size() > 0) {
            sql += " WHERE ";
            for (int i = 0; i < partes.size(); i++) {
                sql += partes.get(i);
                if (i != partes.size() - 1) {
                    sql += " AND ";
                }
            }
        }

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null, ps2 = null;
        ResultSet rs = null, rs2 = null;
        List<Producto> productos = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            //ps.setString(1, termino + "%");
            //ps.setString(2, marca + "%");
            /*
            if (idProveedor != null) {
                ps.setString(1, idProveedor.toString());
            }*/

            rs = ps.executeQuery();

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

                String sql2 = "SELECT * FROM precios WHERE id_precio = ?";
                ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1, p.getIdProducto());

                rs2 = ps2.executeQuery();
                List<Precio> precios = new ArrayList<>();
                while (rs2.next()) {
                    Precio precio = new Precio();
                    precio.setIdPrecio(rs2.getInt("id_precio"));
                    precio.setCantidad(rs2.getInt("cantidad"));
                    precio.setFactor(rs2.getInt("factor"));
                    precio.setPrecioUnitario(rs2.getFloat("precio_unitario"));
                    precio.setUnidad(rs2.getString("unidad"));
                    precios.add(precio);
                }
                p.setPrecios(precios);

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
            if (ps2 != null) {
                try {
                    ps2.close();
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
            if (rs2 != null) {
                try {
                    rs2.close();
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

    @Override
    public List<Producto> listarTodos() {
        String sql = "SELECT * FROM productos";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Producto> productos = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

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

    @Override
    public Producto obtenerPorId(int idProducto) {
        String sql = "SELECT * FROM productos WHERE id_producto = ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null, ps2 = null;
        ResultSet rs = null, rs2 = null;
        Producto p = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();

            if (rs.next()) {
                p = new Producto();
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

                String sql2 = "SELECT * FROM precios WHERE id_producto = ?";

                ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1, idProducto);
                rs2 = ps2.executeQuery();
                List<Precio> precios = new ArrayList<>();
                while (rs2.next()) {
                    Precio precio = new Precio();
                    precio.setIdPrecio(rs2.getInt("id_precio"));
                    precio.setCantidad(rs2.getInt("cantidad"));
                    precio.setFactor(rs2.getInt("factor"));
                    precio.setPrecioUnitario(rs2.getFloat("precio_unitario"));
                    precio.setUnidad(rs2.getString("unidad"));
                    precios.add(precio);
                }
                p.setPrecios(precios);
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
            if (ps2 != null) {
                try {
                    ps2.close();
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
            if (rs2 != null) {
                try {
                    rs2.close();
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
