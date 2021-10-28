<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ZENID</title>
        
        <%@ include file="WEB-INF/layout/estilos.jsp" %> 
    </head>
    <body>
        <%
            String mensaje = (String) request.getAttribute("mensaje");
        %>
        <main class="container">
            <div class="card w-50 shadow-sm bg-body rounded" style="margin: 5rem auto">
                <picture class="text-center">
                    <img src="recursos/img/logo.jpg" class="card-img-top" alt="Logo" style="width: 20rem;">
                </picture>
                <div class="card-body">
                    <form action="iniciar-sesion" method="POST">
                        <div class="form-group">
                            <label>Correo Electrónico</label>
                            <input type="text" name="correo-electronico" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Contraseña</label>
                            <input type="password" name="contrasenia" class="form-control" required>
                        </div>
                        <div class="text-center mt-2 mb-2">
                            <button class="btn btn-primary">Ingresar</button>
                        </div>
                        <% if (mensaje != null && !mensaje.isEmpty()) { %>
                        <div class="alert alert-danger text-center">
                            <%=mensaje%>
                        </div>
                        <% } %>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>
