<%--
    Document   : buscar-proforma
    Created on : 24/11/2021, 01:53:35 PM
    Author     : gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Busqueda de proforma</title>
        <%@ include file="../layout/estilos.jsp" %>

    </head>

    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Buscar proforma</h1>
                <div class="card">
                    <div class="card-body">
                        <form class="row" action="buscar-proforma" method="get">
                            <div class="row justify-content-center">
                                <div class="col-6">
                                    <input class="form-control" type="text" placeholder="Nombre / DNI / Id de Proforma"
                                           aria-label="default input example">
                                </div>
                                <div class="col-4">
                                    <button class="btn btn-primary me-md-2" type="button">Buscar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <h2 class="mt-2 mb-2">Proformas coincidentes</h2>
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead class="table-dark">
                                            <tr>
                                                <th>Id</th>
                                                <th>Fecha de emision</th>
                                                <th>Datos del cliente</th>
                                                <th>Opciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                    <form action="">
                                                        <button class="btn btn-warning me-md-2" type="button">Ver</button>
                                                        <button class="btn btn-danger" type="button">Seleccionar</button>
                                                    </form>
                                                </td>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
    </body>
</html>
