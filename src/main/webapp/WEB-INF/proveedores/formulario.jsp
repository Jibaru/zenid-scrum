<%-- 
    Document   : formulario
    Created on : 12-nov-2021, 21:36:38
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proveedores</title>
    </head>
    <body>
        <%@ include file="../layout/navegacion.jsp" %> 
        
         <form>
      <!-- //imagen -->
        <div id="imagen" style="text-align: center;">
            <img src="C:\Users\Administrador\Downloads\logo.jpg" alt="UNA IMAGEN">
           
        </div>
        
        <div style="height: 120px;">

            <div class="divididos" id="divi1">

              <h1 style="vertical-align: middle">Proveedores</h1>

            </div>

            <div class="divididos" id="divi2" >

              <div class="input-group mb-3" style="width: 35%;margin-left: 200px;">
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

            </div>

          </div>

        
        <!-- <table class="dashboard-table">
            <colgroup>
              <col width="150">
              <col width="80">
              <col width="200">
              <col width="60">
              <col width="220">
              <col width="170">
            </colgroup>
            <thead>
              <tr id="titulos">
                <th><a href="#">ID<i class="fa fa-caret-down"></i></a></th>
                <th><a href="#">NOMBRE <i class="fa fa-caret-down"></i></a></th>
                <th><a href="#">EMPRESA <i class="fa fa-caret-down"></i></a></th>
                <th><a href="#">RUC <i class="fa fa-caret-down"></i></a></th>
                <th><a href="#">TELEFONO <i class="fa fa-caret-down"></i></a></th>
                <th><a href="#">CORREO <i class="fa fa-caret-down"></i></a></th>
              </tr>
            </thead>
            <tbody>

              <tr>
                <td>Single Line</td>
                <td>
                  <div class="flex-container align-justify align-top">
                    <div class="flex-child-shrink">
                      <img class="dashboard-table-image" src="https://lorempixel.com/50/50/people/">
                    </div>
                    <div class="flex-child-grow">
                      <h6 class="dashboard-table-text">A Person</h6>
                      <span class="dashboard-table-timestamp">03/04/2017</span>
                    </div>
                  </div>
                </td>
                <td class="bold">A Bold Line</td>
                <td>A Date</td>
                <td>
                  <div class="flex-container align-top">
                    <div class="flex-child-shrink">
                      <img class="dashboard-table-image" src="https://lorempixel.com/50/50/people/">
                    </div>
                    <div class="flex-child">
                      <h6 class="dashboard-table-text">Another person did something</h6>
                      <span class="dashboard-table-timestamp">03/08/2017</span>
                    </div>
                  </div>
                </td>
                <td class="listing-inquiry-status">
                
                  <div class="flex-container align-top">
                    <div class="flex-child-shrink">
                      <img class="dashboard-table-image" src="https://lorempixel.com/25/25/abstract/">
                    </div>
                    <div class="flex-child">
                      <h6 class="dashboard-table-text"><a href="#">A longer wrapping item with an image that is aligned to the top</a></h6>
                    </div>
                  </div>
                
                </td>
              </tr>

              <tr>
                <td>Single Line</td>
                <td>
                  <div class="flex-container align-justify align-top">
                    <div class="flex-child-shrink">
                      <img class="dashboard-table-image" src="https://lorempixel.com/50/50/people/">
                    </div>
                    <div class="flex-child-grow">
                      <h6 class="dashboard-table-text">A Person</h6>
                      <span class="dashboard-table-timestamp">03/04/2017</span>
                    </div>
                  </div>
                </td>
                <td class="bold">A Bold Line</td>
                <td>A Date</td>
                <td>
                  <div class="flex-container align-top">
                    <div class="flex-child-shrink">
                      <img class="dashboard-table-image" src="https://lorempixel.com/50/50/people/">
                    </div>
                    <div class="flex-child">
                      <h6 class="dashboard-table-text">Another person did something</h6>
                      <span class="dashboard-table-timestamp">03/08/2017</span>
                    </div>
                  </div>
                </td>
                <td class="listing-inquiry-status">
                
                  <div class="flex-container align-top">
                    <div class="flex-child-shrink">
                      <img class="dashboard-table-image" src="https://lorempixel.com/25/25/abstract/">
                    </div>
                    <div class="flex-child">
                      <h6 class="dashboard-table-text"><a href="#">A longer wrapping item with an image that is aligned to the top</a></h6>
                    </div>
                  </div>
                
                </td>
              </tr>

              
            </tbody>
          </table> -->

          <div style="width: 90%;margin: 0 auto;border: 1px solid rgb(138, 132, 132);border-radius: 10px;padding: 10px;" >
<table class="table caption-top" >
            <caption>List of users</caption>
            <thead class="table-dark" >
              <tr style="text-align: center;">
                <th scope="col" style="width: 25%;text-align: center;">#</th>
                <th scope="col" style="width: 25%;text-align: center;">Nombre</th>
                <th scope="col" style="width: 25%;text-align: center;">Ruc</th>
                <th scope="col" style="width: 25%;text-align: center;"><button type="button" class="btn btn-primary" style="width: 260px;">Nuevo</button></th>
              </tr>
            </thead>
            <tbody style="text-align: center;">
              <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td><button type="button" style="margin-right: 10px;" class="btn btn-warning">Editar</button><button type="button" class="btn btn-danger">Inhabilitar</button></td>
              </tr>
              <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td><button type="button" style="margin-right: 10px;" class="btn btn-warning">Editar</button><button type="button" class="btn btn-danger">Inhabilitar</button></td>
              </tr>
              <tr>
                <th scope="row">3</th>
                <td>Larry</td>
                <td>the Bird</td>
                <td><button type="button" style="margin-right: 10px;" class="btn btn-warning">Editar</button><button type="button" class="btn btn-danger">Inhabilitar</button></td>
              </tr>
            </tbody>
          </table>
          </div>
          

          <div id="espbotones">
            <button type="button" class="btn btn-primary">Nuevo</button>
            <button type="button" class="btn btn-primary">Editar</button>
            <button type="button" class="btn btn-primary">Borrar</button>

          </div>
          
        </form> 
        
    </body>
</html>
