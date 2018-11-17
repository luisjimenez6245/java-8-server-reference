/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import extras.archivos;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis
 */
@WebServlet(name = "altas", urlPatterns = {"/altas/*"})
public class altas extends controladores.controladorServlet {

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {
    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // las altas del form siempre se van a ejecutar aquí
        // mejor hagan una clase para que funcione mejor y que se ordene xd 
        if (request.getPart("archivo") != null) {
            archivos control = new archivos();
            respuesta = control.subir(request.getParameter("nombre") == null ? "" : request.getParameter("nombre"), request.getPart("archivo"), request.getRealPath(pagina) + "/");
        } else {
            respuesta = "no hay archivo";
        }

    }

    @Override
    public void put(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {

    }

    

}
