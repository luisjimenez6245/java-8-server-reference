/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.Dao;

import java.util.List;
import objetos.dispositivo;
import objetos.usuario;

/**
 *
 * @author luis
 */
public class dispositivoDao  extends iDao<dispositivo, Long> {

    @Override
    public dispositivo subir(dispositivo objeto, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public dispositivo modificar(dispositivo objeto, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public dispositivo traerDetalles(dispositivo objeto, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<dispositivo> traer(usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long borrar(Long llave, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
