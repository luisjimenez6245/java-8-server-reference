package controladores.seguridad.des;
public class contenedorDES {
    private String clave;
    private String mensaje;
    private String llaves [] = new String [16]; 
    private String llaves2 [] = new String [16]; 
    private String llaves3 [] = new String [16]; 

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the llaves
     */
    public String[] getLlaves() {
        return llaves;
    }

    /**
     * @param llaves the llaves to set
     */
    public void setLlaves(String[] llaves) {
        this.llaves = llaves;
    }
    
    
    /**
     * @return the llaves
     */
    public String[] getLlaves2() {
        return llaves2;
    }

    /**
     * @param llaves the llaves to set
     */
    public void setLlaves2(String[] llaves) {
        this.llaves2 = llaves;
    }
    
    /**
     * @return the llaves
     */
    public String[] getLlaves3() {
        return llaves3;
    }

    /**
     * @param llaves the llaves to set
     */
    public void setLlaves3(String[] llaves) {
        this.llaves3 = llaves;
    }
    
    
    
}
