/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import controladores.seguridad.logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author luis
 */
public class usuario extends objUsuario implements objeto<usuario> {

    public usuario() {

    }

    public usuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public usuario(long idUsuario, long idPersona) {
        this.idUsuario = idUsuario;
        this.idPersona = idPersona;
    }

    public usuario(long idUsuario, long idPersona, int tipo, String nombre, String apellidos, String fechaNacimiento, String otro, String email, String telefono, short genero, long dependencia, int status) {
        this.idUsuario = idUsuario;
        this.idPersona = idPersona;
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.otro = otro;
        this.email = email;
        this.telefono = telefono;
        this.genero = genero;
        this.dependencia = dependencia;
        this.status = status;
    }

    @Override
    public String toJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public usuario parse(HttpServletRequest request) {
        if (request.getParameter("id_usuario") == null && request.getParameter("email") == null && request.getParameter("telefono") == null && request.getParameter("acc") == null) {
            return new usuario();
        } else {
            if (request.getParameter("acc") == null) {
                return new usuario(
                        request.getParameter("id_usuario") == null ? 0 : Long.parseLong(request.getParameter("id_usuario")),
                        request.getParameter("id_persona") == null ? 0 : Long.parseLong(request.getParameter("id_persona")),
                        request.getParameter("tipo") == null ? 0 : Integer.parseInt(request.getParameter("tipo")),
                        request.getParameter("nombre") == null ? "" : request.getParameter("nombre"),
                        request.getParameter("apellidos") == null ? "" : request.getParameter("apellidos"),
                        request.getParameter("fecha_nacimiento") == null ? "" : request.getParameter("fecha_nacimiento"),
                        request.getParameter("otro") == null ? "" : request.getParameter("otro"),
                        request.getParameter("email") == null ? "" : request.getParameter("email"),
                        request.getParameter("telefono") == null ? "" : request.getParameter("telefono"),
                        request.getParameter("genero") == null ? 1 : Short.parseShort(request.getParameter("genero")),
                        request.getParameter("dependencia") == null ? 0 : Long.parseLong(request.getParameter("dependencia")),
                        request.getParameter("status") == null ? 0 : Integer.parseInt(request.getParameter("status"))
                );
            } else {
                String acc = request.getParameter("acc");
                if (acc.matches("\\d+")) {
                    return new usuario(
                            request.getParameter("id_usuario") == null ? 0 : Long.parseLong(request.getParameter("id_usuario")),
                            request.getParameter("id_persona") == null ? 0 : Long.parseLong(request.getParameter("id_persona")),
                            request.getParameter("tipo") == null ? 0 : Integer.parseInt(request.getParameter("tipo")),
                            request.getParameter("nombre") == null ? "" : request.getParameter("nombre"),
                            request.getParameter("apellidos") == null ? "" : request.getParameter("apellidos"),
                            request.getParameter("fecha_nacimiento") == null ? "" : request.getParameter("fecha_nacimiento"),
                            request.getParameter("otro") == null ? "" : request.getParameter("otro"),
                            request.getParameter("email") == null ? "" : request.getParameter("email"),
                            request.getParameter("acc") == null ? "" : request.getParameter("acc"),
                            request.getParameter("genero") == null ? 1 : Short.parseShort(request.getParameter("genero")),
                            request.getParameter("dependencia") == null ? 0 : Long.parseLong(request.getParameter("dependencia")),
                            request.getParameter("status") == null ? 0 : Integer.parseInt(request.getParameter("status"))
                    );
                } else {
                    return new usuario(
                            request.getParameter("id_usuario") == null ? 0 : Long.parseLong(request.getParameter("id_usuario")),
                            request.getParameter("id_persona") == null ? 0 : Long.parseLong(request.getParameter("id_persona")),
                            request.getParameter("tipo") == null ? 0 : Integer.parseInt(request.getParameter("tipo")),
                            request.getParameter("nombre") == null ? "" : request.getParameter("nombre"),
                            request.getParameter("apellidos") == null ? "" : request.getParameter("apellidos"),
                            request.getParameter("fecha_nacimiento") == null ? "" : request.getParameter("fecha_nacimiento"),
                            request.getParameter("otro") == null ? "" : request.getParameter("otro"),
                            request.getParameter("acc") == null ? "" : request.getParameter("acc"),
                            request.getParameter("telefono") == null ? "" : request.getParameter("telefono"),
                            request.getParameter("genero") == null ? 1 : Short.parseShort(request.getParameter("genero")),
                            request.getParameter("dependencia") == null ? 0 : Long.parseLong(request.getParameter("dependencia")),
                            request.getParameter("status") == null ? 0 : Integer.parseInt(request.getParameter("status"))
                    );
                }
            }
        }
    }

    @Override
    public usuario parse(ResultSet res) {
        try {
            if (res.next()) {
                return new usuario(
                        res.getLong("idUsuario"),
                        res.getLong("idPersona"),
                        res.getInt("tipo"),
                        res.getString("nombre"),
                        res.getString("apellidos"),
                        res.getString("fechaNacimiento"),
                        res.getString("otro"),
                        res.getString("email"),
                        res.getString("telefono"),
                        res.getShort("genero"),
                        res.getLong("dependencia"),
                        res.getInt("status"));
            } else {
                return new usuario(0L, 0L, 0, "", "", "", "", "", "", (short) 0, 0, 0);
            }
        } catch (SQLException ex) {
            new logger().error(ex);
            return null;
        }
    }

    @Override
    public List<usuario> parseList(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<usuario> parseList(ResultSet res) {
        ArrayList<usuario> contenedorNav = new ArrayList<>();
        try {
            while (res.next()) {
                contenedorNav.add(
                        new usuario(
                                res.getLong("idUsuario"),
                                res.getLong("idPersona"),
                                res.getInt("tipo"),
                                res.getString("nombre"),
                                res.getString("apellidos"),
                                res.getString("fechaNacimiento"),
                                res.getString("otro"),
                                res.getString("email"),
                                res.getString("telefono"),
                                res.getShort("genero"),
                                res.getLong("dependencia"),
                                res.getInt("status"))
                );
            }
            res.close();
        } catch (SQLException ex) {
            new logger().error(ex);
        }
        return contenedorNav;
    }

}
