<%-- 
    Document   : formulario
    Created on : 14-nov-2021, 23:16:08
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="../layout/estilos.jsp" %> 
        <title>Provedor</title>
    </head>
    <body>
        <%@ include file="../layout/navegacion.jsp" %> 
        <main class="container">
            <div class="card">
                <div class="card-header">
                    <h2>Proveedor</h2>
                </div>
                <div class="card-body">

                    <form 
                        
                        method="POST">
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
                                type="text" 
                                name="ruc" 
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
                                type="text" 
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
        </main>
        
    </body>
</html>
