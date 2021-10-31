package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Privilegio;
import com.untels.zenidscrum.modelo.bean.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLRolDAO implements RolDAO {

    private final Conexion conexion;

    public SQLRolDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Rol> listarTodos() {
        String sql = "SELECT "
                + "r.id_rol, "
                + "r.nombre as nombre_rol, "
                + "r.descripcion, "
                + "r.habilitado, "
                + "p.id_privilegio, "
                + "p.nombre as nombre_privilegio, "
                + "p.crear, "
                + "p.actualizar, "
                + "p.listar, "
                + "p.eliminar "
                + "FROM roles r "
                + "INNER JOIN privilegios p "
                + "ON r.id_rol = p.id_rol";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Rol> roles = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            List<Privilegio> privilegios = new ArrayList<>();
            Rol rol = null;

            while (rs.next()) {

                if (rol != null && rs.getInt("id_rol") != rol.getIdRol()) {
                    rol.setPrivilegios(privilegios);
                    roles.add(rol);

                    privilegios = new ArrayList<>();
                    rol = null;
                }

                if (rol == null) {
                    rol = new Rol();
                    rol.setIdRol(rs.getInt("id_rol"));
                    rol.setNombre(rs.getString("nombre_rol"));
                    rol.setDescripcion(rs.getString("descripcion"));
                    rol.setHabilitado(rs.getBoolean("habilitado"));
                }

                Privilegio pr = new Privilegio();
                pr.setIdPrivilegio(rs.getInt("id_privilegio"));
                pr.setNombre(rs.getString("nombre_privilegio"));
                pr.setCrear(rs.getBoolean("crear"));
                pr.setActualizar(rs.getBoolean("actualizar"));
                pr.setListar(rs.getBoolean("listar"));
                pr.setEliminar(rs.getBoolean("eliminar"));
                pr.setIdRol(rs.getInt("id_rol"));
                privilegios.add(pr);
            }

            if (rol != null) {
                rol.setPrivilegios(privilegios);
                roles.add(rol);
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

        return roles;
    }

    @Override
    public Rol obtenerPorId(int idRol) {
        String sql = "SELECT "
                + "r.id_rol, "
                + "r.nombre as nombre_rol, "
                + "r.descripcion, "
                + "r.habilitado, "
                + "p.id_privilegio, "
                + "p.nombre as nombre_privilegio, "
                + "p.crear, "
                + "p.actualizar, "
                + "p.listar, "
                + "p.eliminar "
                + "FROM roles r "
                + "INNER JOIN privilegios p "
                + "ON r.id_rol = p.id_rol "
                + "WHERE r.id_rol = ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Rol rol = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idRol);

            rs = ps.executeQuery();

            List<Privilegio> privilegios = new ArrayList<>();

            while (rs.next()) {

                if (rol == null) {
                    rol = new Rol();
                    rol.setIdRol(rs.getInt("id_rol"));
                    rol.setNombre(rs.getString("nombre_rol"));
                    rol.setDescripcion(rs.getString("descripcion"));
                    rol.setHabilitado(rs.getBoolean("habilitado"));
                }

                Privilegio pr = new Privilegio();
                pr.setIdPrivilegio(rs.getInt("id_privilegio"));
                pr.setNombre(rs.getString("nombre_privilegio"));
                pr.setCrear(rs.getBoolean("crear"));
                pr.setActualizar(rs.getBoolean("actualizar"));
                pr.setEliminar(rs.getBoolean("eliminar"));
                pr.setListar(rs.getBoolean("listar"));
                pr.setIdRol(rs.getInt("id_rol"));
                privilegios.add(pr);
            }

            if (rol != null) {
                rol.setPrivilegios(privilegios);
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

        return rol;
    }

    @Override
    public boolean crear(Rol rol) {
        String sql = "INSERT INTO roles (nombre, descripcion, habilitado) VALUES (?, ?, ?)";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, rol.getNombre());
            ps.setString(2, rol.getDescripcion());
            ps.setBoolean(3, rol.isHabilitado());

            filasModificadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                rol.setIdRol(rs.getInt(1));
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
    public boolean modificar(Rol rol) {
        String sql = "UPDATE roles SET nombre=?, descripcion=?, habilitado=? WHERE id_rol=?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, rol.getNombre());
            ps.setString(2, rol.getDescripcion());
            ps.setBoolean(3, rol.isHabilitado());
            ps.setInt(4, rol.getIdRol());

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
    public boolean inhabilitar(int idRol) {
        return modificarHabilitado(idRol, false);
    }

    @Override
    public boolean habilitar(int idRol) {
        return modificarHabilitado(idRol, true);
    }

    private boolean modificarHabilitado(int idRol, boolean habilitado) {
        String sql = "UPDATE roles SET habilitado=? WHERE id_rol=?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, habilitado);
            ps.setInt(2, idRol);

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
