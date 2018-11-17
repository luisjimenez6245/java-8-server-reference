<%-- 
    Document   : index
    Created on : Nov 17, 2018, 1:27:17 AM
    Author     : luis
--%>
<%@page import="controladores.seguridad.logger"%>
<%@page import="java.io.OutputStream"%>
<%@page import="extras.archivos"%>
<%
    String id = request.getParameter("id") == null ? "" : request.getParameter("id");
    archivos control = new archivos();
    byte archivo[] = control.verDeBase(id);
    if (archivo != null) {
        try {
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            os.write(archivo);
            os.flush();
            os.close();
        } catch (Exception ex) {
            new logger().error(ex);
        }
    }
    else{
    }
%>