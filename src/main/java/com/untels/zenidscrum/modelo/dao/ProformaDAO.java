package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.modelo.bean.Proforma;

public interface ProformaDAO {

    public Proforma obtenerPorId(int idProforma);

    public boolean crear(Proforma proforma);
}
