package com.untels.zenidscrum.modelo.bean;

import java.time.LocalDate;
import java.util.List;

public class Proforma {

    private int idProforma;
    private String nombreReferencial;
    private LocalDate fechaCreacion;
    private List<ProductoProformado> productosProformados;

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
    }

    public String getNombreReferencial() {
        return nombreReferencial;
    }

    public void setNombreReferencial(String nombreReferencial) {
        this.nombreReferencial = nombreReferencial;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<ProductoProformado> getProductosProformados() {
        return productosProformados;
    }

    public void setProductosProformados(List<ProductoProformado> productosProformados) {
        this.productosProformados = productosProformados;
    }

}
