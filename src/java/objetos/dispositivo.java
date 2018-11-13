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
public class dispositivo implements objeto<dispositivo> {

    public Long idDispositivo;
    public String token;
    public String nombre;
    public Long idUsuario;
    public int tipoDispositivo;
    public int status;

    public dispositivo() {
    }

    public dispositivo(Long idDispositivo, String token, String nombre, Long idUsuario, int tipoDispositivo, int status) {
        this.idDispositivo = idDispositivo;
        this.token = token;
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.tipoDispositivo = tipoDispositivo;
        this.status = status;
    }

    public dispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public dispositivo(String token) {
        this.token = token;
    }

    public dispositivo(Long idDispositivo, Long idUsuario) {
        this.idDispositivo = idDispositivo;
        this.idUsuario = idUsuario;
    }

    @Override
    public dispositivo parse(HttpServletRequest request) {
        return new dispositivo();
    }

    @Override
    public dispositivo parse(ResultSet res) {
        return new dispositivo();
    }

    @Override
    public String toJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<dispositivo> parseList(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<dispositivo> parseList(ResultSet res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
