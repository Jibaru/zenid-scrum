package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.ProductoProformado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLProductoProformadoDAO implements ProductoProformadoDAO {

    private final Conexion conexion;

    public SQLProductoProformadoDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean crear(ProductoProformado p) {
        String sql = "INSERT INTO productos_proformados ("
                + "nombre, "
                + "cod_barras,"
                + "descripcion, "
                + "marca, "
                + "igv, "
                + "precio_unitario, "
                + "unidad, "
                + "cantidad, "
                + "factor, "
                + "id_proforma,"
                + "id_producto) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            ps.setFloat(5, p.getIgv());
            ps.setFloat(6, p.getPrecioUnitario());
            ps.setString(7, p.getUnidad());
            ps.setInt(8, p.getCantidad());
            ps.setInt(9, p.getFactor());
            ps.setInt(10, p.getIdProforma());
            ps.setInt(11, p.getIdProducto());

            System.out.println(ps);

            filasModificadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                p.setIdProductoProformado(rs.getInt(1));
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

}
