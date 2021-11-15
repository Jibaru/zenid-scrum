package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Proveedor;
import com.untels.zenidscrum.modelo.bean.Representante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLProveedorDAO implements ProveedorDAO {

    private final Conexion conexion;

    public SQLProveedorDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Proveedor> listarTodos() {
        String sql = "SELECT p.id_proveedor,p.nombre,p.ruc,p.telefono, p.correo_electronico, p.id_representante, p.habilitado, r.id_representante as id_rep, r.nombre as nombre_representante from proveedor p INNER JOIN representante r on p.id_representante = r.id_representante";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proveedor prov=null;
        Representante rep=null;
        List<Proveedor> proveedores = new ArrayList<>();
        
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                
                if (prov != null && rs.getInt("id_proveedor") != prov.getIdProveedor()) {
                    
                    prov.setRepresentante(rep);
                    proveedores.add(prov);
                    rep = null;
                    prov =null;
         
                } 
                
               if (prov==null){
               prov = new Proveedor();
                rep = new Representante();
                prov.setIdProveedor(rs.getInt("id_proveedor"));
                prov.setCorreoElectronico(rs.getString("correo_electronico"));
                prov.setNombre(rs.getString("nombre"));
                prov.setRuc(rs.getLong("ruc"));
                prov.setTelefono(rs.getInt("telefono"));
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

    @Override
    public Proveedor obtenerPorId(int idProveedor) {
        String sql = "SELECT p.id_proveedor, "
                + "p.nombre, "
                + "p.ruc, "
                + "p.correo_electronico, "
                + "p.telefono, "
                + "p.id_representante, "
                + "p.habilitado, "
                + "r.id_representante as id_rep, "
                + "r.nombre as nombre_rep "
                + "FROM proveedor p "
                + "INNER JOIN representante r "
                + "ON p.id_representante = r.id_representante "
                + "WHERE u.id_proveedor= ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proveedor prov = null;
        Representante rep = null;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, idProveedor);

            rs = ps.executeQuery();

            while (rs.next()) {

                if (prov == null) {
                    prov = new Proveedor();
                    rep = new Representante();
                    prov.setIdProveedor(rs.getInt("id_proveedor"));
                    prov.setNombre(rs.getString("nombre"));
                    prov.setRuc(rs.getLong("ruc"));
                    prov.setCorreoElectronico(rs.getString("correo_electronico"));
                    prov.setTelefono(rs.getInt("telefono"));
                    prov.setHabilitado(rs.getBoolean("habilitado"));
                    rep.setIdRepresentante(rs.getInt("id_represetante"));
/*chequeardespues*/ rep.setNombre(rs.getString("nombre_rep"));
                    
                }

            }

            if (prov != null) {
                prov.setRepresentante(rep);
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

        return prov;
    }

    @Override
    public boolean crear(Proveedor prov) {
                String sql = "INSERT INTO proveedor ("
                + "nombre, "
                + "ruc ,"
                + "correo_electronico, "
                + "telefono, "
                + "id_representante, "      
                + "habilitado ) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, prov.getNombre());
            ps.setLong(2, prov.getRuc());
            ps.setString(3, prov.getCorreoElectronico());
            ps.setInt(4, prov.getTelefono());
            ps.setInt(5, prov.getRepresentante().getIdRepresentante());
            ps.setBoolean(6, prov.isHabilitado());
            
            filasModificadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                prov.setIdProveedor(rs.getInt(1));
                
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
    public boolean modificar(Proveedor prov) {
        String sql = "UPDATE proveedor SET "
                + "nombre=?, "
                + "ruc=?,"
                + "correo_electronico=?, "
                + "telefono=?"
                + "id_representante=?, "
                + "habilitado=? "
                + "WHERE id_proveedor=?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, prov.getNombre());
            ps.setLong(2, prov.getRuc());
            ps.setString(3, prov.getCorreoElectronico());
            ps.setInt(4, prov.getTelefono());
            ps.setInt(5, prov.getIdRepresentante());
            ps.setBoolean(6, prov.isHabilitado());

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
