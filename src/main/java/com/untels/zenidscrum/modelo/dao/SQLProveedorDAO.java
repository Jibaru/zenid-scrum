package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Proveedor;
import com.untels.zenidscrum.modelo.bean.Representante;
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
        String sql = "SELECT p.id_proveedor, "
                + "p.nombre,"
                + "p.ruc,"
                + "p.telefono, "
                + "p.correo_electronico, "
                + "p.id_representante, "
                + "p.habilitado, "
                + "r.id_representante as id_rep, "
                + "r.nombre as nombre_representante "
                + "from proveedores p "
                + "INNER JOIN representantes r "
                + "on p.id_representante = r.id_representante";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proveedor prov = null;
        Representante rep = null;
        List<Proveedor> proveedores = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {

                if (prov != null && rs.getInt("id_proveedor") != prov.getIdProveedor()) {

                    prov.setRepresentante(rep);
                    proveedores.add(prov);
                    rep = null;
                    prov = null;

                }

                if (prov == null) {
                    prov = new Proveedor();
                    rep = new Representante();
                    prov.setIdProveedor(rs.getInt("id_proveedor"));
                    prov.setCorreoElectronico(rs.getString("correo_electronico"));
                    prov.setNombre(rs.getString("nombre"));
                    prov.setRuc(rs.getString("ruc"));
                    prov.setTelefono(rs.getString("telefono"));
                    prov.setHabilitado(rs.getBoolean("habilitado"));
                    rep.setIdRepresentante(rs.getInt("id_representante"));
                    rep.setNombre(rs.getString("nombre_representante"));
                }

            }
            if (prov != null) {
                prov.setRepresentante(rep);
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
