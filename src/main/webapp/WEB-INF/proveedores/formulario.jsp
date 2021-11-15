<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="../layout/estilos.jsp" %>
        <title>Proveedor</title>
    </head>
    <body>
        <main>
            <%@ include file="../layout/navegacion.jsp" %>
            <%@ include file="../layout/barra-lateral.jsp" %>
            <section id="main">
                <h1>Nuevo Proveedor</h1>
                <div class="card">
                    <div class="card-body">
                        <form action="#" method="POST">
                            <div class="form-group mb-2">
                                <label>Nombre</label>
                                <input
                                    type="text"
                                    name="nombre"
                                    class="form-control"

                                    required>
                            </div>
                            <div class="form-group mb-2">
                                <label>RUC</label>
                                <input
                                    type="email"
                                    name="correo-electronico"
                                    class="form-control"

                                    required>
                            </div>
                            <div class="form-group mb-2">
                                <label>Correo Electrónico</label>
                                <input
                                    type="email"
                                    name="correo-electronico"
                                    class="form-control"

                                    required>
                            </div>

                            <div class="form-group mb-2">
                                <label>Telefono</label>
                                <input
                                    type="email"
                                    name="correo-electronico"
                                    class="form-control"

                                    required>
                            </div>


                            <div class="form-group mb-2">
                                <label>Representante</label>
                                <select name="rol" class="form-control">

                                    <option value="fgfg" >

                                    </option>

                                </select>
                            </div>

                            <button class="btn btn-success">
                                Modificar
                            </button>

                            <button class="btn btn-primary">
                                Guardar
                            </button>

                        </form>

                        <div class="alert alert-danger">
                            No tiene acceso
                        </div>

                        <div class="alert alert-danger">

                        </div>
                    </div>
                </div>
            </section>
        </main>
    </body>
</html>
