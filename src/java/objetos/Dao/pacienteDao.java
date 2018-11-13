/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.Dao;

import java.util.List;
import objetos.paciente;
import objetos.usuario;

/**
 *
 * @author luis
 */
public class pacienteDao extends iDao<paciente, Long>{

    @Override
    public paciente subir(paciente objeto, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public paciente modificar(paciente objeto, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public paciente traerDetalles(paciente objeto, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<paciente> traer(usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long borrar(Long llave, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
