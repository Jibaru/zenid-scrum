
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>boleta o factua</title>
        <%@ include file="../layout/estilos.jsp" %>
    </head>
    <body>

        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Checkout</h1>
                <h4>Proforma N°</h4>
                <div class="card">
                    <div class="card-body">
                        <form
                            class="row"

                            action=""

                            action = "prueba"

                            method="POST">
                            <div class="form-group mb-2 col-md-">
                                <label>Datos del Cliente:</label>
                                <input
                                    type="text"
                                    name="nombre"
                                    class="form-control"
                                    placeholder="Nombre"
                                    value=""
                                    required>
                            </div>

                            <div class="form-group mb-2 col-md-6">
                                <label ></label>
                                <input
                                    type="email"
                                    name="correo-electronico"
                                    class="form-control"
                                    placeholder="Apellido Paterno"
                                    value=""
                                    required>
                            </div>

                            <div class="form-group mb-2 col-md-6">
                                <label></label>
                                <input
                                    type="text"
                                    name="telefono"
                                    class="form-control"
                                    value=""
                                    placeholder="Apellido Materno"
                                    required>
                            </div>

                            <div class="form-group mb-2 col-md-3">
                                <label>Fecha:</label>
                                <input

                                    type="text"
                                    id="fecha"
                                    name="nombre-representante"
                                    class="form-control"
                                    placeholder="Fecha"
                                    value=""
                                    required>
                            </div>
                            <div class="collapse col-md-5" id="dni" >
                                <label>Ingrese su DNI par la boleta:</label>
                                <input
                                    type="text"
                                    name="dni"
                                    class="form-control"
                                    value=""
                                    placeholder="DNI"
                                    required>
                            </div>

                            <div class="container collapse col-md-9 " id="ruc" >
                                <div class="row align-items-start">
                                    <label >Ingrese nro de RUC para la factura:</label>
                                    <div class="col">

                                        <input
                                            style="display: inline;"
                                            type="text"
                                            name="ruc"
                                            class="form-control"
                                            value=""
                                            placeholder="RUC"
                                            required>
                                    </div>
                                    <div class="col">
                                        <button  class="btn btn-dark" style="display: inline;" >
                                            Validar RUC
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div>
                                <label style="display: block;">Tipo de Documento</label>
                                <div class="form-check" style="display: inline-block; margin-right: 60px;">
                                    <input class="form-check-input" value="bol" onchange="mostrar(this.value)" type="radio" name="flexRadioDefault" id="flexRadioDefault1" >
                                    <label class="form-check-label" for="flexRadioDefault1">
                                        Boleta
                                    </label>
                                </div>
                                <div class="form-check" style="display: inline-block;" >
                                    <input class="form-check-input" onchange="mostrar(this.value)" type="radio" name="flexRadioDefault" id="flexRadioDefault2" >
                                    <label class="form-check-label" for="flexRadioDefault2">
                                        Factura
                                    </label>
                                </div>

                            </div>

                            <div class="form-group mb-2">

                                <button class="btn btn-dark" style="float: right;">
                                    Generar Venta
                                </button>

                            </div>
                        </form>

                    </div>
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
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td class="text-end"></td>
                                <td class="text-end"></td>
                                <td class="text-end">

                                </td>
                                <td class="text-end">

                                </td>
                                <td class="text-end">

                                </td>
                                <td class="text-end">

                                </td>
                            </tr>

                            <tr>
                                <td colspan="7" class="text-end">

                                </td>
                                <td class="text-end">

                                </td>
                                <td class="text-end">

                                </td>
                            </tr>
                            <tr>
                                <td colspan="8" class="text-end">
                                    <b>Precio Total</b>
                                </td>
                                <td class="text-end">
                                    <b>

                                    </b>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

            </section>
        </main>
        <script>
            const tiempoTranscurrido = Date.now();
            const hoy = new Date(tiempoTranscurrido);
            // hoy.toLocaleDateString();
            console.log(hoy.toLocaleDateString());
            document.getElementById("fecha").value = hoy.toLocaleDateString();

            function mostrar(dato) {
                if (dato == "bol") {
                    document.getElementById("dni").style.display = "block";
                    document.getElementById("ruc").style.display = "none";
                } else {
                    document.getElementById("dni").style.display = "none";
                    document.getElementById("ruc").style.display = "block";
                }

            }
        </script>
    </body>
</html>
