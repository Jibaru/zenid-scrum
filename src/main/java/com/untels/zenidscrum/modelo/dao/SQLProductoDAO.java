package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Precio;
import com.untels.zenidscrum.modelo.bean.Producto;
import com.untels.zenidscrum.modelo.bean.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLProductoDAO implements ProductoDAO {

    private final Conexion conexion;

    public SQLProductoDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Producto> buscar(String termino, String marca, Integer idProveedor) {
        String sql = "SELECT "
                + "p.*, "
                + "a.nombre as nombre_prov, "
                + "a.ruc, "
                + "a.correo_electronico, "
                + "a.telefono, "
                + "a.id_representante, "
                + "a.habilitado as habilitado_prov,"
                + "pr.* "
                + "FROM "
                + "productos p "
                + "INNER JOIN "
                + "precios pr "
                + "ON p.id_producto = pr.id_producto "
                + "INNER JOIN "
                + "proveedores a "
                + "ON p.id_proveedor = a.id_proveedor ";
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
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Producto> productos = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Producto p = null;
            Proveedor pr = null;
            List<Precio> precios = new ArrayList<>();

            while (rs.next()) {
                if (p != null && rs.getInt("id_producto") != p.getIdProducto()) {
                    p.setPrecios(precios);
                    p.setProveedor(pr);
                    productos.add(p);
                    p = null;
                    pr = null;
                    precios = new ArrayList<>();
                }

                if (p == null) {
                    p = new Producto();
                    pr = new Proveedor();
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
                    p.setPrecioCompraUnitario(rs.getFloat("precio_compra_unitario"));
                    p.setHabilitado(rs.getBoolean("habilitado"));

                    pr.setIdProveedor(rs.getInt("id_proveedor"));
                    pr.setNombre(rs.getString("nombre_prov"));
                    pr.setRuc(rs.getString("ruc"));
                    pr.setTelefono(rs.getString("telefono"));
                    pr.setCorreoElectronico(rs.getString("correo_electronico"));
                }

                Precio precio = new Precio();
                precio.setIdPrecio(rs.getInt("id_precio"));
                precio.setCantidad(rs.getInt("cantidad"));
                precio.setFactor(rs.getInt("factor"));
                precio.setPrecioUnitario(rs.getFloat("precio_unitario"));
                precio.setUnidad(rs.getString("unidad"));
                precios.add(precio);
            }

            if (p != null) {
                p.setPrecios(precios);
                p.setProveedor(pr);
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
    public List<Producto> buscarEquivalentes(String familia, String linea) {
        String sql = "SELECT "
                + "p.*, "
                + "a.nombre as nombre_prov, "
                + "a.ruc, "
                + "a.correo_electronico, "
                + "a.telefono, "
                + "a.id_representante, "
                + "a.habilitado as habilitado_prov,"
                + "pr.* "
                + "FROM "
                + "productos p "
                + "INNER JOIN "
                + "precios pr "
                + "ON p.id_producto = pr.id_producto "
                + "INNER JOIN "
                + "proveedores a "
                + "ON p.id_proveedor = a.id_proveedor ";
        List<String> partes = new ArrayList<>();

        if (familia != null) {
            partes.add("p.familia LIKE '" + familia + "%'");
        }

        if (linea != null) {
            partes.add("p.linea LIKE '" + linea + "%'");
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
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Producto> productos = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Producto p = null;
            Proveedor pr = null;
            List<Precio> precios = new ArrayList<>();

            while (rs.next()) {
                if (p != null && rs.getInt("id_producto") != p.getIdProducto()) {
                    p.setPrecios(precios);
                    p.setProveedor(pr);
                    productos.add(p);
                    p = null;
                    pr = null;
                    precios = new ArrayList<>();
                }

                if (p == null) {
                    p = new Producto();
                    pr = new Proveedor();
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
                    p.setPrecioCompraUnitario(rs.getFloat("precio_compra_unitario"));
                    p.setHabilitado(rs.getBoolean("habilitado"));

                    pr.setIdProveedor(rs.getInt("id_proveedor"));
                    pr.setNombre(rs.getString("nombre_prov"));
                    pr.setRuc(rs.getString("ruc"));
                    pr.setTelefono(rs.getString("telefono"));
                    pr.setCorreoElectronico(rs.getString("correo_electronico"));
                }

                Precio precio = new Precio();
                precio.setIdPrecio(rs.getInt("id_precio"));
                precio.setCantidad(rs.getInt("cantidad"));
                precio.setFactor(rs.getInt("factor"));
                precio.setPrecioUnitario(rs.getFloat("precio_unitario"));
                precio.setUnidad(rs.getString("unidad"));
                precios.add(precio);
            }

            if (p != null) {
                p.setPrecios(precios);
                p.setProveedor(pr);
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
                p.setPrecioCompraUnitario(rs.getFloat("precio_compra_unitario"));
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
        PreparedStatement ps = null, ps2 = null, ps3 = null;
        ResultSet rs = null, rs2 = null, rs3 = null;
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
                p.setPrecioCompraUnitario(rs.getFloat("precio_compra_unitario"));
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

                String sql3 = "SELECT * FROM proveedores WHERE id_proveedor = ?";

                ps3 = conn.prepareStatement(sql3);
                ps3.setInt(1, rs.getInt("id_proveedor"));
                rs3 = ps3.executeQuery();

                if (rs3.next()) {
                    Proveedor pr = new Proveedor();
                    pr.setIdProveedor(rs3.getInt("id_proveedor"));
                    pr.setNombre(rs3.getString("nombre"));
                    pr.setCorreoElectronico(rs3.getString("correo_electronico"));
                    p.setProveedor(pr);
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

    @Override
    public List<Producto> listarStockBajo() {
        String sql = "SELECT * FROM productos WHERE stock <= stock_minimo";

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
                p.setPrecioCompraUnitario(rs.getFloat("precio_compra_unitario"));
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
    public boolean crear(Producto p) {
        String sql = "INSERT INTO productos ("
                + "nombre, "
                + "cod_barras,"
                + "descripcion, "
                + "marca,"
                + "familia,"
                + "linea,"
                + "stock,"
                + "id_proveedor,"
                + "igv,"
                + "stock_minimo,"
                + "precio_compra_unitario,"
                + "habilitado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCodBarras());
            ps.setString(3, p.getDescripcion());
            ps.setString(4, p.getMarca());
            ps.setString(5, p.getFamilia());
            ps.setString(6, p.getLinea());
            ps.setInt(7, p.getStock());
            ps.setInt(8, p.getProveedor().getIdProveedor());
            ps.setFloat(9, p.getIgv());
            ps.setInt(10, p.getStockMinimo());
            ps.setFloat(11, p.getPrecioCompraUnitario());
            ps.setBoolean(12, p.isHabilitado());

            filasModificadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                p.setIdProducto(rs.getInt(1));

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
    public boolean modificar(Producto p) {
        String sql = "UPDATE productos SET "
                + "nombre=?, "
                + "cod_barras=?, "
                + "descripcion=?, "
                + "marca=?, "
                + "familia=?, "
                + "linea=?, "
                + "stock=?, "
                + "id_proveedor=?, "
                + "igv=?, "
                + "stock_minimo=?, "
                + "precio_compra_unitario=? "
                + "WHERE id_producto=?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCodBarras());
            ps.setString(3, p.getDescripcion());
            ps.setString(4, p.getMarca());
            ps.setString(5, p.getFamilia());
            ps.setString(6, p.getLinea());
            ps.setInt(7, p.getStock());
            ps.setInt(8, p.getProveedor().getIdProveedor());
            ps.setFloat(9, p.getIgv());
            ps.setInt(10, p.getStockMinimo());
            ps.setFloat(11, p.getPrecioCompraUnitario());
            ps.setInt(12, p.getIdProducto());

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

    @Override
    public boolean inhabilitar(int idProducto) {
        return modificarHabilitado(idProducto, false);
    }

    @Override
    public boolean habilitar(int idProducto) {
        return modificarHabilitado(idProducto, true);
    }

    private boolean modificarHabilitado(int idProducto, boolean habilitado) {
        String sql = "UPDATE productos SET habilitado=? WHERE id_producto=?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, habilitado);
            ps.setInt(2, idProducto);

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
