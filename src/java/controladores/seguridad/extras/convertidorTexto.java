/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

/**
 *
 * @author luis
 */
public class convertidorTexto {

    private String resultado;

    public String aBinario(String aConvetir) {
        resultado = "";
        for (char caracter : aConvetir.toCharArray()) {
            String numero = ("00000000" + Integer.toBinaryString((int) caracter));
            resultado += numero.substring(numero.length() - 8, numero.length());
        }
        return resultado;
    }
    
    public String binarioHexadecimal(){
        
        resultado = "";
        return resultado;
    }

    public String aHexadecimal(String aConvetir) {
        resultado = "";
        for (char caracter : aConvetir.toCharArray()) {
            resultado += ("" + Integer.toHexString((int) caracter));
        }
        return resultado;
    }

    public String aOctal(String aConvetir) {
        resultado = "";
        for (char caracter : aConvetir.toCharArray()) {
            resultado += ("" + Integer.toOctalString((int) caracter));
        }
        return resultado;
    }

    public String enteroBinario(int numero) {
        resultado = Integer.toBinaryString(numero);
        resultado = "00000000000000000000000000000000000000" + resultado;
        resultado = resultado.substring(resultado.length() - 32, resultado.length());
        return resultado;
    }

    public String enteroOctal(int numero) {
        resultado = Integer.toOctalString(numero);
        return resultado;
    }

    public String enteroHexadecimal(int numero) {
        resultado = Integer.toHexString(numero);
        return resultado;
    }

    public String binarioCadena(String binario) {
        resultado = "";
        binario = rellenaCeros(binario, 8);
        for (int i = 0; i < (binario.length() / 8); ++i) {
            int a = Integer.parseInt(binario.substring(8 * i, (i + 1) * 8), 2);
            resultado += Character.toString((char) (a));
        }
        return resultado;
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
