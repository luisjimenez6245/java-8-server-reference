/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author luis
 */
public abstract class objUsuario {

    public long idUsuario = 0;
    public long idPersona = 0;
    public int tipo = 0;
    public String nombre = "";
    public String apellidos = "";
    public String email = "";
    public String telefono = "";
    public int status;
    public String fechaNacimiento = "";
    public String otro = "";
    public short genero;
    public long  dependencia;

    public objUsuario() {
    }

    public objUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public objUsuario(long idUsuario, long idPersona) {
        this.idUsuario = idUsuario;
        this.idPersona = idPersona;
    }

    public objUsuario(long idUsuario, long idPersona, int tipo, String nombre, String apellidos, String fechaNacimiento, String otro, String email, String telefono, short genero, long dependencia, int status) {
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

}
