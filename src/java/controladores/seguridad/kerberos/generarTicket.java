/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.seguridad.kerberos;

import com.google.gson.Gson;
import controladores.seguridad.asimetrico.encriptado;
import java.util.Calendar;

/**
 *
 * @author luis
 */
public class generarTicket {

    private final Gson json = new Gson();
    private final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

    private String identificador;
    private String ip;

    public generarTicket() {

    }

    public String generar(String usuario) {
        String resultado = "";

        if (resultado.equals("1")) {
            resultado = generarTicket(usuario);

        }
        return resultado;
    }

    private String generarTicket(String usuario) {
        String claveS = "AFgSaas64G-swpñopwq3/saw";
        String resultado;

        if (usuario != null) {
            String tiempo = "";
            String key = crear();
            usuario = usuario.substring(0, 7);
            String relleno = generarRelleno();
            usuario = rellenar(usuario, relleno);
            ticket[] contenedor = new ticket[2];
            contenedor[0] = construir(usuario, key, tiempo, relleno, claveS);
            contenedor[1] = construir(usuario, key, tiempo, relleno, usuario);
            resultado = json.toJson(contenedor);
        } else {

            resultado = "hola";

        }
        return resultado;
    }

    private String deAsimetrico(String ade) {
        encriptado asi = new encriptado();
        return asi.desencripta(ade) ? asi.getCadena() : "";
    }

    private ticket construir(String usuario, String sessionKey, String timeStamp, String relleno, String pass) {
        ticket ticket = new ticket();
        ticket.setIdUsuario(encriptarDes.principalMetodoE(pass, usuario));
        ticket.setRelleno(relleno);
        ticket.setSessionKey(encriptarDes.principalMetodoE(pass, sessionKey));
        ticket.setTimestamp(timeStamp);
        ticket.setServiceId("");
        return ticket;
    }


    public String timeStamps(int minutos) {
        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        long fechaNuevaa = t + (minutos * ONE_MINUTE_IN_MILLIS);
        String resultado = Long.toString(fechaNuevaa);
        return resultado;
    }

    public String crear() {
        String digitoHexa = "";
        int num1 = 34;
        int num2 = 125;
        for (int i = 0; i < 24; i++) {
            int numAleatorio = (int) Math.floor(Math.random() * (num1 - (num2 + 1)) + (num2));
            System.out.println(numAleatorio);
            char letra = (char) (numAleatorio);
            System.out.println(letra);
            digitoHexa = digitoHexa + letra;
        }

        return digitoHexa;
    }

}
