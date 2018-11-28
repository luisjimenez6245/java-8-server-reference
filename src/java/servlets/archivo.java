/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import extras.archivos;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet(name = "archivo", urlPatterns = {"/archivo/*"})
public class archivo extends controladores.controladorServlet {

    private String contentType;
    private byte[] archivo;

    public archivo() {
        this.archivo = null;
    }

    @Override
    protected void envia(HttpServletResponse response) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType((contentType == null || contentType.equals("")) ? "application/json;charset=UTF-8" : contentType);
        resStatus = resStatus != 0 ? resStatus : archivo == null ? 404 : 0;
        if (respuesta.equals("")) {
            if (archivo != null && resStatus == 0) {
                OutputStream os = response.getOutputStream();
                os.write(archivo);
                os.flush();
                os.close();
            } else {
                response.sendError(resStatus);
            }
        } else {
            PrintWriter out = response.getWriter();
            if (!respuesta.equals("null")) {
                out.print(respuesta);
            } else {
                response.sendError(400);
            }
        }
        this.destroy();
    }

    @Override
    protected void get(HttpServletRequest request, HttpServletResponse response) throws Exception {
        extras.archivos arch = new extras.archivos();
        if (!pagina.equals("")) {
            archivo = arch.verDeBase(pagina);
            contentType = arch.contentType;
        } else {
            if (request.getParameter("id") != null) {
            } else {
                resStatus = 403;
            }
        }
    }

    @Override
    protected void post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getPart("archivo") != null) {
            archivos control = new archivos();
            respuesta = control.subir(request.getParameter("nombre") == null ? "" : request.getParameter("nombre"), request.getPart("archivo"), request.getRealPath(pagina) + "/carpeta/");

        } else {
            respuesta = "no hay archivo";
        }
    }

    @Override
    protected void put(HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
