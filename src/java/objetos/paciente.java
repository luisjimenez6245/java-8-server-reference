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
public class paciente extends objUsuario implements objeto<paciente> {

    public float peso;
    public float altura;
    public float imc;
    public short tipoSangre;
    public short edad;
    public String alergias;
    public short menstruacion;
    public short vidaSexual;
    public short diu;
    public short embarazos;
    public short abortos;
    public antecedente[] antecedentes;

    public paciente() {

    }

    public paciente(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public paciente(long idUsuario, long idPersona) {
        this.idUsuario = idUsuario;
        this.idPersona = idPersona;
    }

    public paciente(long idUsuario, long idPersona, int tipo, String nombre, String apellidos, String fechaNacimiento, String otro, String email, String telefono, short genero,
            float peso, float altura, float imc, short tipoSangre, short edad, String alergias, short menstruacion, short vidaSexual, short diu, short embarazos, short abortos, antecedente[] antecedentes, int status) {
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
        this.peso = peso;
        this.altura = altura;
        this.imc = imc;
        this.tipoSangre = tipoSangre;
        this.edad = edad;
        this.alergias = alergias;
        this.menstruacion = menstruacion;
        this.vidaSexual = vidaSexual;
        this.diu = diu;
        this.embarazos = embarazos;
        this.abortos = abortos;
        this.antecedentes = antecedentes;
        this.status = status;
    }

    @Override
    public paciente parse(HttpServletRequest request) {

        if (request.getParameter("id_Persona") != null) {
            return new paciente(
                    request.getParameter("id_usuario") == null ? 0 : Long.parseLong(request.getParameter("id_usuario")),
                    request.getParameter("id_persona") == null ? 0 : Long.parseLong(request.getParameter("id_persona")),
                    request.getParameter("tipo") == null ? 0 : Integer.parseInt(request.getParameter("tipo")),
                    request.getParameter("nombre") == null ? "" : request.getParameter("nombre"),
                    request.getParameter("apellidos") == null ? "" : request.getParameter("apellidos"),
                    request.getParameter("fecha_nacimiento") == null ? "" : request.getParameter("fecha_nacimiento"),
                    request.getParameter("otro") == null ? "" : request.getParameter("otro"),
                    request.getParameter("email") == null ? "" : request.getParameter("email"),
                    request.getParameter("telefono") == null ? "" : request.getParameter("telefono"),
                    request.getParameter("genero") == null ? 0 : Short.parseShort(request.getParameter("genero")),
                    request.getParameter("peso") == null ? 0 : Float.parseFloat(request.getParameter("peso")),
                    request.getParameter("altura") == null ? 0 : Float.parseFloat(request.getParameter("altura")),
                    request.getParameter("imc") == null ? 0 : Float.parseFloat(request.getParameter("imc")),
                    request.getParameter("tipoSangre") == null ? 0 : Short.parseShort(request.getParameter("tipoSangre")),
                    request.getParameter("edad") == null ? 0 : Short.parseShort(request.getParameter("edad")),
                    request.getParameter("alergias") == null ? "" : request.getParameter("alergias"),
                    request.getParameter("menstruacion") == null ? 0 : Short.parseShort(request.getParameter("menstruacion")),
                    request.getParameter("vidaSexual") == null ? 0 : Short.parseShort(request.getParameter("vidaSexual")),
                    request.getParameter("diu") == null ? 0 : Short.parseShort(request.getParameter("diu")),
                    request.getParameter("embarazos") == null ? 0 : Short.parseShort(request.getParameter("embarazos")),
                    request.getParameter("abortos") == null ? 0 : Short.parseShort(request.getParameter("abortos")),
                    new antecedente().parseList(request).toArray(new antecedente[request.getParameterValues("antecedentes") == null ? 0 : request.getParameterValues("antecedentes").length - 1]),
                    request.getParameter("status") == null ? 0 : Integer.parseInt(request.getParameter("status"))
            );

        } else {
            return new paciente();
        }
    }

    @Override
    public paciente parse(ResultSet res) {
        try {
            if (res.next()) {
                return new paciente(
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
                        res.getFloat("peso"),
                        res.getFloat("altura"),
                        res.getFloat("imc"),
                        res.getShort("tipoSangre"),
                        res.getShort("edad"),
                        res.getString("alergias"),
                        res.getShort("menstruacion"),
                        res.getShort("vidaSexual"),
                        res.getShort("diu"),
                        res.getShort("embarazos"),
                        res.getShort("abortos"),
                        null,
                        res.getInt("status")
                );
            } else {
                return new paciente(
                        0L,
                        0L,
                        0,
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        (short) 0,
                        0,
                        0,
                        0,
                        (short) 0,
                        (short) 0,
                        "",
                        (short) 0,
                        (short) 0,
                        (short) 0,
                        (short) 0,
                        (short) 0,
                        null,
                        0
                );
            }
        } catch (SQLException ex) {
            new logger().error(ex);
            return null;
        }
    }

    @Override
    public String toJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<paciente> parseList(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<paciente> parseList(ResultSet res) {
        ArrayList<paciente> con = new ArrayList<>();
        try {
            while (res.next()) {
                con.add(
                        new paciente(
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
                                res.getFloat("peso"),
                                res.getFloat("altura"),
                                res.getFloat("imc"),
                                res.getShort("tipoSangre"),
                                res.getShort("edad"),
                                res.getString("alergias"),
                                res.getShort("menstruacion"),
                                res.getShort("vidaSexual"),
                                res.getShort("diu"),
                                res.getShort("embarazos"),
                                res.getShort("abortos"),
                                null,
                                res.getInt("status")
                        )
                );
            }
        } catch (SQLException ex) {
            new logger().error(ex);
        }
        return con;
    }

}
