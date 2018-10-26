/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

import controladores.controladorBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class logger {

    private Exception ex;

    public void error(Exception ex) {
        this.ex = ex;
        guardaExcepcion();
        //mandaEmail();
        imprimeExcepcion();
    }
    
    public void error(Exception ex,controladorBD base ) {
        this.ex = ex;
        try{
        base.cierraConexion();
        }
        catch(Exception e){
        }
        guardaExcepcion();
        //mandaEmail();
        imprimeExcepcion();
    }

    public void errorEmail(Exception ex) {
        this.ex = ex;
        guardaExcepcion();
        imprimeExcepcion();
    }

    public void errorBd(Exception ex) {
        this.ex = ex;
        mandaEmail();
        imprimeExcepcion();
    }

    private void guardaExcepcion() {
        controladorBD base = new controladorBD();
        try {
            base.conectar();
            try (PreparedStatement llamada = base.getCall("call sp_RegistroErrores(?,?,?,?,?,?,?,?,?,?,?);")) {
                llamada.setInt(1, 1);
                llamada.setString(2, ex.toString());
                llamada.setString(3, ex.getClass().getName());
                llamada.setString(4, ex.getLocalizedMessage());
                llamada.setString(5, ex.getMessage());
                llamada.setString(6, Arrays.toString(ex.getStackTrace()));
                llamada.setString(7, ex.fillInStackTrace().getMessage());
                llamada.setString(8, ex.fillInStackTrace().getLocalizedMessage());
                llamada.setString(9, "" );
                llamada.setString(10,  "");
                llamada.setString(11,  "");
                base.sinRes(llamada);
                llamada.close();
                
            }
            base.cierraConexion();
            
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            this.ex = e;
            mandaEmail();
            Logger.getLogger(ex.getClass().getName()).log(Level.SEVERE, null, ex);
            imprimeExcepcion();
        } finally {

        }
    }

    private String mandaEmail() {

        new Thread(new Runnable() {
            public void run() {
                enviarEmail email = new enviarEmail(getExcepcion(), "luisjimenez6245@hotmail.com", "Error");
                if (!email.mandaEmail()) {
                    Logger.getLogger(ex.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

        return "";
    }

    private String getExcepcion() {
        return ex.getLocalizedMessage() + "\n" + ex.getMessage() + "\n" + ex.toString() + "\n" + Arrays.toString(ex.getStackTrace()) + "\n" + ex.fillInStackTrace() + "\n" + ex.getCause();
    }

    private void imprimeExcepcion() {
        System.out.println(ex.getLocalizedMessage());
        System.out.println(ex.getMessage());
        System.out.println(ex.toString());
        System.out.println(Arrays.toString(ex.getStackTrace()));
    }

    private static class cc {

    }

}
