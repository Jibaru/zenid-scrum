package com.untels.zenidscrum.acceso.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConexion implements Conexion {

    private final String HOST = System.getenv("ZENID_SCRUM_BD_HOST");
    private final String PUERTO = System.getenv("ZENID_SCRUM_BD_PORT");
    private final String NOMBRE = System.getenv("ZENID_SCRUM_BD_NAME");
    private final String USUARIO = System.getenv("ZENID_SCRUM_BD_USER");
    private final String CLAVE = System.getenv("ZENID_SCRUM_BD_PASSWORD");
    private final String url;

    public SQLConexion() {
        url = "jdbc:mysql://" + HOST + ":" + PUERTO + "/" + NOMBRE + "?"
                + "useSSL=false&"
                + "serverTimezone=UTC&"
                + "allowPublicKeyRetrieval=true&"
                + "useLegacyDateTimeCode=false";
    }

    @Override
    public Connection getConnection() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, USUARIO, CLAVE);

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cn;
    }

}
