/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.firebase;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author luis
 */
public class conectaFirebase {

    private final String SERVER = "https://fcm.googleapis.com/fcm/send";
    private final String llave = "AAAAdMhZSFc:APA91bFTCaCcDZ1I8-OO6cvqyr0Sb75Qi9i7t_YjW6wiYkfcv0znPe0h5ypug8y5CoSDjYwRzPKTXE8VqyZZbVdOvEOaYRvN5xHywsRCySJvPfA2Pyvg1YPiCT4tQyioYodPAZk_E384";

    private String error;
    private String aEnviar;
    private String respuesta;
    
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getaEnviar() {
        return aEnviar;
    }

    public void setaEnviar(String aEnviar) {
        this.aEnviar = aEnviar;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    public boolean llamada(String aEnviar){
        this.aEnviar = aEnviar;
        return llamada();
    }

    public boolean llamada() {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(SERVER);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "key=" + llave);
            conn.setRequestProperty("Content-Type", "application/json");
            DataOutputStream dStrem = new DataOutputStream(conn.getOutputStream());
            dStrem.write(aEnviar.getBytes(StandardCharsets.UTF_8));
            InputStream in = new BufferedInputStream(conn.getInputStream());
            return streamToString(in);
        } catch (IOException ex) {
            error =  ex.toString();
            return false;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private boolean streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            is.close();
            respuesta = sb.toString();
            return true;
        } catch (IOException ex) {
            error =  ex.toString();
            return false;
        }
    }

}
