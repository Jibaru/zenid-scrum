<%@page import="java.util.List"%>
<%@page import="com.untels.zenidscrum.modelo.bean.ProductoProformado"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Proforma"%>
<%
    Proforma proforma = (Proforma) request.getAttribute("proforma");
    List<ProductoProformado> productosProforma = null;

    if (proforma != null) {
        productosProforma = proforma.getProductosProformados();
    }

    float subTotal = 0;
    float igvTotal = 0;
    if (productosProforma != null) {
        for (ProductoProformado p : productosProforma) {
            subTotal += (p.getPrecioUnitario() * p.getCantidad());
            igvTotal += (p.getIgv() * p.getPrecioUnitario() * p.getCantidad());
        }
    }
    float total = subTotal + igvTotal;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generar venta</title>
        <%@ include file="../layout/estilos.jsp" %>
    </head>
    <body>

        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Generar venta</h1>
                <h4>Proforma N° <%=proforma.getIdProforma()%></h4>
                <div class="card">
                    <div class="card-body">
                        <form
                            class="row"
                            action="generar-venta"
                            method="POST">

                            <input type="hidden" name="idProforma" value="<%=proforma.getIdProforma()%>" />

                            <div class="form-group mb-2 col-md-">
                                <label>Nombre del cliente</label>
                                <input
                                    type="text"
                                    name="nombre"
                                    class="form-control"
                                    placeholder="Nombre"
                                    value=""
                                    required>
                            </div>
                            <div class="form-group mb-2 col-md-6">
                                <label>Apellido Paterno</label>
                                <input
                                    type="text"
                                    name="ape-paterno"
                                    class="form-control"
                                    placeholder="Apellido Paterno"
                                    required>
                            </div>
                            <div class="form-group mb-2 col-md-6">
                                <label>Apellido Materno</label>
                                <input
                                    type="text"
                                    name="ape-materno"
                                    class="form-control"
                                    placeholder="Apellido Materno"
                                    required>
                            </div>
                            <div class="form-group mb-2 col-md-3">
                                <label>Fecha</label>
                                <input
                                    type="text"
                                    id="fecha"
                                    name="fecha"
                                    class="form-control"
                                    placeholder="Fecha"
                                    value=""
                                    required>
                            </div>
                            <div class="collapse col-md-5" id="dni" >
                                <label>Ingrese DNI del cliente para la boleta</label>
                                <input
                                    type="text"
                                    name="dni"
                                    class="form-control"
                                    placeholder="DNI"
                                    min="8"
                                    max="8">
                            </div>

                            <div class="container collapse col-md-9 " id="ruc" >
                                <div class="row align-items-start">
                                    <label >Ingrese RUC de la empresa para la factura</label>
                                    <div class="col">
                                        <input
                                            style="display: inline;"
                                            type="text"
                                            id="ruc-input"
                                            name="ruc"
                                            class="form-control"
                                            placeholder="RUC">
                                    </div>
                                    <div class="col">
                                        <button  id="consulta-ruc" class="btn btn-dark" type="button" style="display: inline;" >
                                            Validar RUC
                                        </button>
                                    </div>
                                    <div class="modal fade" id="ruc-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Alerta</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div id="ruc-mensaje" class="modal-body"></div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Aceptar</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div>
                                <label style="display: block;">Tipo de Documento</label>
                                <div class="form-check" style="display: inline-block; margin-right: 60px;">
                                    <input class="form-check-input" value="boleta" onchange="mostrar(this.value)" type="radio" name="tipo-documento"  >
                                    <label class="form-check-label" for="tipo-documento">
                                        Boleta
                                    </label>
                                </div>
                                <div class="form-check" style="display: inline-block;" >
                                    <input class="form-check-input" value="factura" onchange="mostrar(this.value)" type="radio" name="tipo-documento" >
                                    <label class="form-check-label" for="tipo-documento">
                                        Factura
                                    </label>
                                </div>
                            </div>
                            <div class="form-group mb-2">
                                <button id="generar-venta-btn" class="btn btn-dark" style="float: right;" type="submit">
                                    Generar Venta
                                </button>

                            </div>
                        </form>
                        <hr />
                        <% if (proforma != null) {%>
                        <h5>
                            Nombre Referencial
                        </h5>
                        <p><%=proforma.getNombreReferencial()%></p>
                        <h5>
                            Fecha de Emisión
                        </h5>
                        <p><%=proforma.getFechaCreacion()%></p>
                        <h5>
                            Productos proformados
                        </h5>
                        <table class="table">
                            <thead class="table-dark">
                                <tr>
                                    <th>Cod. Barras</th>
                                    <th>Nombre</th>
                                    <th>Descripción</th>
                                    <th>Precio Unitario (S/.)</th>
                                    <th>Cantidad</th>
                                    <th>IGV</th>
                                    <th>Subtotal (S/.)</th>
                                    <th>Total IGV (S/.)</th>
                                    <th>Total + IGV (S/.)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (ProductoProformado p : productosProforma) {%>
                                <%
                                    float subtotalProducto = p.getCantidad() * p.getPrecioUnitario();
                                    float totalIGVProducto = subtotalProducto * p.getIgv();
                                    float totalProducto = subtotalProducto + totalIGVProducto;
                                %>
                                <tr>
                                    <td><%=p.getCodBarras()%></td>
                                    <td><%=p.getNombre()%></td>
                                    <td><%=p.getDescripcion()%></td>
                                    <td class="text-end"><%=p.getPrecioUnitario()%></td>
                                    <td class="text-end"><%=p.getCantidad()%></td>
                                    <td class="text-end">
                                        <%=p.getIgv()%>
                                    </td>
                                    <td class="text-end">
                                        <%=String.format("%.02f", subtotalProducto)%>
                                    </td>
                                    <td class="text-end">
                                        <%=String.format("%.02f", totalIGVProducto)%>
                                    </td>
                                    <td class="text-end">
                                        <%=String.format("%.02f", totalProducto)%>
                                    </td>
                                </tr>
                                <% }%>
                                <tr>
                                    <td colspan="7" class="text-end">
                                        <%=String.format("%.02f", subTotal)%>
                                    </td>
                                    <td class="text-end">
                                        <%=String.format("%.02f", igvTotal)%>
                                    </td>
                                    <td class="text-end">
                                        <%=String.format("%.02f", total)%>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="8" class="text-end">
                                        <b>Precio Total</b>
                                    </td>
                                    <td class="text-end">
                                        <b>
                                            <%=String.format("%.02f", total)%>
                                        </b>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <% } else { %>
                        <h1>Proforma no encontrada</h1>
                        <% }%>
                    </div>
                </div>
            </section>
        </main>
        <script>
            const tiempoTranscurrido = Date.now();
            const hoy = new Date(tiempoTranscurrido);
            const generarVentaBtn = document.getElementById("generar-venta-btn");

            // hoy.toLocaleDateString();
            console.log(hoy.toLocaleDateString());
            document.getElementById("fecha").value = hoy.toLocaleDateString();

            function mostrar(dato) {
                if (dato == "boleta") {
                    document.getElementById("dni").style.display = "block";
                    document.getElementById("ruc").style.display = "none";
                    generarVentaBtn.disabled = false;
                } else {
                    document.getElementById("dni").style.display = "none";
                    document.getElementById("ruc").style.display = "block";
                    generarVentaBtn.disabled = true;
                }

            }
        </script>
        <script>
            var url =
                    "https://dniruc.apisperu.com/api/v1/ruc/";
            var token =
                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImlnbmFjaW9ydWVkYWJvYWRhQGdtYWlsLmNvbSJ9.PEtcuSdcO3NvRlhSOe2br5jQHiIr99IIO4SNxH1syRM";

            var consultaRUCBtn = document.getElementById("consulta-ruc");
            var rucInput = document.getElementById("ruc-input");


            // Ejemplo ruc válido

            var rucModal = new bootstrap.Modal(document.getElementById('ruc-modal'), {
                keyboard: false
            });
            var mensaje = document.getElementById("ruc-mensaje");

            consultaRUCBtn.addEventListener("click", function (_) {
                var ruc = rucInput.value;

                fetch(url + ruc + "?token=" + token).then((resp) => {
                    if (resp.status === 200) {
                        resp.json().then((json) => {
                            var razonSocial = json["razonSocial"];
                            mensaje.innerHTML = "RUC VÁLIDO<br/>";
                            mensaje.innerHTML += razonSocial;
                            rucModal.toggle();
                            generarVentaBtn.disabled = false;
                        })
                    } else {
                        // RUC Inválido
                        mensaje.textContent = "RUC INVÁLIDO";
                        rucModal.toggle();
                        generarVentaBtn.disabled = true;
                    }
                })
            });
        </script>
    </body>
</html>
