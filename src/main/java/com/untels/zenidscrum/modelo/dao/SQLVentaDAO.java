package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Venta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLVentaDAO implements VentaDAO {

    private final Conexion conexion;

    public SQLVentaDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean Crear(Venta v) {
        String sql = "INSERT INTO ventas ("
                + "nombres, "
                + "ape_paterno,"
                + "ape_materno, "
                + "tipo_comprobante,"
                + "numero_comprobante,"
                + "fecha_emision,"
                + "despachado, "
                + "id_proforma) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, v.getNombres());
            ps.setString(2, v.getApePaterno());
            ps.setString(3, v.getApeMaterno());
            ps.setString(4, v.getTipoComprobante());
            ps.setString(5, v.getNumeroComprobante());
            ps.setDate(6, Date.valueOf(v.getFechaEmision()));
            ps.setBoolean(7, v.isDespachado());
            ps.setInt(8, v.getProforma().getIdProforma());

            filasModificadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                v.setIdVenta(rs.getInt(1));

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
    public Venta obtenerPorId(int idVenta) {
        String sql = "SELECT * FROM ventas WHERE "
                + "id_venta = ? ";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Venta v = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idVenta);

            rs = ps.executeQuery();

            if (rs.next()) {

                v = new Venta();
                v.setIdVenta(rs.getInt("id_venta"));
                v.setNombres(rs.getString("nombres"));
                v.setApePaterno(rs.getString("ape_paterno"));
                v.setApeMaterno(rs.getString("ape_materno"));
                v.setTipoComprobante(rs.getString("tipo_comprobante"));
                v.setNumeroComprobante(rs.getString("numero_comprobante"));
                v.setFechaEmision(rs.getDate("fecha_emision").toLocalDate());
                v.setDespachado(rs.getBoolean("despachado"));

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

        return v;
    }

    @Override
    public int obtenerIdProformaPorIdVenta(int idVenta) {
        String sql = "SELECT * FROM ventas WHERE "
                + "id_venta = ? ";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idproforma = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idVenta);

            rs = ps.executeQuery();

            if (rs.next()) {

                idproforma = rs.getInt("id_proforma");

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

        return idproforma;
    }

    @Override
    public List<Venta> listarTodos() {
        String sql = "SELECT * FROM ventas";

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
                v.setApePaterno(rs.getString("ape_paterno"));
                v.setApeMaterno(rs.getString("ape_materno"));
                v.setNombres(rs.getString("nombres"));
                v.setTipoComprobante(rs.getString("tipo_comprobante"));
                v.setNumeroComprobante(rs.getString("numero_comprobante"));
                v.setDespachado(rs.getBoolean("despachado"));
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

    @Override
    public List<Venta> listarPorTerminoBoletaFactura(
            String termino,
            boolean boleta,
            boolean factura
    ) {
        String sql = "SELECT * FROM ventas WHERE ("
                + "nombres LIKE ? OR ape_paterno LIKE ? OR ape_materno LIKE ? ) "
                + "AND (tipo_comprobante = ? OR tipo_comprobante = ?)";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Venta> ventas = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, termino + "%");
            ps.setString(2, termino + "%");
            ps.setString(3, termino + "%");

            if (boleta) {
                ps.setString(4, "boleta");
            } else {
                ps.setString(4, "%");
            }

            if (factura) {
                ps.setString(5, "factura");
            } else {
                ps.setString(5, "%");
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                Venta v = new Venta();
                v.setIdVenta(rs.getInt("id_venta"));
                v.setApePaterno(rs.getString("ape_paterno"));
                v.setApeMaterno(rs.getString("ape_materno"));
                v.setNombres(rs.getString("nombres"));
                v.setTipoComprobante(rs.getString("tipo_comprobante"));
                v.setNumeroComprobante(rs.getString("numero_comprobante"));
                v.setDespachado(rs.getBoolean("despachado"));
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

    @Override
    public boolean despachar(int idVenta) {
        String sql = "UPDATE ventas SET despachado='1' WHERE id_venta=?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idVenta);

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
