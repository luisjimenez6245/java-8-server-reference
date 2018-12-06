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
public class relleno {

    public relleno() {
    }

    public String rellenar(String aRellenar, String relleno) {
        String resultado;
        int i = 0;
        while (aRellenar.length() < 24) {
            if (i < aRellenar.length() && i > -1) {
                resultado = Integer.toHexString((int) aRellenar.charAt(i));
                int aux = Integer.parseInt(resultado.trim(), 16);
                aRellenar += relleno.charAt(aux - 32);
            }
            if (i % 2 == 0) {
                if ((i - 1) < 0) {
                    ++i;
                } else {
                    if ((i + 3) > aRellenar.length()) {
                        --i;
                    } else {
                        ++i;
                        ++i;
                    }
                }
            } else {
                if ((i + 2) > aRellenar.length()) {
                    --i;
                    --i;
                    --i;
                } else {
                    ++i;
                    ++i;
                }
            }
        }
        return aRellenar;
    }

    public String generarRelleno() {
        String llenado = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a0.__$.&/./-.-.";
        String resultado = "";
        while (resultado.length() < 120) {
            int randomNum = 0 + (int) (Math.random() * 69);
            resultado += llenado.charAt(randomNum);
        }
        return resultado;
    }
}
