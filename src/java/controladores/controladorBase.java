/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.google.gson.Gson;
import controladores.seguridad.logger;

/**
 *
 * @author luis
 */
public abstract class controladorBase {
    
    
    public Gson JSON = new Gson();
    protected final logger ERRORES = new logger();

}
