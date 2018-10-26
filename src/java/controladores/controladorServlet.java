/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.google.gson.Gson;
import extras.logger;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objetos.contenedorAjax;
import objetos.objetoVentana;
import objetos.usuario;

/**
 *
 * @author luis
 */
public class controladorServlet extends HttpServlet {

    protected String direccion;
    protected String respuesta;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    protected String pagina;
    protected String js = "";

    protected short tipoRequest;
    protected short posicionServidor = 0;

    protected boolean tipoContenido;
    protected controladorBD base;
    protected usuario _usuario;
    protected Gson json = new Gson();

    protected logger errores = new logger();
    protected int idVentana = 0;
    

    public controladorServlet() {
        base = new controladorBD();
        _usuario = new usuario();
    }

    protected void processRequest() throws ServletException, IOException {
        idVentana = 0;
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String dispositivo = request.getHeader("movil") == null ? "0" : request.getHeader("movil");
        tipoContenido = dispositivo.equals("0");

        if (tipoContenido) {
            session = request.getSession(true);
            obtenDireccion(request.getRequestURI());
            response.setContentType("text/html;charset=UTF-8");

            if (calculaContenido()) {
            } else {
                // response.setContentType("application/json;charset=UTF-8");
            }
            respuesta = web();
        } else {
            respuesta = movil();
        }
        System.out.println(idVentana);
        try (PrintWriter out = response.getWriter()) {
            if (response.getStatus() >= 200 && response.getStatus() <= 204) {
                out.print(respuesta);
            } else {
                out.print("Sin Accceso" + response.getStatus());
            }
        } catch (Exception ex) {
            errores.error(ex);
        }

        this.destroy();

    }

    private boolean calculaContenido() {
        System.out.println(pagina);
        if (!(request.getParameter("pagina") == null)) {
            tipoContenido = true;
            pagina = request.getParameter("pagina") == null ? "" : request.getParameter("pagina");
            return false;
        } else {
            tipoContenido = false;
            return true;
        }
    }

    protected String movil() {
        // considera la utilizacion de webservice como el método principal
        return "";
    }

    protected String web() {
        return "";
    }

    private boolean obtenDireccion(String url) {
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
            if (contenedor.length == posicionServidor) {
            } else {
            }
            creaDireccion();
            pagina = pagina == null ? "" : pagina;
            return true;
        } catch (Exception ex) {
            errores.error(ex);
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

    protected String contendioWEB(objetoVentana control, int status) {
        control.html = true;
        if (tipoContenido) {
            contenedorAjax ajax = new contenedorAjax();
            ajax.setContenido(control.crearWEB());
            ajax.setJs(control.JS() + js);
            ajax.setAlertas("");
            ajax.setOculto("");
            return json.toJson(ajax);
        } else {
            controladorNavBar nav = new controladorNavBar();
            controladorContenido contenido = new controladorContenido();
            contenido.setContenido(control.crearWEB());
            contenido.setNav(nav.costruye(true, 1, (int) _usuario.getIdUsuario(), (int) _usuario.getIdPersona(), idVentana, pagina, status));
            contenido.setNoJS("");
            contenido.setOculto("");
            contenido.setTitulo("MEDICALL");
            contenido.setScripts(javaScript());
            contenido.setScriptsFinal(js);
            contenido.setCss("<link rel='stylesheet' type='text/css' href='" + direccion + "CSS/general.css'>\n");
            return contenido.crear();
        }
    }

    protected String javaScript() {
        String resultado =  "<script src = '/direccion/JS/jQuery.js'></script>\n"
                + "<script src = '/direccion/JS/jquery.waypoints.min.js'></script>\n"
                + "<script src = '/direccion/JS/jquery.scrollify.js'></script>\n"
                + "<script src = '/direccion/JS/flowtype.js'></script>\n"
                + "<script src = '/direccion/JS/main.js'></script>\n"
                + "<script> \n paginaMostrada = " + idVentana + "; \n</script> \n";


        return         resultado.replace("/direccion/", direccion);

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
        try {
            this.request = request;
            this.response = response;
            processRequest();
        } catch (IOException | ServletException ex) {
            System.out.println("Error doGet: " + ex.toString());
        }
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
        try {
            this.request = request;
            this.response = response;

            processRequest();
        } catch (IOException | ServletException ex) {
            System.out.println("Error doPost: " + ex.toString());
        }
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

}
