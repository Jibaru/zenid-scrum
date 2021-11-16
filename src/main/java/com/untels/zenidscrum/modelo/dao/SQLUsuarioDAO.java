package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.acceso.datos.Conexion;
import com.untels.zenidscrum.modelo.bean.Privilegio;
import com.untels.zenidscrum.modelo.bean.Rol;
import com.untels.zenidscrum.modelo.bean.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLUsuarioDAO implements UsuarioDAO {

    private final Conexion conexion;

    public SQLUsuarioDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public Usuario obtenerUsuarioPorCorreoElectronico(String correoElectronico) {
        String sql = "SELECT u.id_usuario, "
                + "u.nombre as nombre_usuario, "
                + "u.correo_electronico, "
                + "u.contrasenia, "
                + "u.habilitado as habilitado_usuario, "
                + "u.id_rol, "
                + "r.id_rol, "
                + "r.nombre as nombre_rol, "
                + "r.descripcion, "
                + "r.habilitado as habilitado_rol, "
                + "p.id_privilegio, "
                + "p.nombre as nombre_privilegio, "
                + "p.crear, "
                + "p.actualizar, "
                + "p.listar, "
                + "p.eliminar "
                + "FROM usuarios u "
                + "INNER JOIN roles r "
                + "ON u.id_rol = r.id_rol "
                + "INNER JOIN privilegios p "
                + "ON r.id_rol = p.id_rol "
                + "WHERE u.correo_electronico = ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario us = null;
        Rol rol = null;
        List<Privilegio> privilegios = null;

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, correoElectronico);

            rs = ps.executeQuery();

            while (rs.next()) {

                if (us == null) {
                    us = new Usuario();
                    rol = new Rol();
                    us.setIdUsuario(rs.getInt("id_usuario"));
                    us.setCorreoElectronico(rs.getString("correo_electronico"));
                    us.setContrasenia(rs.getString("contrasenia"));
                    us.setNombre(rs.getString("nombre_usuario"));
                    us.setHabilitado(rs.getBoolean("habilitado_usuario"));
                    rol.setIdRol(rs.getInt("id_rol"));
                    rol.setNombre(rs.getString("nombre_rol"));
                    rol.setDescripcion(rs.getString("descripcion"));
                    rol.setHabilitado(rs.getBoolean("habilitado_rol"));

                    privilegios = new ArrayList<>();
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
            }
            if (us != null) {
                us.setRol(rol);
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

        return us;
    }

    @Override
    public List<Usuario> listarTodos() {
        String sql = "SELECT u.id_usuario, "
                + "u.nombre as nombre_usuario, "
                + "u.correo_electronico, "
                + "u.contrasenia, "
                + "u.habilitado as habilitado_usuario, "
                + "u.id_rol, "
                + "r.id_rol, "
                + "r.nombre as nombre_rol, "
                + "r.descripcion, "
                + "r.habilitado as habilitado_rol, "
                + "p.id_privilegio, "
                + "p.nombre as nombre_privilegio, "
                + "p.crear, "
                + "p.actualizar, "
                + "p.listar, "
                + "p.eliminar "
                + "FROM usuarios u "
                + "INNER JOIN roles r "
                + "ON u.id_rol = r.id_rol "
                + "INNER JOIN privilegios p "
                + "ON r.id_rol = p.id_rol ";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario us = null;
        Rol rol = null;
        List<Privilegio> privilegios = new ArrayList<>();
        List<Usuario> usuarios = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                if (us != null && rs.getInt("id_usuario") != us.getIdUsuario()) {
                    rol.setPrivilegios(privilegios);
                    us.setRol(rol);
                    usuarios.add(us);

                    privilegios = new ArrayList<>();
                    rol = null;
                    us = null;
                }

                if (us == null) {
                    us = new Usuario();
                    rol = new Rol();
                    us.setIdUsuario(rs.getInt("id_usuario"));
                    us.setCorreoElectronico(rs.getString("correo_electronico"));
                    us.setContrasenia(rs.getString("contrasenia"));
                    us.setNombre(rs.getString("nombre_usuario"));
                    us.setHabilitado(rs.getBoolean("habilitado_usuario"));
                    rol.setIdRol(rs.getInt("id_rol"));
                    rol.setNombre(rs.getString("nombre_rol"));
                    rol.setDescripcion(rs.getString("descripcion"));
                    rol.setHabilitado(rs.getBoolean("habilitado_rol"));
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
            if (us != null) {
                us.setRol(rol);
                usuarios.add(us);
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

        return usuarios;
    }

    @Override
    public Usuario obtenerPorId(int idUsuario) {
        String sql = "SELECT u.id_usuario, "
                + "u.nombre as nombre_usuario, "
                + "u.correo_electronico, "
                + "u.contrasenia, "
                + "u.habilitado as habilitado_usuario, "
                + "u.id_rol, "
                + "r.id_rol, "
                + "r.nombre as nombre_rol, "
                + "r.descripcion, "
                + "r.habilitado as habilitado_rol, "
                + "p.id_privilegio, "
                + "p.nombre as nombre_privilegio, "
                + "p.crear, "
                + "p.actualizar, "
                + "p.listar, "
                + "p.eliminar "
                + "FROM usuarios u "
                + "INNER JOIN roles r "
                + "ON u.id_rol = r.id_rol "
                + "INNER JOIN privilegios p "
                + "ON r.id_rol = p.id_rol "
                + "WHERE u.id_usuario = ?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario us = null;
        Rol rol = null;
        List<Privilegio> privilegios = null;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, idUsuario);

            rs = ps.executeQuery();

            while (rs.next()) {

                if (us == null) {
                    us = new Usuario();
                    rol = new Rol();
                    us.setIdUsuario(rs.getInt("id_usuario"));
                    us.setCorreoElectronico(rs.getString("correo_electronico"));
                    us.setContrasenia(rs.getString("contrasenia"));
                    us.setNombre(rs.getString("nombre_usuario"));
                    us.setHabilitado(rs.getBoolean("habilitado_usuario"));
                    rol.setIdRol(rs.getInt("id_rol"));
                    rol.setNombre(rs.getString("nombre_rol"));
                    rol.setDescripcion(rs.getString("descripcion"));
                    rol.setHabilitado(rs.getBoolean("habilitado_rol"));

                    privilegios = new ArrayList<>();
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
            if (us != null) {
                us.setRol(rol);
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

        return us;
    }

    @Override
    public boolean crear(Usuario usr) {
        String sql = "INSERT INTO usuarios ("
                + "nombre, "
                + "correo_electronico, "
                + "contrasenia, "
                + "habilitado, "
                + "id_rol) "
                + "VALUES (?, ?, ?, ?, ?)";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usr.getNombre());
            ps.setString(2, usr.getCorreoElectronico());
            ps.setString(3, usr.getContrasenia());
            ps.setBoolean(4, usr.isHabilitado());
            ps.setInt(5, usr.getRol().getIdRol());

            filasModificadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                usr.setIdUsuario(rs.getInt(1));
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
    public boolean modificar(Usuario usr) {
        String sql = "UPDATE usuarios SET "
                + "nombre=?, "
                + "correo_electronico=?, "
                + "habilitado=?, "
                + "id_rol=? "
                + "WHERE id_usuario=?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usr.getNombre());
            ps.setString(2, usr.getCorreoElectronico());
            ps.setBoolean(3, usr.isHabilitado());
            ps.setInt(4, usr.getRol().getIdRol());
            ps.setInt(5, usr.getIdUsuario());

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
    public boolean inhabilitar(int idUsuario) {
        return modificarHabilitado(idUsuario, false);
    }

    @Override
    public boolean habilitar(int idUsuario) {
        return modificarHabilitado(idUsuario, true);
    }

    private boolean modificarHabilitado(int idUsuario, boolean habilitado) {
        String sql = "UPDATE usuarios SET "
                + "habilitado=? "
                + "WHERE id_usuario=?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, habilitado);
            ps.setInt(2, idUsuario);

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
    public boolean modificarContrasenia(int idUsuario, String contrasenia) {
        String sql = "UPDATE usuarios SET "
                + "contrasenia=? "
                + "WHERE id_usuario=?";

        Connection conn = conexion.getConnection();
        PreparedStatement ps = null;
        int filasModificadas = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, contrasenia);
            ps.setInt(2, idUsuario);

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
