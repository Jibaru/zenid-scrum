package com.untels.zenidscrum.modelo.bean;

public class Privilegio {

    public static String[] TIPOS = new String[]{
        "USUARIOS",
        "ROLES",
        "PROFORMAS",
        "COMPROBANTES",
        "REPORTES",};

    private int idPrivilegio;
    private String nombre;
    private boolean crear;
    private boolean actualizar;
    private boolean listar;
    private boolean eliminar;
    private int idRol;

    public int getIdPrivilegio() {
        return idPrivilegio;
    }

    public void setIdPrivilegio(int idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isCrear() {
        return crear;
    }

    public void setCrear(boolean crear) {
        this.crear = crear;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public boolean isListar() {
        return listar;
    }

    public void setListar(boolean listar) {
        this.listar = listar;
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

}
