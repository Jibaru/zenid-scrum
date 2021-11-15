<%-- 
    Document   : index
    Created on : 12-nov-2021, 22:10:14
    Author     : Administrador
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.untels.zenidscrum.modelo.bean.Proveedor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Proveedor> proveedores = (ArrayList<Proveedor>) request.getAttribute("proveedores");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proveedores</title>
        <%@ include file="../layout/estilos.jsp" %> 
        <!-- Compressed CSS -->
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/foundation-sites@6.7.3/dist/css/foundation.min.css" crossorigin="anonymous">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<!--Compressed JavaScript -->
 <!--<script src="https://cdn.jsdelivr.net/npm/foundation-sites@6.7.3/dist/js/foundation.min.js" crossorigin="anonymous"></script>-->
    </head>
    <body >
         <%@ include file="../layout/navegacion.jsp" %> 
         
         <main  style="display: inline;float: left;width: 85%;border: 2px solid #04414d" >
         
         <div style="height: 120px;">

            <div class="divididos" id="divi1">

              <h1 style="vertical-align: middle">Proveedores</h1>

           </div>
<!-- 
            <div class="divididos" id="divi2" >

              <div class="input-group mb-3" style="width: 45%;margin-left: 200px;">
                <label class="input-group-text" for="inputGroupSelect01">Buscar por: </label>
                <select class="form-select" id="inputGroupSelect01">
                  <option selected>Elegir...</option>
                  <option value="1">NOMBRE</option>
                  <option value="2">RUC</option>
                  <option value="3">ID</option>
                </select>
              </div>
             
              
              <div class="input-group input-group-rounded" style="width: 70%;margin-left: 60px;margin-top: 10px;">
                <input class="input-group-field" type="search">
                <div class="input-group-button">
                  <input type="submit" class="button secondary" value="Buscar">
                </div>
              </div>

            </div>-->

          </div>
             
           <div style="width: 99%;margin: 0 auto;border: 1px solid rgb(138, 132, 132);border-radius: 10px;padding: 10px;box-shadow: 30px 30px 60px 20px rgba(0, 0, 0, 0.2); " >
<table class="table caption-top" >
            <caption>List of users</caption>
            <thead class="table-dark" >
              <tr style="text-align: center;">
                  <th scope="col" style="width: 5%;text-align: center;">#Id</th>
                <th scope="col" style="text-align: center;">Nombre</th>
                <th scope="col" style="text-align: center;">Ruc</th>   
                <th scope="col" style="text-align: center;">Telefono</th>
                <th scope="col" style="text-align: center;">Correo</th>
                <th scope="col" style="text-align: center;">Representante</th>
                <th scope="col" style="text-align: center;"><a href="ProveedorNu_ed" ><button type="button" class="btn btn-primary" >Nuevo</button></a></th>
                
              </tr>
            </thead>
            <tbody style="text-align: center;">
                <% for (Proveedor p:proveedores){%>
              <tr>
                <td><%=p.getIdProveedor()%></td>
                <td><%=p.getNombre() %></td>
                <td><%=p.getRuc() %></td>
                <td><%=p.getTelefono() %></td>
                <td><%=p.getCorreoElectronico()%></td>
                <td><%=p.getRepresentante().getNombre()%></td>
                <td><a href="formulario-usuario?idUsuario=<%=p.getIdProveedor()%>" class="btn btn-warning" style="margin-right: 5px" >Editar</a><a href="inhabilitar-usuario?idUsuario=<%=p.getIdProveedor()%>" 
                                           class="btn btn-danger">
                                            Inhabilitar
                                        </a></td>
              </tr>
              <%}%>
              
            </tbody>
          </table>
          </div>
            
         <!-- <div id="espbotones">
            <button type="button" class="btn btn-primary">Nuevo</button>
            <button type="button" class="btn btn-primary">Editar</button>
            <button type="button" class="btn btn-primary">Borrar</button>

          </div>-->

         </main>
           
    </body>
</html>
