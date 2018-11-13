/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad.kerberos;

import objetos.ticket;

/**
 *
 * @author luis
 */
public class controladorTicket extends controladores.controladorBase{

    private seguridad.asimetrico.encriptado asim = new seguridad.asimetrico.encriptado();
    

    public String genera(String accesKey, String token, String ip) {
        
        return JSON.toJson(new ticket("", "", accesKey , "", token , ip));
    }

    public String valida(String accesKey, String token, String ip) {
        return "";
    }

    public String elimina(String accesKey, String token, String ip) {
        return "";
    }

}
