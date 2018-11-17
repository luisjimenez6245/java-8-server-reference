/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.seguridad.logger;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@MultipartConfig(maxFileSize = 16177215)
public class controladorServlet extends HttpServlet {

    protected String direccion;
    protected String respuesta;
    protected String pagina;

    protected short posicionServidor = 0;
    protected int resStatus = 0;

    protected final logger ERRORES = new logger();

    public controladorServlet() {

    }

    private void envia(HttpServletResponse response) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        resStatus = resStatus != 0 ? resStatus : respuesta.equals("") ? 400 : 0;
        if (respuesta != null && resStatus == 0) {
            if (!respuesta.equals("null")) {
                out.print(respuesta);
            } else {
                response.sendError(400);
            }
        } else {
            response.sendError(resStatus);
        }
        this.destroy();
    }

    private boolean obtenDireccion(String url) {
        respuesta = "";
        resStatus = 0;
        if (url.startsWith("/")) {
            url = url.substring(1, url.length());
        }
        try {
            String[] contenedor = url.split("/");
            posicionServidor = calculaDiagonal(url);
            if (contenedor.length - 1 > 0) {
                if (contenedor.length - 1 > 1) {
                    pagina = contenedor[2];
                } else {
                    pagina = contenedor[1];
                }

            } else {
                pagina = "";
            }
            creaDireccion();
            pagina = pagina == null ? "" : pagina;
            pagina = pagina.replace(this.getServletName(), "");
            return true;
        } catch (Exception ex) {
            ERRORES.error(ex);
            return false;
        }
    }

    private void creaDireccion() {
        int contador = 2;
        direccion = "";
        while (contador <= posicionServidor) {
            direccion += "../";
            ++contador;
        }
    }

    private short calculaDiagonal(String url) {
        short resultado = 0;
        for (char _a : url.toCharArray()) {
            if (_a == '/') {
                ++resultado;
            }
        }
        return resultado;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        metodo(request, response, 1);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        metodo(request, response, 2);
    }

    /**
     * Handles the HTTP <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        metodo(request, response, 3);
    }

    /**
     * Handles the HTTP <code>Delete</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        metodo(request, response, 4);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>

    private void metodo(HttpServletRequest request, HttpServletResponse response, int numero) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            obtenDireccion(request.getRequestURI());
            switch (numero) {
                case 1:
                    get(request, response);
                    break;
                case 2:
                    post(request, response);
                    break;
                case 3:
                    put(request, response);
                    break;
                case 4:
                    delete(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            resStatus = 400;
            ERRORES.error(ex);
        }
        envia(response);
    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void put(HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
