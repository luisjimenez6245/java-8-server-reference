/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controladores.interfaz.*;
import controladores.seguridad.acceso;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.dispositivo;
import objetos.nav;
import objetos.usuario;

/**
 *
 * @author luis
 */
@WebServlet(name = "index", urlPatterns = {"/index/*"})
public class index extends controladores.controladorServlet {

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {
        controladorNavegacion control = new controladorNavegacion();
        if (request.getParameter("id_catalogo") == null) {
            if (!pagina.equals("") && request.getParameter("id_nav") == null) {
                respuesta = control.verficaPermiso(new nav(0L, direccion, pagina, "", 0, 0), request.getParameter("id_usuario") == null ? null : new usuario().parse(request));
            } else {
                if (request.getParameter("id_nav") == null) {
                    respuesta = control.traeAutorizados(request.getParameter("id_usuario") == null ? null : new usuario().parse(request));
                } else {
                    respuesta = control.verficaPermiso(new nav().parse(request), request.getParameter("id_usuario") == null ? null : new usuario().parse(request));
                }
            }
        } else {
            respuesta = control.traeAntecedentes();
        }

    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        acceso acc = new acceso();
        if (request.getParameter("dispositivo_token") != null) {
            respuesta = acc.porDispositivo(new dispositivo().parse(request), request.getParameter("token"), request.getRemoteHost());
        } else {
            if (request.getParameter("acc") != null) {
                respuesta = acc.porUsuario(new usuario().parse(request), request.getParameter("token"), request.getRemoteHost());
            } else {
                if (request.getParameter("id_usuario") != null) {
                    respuesta = acc.porUsuario(new usuario().parse(request), request.getParameter("token"), request.getRemoteHost());
                }
            }
        }
        resStatus = respuesta.equals("") ? 403 : 0;
    }

    @Override
    public void put(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {

    }

}
