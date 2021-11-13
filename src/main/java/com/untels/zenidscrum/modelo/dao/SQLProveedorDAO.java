package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLProveedorDAO implements ProveedorDAO {

    private final Conexion conexion;

    public SQLProveedorDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Proveedor> listarTodos() {
        String sql = "SELECT * FROM proveedor";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Proveedor> proveedores = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                Proveedor prov = new Proveedor();
                prov.setIdProveedor(rs.getInt("id_proveedor"));
                prov.setCorreoElectronico(rs.getString("correo_electronico"));
                prov.setNombre(rs.getString("nombre"));
                prov.setRuc(rs.getInt("ruc"));
                prov.setTelefono(rs.getInt("telefono"));
                prov.setHabilitado(rs.getBoolean("habilitado"));
                prov.setId_representante(rs.getInt("id_representante"));
                proveedores.add(prov);
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

        return proveedores;
    }

}
