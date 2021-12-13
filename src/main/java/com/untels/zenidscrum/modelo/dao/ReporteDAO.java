package com.untels.zenidscrum.modelo.dao;

import com.untels.zenidscrum.modelo.bean.Venta;
import java.util.Date;
import java.util.List;

public interface ReporteDAO {

    public List<Venta> listarPorNombreReferencial(String inicio, String fin);

}
