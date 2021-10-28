<%@page import="com.untels.zenidscrum.modelo.bean.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Principal</title>
        <%@ include file="layout/estilos.jsp" %> 
    </head>
    <body>
        <%
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario-autenticado");
        %>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="principal">
                    Zenid
                </a>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <span class="nav-link active" aria-current="page">
                                <%=usuario.getNombre()%>
                            </span>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="cerrar-sesion">
                                Cerrar Sesión
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </body>
</html>
