/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.seguridad;

import controladores.controladorBD;
import extras.enviarEmail;
import java.sql.SQLException;
import java.util.ArrayList;
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
        mandaEmail();
        imprimeExcepcion();
    }

    public void error(Exception ex, controladorBD base) {
        this.ex = ex;
        try {
            base.cierraConexion();
        } catch (Exception e) {
        }
        guardaExcepcion();
        mandaEmail();
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
            ArrayList<String> param = new ArrayList<>();
            param.add("1");
            param.add(ex.toString());
            param.add(ex.getClass().getName());
            param.add(ex.getLocalizedMessage());
            param.add(ex.getMessage());
            param.add(Arrays.toString(ex.getStackTrace()));
            param.add(ex.fillInStackTrace().getMessage());
            param.add(ex.fillInStackTrace().getLocalizedMessage());
            param.add("1");
            param.add("1");
            param.add("1");
            base.preparedS("call sp_RegistroErrores(?,?,?,?,?,?,?,?,?,?,?);", param.toArray(new String[param.size()]));
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
