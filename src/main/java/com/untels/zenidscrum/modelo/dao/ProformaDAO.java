package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.modelo.bean.Proforma;
import java.util.List;

public interface ProformaDAO {

    public List<Proforma> listarPorNombreReferencial(String nombreReferencial);

    public Proforma obtenerPorId(int idProforma);

    public boolean crear(Proforma proforma);
}
