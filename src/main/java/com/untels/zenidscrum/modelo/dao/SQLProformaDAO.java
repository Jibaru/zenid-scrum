package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.ProductoProformado;
import com.untels.zenidscrum.modelo.bean.Proforma;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLProformaDAO implements ProformaDAO {

    private final Conexion conexion;

    public SQLProformaDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public Proforma obtenerPorId(int idProforma) {
        String sql = "SELECT * FROM proformas prof "
                + "INNER JOIN productos_proformados prod "
                + "ON prof.id_proforma = prod.id_proforma "
                + "WHERE prof.id_proforma = ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proforma p = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idProforma);

            rs = ps.executeQuery();

            List<ProductoProformado> prods = new ArrayList<>();

            while (rs.next()) {

                if (p == null) {
                    p = new Proforma();
                    p.setIdProforma(rs.getInt("id_proforma"));
                    p.setNombreReferencial(rs.getString("nombre_referencial"));
                    p.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
                }

                ProductoProformado pr = new ProductoProformado();
                pr.setIdProductoProformado(rs.getInt("id_producto_proformado"));
                pr.setIdProducto(rs.getInt("id_producto"));
                pr.setIdProforma(rs.getInt("id_proforma"));
                pr.setNombre(rs.getString("nombre"));
                pr.setDescripcion(rs.getString("descripcion"));
                pr.setCantidad(rs.getInt("cantidad"));
                pr.setCodBarras(rs.getString("cod_barras"));
                pr.setMarca(rs.getString("marca"));
                pr.setIgv(rs.getFloat("igv"));
                pr.setPrecioUnitario(rs.getFloat("precio_unitario"));
                pr.setFactor(rs.getInt("factor"));
                pr.setUnidad(rs.getString("unidad"));
                prods.add(pr);
            }

            if (p != null) {
                p.setProductosProformados(prods);
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
    public boolean crear(Proforma p) {
        String sql = "INSERT INTO proformas ("
                + "nombre_referencial, "
                + "fecha_creacion) "
                + "VALUES (?, ?)";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNombreReferencial());
            ps.setDate(2, Date.valueOf(p.getFechaCreacion()));

            filasModificadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                p.setIdProforma(rs.getInt(1));
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
    public List<Proforma> listarPorNombreReferencial(String nombreReferencial) {
        String sql = "SELECT * FROM proformas";

        if (nombreReferencial != null) {
            sql += " WHERE nombre_referencial LIKE '" + nombreReferencial + "%'";
        }

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Proforma> proformas = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Proforma p = new Proforma();
                p.setIdProforma(rs.getInt("id_proforma"));
                p.setNombreReferencial(rs.getString("nombre_referencial"));
                p.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());

                proformas.add(p);
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

        return proformas;

    }

}
