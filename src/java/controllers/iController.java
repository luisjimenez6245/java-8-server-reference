/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import controllers.security.logger;

/**
 *
 * @author luis
 */
public abstract class iController {
    
    
    public Gson JSON = new Gson();
    protected final logger ERRORES = new logger();

}
