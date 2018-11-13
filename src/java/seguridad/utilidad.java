/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

/**
 *
 * @author luis
 */
public class utilidad {

    public boolean validarEmail(String email) {
        try {
            String[] emailDividido = email.split("@");
            return emailDividido[1].contains(".");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validarDispositivoToken(String token) {
        return ((token.length() - 1) > 20);
    }
    
    public boolean validaTelefono(String telefono){
        return true;
    }
    
    public boolean validaToken(String token){
        return true;
    }
    
    public boolean  validaIP(String ip){
        return true;
    }

}
