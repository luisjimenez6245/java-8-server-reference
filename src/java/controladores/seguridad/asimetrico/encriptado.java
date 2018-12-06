package controladores.seguridad.asimetrico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrador
 */
import java.math.BigInteger;

public class encriptado {

    private String cadena = "";

    private final BigInteger n = new BigInteger("42463683695458919079677867600267");
    private final BigInteger d = new BigInteger("33970946956367124764793571190573");
    private final BigInteger s = new BigInteger("5");

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String encripta(String aEncriptar) {
        cadena = "";
        char[] arreglo = separa(aEncriptar);
        BigInteger numero;
        for (char caracter : arreglo) {
            numero = new BigInteger(((int)caracter) + "").modPow(s, n);
            cadena += numero + ",";
        }
        return cadena.substring(0, cadena.length() - 1);
    }

    public boolean desencripta(String aDesencriptar) {
        cadena = "";
        try {
            String[] arregloCifrado = aDesencriptar.split(",");
            BigInteger numero;
            for (String st : arregloCifrado) {
                numero = new BigInteger(st);
                cadena += Character.toString((char) ((char)numero.modPow(d, n).intValue()));
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    private char[] separa(String aSeparar) {
        char[] arreglo = new char[aSeparar.length()];
        int numero = 0;
        for (char car : aSeparar.toCharArray()) {
            arreglo[numero] = car;
            ++numero;
        }
        return arreglo;
    }

}
