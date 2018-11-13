/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import controladores.objetos.pacientes;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.usuario;


/**
 *
 * @author luis
 */
@WebServlet(name = "paciente", urlPatterns = {"/paciente/*"})
public class paciente extends controladores.controladorServlet {
    
    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        
    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        controladores.objetos.pacientes control =  new pacientes();
        respuesta = control.nuevoPaciente(new usuario().parse(request), null);
        
    }

    @Override
    public void put(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        
    }

}
