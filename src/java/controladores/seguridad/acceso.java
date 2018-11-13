/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.seguridad;

import objetos.Dao.dispositivoDao;
import objetos.Dao.usuarioDao;
import objetos.dispositivo;
import objetos.usuario;
import seguridad.kerberos.controladorTicket;

/**
 *
 * @author luis
 */
public class acceso {

    private String key;
    private String token;
    private String ip;
    private final seguridad.utilidad UTIL;
    private usuario _usuario;

    public acceso() {
        UTIL = new seguridad.utilidad();
    }

    public String porUsuario(usuario user, String token, String ip) {
        this.token = token == null ? "" : token;
        this.ip = ip == null ? "" : ip;
        key = !user.email.equals("") ? user.email : (!user.telefono.equals("") ? user.telefono : "");
        this._usuario = new usuarioDao().traerDetalles(user, null);
        if (!user.email.equals("")) {
            return UTIL.validarEmail(user.email) ? ejecuta(user.email, 1) : "";
        } else {
            if (!user.telefono.equals("")) {
                return UTIL.validaTelefono(user.telefono) ? ejecuta(user.telefono, 2) : "";
            }
        }
        return "";
    }

    public String porDispositivo(dispositivo dis, String token, String ip) {
        key = dis.token;
        this.token = token == null ? "" : token;
        this.ip = ip == null ? "" : ip;
        if (!dis.token.equals("")) {
            dis = new dispositivoDao().traerDetalles(dis, null);
            this._usuario = new usuarioDao().traerDetalles(new usuario(dis.idUsuario), null);
            return UTIL.validarDispositivoToken(dis.token) ? "" : "";
        }
        return "";
    }

    private String ejecuta(String _key, int opc) {
        controladorTicket ticket = new controladorTicket();
        if (UTIL.validaToken(token) && UTIL.validaIP(ip) && _usuario.idUsuario != 0) {
            return ticket.genera(_key, _usuario.otro, ip);
        }
        return "";
    }

}
