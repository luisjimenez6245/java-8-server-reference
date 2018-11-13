/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.seguridad;

import extras.enviarEmail;
import java.util.Base64;
import java.util.Date;


/**
 *
 * @author luis
 */
public class recuperacion {
    
    private String key = "sagdhjsadhjsadhjs<";
    private String email;
    private final Date day = new Date();
    private String server  = "";
   
    
    public void recupera(String email){
        if(bd()){
            mandaEmail(generaLlave());
        }
        else{
            mandaEmail(key);
        }
    }
    
    
    private String generaLlave(){
        String llave = day.toString() + email + key;
        return Base64.getEncoder().encodeToString(llave.getBytes());
    }
    

    
    private boolean bd(){
        
        return true;
    }
    
    private boolean mandaEmail(String publicKey){
        String msj = "" + server + publicKey + "";
        enviarEmail  obj = new enviarEmail(msj , email, "Recuperación de tu cuenta");
        return obj.mandaEmail();
    }
    
    
}
