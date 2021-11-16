package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Pedido;
import com.untels.zenidscrum.modelo.bean.Proveedor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLPedidoDAO implements PedidoDAO {

    private final Conexion conexion;

    public SQLPedidoDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean crear(Pedido p) {
        String sql = "INSERT INTO pedidos ("
                + "observaciones, "
                + "fecha_creacion, "
                + "codigo_compra, "
                + "id_proveedor) "
                + "VALUES (?, ?, ?, ?)";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getObservaciones());
            ps.setDate(2, Date.valueOf(p.getFechaCreacion()));
            ps.setInt(3, p.getCodigoCompra());
            ps.setInt(4, p.getProveedor().getIdProveedor());

            filasModificadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                p.setIdPedido(rs.getInt(1));
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
    public Pedido obtenerPorId(int idPedido) {
        String sql = "SELECT * from pedidos p "
                + "INNER JOIN proveedores r "
                + "on p.id_proveedor = r.id_proveedor "
                + "WHERE p.id_pedido = ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pedido pedido = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPedido);
            rs = ps.executeQuery();

            if (rs.next()) {
                pedido = new Pedido();

                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
                pedido.setCodigoCompra(rs.getInt("codigo_compra"));
                pedido.setObservaciones(rs.getString("observaciones"));

                Proveedor prov = new Proveedor();
                prov.setIdProveedor(rs.getInt("id_proveedor"));
                prov.setCorreoElectronico(rs.getString("correo_electronico"));
                prov.setNombre(rs.getString("nombre"));
                prov.setRuc(rs.getString("ruc"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setHabilitado(rs.getBoolean("habilitado"));

                pedido.setProveedor(prov);
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

        return pedido;
    }

    @Override
    public int obtenerSiguienteCodigoCompra() {
        String sql = "SELECT MAX(id_pedido) as id_producto FROM pedidos";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int siguienteCodigoCompra = 0;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                siguienteCodigoCompra = rs.getInt("id_producto");
            }
            siguienteCodigoCompra++;

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

        return siguienteCodigoCompra;
    }

    @Override
    public List<Pedido> listarTodos() {
        String sql = "SELECT * from pedidos p "
                + "INNER JOIN proveedores r "
                + "on p.id_proveedor = r.id_proveedor";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pedido> pedidos = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pedido ped = new Pedido();

                ped.setIdPedido(rs.getInt("id_pedido"));
                ped.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
                ped.setCodigoCompra(rs.getInt("codigo_compra"));
                ped.setObservaciones(rs.getString("observaciones"));

                Proveedor prov = new Proveedor();
                prov.setIdProveedor(rs.getInt("id_proveedor"));
                prov.setCorreoElectronico(rs.getString("correo_electronico"));
                prov.setNombre(rs.getString("nombre"));
                prov.setRuc(rs.getString("ruc"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setHabilitado(rs.getBoolean("habilitado"));

                ped.setProveedor(prov);

                pedidos.add(ped);
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

        return pedidos;
    }

    @Override
    public List<Pedido> listarPorCodigoCompra(int codigoCompra) {
        String sql = "SELECT * from pedidos p "
                + "INNER JOIN proveedores r "
                + "on p.id_proveedor = r.id_proveedor "
                + "WHERE p.codigo_compra = ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pedido> pedidos = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, codigoCompra);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pedido ped = new Pedido();

                ped.setIdPedido(rs.getInt("id_pedido"));
                ped.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
                ped.setCodigoCompra(rs.getInt("codigo_compra"));
                ped.setObservaciones(rs.getString("observaciones"));

                Proveedor prov = new Proveedor();
                prov.setIdProveedor(rs.getInt("id_proveedor"));
                prov.setCorreoElectronico(rs.getString("correo_electronico"));
                prov.setNombre(rs.getString("nombre"));
                prov.setRuc(rs.getString("ruc"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setHabilitado(rs.getBoolean("habilitado"));

                ped.setProveedor(prov);

                pedidos.add(ped);
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

        return pedidos;
    }

    @Override
    public boolean eliminar(int idPedido) {
        String sql = "DELETE FROM pedidos WHERE id_pedido=?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPedido);

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
