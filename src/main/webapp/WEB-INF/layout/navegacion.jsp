<%@page import="com.untels.zenidscrum.modelo.bean.Usuario"%>
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
                    <a class="nav-link" href="roles">
                        Roles
                    </a>
                </li>
                <li class="nav-item">
                    <span class="nav-link active">
                        <%=usuario.getNombre()%>
                    </span>

                </li>
                <li class="nav-item">
                    <span class="nav-link active bg-dark text-light">
                        <%=usuario.getRol().getNombre()%>
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
