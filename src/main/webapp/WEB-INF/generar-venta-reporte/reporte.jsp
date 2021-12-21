
<%@page import="java.sql.Date"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.StringReader" %>
<%@page import="com.lowagie.text.html.simpleparser.HTMLWorker"%>
<%@page import="com.lowagie.text.pdf.PdfWriter" %>
<%@page import="com.lowagie.text.*" %>
<%@page import="com.lowagie.text.pdf.PdfPTable" %>




<%
    response.setContentType("application/pdf");
    response.setHeader("Content-Type",
            "application/pdf");
    try {
        Document documento = new Document();
        PdfWriter.getInstance(documento, response.getOutputStream());
        documento.open();
        documento.addAuthor("LAS XIKIS");
        documento.addCreator("LAS XIKIS");
        documento.addSubject("REPORTE");
        documento.addCreationDate();
        documento.addTitle("EL MENEITO");

        documento.add(new Paragraph("DOCUEMTNO DE PRUEBA XDDDDDDDDDDDDDDDDD"));
        //documento.add(new Paragraph(new Date().toString());
        documento.add(new Paragraph("\n"));

        documento.close();

    } catch (Exception e) {
    }
%>
