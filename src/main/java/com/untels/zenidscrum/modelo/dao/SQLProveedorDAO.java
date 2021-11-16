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
        String sql = "SELECT p.id_proveedor, "
                + "p.nombre,"
                + "p.ruc,"
                + "p.telefono, "
                + "p.correo_electronico, "
                + "p.id_representante, "
                + "p.habilitado, "
                + "r.id_representante as id_rep, "
                + "r.nombre as nombre_rep, "
                + "r.correo_electronico as correo_electronico_rep, "
                + "r.telefono as telefono_rep "
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
                prov = new Proveedor();
                rep = new Representante();
                prov.setIdProveedor(rs.getInt("id_proveedor"));
                prov.setCorreoElectronico(rs.getString("correo_electronico"));
                prov.setNombre(rs.getString("nombre"));
                prov.setRuc(rs.getString("ruc"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setHabilitado(rs.getBoolean("habilitado"));
                rep.setIdRepresentante(rs.getInt("id_representante"));
                rep.setNombre(rs.getString("nombre_rep"));
                rep.setCorreoElectronico(rs.getString("correo_electronico_rep"));
                rep.setTelefono(rs.getString("telefono_rep"));
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
                + "r.nombre as nombre_rep, "
                + "r.correo_electronico as correo_electronico_rep, "
                + "r.telefono as telefono_rep "
                + "FROM proveedores p "
                + "INNER JOIN representantes r "
                + "ON p.id_representante = r.id_representante "
                + "WHERE p.id_proveedor= ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proveedor prov = null;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, idProveedor);

            rs = ps.executeQuery();

            if (rs.next()) {
                prov = new Proveedor();
                Representante rep = new Representante();
                prov.setIdProveedor(rs.getInt("id_proveedor"));
                prov.setNombre(rs.getString("nombre"));
                prov.setRuc(rs.getString("ruc"));
                prov.setCorreoElectronico(rs.getString("correo_electronico"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setHabilitado(rs.getBoolean("habilitado"));
                rep.setIdRepresentante(rs.getInt("id_representante"));
                rep.setNombre(rs.getString("nombre_rep"));
                rep.setCorreoElectronico(rs.getString("correo_electronico_rep"));
                rep.setTelefono(rs.getString("telefono_rep"));
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
        String sql = "INSERT INTO proveedores ("
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
            ps.setString(2, prov.getRuc());
            ps.setString(3, prov.getCorreoElectronico());
            ps.setString(4, prov.getTelefono());
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
        String sql = "UPDATE proveedores SET "
                + "nombre=?, "
                + "ruc=?,"
                + "correo_electronico=?, "
                + "telefono=?,"
                + "id_representante=?, "
                + "habilitado=? "
                + "WHERE id_proveedor=?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, prov.getNombre());
            ps.setString(2, prov.getRuc());
            ps.setString(3, prov.getCorreoElectronico());
            ps.setString(4, prov.getTelefono());
            ps.setInt(5, prov.getRepresentante().getIdRepresentante());
            ps.setBoolean(6, prov.isHabilitado());
            ps.setInt(7, prov.getIdProveedor());

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
    public boolean inhabilitar(int idProveedor) {
        return modificarHabilitado(idProveedor, false);
    }

    @Override
    public boolean habilitar(int idProveedor) {
        return modificarHabilitado(idProveedor, true);
    }

    private boolean modificarHabilitado(int idHabilitado, boolean habilitado) {
        String sql = "UPDATE proveedores SET habilitado=? WHERE id_proveedor=?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, habilitado);
            ps.setInt(2, idHabilitado);

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
