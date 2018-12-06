package controladores.seguridad.simetrico;

import java.math.BigInteger;

/**
 *
 * @author Luis Diego Jiménez Delado 5IM6
 */
public final class principal {

    private final contenedorDES objeto = new contenedorDES();
    private final convertidorTexto convertidor = new convertidorTexto();
    private final permutador permutaciones = new permutador();

    public principal() {

    }

    public String principalMetodoE(String llavecita, String mensajito) {
        String llaveAux = "";
        String mensajeAux = mensajito;
        for (int i = 0; i < 8; ++i) {
            llaveAux += llavecita.charAt(i);
        }
        mensajeAux = DesE(llaveAux, mensajeAux);
        llaveAux = "";
        for (int i = 8; i < 16; ++i) {
            llaveAux += llavecita.charAt(i);
        }
        mensajeAux = DesDHEX(llaveAux, mensajeAux);
        llaveAux = "";
        for (int i = 16; i < 24; ++i) {
            llaveAux += llavecita.charAt(i);
        }

        mensajeAux = DesEHEX(llaveAux, mensajeAux);
        return mensajeAux;
    }

    public String principalMetodoD(String llavecita, String mensajito) {
        String llaveAux = "";
        String mensajeAux = mensajito;
        for (int i = 16; i < 24; ++i) {
            llaveAux += llavecita.charAt(i);
        }
        mensajeAux = DesDHEX(llaveAux, mensajeAux);
        llaveAux = "";
        for (int i = 8; i < 16; ++i) {
            llaveAux += llavecita.charAt(i);
        }
        mensajeAux = DesEHEX(llaveAux, mensajeAux);
        llaveAux = "";
        for (int i = 0; i < 8; ++i) {
            llaveAux += llavecita.charAt(i);
        }
        mensajeAux = DesDHEX(llaveAux, mensajeAux);

        return mensajeAux;
    }

    private String DesE(String llavecita, String mensajito) {
        String clave = String.format("%016x", new BigInteger(1, llavecita.getBytes(/*YOUR_CHARSET?*/)));
        objeto.setClave(permutaciones.permutacionPC1(convertidor.hexaBinario(clave)));
        objeto.setLlaves(new llavesCD().ejecutar(objeto.getClave()));
        String aux = "";
        for (int i = 0; i < (mensajito.length() / 8); ++i) {
            String mensaje = mensajito.charAt(0 + (8 * i)) + "" + mensajito.charAt(1 + (8 * i)) + mensajito.charAt(2 + (8 * i)) + mensajito.charAt(3 + (8 * i)) + mensajito.charAt(4 + (8 * i)) + mensajito.charAt(5 + (8 * i)) + mensajito.charAt(6 + (8 * i)) + mensajito.charAt(7 + (8 * i));
            mensaje = String.format("%016x", new BigInteger(1, mensaje.getBytes(/*YOUR_CHARSET?*/)));
            objeto.setMensaje(permutaciones.permutacionInicial(convertidor.hexaBinario(mensaje)));
            aux += metodoMensaje(objeto.getMensaje());
        }
        return aux;
    }

    private String DesEHEX(String llave, String mensaje) {
        llave = String.format("%016x", new BigInteger(1, llave.getBytes(/*YOUR_CHARSET?*/)));
        objeto.setClave(permutaciones.permutacionPC1(convertidor.hexaBinario(llave)));
        objeto.setLlaves(new llavesCD().ejecutar(objeto.getClave()));
        String aux = "";
        for (int i = 0; i < (mensaje.length() / 16); ++i) {
            objeto.setMensaje(permutaciones.permutacionInicial(convertidor.hexaBinario(mensaje.substring(0 + (16 * i), 15 + (16 * i)))));
            aux += metodoMensaje(objeto.getMensaje());
        }
        return aux;
    }

    private String DesDHEX(String llave, String mensaje) {
        //entra en hex, sale en cadena
        llave = String.format("%016x", new BigInteger(1, llave.getBytes()));
        objeto.setClave(permutaciones.permutacionPC1(convertidor.hexaBinario(llave)));
        objeto.setLlaves(new llavesCD().ejecutar(objeto.getClave()));
        String aux = "";
        for (int i = 0; i < (mensaje.length() / 16); ++i) {
            objeto.setMensaje(permutaciones.permutacionInicial(convertidor.hexaBinario(mensaje.substring(0 + (16 * i), 15 + (16 * i)))));
            aux += metodoMensajeDes(objeto.getMensaje());
        }
        return aux;
    }

    private String metodoMensaje(String mensaje) {
        String mensajeCalculado = "";
        String derecho = "";
        String izquierdo = "";
        Mensaje calcularMensaje = new Mensaje();
        for (int i = 0; i < 32; ++i) {
            derecho = derecho + mensaje.charAt(i + 32);
            izquierdo = izquierdo + mensaje.charAt(i);
        }
        calcularMensaje.setDerecho(derecho);
        calcularMensaje.setIzquierdo(izquierdo);
        for (int i = 0; i < 16; ++i) {
            String aDividir = calcularMensaje.xorLlave((objeto.getLlaves())[i], calcularMensaje.expandir(derecho));
            String resultado = "";
            for (int j = 0; j < 8; ++j) {
                String numero = aDividir.charAt((j * 6)) + "" + aDividir.charAt((j * 6) + 1) + aDividir.charAt((j * 6) + 2) + aDividir.charAt((j * 6) + 3) + aDividir.charAt((j * 6) + 4) + aDividir.charAt((j * 6) + 5);
                int fila = Integer.parseInt(convertidor.binarioDecimal(("" + numero.charAt(0) + numero.charAt(5))));
                int columna = Integer.parseInt(convertidor.binarioDecimal(("" + numero.charAt(1) + numero.charAt(2) + numero.charAt(3) + numero.charAt(4) + "")));
                resultado = resultado + convertidor.decimalBinario4digitos(permutaciones.cajaS(fila, columna, j));
            }
            resultado = permutaciones.metodoPermutacionP(resultado);
            resultado = calcularMensaje.xorIzquierdo(resultado);
            calcularMensaje.cambiarLado(resultado);
            derecho = calcularMensaje.getDerecho();
        }
        mensajeCalculado = convertidor.binarioHexadecimal(permutaciones.metodoPermutacionFinal(calcularMensaje.getIzquierdo(), calcularMensaje.getDerecho()));
        return mensajeCalculado;
    }

    private String metodoMensajeDes(String mensaje) {
        String mensajeCalculado = "";
        String derecho = "";
        String izquierdo = "";
        Mensaje calcularMensaje = new Mensaje();
        for (int i = 0; i < 32; ++i) {
            derecho = derecho + mensaje.charAt(i + 32);
            izquierdo = izquierdo + mensaje.charAt(i);
        }
        calcularMensaje.setDerecho(derecho);
        calcularMensaje.setIzquierdo(izquierdo);
        for (int i = 15; i > -1; --i) {
            String aDividir = calcularMensaje.xorLlave((objeto.getLlaves())[i], calcularMensaje.expandir(derecho));
            String resultado = "";
            for (int j = 0; j < 8; ++j) {
                String numero = aDividir.charAt((j * 6)) + "" + aDividir.charAt((j * 6) + 1) + aDividir.charAt((j * 6) + 2) + aDividir.charAt((j * 6) + 3) + aDividir.charAt((j * 6) + 4) + aDividir.charAt((j * 6) + 5);
                int fila = Integer.parseInt(convertidor.binarioDecimal(("" + numero.charAt(0) + numero.charAt(5))));
                int columna = Integer.parseInt(convertidor.binarioDecimal(("" + numero.charAt(1) + numero.charAt(2) + numero.charAt(3) + numero.charAt(4) + "")));
                resultado = resultado + convertidor.decimalBinario4digitos(permutaciones.cajaS(fila, columna, j));
            }
            resultado = permutaciones.metodoPermutacionP(resultado);
            resultado = calcularMensaje.xorIzquierdo(resultado);
            calcularMensaje.cambiarLado(resultado);
            derecho = calcularMensaje.getDerecho();
        }
        mensajeCalculado = convertidor.binarioHexadecimal(permutaciones.metodoPermutacionFinal(calcularMensaje.getIzquierdo(), calcularMensaje.getDerecho()));
        return mensajeCalculado;
    }

}
