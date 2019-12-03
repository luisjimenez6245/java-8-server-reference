<%-- 
    Document   : log
    Created on : Apr 9, 2019, 1:10:50 PM
    Author     : luis
--%>

<%@page import="controllers.security.manager"%>
<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    manager man = manager.getInstance();
    user
    User user;
    if (request.getParameter("ex") == null) {
        if (request.getParameter("aaa") != null) {
           user = man.getEncryptedUser(request);
        } else {
           user = man.getUser(request);
        }
        if (user != null) {
            session.setAttribute("x", user.id);
            session.setAttribute("a", user.name);
            session.setAttribute("r", user.email);
            session.setAttribute("t", user.type);
            session.setAttribute("token", user.id);
            if (user.type.equals("Type2")) {
                response.sendRedirect(man.URL + "/participantes/");
            } else {
                response.sendRedirect(man.URL + "/mentores/");
            }
        } else {
            session.invalidate();
            response.sendRedirect(man.URL + "/login");
        }
    } else {
        session.invalidate();
        response.sendRedirect(man.URL + "/login");
    }

%>
