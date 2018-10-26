/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import objetos.usuario;

/**
 *
 * @author luis
 */
public class controladorAlertas {
    
    private controladorBD base;
    private usuario _usuario;
    
    public controladorAlertas(){
        base = new controladorBD();
        _usuario = new usuario();
    }
    public controladorAlertas(usuario _usuario){
        this._usuario = _usuario;
        base = new controladorBD();
    }
    
    public controladorAlertas(usuario _usuario, controladorBD base){
        this._usuario = _usuario;
        this.base = base;
    }
    
    
    public void mandaNotificaciones(){
        
    }
    
    public String getNotificaciones(){
        String res = "";
        return res;
    }

    public controladorBD getBase() {
        return base;
    }

    public void setBase(controladorBD base) {
        this.base = base;
    }

    public usuario getUsuario() {
        return _usuario;
    }

    public void setUsuario(usuario _usuario) {
        this._usuario = _usuario;
    }
    
  
}
