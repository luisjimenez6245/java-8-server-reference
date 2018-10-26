/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import javax.servlet.annotation.WebServlet;
import objetos.objetoVentana;
import org.owasp.esapi.ESAPI;
import ventanas.inicio.ventanaInicio;
import ventanas.inicio.ventanaLogin;
import ventanas.inicio.ventanaSoyMedico;

/**
 *
 * @author luis
 */
@WebServlet(name = "registro", urlPatterns = {"/registro/*"})

public class registro  extends controladores.controladorServlet {
    @Override
    protected String web() {
        String resultado = contendioWEB(calcula(), 3);
        return resultado;
    }

    private objetoVentana calcula() {
        objetoVentana control;
        Object ventana;
        js= "";
        if (pagina.contains("especialista") || pagina.equals("11")) {
            idVentana = 11;
            ventana = new ventanas.inicio.ventanaFormularioMedico(tipoContenido, direccion);
        } else if (pagina.contains("exito") || pagina.equals("13")) {
            idVentana = 13;
            js = ESAPI.encoder().encodeForHTML("<script>\n"
                    + "$('#principalNav').removeClass('principalNav');\n"
                    + "$('#principalNav').addClass('navLogin');\n"
                    + "</script>\n");
            ventana = new ventanaLogin(tipoContenido, direccion);
        } else {
            idVentana = 10;
            ventana = new ventanas.inicio.ventanaFormularioPaciente(tipoContenido, direccion);
        }

        control = (objetoVentana) ventana;
        return control;
    }
    
}
