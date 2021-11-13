<%-- 
    Document   : agregar
    Created on : 13-nov-2021, 0:25:24
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Producto</title>
        <%@ include file="../layout/estilos.jsp" %>
        <!-- Compressed CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/foundation-sites@6.7.3/dist/css/foundation.min.css" crossorigin="anonymous">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <!--Compressed JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/foundation-sites@6.7.3/dist/js/foundation.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <%@ include file="../layout/navegacion.jsp" %> 
        
        <main  style="display: inline;float: left;width: 85%" >
            
            <div  id=imagen >
            <img src="C:\Users\Administrador\Downloads\logo.jpg"  > 
         </div>

        <div  id=contenedor  >
             <h1>PRODUCTO</h1>

             <div class="divididos">
                <!-- <div class="inp">
                    <label for="idprod">Id_producto</label>
                    <br>
                    <input width="10px" type="text" id="idprod" class="form-control"/>
                </div> -->
                    
                <div class="inp">
                    <label for="nombre">Nombre</label>
                    <br>
                    <input width="10px" type="text" id="nombre" class="form-control"/>
                </div>

                <div class="inp" >
                    <label for="familia">Familia</label>
                    <br>
                    <input width="20px" type="text" id="familia" class="form-control"/>
                </div>
    
                <div class="inp" >
                    <label for="linea">Linea</label>
                    <br>
                    <input width="20px" type="text" id="linea" class="form-control"/>
                </div>
    
                <div class="inp">
                    <label for="unimed">Unidad de medida</label>
                    <br>
                    <input width="20px" type="text" id="unimed" class="form-control"/>
                </div>

            </div>


            <div class="divididos" id="ee">

                <div class="inp" >
                    <label for="proveedor">Proveedor</label>
                    <br>
                    <input width="20px" type="text" id="proveedor" class="form-control"/>
                </div>

                <div class="inp">
                    <label for="marca">Marca</label>
                    <div class="input-group mb-3">
                        <select class="form-select" id="inputGroupSelect01">
                          <option selected>Elige</option>
                          <option value="1">Faber Castell</option>
                          <option value="2">Layconsa</option>
                          <option value="3">Stanford</option>
                        </select>
                      </div>
                    <!-- <br>
                    <input width="20px" type="text" id="marca" class="form-control"/> -->
                </div>
                
                <div class="inp" style="margin-top:-16px ;">
                    <label for="sto">Stock</label>
                    <br>
                    <input width="20px" type="text" id="sto" class="form-control"/>
                </div>


            </div>

            <div class="d-grid gap-2 col-6 mx-auto">
                <button class="btn btn-primary" type="button">REGISTRAR</button>
                <br> 
            </div>
            
            <!-- <div class="d-grid gap-2 col-6 mx-auto">
                <button class="btn btn-primary" type="button">VER LISTA</button>
                <br> 
            </div> -->
            

        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
            
        </main>
    </body>
</html>
