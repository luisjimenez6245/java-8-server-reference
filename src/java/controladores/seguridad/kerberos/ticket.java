/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.seguridad.kerberos;

/**
 *
 * @author luis
 */
public class ticket {

    private String serviceId; //nombre
    private String timestamp; //encriptar;
    private String sessionKey; //encriptar;
    private String idUsuario;
    private String relleno;

    /**
     * @return the relleno
     */
    public String getRelleno() {
        return relleno;
    }

    /**
     * @param relleno the relleno to set
     */
    public void setRelleno(String relleno) {
        this.relleno = relleno;
    }

    /**
     * @return the serviceId
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId the serviceId to set
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the sesionKey
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * @param sesionKey the sesionKey to set
     */
    public void setSessionKey(String sesionKey) {
        this.sessionKey = sesionKey;
    }

    /**
     * @return the id
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param id the id to set
     */
    public void setIdUsuario(String id) {
        this.idUsuario = id;
    }

}
