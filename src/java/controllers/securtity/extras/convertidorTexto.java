/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.securtity.extras;

/**
 *
 * @author luis
 */
public class convertidorTexto {

    private String res;

    public String aBinario(String aConvetir) {
        res = "";
        for (char caracter : aConvetir.toCharArray()) {
            String numero = ("00000000" + Integer.toBinaryString((int) caracter));
            res += numero.substring(numero.length() - 8, numero.length());
        }
        return res;
    }
    
    public String binarioHexadecimal(){
        
        res = "";
        return res;
    }

    public String aHexadecimal(String aConvetir) {
        res = "";
        for (char caracter : aConvetir.toCharArray()) {
            res += ("" + Integer.toHexString((int) caracter));
        }
        return res;
    }

    public String aOctal(String aConvetir) {
        res = "";
        for (char caracter : aConvetir.toCharArray()) {
            res += ("" + Integer.toOctalString((int) caracter));
        }
        return res;
    }

    public String enteroBinario(int numero) {
        res = Integer.toBinaryString(numero);
        res = "00000000000000000000000000000000000000" + res;
        res = res.substring(res.length() - 32, res.length());
        return res;
    }

    public String enteroOctal(int numero) {
        res = Integer.toOctalString(numero);
        return res;
    }

    public String enteroHexadecimal(int numero) {
        res = Integer.toHexString(numero);
        return res;
    }

    public String binarioCadena(String binario) {
        res = "";
        binario = rellenaCeros(binario, 8);
        for (int i = 0; i < (binario.length() / 8); ++i) {
            int a = Integer.parseInt(binario.substring(8 * i, (i + 1) * 8), 2);
            res += Character.toString((char) (a));
        }
        return res;
    }

    private String rellenaCeros(String aRellenar, int numero) {
        while ((aRellenar.length() % numero) != 0) {
            aRellenar = "0" + aRellenar;
        }

        return aRellenar;
    }

    private String[] separa(String cadena, int numero) {
        int contador = (((int) cadena.length() / numero)) - 1;
        String[] contenedor = new String[contador];
        String auxiliar;
        while (contador >= 0) {
            auxiliar = "";
            for (int i = 0; i < numero; ++i) {
                auxiliar += cadena.charAt(i + (numero * contador));
            }
            contenedor[contador] = auxiliar;
            --contador;
        }
        return contenedor;

    }

}
