<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ZENID</title>
        
        <%@ include file="WEB-INF/layout/estilos.jsp" %> 
    </head>
    <body>
        <main class="container">
            <div class="card">
                <div class="card-body">
                    <form action="iniciar-sesion" method="POST">
                        <div class="form-group">
                            <label>Correo Electrónico</label>
                            <input type="text" name="correo-electronico" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Contraseña</label>
                            <input type="text" name="contrasenia" class="form-control">
                        </div>
                        <button class="btn btn-primary">Ingresar</button>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>
