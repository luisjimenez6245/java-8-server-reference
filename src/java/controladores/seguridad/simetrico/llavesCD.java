package controladores.seguridad.simetrico;

/**
 *
 * @author Luis DIego Jiménez Delado 5IM6
 */
public class llavesCD {

    private String llaveI = "";
    private String llaveD = "";
    private final int[] rotaciones;
    private final int[] PC2;

    llavesCD() {
        this.rotaciones = new int[]{
            1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
        };
        this.PC2 = new int[]{
            14, 17, 11, 24, 1, 5, 3, 28,
            15, 6, 21, 10, 23, 19, 12, 4,
            26, 8, 16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55, 30, 40,
            51, 45, 33, 48, 44, 49, 39, 56,
            34, 53, 46, 42, 50, 36, 29, 32
        };
    }

    public String[] ejecutar(String llave) {
        String[] llaves = new String[16];
        for (int i = 0; i < 28; ++i) {
            llaveI = llaveI + llave.charAt(i);
            llaveD = llaveD + llave.charAt(i + 28);
        }

        for (int i = 0; i < 16; ++i) {
            rotacion(rotaciones[i]);
            llaves[i] = llaveX();
        }
        return llaves;
    }

    private void rotacion(int veces) {
        for (int i = 0; i < veces; ++i) {
            String resultadoI = "";
            String resultadoD = "";
            char auxI = llaveI.charAt(0);
            char auxD = llaveD.charAt(0);
            for (int j = 0; j < 27; ++j) {
                resultadoI = resultadoI + llaveI.charAt(j + 1);
                resultadoD = resultadoD + llaveD.charAt(j + 1);
            }
            llaveI = resultadoI + auxI;
            llaveD = resultadoD + auxD;
        }
    }

    private String llaveX() {
        String llaveActual = llaveI + llaveD;
        String llaveX = "";
        for (int i = 0; i < 48; ++i) {
            llaveX = llaveX + llaveActual.charAt((PC2[i] - 1));
        }
        return llaveX;
    }
}
