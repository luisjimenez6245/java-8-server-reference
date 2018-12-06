/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.seguridad.simetrico;

import java.math.BigInteger;

/**
 *
 * @author luis
 */
public class desPrincipal {

    private String respuesta;
    private final contenedorDES objeto = new contenedorDES();
    private final convertidorTexto convertidor = new convertidorTexto();
    private final permutador permutaciones = new permutador();
    private Mensaje calcularMensaje;
    private String derecho;
    private String izquierdo;
    
    
    
    private String encripta (String llave , String mensaje){
        llave = String.format("%016x", new BigInteger(1, llave.getBytes(/*YOUR_CHARSET?*/)));
        llavesCD calcularLlaves = new llavesCD();
        objeto.setClave(permutaciones.permutacionPC1(convertidor.hexaBinario(llave)));
        objeto.setLlaves(calcularLlaves.ejecutar(objeto.getClave()));
        String aux = "";
        for (int i = 0; i < (mensaje.length() / 16); ++i) {
            objeto.setMensaje(permutaciones.permutacionInicial(convertidor.hexaBinario(mensaje.substring(0 + (16 * i), 15 + (16 *i)))));
            aux += encriptaHex(objeto.getMensaje());
        }
        return aux;
    }
    
    private String desencripta(String llave, String mensaje){
        
    }

    private String encriptaHex(String mensaje) {
        derecho = "";
        izquierdo = "";
        calcularMensaje = new Mensaje();
        for (int i = 0; i < 32; ++i) {
            derecho = derecho + mensaje.charAt(i + 32);
            izquierdo = izquierdo + mensaje.charAt(i);
        }
        calcularMensaje.setDerecho(derecho);
        calcularMensaje.setIzquierdo(izquierdo);
        for (int i = 0; i < 16; ++i) {
            String aDividir = calcularMensaje.xorLlave((objeto.getLlaves())[i], calcularMensaje.expandir(derecho));
            String resultado = "";
            String numero;
            for (int j = 0; j < 8; ++j) {
                numero = aDividir.substring((j * 6), (j * 6) + 5);
                resultado += convertidor.decimalBinario4digitos(permutaciones.cajaS(Integer.parseInt(convertidor.binarioDecimal(("" + numero.charAt(0) + numero.charAt(5)))), Integer.parseInt(convertidor.binarioDecimal(("" + numero.substring(1, 4) + ""))), j));
            }
            resultado = permutaciones.metodoPermutacionP(resultado);
            resultado = calcularMensaje.xorIzquierdo(resultado);
            calcularMensaje.cambiarLado(resultado);
            derecho = calcularMensaje.getDerecho();
        }
        respuesta = convertidor.binarioHexadecimal(permutaciones.metodoPermutacionFinal(calcularMensaje.getIzquierdo(), calcularMensaje.getDerecho()));
        return respuesta;
    }

    private String desencriptaHex(String mensaje) {
        derecho = "";
        izquierdo = "";
        calcularMensaje = new Mensaje();
        for (int i = 0; i < 32; ++i) {
            derecho = derecho + mensaje.charAt(i + 32);
            izquierdo = izquierdo + mensaje.charAt(i);
        }
        calcularMensaje.setDerecho(derecho);
        calcularMensaje.setIzquierdo(izquierdo);
        for (int i = 15; i > -1; --i) {
            String aDividir = calcularMensaje.xorLlave((objeto.getLlaves())[i], calcularMensaje.expandir(derecho));
            String resultado = "";
            String numero;
            for (int j = 0; j < 8; ++j) {
                numero = aDividir.substring((j * 6), (j * 6) + 5);
                resultado = resultado + convertidor.decimalBinario4digitos(permutaciones.cajaS(Integer.parseInt(convertidor.binarioDecimal(("" + numero.charAt(0) + numero.charAt(5)))), Integer.parseInt(convertidor.binarioDecimal(("" + numero.substring(1, 4) + ""))), j));
            }
            resultado = permutaciones.metodoPermutacionP(resultado);
            resultado = calcularMensaje.xorIzquierdo(resultado);
            calcularMensaje.cambiarLado(resultado);
            derecho = calcularMensaje.getDerecho();
        }
        respuesta = convertidor.binarioHexadecimal(permutaciones.metodoPermutacionFinal(calcularMensaje.getIzquierdo(), calcularMensaje.getDerecho()));
        return respuesta;
    }

    private String rellena(String a) {
        while (a.length() % 24 != 0) {
            a = "0" + a;
        }
        return a;
    }

    private String[] separa(String a) {
        String[] arreglo = new String[(int) (a.length() / 24)];
        for (int i = 0; i < arreglo.length; ++i) {
            arreglo[i] = a.substring((i * 16), (i * 16) + 16);
        }
        return arreglo;
    }
}
