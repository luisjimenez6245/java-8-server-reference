/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.objetos;

import objetos.Dao.pacienteDao;
import objetos.Dao.usuarioDao;
import objetos.paciente;
import objetos.usuario;

/**
 *
 * @author luis
 */
public class pacientes extends controladores.controladorBase {

    public String nuevoPaciente(usuario _usuario, paciente pac) {
        usuarioDao dao = new usuarioDao();
        
        if(_usuario != null){
            _usuario =  dao.subir(_usuario, null);
            return dao.tipo != 0 ? JSON.toJson(_usuario): dao.getSP();
        }
        return "";
    }

    public String modificaPaciente(usuario _usuario, paciente pac) {
        pacienteDao dao = new pacienteDao();

        return "";
    }

    public String datosPaciente(usuario _usuario, paciente pac) {
        pacienteDao dao = new pacienteDao();

        return "";
    }

    public String historialMedico(usuario _usuario, paciente pac) {
        pacienteDao dao = new pacienteDao();

        return "";
    }

    public String eliminaPaciente(usuario _usuario, paciente pac) {
        pacienteDao dao = new pacienteDao();

        return "";
    }

    private float calculaIMC(float peso, float altura){
        return (float)0.0;
    }
    
    private short calculaEdad(String fechaNacimiento){
        return 0;
    }

}
