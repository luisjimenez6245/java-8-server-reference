/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

import controladores.controladorCuenta;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author luis
 */
@WebServlet(name = "email", urlPatterns = {"/email/*"})
public class controladorCorreos extends controladores.controladorServlet {

    @Override
    protected String web() {
        String res= "";
        if(session.getAttribute("email") ==  null)
             res = controlador();
        else{
            response.setStatus(403);
        }
        return res;
    }

    private String controlador() {
        controladorCuenta control = new controladorCuenta();
        int opcion = 0;
        try {
            opcion = Integer.parseInt(request.getParameter("opcion") == null ? "0" : request.getParameter("opcion"));
        } catch (NumberFormatException ex) {
            errores.error(ex);
        }

        String _email = request.getParameter("email") == null ? "0" : request.getParameter("email");
        if (opcion == 1) {
           control.existenciaCorreo(_email);
           respuesta = control.getContendor();
        } 
        return respuesta;
    }

}
