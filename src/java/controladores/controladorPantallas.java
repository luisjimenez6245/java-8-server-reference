/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.google.gson.Gson;
import extras.logger;
import objetos.contenedorAjax;
import objetos.usuario;

/**
 *
 * @author luis
 */
public class controladorPantallas {

    protected String posicionRelativa;
    protected usuario _usuario;
    protected controladorBD base;
    protected boolean tipoContenido;
    protected String respuesta  = "";
    protected Object objetoPantalla;
    protected controladorNavBar  nav;
    protected logger errores =  new logger();
    protected String pagina = "";
    private Gson json = new Gson();
    
    private controladorContenido contenido;
    

    public controladorPantallas(String posicionRelativa, usuario _usuario) {
        this.posicionRelativa = posicionRelativa;
        this._usuario = _usuario;
    }

    public controladorPantallas(String posicionRelativa, usuario _usuario, boolean tipoContenido) {
        this.posicionRelativa = posicionRelativa;
        this._usuario = _usuario;
        this.tipoContenido = tipoContenido;
    }
    
    private String traeContenido(){
        contenido = (controladorContenido) objetoPantalla;
        if(tipoContenido){
            return contenido.crear();
        }
        else{
            contenedorAjax obj = new contenedorAjax();
            obj.setContenido(pagina);
            obj.setAlertas(pagina);
            obj.setJs(pagina);
            obj.setOculto(pagina);
            return json.toJson(obj);
        }
    }
     
    protected String movil(){
        return  "";
    }
    protected  String web(){
        return "";
    }

    public String getRespuesta() {
        return respuesta;
    }
    
    public String getPosicionRelativa() {
        return posicionRelativa;
    }

    public void setPosicionRelativa(String posicionRelativa) {
        this.posicionRelativa = posicionRelativa;
    }

    public usuario getUsuario() {
        return _usuario;
    }

    public void setUsuario(usuario _usuario) {
        this._usuario = _usuario;
    }

    public controladorBD getBase() {
        return base;
    }

    public void setBase(controladorBD base) {
        this.base = base;
    }

    public boolean isTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(boolean tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

}
