/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.firebase;


/**
 *
 * @author luis
 */
public class controladorNotificaciones {

    public controladorNotificaciones(int tipoNotifiacion, String contenidoMensaje, String titulomMsj) {
        this.tipoNotifiacion = tipoNotifiacion;
        this.contenidoMensaje = contenidoMensaje;
        this.titulomMsj = titulomMsj;
    }
    
    private int tipoNotifiacion;
    private String UDI_Dispositivo;
    private String contenidoMensaje;
    private String titulomMsj;

    public controladorNotificaciones(){
        
    }
    
    
}
