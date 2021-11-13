<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verificar Stock</title>
        <%@ include file="../layout/estilos.jsp" %> 
    </head>
    <body>
        <%@ include file="../layout/navegacion.jsp" %> 
        <div class="container" style="margin-top: 5rem;">
            <p class="h3">Verificar stock</p>
            <div class="row align-items-start" style="margin-bottom: 0.5rem;">
                <div class="col">
                    Nombre
                </div> 
                <div class="col">
                    Marca
                </div>
                <div class="col">

                </div>
            </div>
            <div class="row align-items-center">
                <div class="col">
                    <input class="form-control" type="text" placeholder="Ingrese nombre del producto" aria-label="default input example">
                </div>
                <div class="col">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Seleccione una marca</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                </div>
                <div class="col">
                    <button type="button" class="btn btn-dark">Buscar</button>
                </div>
            </div>
        </div>

        <div class="container" style="margin-top: 3rem;">
            <p class="h4">Productos encontrados</p>
            <div class="table-responsive">
                <table class="table table-hover table-bordered">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Marca</th>
                            <th scope="col">Descripción</th>
                            <th scope="col">Stock minimo.</th>
                            <th scope="col">Stock actual</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Opciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>nombre</td>
                            <td>marca</td>
                            <td>descripción</td>
                            <td>stock minimo</td>
                            <td>stock actual</td>
                            <td>estado</td>
                            <td><a href="#" class="link-dark">Agregar pedido</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="container" style="margin-top: 3rem;">
            <p class="h4">Pedidos a generar</p>
            <div class="table-responsive">
                <table class="table table-hover table-bordered">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Marca</th>
                            <th scope="col">Descripción</th>
                            <th scope="col">Cantidad</th>
                            <th scope="col">Proveedor</th>
                            <th scope="col">Opciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    <th scope="row">1</th>
                    <td>nombre</td>
                    <td>marca</td>
                    <td>descripcion</td>
                    <td>cantidad</td>
                    <td>proveedor</td>
                    <td><a href="#" class="link-dark">Remover</a></td>
                    </tbody>
                </table>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col align-self-start">
                        <button type="button" class="btn btn-dark" style="margin-top: 2rem;">Generar</button>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
