package seguridad.des;
/**
 *
 * @author Luis DIego Jiménez Delado 5IM6
 */
public class Mensaje {
    private String izquierdo = "";
    private String derecho = "";
    private final int[] expancion;
    Mensaje (){
        this.expancion = new int[] {
            32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1   
        };
    }
    
    public String expandir (String expandible){
        String resultado = "";
        for(int i = 0; i <48;++i){
            resultado = resultado + expandible.charAt(expancion[i]-1);
        }
        return resultado;
    }
    
    public String xorLlave(String llave, String derechoVerificar){
        String resultado = "";
        for (int i = 0 ; i <48 ; ++i)
            if (llave.charAt(i)== derechoVerificar.charAt(i))
                resultado = resultado + '0';
            else 
                resultado = resultado + '1';
        return resultado;
    }

    public String xorIzquierdo (String parteBaja){
        String resultado = "";
        for (int i =0 ; i <32; ++i )
            if (parteBaja.charAt(i)==izquierdo.charAt(i))
                resultado = resultado +'0';
            else 
                resultado = resultado + '1';
        return resultado;
    }
    
    public String dividir (int veces, String aDividir){
        String  resultado = "";
        for(int i = 0; i <6; ++i){
            resultado = resultado + aDividir.charAt(i+(6*veces));
        }
        return resultado;
    }
    public void cambiarLado(String derechoResuelto){
        setIzquierdo(derecho);
        setDerecho(derechoResuelto);
    }
    /**
     * @return the izquierdo
     */
    public String getIzquierdo() {
        return izquierdo;
    }

    /**
     * @param izquierdo the izquierdo to set
     */
    public void setIzquierdo(String izquierdo) {
        this.izquierdo = izquierdo;
    }

    /**
     * @return the derecho
     */
    public String getDerecho() {
        return derecho;
    }

    /**
     * @param derecho the derecho to set
     */
    public void setDerecho(String derecho) {
        this.derecho = derecho;
    }
}
