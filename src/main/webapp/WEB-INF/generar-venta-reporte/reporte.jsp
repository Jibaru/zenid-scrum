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

<%
    List<Venta> ventita = (ArrayList<Venta>) request.getAttribute("ventasrep");
    String fech = LocalDate.now().toString();

    Proforma proforma = (Proforma) request.getAttribute("proforma");
%>
<%    response.setHeader("Content-Type",
            "application/pdf");
    try {
        Document documento = new Document();
        OutputStream outs = response.getOutputStream();
        PdfWriter.getInstance(documento, outs);
        documento.open();
        documento.addSubject("Reporte de ventas");
        documento.addCreationDate();
        documento.addTitle("Reporte de ventas");
        documento.add(new Paragraph("Reporte de ventas"));
        //documento.add(new Paragraph(new Date().toString());
        documento.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(4);
        table.addCell("Id");
        table.addCell("Tipo");
        table.addCell("Proforma");
        table.addCell("Fecha");

        for (Venta v : ventita) {
            String numero = v.getNumeroComprobante().toString();
            String tipo = v.getTipoComprobante().toString();
            String nombre = v.getNombres().toString();
            String fecha = v.getFechaEmision().toString();
            table.addCell(numero);
            table.addCell(tipo);
            table.addCell(nombre);
            table.addCell(fecha);
        }

        documento.add(table);
        documento.close();
        documento.add(new Paragraph(fech));

    } catch (Exception e) {
    }
%>
