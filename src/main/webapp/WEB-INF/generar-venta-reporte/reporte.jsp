<%@page import="java.io.OutputStream"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.untels.zenidscrum.modelo.bean.*"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.StringReader" %>
<%@page import="com.lowagie.text.html.simpleparser.HTMLWorker"%>
<%@page import="com.lowagie.text.pdf.PdfWriter" %>
<%@page import="com.lowagie.text.*" %>
<%@page import="com.lowagie.text.pdf.PdfPTable" %>
<%@page import="java.time.LocalDate" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>

        <%
            List<Venta> ventita = (ArrayList<Venta>) request.getAttribute("ventasrep");
            String fech = LocalDate.now().toString();
            //Proforma proforma = (Proforma) request.getAttribute("proforma");
%>
    </head>
    <body >
        <div style="margin:10px" id="todo">
            <h3 style="float:right" > <%=LocalDate.now()%></h3>
            <h1 style="margin: 10px" >Reporte</h1>
            <table class="table">
                <thead class="table-dark">
                    <tr>
                        <th>Id</th>
                        <th>Nombre referencial</th>
                        <th>Tipo Comprobante</th>
                        <th>Fecha emisión</th>
                        <th>Total</th>

                    </tr>
                </thead>
                <tbody>
                    <%for (Venta v : ventita) {%>
                    <tr>
                        <td><%=v.getIdVenta()%></td>
                        <td><%=v.getNombres()%> <%=v.getApePaterno()%> <%=v.getApeMaterno()%></td>
                        <td><%=v.getTipoComprobante()%></td>
                        <td><%=v.getFechaEmision()%></td>
                        <td>

                    </tr>
                    <% }%>
                </tbody>
            </table>

        </div>
        <a class="btn btn-dark" onclick="generar()" role="button" style="margin-left: 10px" >Generar PDF</a>
        <script>
            function generar() {
                const element = document.getElementById("todo");
                html2pdf().from(element).save("Reporte de Ventas");
            }
            ;
        </script>
    </body>
</html>
