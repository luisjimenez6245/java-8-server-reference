/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.sql.ResultSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author luis
 */
public class ticket implements objeto<ticket>{
    
    
    public String idServicio; //nombre
    public String timestamp; //encriptar;
    public String sessionKey; //encriptar;
    public String idUsuario;
    public String publicKey;
    public String ip;

    public ticket(String idServicio, String timestamp, String sessionKey, String idUsuario, String publicKey, String ip) {
        this.idServicio = idServicio;
        this.timestamp = timestamp;
        this.sessionKey = sessionKey;
        this.idUsuario = idUsuario;
        this.publicKey = publicKey;
    }
    
    @Override
    public ticket parse(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ticket parse(ResultSet res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ticket> parseList(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ticket> parseList(ResultSet res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
