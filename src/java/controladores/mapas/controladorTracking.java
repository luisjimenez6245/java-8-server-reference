/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.mapas;

import controladores.controladorBD;

/**
 *
 * @author luis
 */
public class controladorTracking {

    private controladorBD base = new controladorBD();

    public String sube(String lan, String lon) {
        try {
            base.conectar();
            base.insertar("insert into tbl_track (idUsuario, lat, lon) values (1,'"+lan+"','"+ lon+"');");
            base.cierraConexion();
        } catch (Exception e) {
        };
        return "";
    }

}
