package com.untels.zenidscrum.acceso.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConexion implements Conexion {

    private final String URL = "jdbc:mysql://localhost/bd_zenid_scrum?useSSL=false&serverTimezone=UTC&useLegacyDateTimeCode=false";
    private final String USUARIO = "root";
    private final String CLAVE = "";

    @Override
    public Connection getConnection() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(URL, USUARIO, CLAVE);

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cn;
    }
}
