package controladores.seguridad.simetrico;

/**
 *
 * @author Luis DIego Jiménez Delado 5IM6
 */
public class convertidorTexto {

    private final String[] hexadecimalBinario;
    private final char[] binarioHexademcimal;

    public convertidorTexto() {
        this.hexadecimalBinario = new String[]{
            "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"
        };
        this.binarioHexademcimal = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
    }

    public String binarioDecimal(String cadenaBinario) {
        //Ejecuto el proceso para cambiar de binario a decimal 
        String resultado;
        int suma = 0;
        for (int i = 0; i < cadenaBinario.length(); ++i) {
            if (cadenaBinario.charAt((cadenaBinario.length() - 1 - i)) == '1') {
                suma = (int) (suma + Math.pow(2, (i)));
            }
        }
        resultado = Integer.toString((int) suma);
        return resultado;
    }

    public String hexaBinario(String cadenaHexadecimal) {
        String cadenaBinario = "";
        for (int i = 0; i < cadenaHexadecimal.length(); ++i) {
            cadenaBinario += Integer.toHexString((int) cadenaHexadecimal.charAt(i)) + "";
        }
        return cadenaBinario;
    }

    public String binarioHexadecimal(String cadenaBinario) {
        String cadenaHexadecimal = "";
        String mensajeAuxiliar;
        for (int i = 0; i < (cadenaBinario.length() / 4); ++i) {
            mensajeAuxiliar = cadenaBinario.charAt((i * 4)) + "" + cadenaBinario.charAt((i * 4) + 1) + cadenaBinario.charAt((i * 4) + 2) + cadenaBinario.charAt((i * 4) + 3);
            for (int j = 0; j < 16; ++j) {
                if (mensajeAuxiliar.equals(hexadecimalBinario[j])) {
                    cadenaHexadecimal = cadenaHexadecimal + binarioHexademcimal[j];
                }
            }
        }
        return cadenaHexadecimal;
    }

    public String decimalBinario4digitos(int numero) {
        String resultado = "";
        boolean ejecutar = true;
        while (ejecutar) {
            resultado = resultado + Integer.toString(numero % 2);
            numero = numero / 2;
            if (numero == 0) {
                resultado = resultado + 0;
                ejecutar = false;
            } else if (numero == 1) {
                resultado = resultado + 1;
                ejecutar = false;
            }
        }
        String correcto = "";
        for (int i = resultado.length(); i > 0; --i) {
            correcto = correcto + resultado.charAt(i - 1);
        }
        if (correcto.length() < 4) {
            int veces = 4 - correcto.length();
            for (int i = 0; i < veces; ++i) {
                correcto = "0" + correcto;
            }
        }
        return correcto;
    }

}
