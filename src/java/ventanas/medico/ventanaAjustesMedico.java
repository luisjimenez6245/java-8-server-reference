/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas.medico;

import medico.*;
import BD.cDatos;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author luisjimenezdelgado
 */
public class ventanaAjustesMedico extends interfaceP.ventanaPadre {

    private medicoContenedor medico = new medicoContenedor();

    public ventanaAjustesMedico() {
        super();
    }

    public void crear() {
        medicoBD();
        ajustar();
    }

    private void ajustar() {
        clase = "ajustesUsuario";

        primeraParte = "<div class='contenedor'>\n"
                + "    <div class='titulo'>\n"
                + "        Información General\n"
                + "    </div>\n"
                + "    <br>\n"
                + "    <div class='contenido'>\n"
                + "       <div class='absoluto'>\n"
                + "          <div class='foto'>\n"
                + "              <div class='fotito' style='background-image: url(" + puntos + "getImage.jsp?id=" + usuario.getIdUsuario() + ")'>\n"
                + "                  &nbsp;\n"
                + "              </div>\n"
                + "          </div>\n"
                + "          <div class='txt'>\n"
                + "             <input type='file' name='pic' accept='image/*'>\n"
                + "          </div>\n"
                + "       </div>\n"
                + "       <div class='absoluto'>\n"
                + "           <div class='divAbsoluto'>\n"
                + "               <label for='nombreUsuario' >Nombre:</label>\n"
                + "                    <input type ='text' id='nombreUsuario'  value='" + medico.getNombre() + "' name='nombreUsuario'>\n"
                + "           </div>\n"
                + "           <div class='divAbsoluto'>\n"
                + "               <label for='Apellidos' >Apellidos:</label>\n"
                + "               <input type ='text' id='Apellidos'  value='" + medico.getApellido() + "' name='Apellidos'>\n"
                + "           </div>\n"
                + "           <div class='divAbsoluto'>\n"
                + "               <label for='Email' >Email:</label>\n"
                + "               <input type ='text' id='Email'  value='" + medico.getEmail() + "' name=''  disable=''>\n"
                + "           </div>\n"
                + "           <div class='divAbsoluto'>\n"
                + "               <label for='costo' >Costo:</label>\n"
                + "               <input type ='text' id='costo'  maxlength='5' value='" + medico.getCosto() + "' name='costo' >\n"
                + "           </div>\n"
                + "           <div class='divAbsoluto'>\n"
                + "               <label for='Acerca' >Acerca de mi:</label>\n"
                + "               <input type ='text' id='Acerca' maxlength='20' value='" + medico.getAcerca() + "' name='Acerca' >\n"
                + "           </div>\n"
                + "           <div class='divAbsoluto'>\n"
                + "               <label for='Telefono' >Telefono:</label>\n"
                + "               <input type ='text' id='Telefono'  value='" + medico.getTelefono() + "' name='Telefono' >\n"
                + "           </div>\n"
                + "           <br>\n"
                + "           <div class='contenedorBoton'>\n"
                + "               <div class='container'>\n"
                + "                   <div class='boton rojoso '>\n"
                + "                       cambiar contraseña\n"
                + "                   </div>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</div>";
        segundaParte = ""
                + "<div class='contenedor'>\n"
                + "   <br>\n"
                + "   <div class='titulo'>\n"
                + "        Método de Pago\n"
                + "   </div>\n"
                + "   <br>\n"
                + "   <div class='contenido'>\n"
                + "                        <div class='absoluto'>\n"
                + "                            <div class='divAbsoluto'>\n"
                + "                                <label for='nTarjtea' >Número de tarjeta:</label>\n"
                + "                                <input type ='text' id='nTarjtea'  value='' >\n"
                + "                            </div>\n"
                + "                            <br>\n"
                + "                            <div class='divAbsoluto'>\n"
                + "                                <label for='ccvTarjeta' >CCV:</label>\n"
                + "                                <input type ='text' id='ccvTarjeta'  value='' >\n"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto'>\n"
                + "                                <label for='fechaTarjeta'></label>\n"
                + "                                <input type ='text' id='fechaTarjeta'  value='' >\n"
                + "                            </div>\n"
                + "                            <br>\n"
                + "                            <div class='contenedorBoton'>\n"
                + "                                <div class='container'>\n"
                + "                                    <div class='boton rojoso ' onclick='guardarCambiosAjustes()'>\n"
                + "                                        Guardar Cambios\n"
                + "                                    </div>\n"
                + "                                    <div class='boton verdeBotones' onclick='cancelarCambiosAjustes()'>\n"
                + "                                        Cancelar\n"
                + "                                    </div>\n"
                + "                                    <div class='boton verdeBotones' onclick='borrarCuentaAjustes()'>\n"
                + "                                        borrar cuenta\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                </div>";
        terceraParte = "";
        cuartaParte = "";
        quintaParte = "";
        JS = "";
    }

    private void medicoBD() {
        cDatos base = new cDatos();
        ResultSet res;
        try {
            base.conectar();
            res = base.consulta("SELECT cuentaM.costo, escuela.nombreEscuela, cuentaM.nombreM, cuentaM.apellidoM, correosCuentas.correo,"
                    + " telefono.digitos , tarjeta.noTarjeta, tarjeta.fechaV FROM cuentaM  "
                    + " JOIN correosCuentas on cuentaM.idMedico = correosCuentas.idUsuario "
                    + " JOIN telefono on cuentaM.idMedico = telefono.idUsuario "
                    + " JOIN tarjeta on cuentaM.idMedico = tarjeta.idUsuario "
                    + " JOIN escuela on cuentaM.escuela = escuela.idEscuela "
                    + "where cuentaM.idMedico =" + usuario.getIdUsuario() + "; ");
            if (res.next()) {
                medico.setNombre(usuario.getNombre());
                medico.setApellido(usuario.getApellido());
                medico.setCosto(res.getString("costo"));
                medico.setAcerca(res.getString("nombreEscuela"));
                medico.setTelefono(res.getString("digitos"));
                medico.setEmail(res.getString("correosCuentas.correo"));
               
            }
            base.cierraConexion();
        } catch (SQLException e) {
            System.out.println("error en clase  ventanaPricnipal medico: " + e.toString());
        }
    }
}
