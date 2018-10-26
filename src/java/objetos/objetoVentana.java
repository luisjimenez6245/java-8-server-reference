/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import controladores.controladorBD;
import extras.logger;

/**
 *
 * @author luis
 */
public class objetoVentana {

    public boolean html;
    protected String clase;
    protected String primeraParte;
    protected String segundaParte;
    protected String terceraParte;
    protected String cuartaParte;
    protected String quintaParte;
    protected String alertas;
    protected String JS;
    protected String puntos = "";
    protected String posicionRelativa;
    protected logger errores =  new logger();

    protected usuario usuario;

    public objetoVentana(String posicionRelativa, usuario usuario) {
        this.posicionRelativa = posicionRelativa;
        this.usuario = usuario;
    }

    public objetoVentana(boolean html, String posicionRelativa) {
        this.html = html;
        this.posicionRelativa = posicionRelativa;
    }

    public objetoVentana(boolean html, String posicionRelativa, usuario usuario) {
        this.html = html;
        this.posicionRelativa = posicionRelativa;
        this.usuario = usuario;
    }
    protected controladorBD base = new controladorBD();

    public String crearWEB() {
        String respuesta = "";
        if (html) {
            respuesta = "<div class='contenedorPrincipal' id='contenedorPrincipal'>";
        }
        respuesta += "<div class='" + clase + "'>\n" + primeraParte + segundaParte + terceraParte + cuartaParte + quintaParte + "</div>";
        if (html) {
            respuesta += "</div>";
        }
        return respuesta;
    }

    public String JS() {
        return JS;
    }

}
