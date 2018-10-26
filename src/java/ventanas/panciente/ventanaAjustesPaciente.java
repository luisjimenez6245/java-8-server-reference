/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas.panciente;

import BD.cDatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import pagos.tarjetaContenedor;

/**
 *
 * @author luisjimenezdelgado
 */
public class ventanaAjustesPaciente extends interfaceP.ventanaPadre {

    private pacienteContenedor paciente = new pacienteContenedor();

    public ventanaAjustesPaciente() {
        super();
    }

    public void crear() {
        pacienteBD();
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
                + "                    <input type ='text' id='nombreUsuario'  value='" + paciente.getNombre() + "' name='nombreUsuario'>\n"
                + "           </div>\n"
                + "           <div class='divAbsoluto'>\n"
                + "               <label for='Apellidos' >Apellidos:</label>\n"
                + "               <input type ='text' id='Apellidos'  value='" + paciente.getApellido() + "' name='Apellidos'>\n"
                + "           </div>\n"
                + "           <div class='divAbsoluto'>\n"
                + "               <label for='Peso' >Peso:</label>\n"
                + "               <input type ='text' id='Peso'  maxlength='5' value='" + paciente.getPeso() + "' name='Peso' >\n"
                + "           </div>\n"
                + "           <div class='divAbsoluto'>\n"
                + "               <label for='Estatura' >Estatura:</label>\n"
                + "               <input type ='text' id='Estatura' maxlength='5' value='" + paciente.getAltura() + "' name='Estatura' >\n"
                + "           </div>\n"
                + "           <div class='divAbsoluto'>\n"
                + "               <label for='Edad' >Edad:</label>\n"
                + "               <input type ='text' id='Edad' maxlength='5' value='" + paciente.getEdad() + "' name='Edad' >\n"
                + "           </div>\n"
                + "           <div class='divAbsoluto'>\n"
                + "               <label for='Email' >Email:</label>\n"
                + "               <input type ='text' id='Email'  value='" + paciente.getEmail() + "' name=''  disable=''>\n"
                + "           </div>\n"
                + "           <div class='divAbsoluto'>\n"
                + "               <label for='Telefono' >Telefono:</label>\n"
                + "               <input type ='text' id='Telefono'  value='" + paciente.getTelefono() + "' name='Telefono' >\n"
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
                + "                                    <div class='boton azulBotones' onclick='cancelarCambiosAjustes()'>\n"
                + "                                        Cancelar\n"
                + "                                    </div>\n"
                + "                                    <div class='boton azulBotones' onclick='borrarCuentaAjustes()'>\n"
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

    private void pacienteBD() {
        cDatos base = new cDatos();
        ResultSet res;
        try {
            base.conectar();
            res = base.consulta("SELECT cuentaU.nombre,cuentaU.apellido,cuenta.correo, cuentaU.edad, histMed.peso, histMed.estatura, telefono.digitos,tarjeta.noTarjeta,"
                    + "tarjeta.fechaV FROM cuentaU "
                    + " JOIN cuenta on cuentaU.idPaciente = cuenta.idUsuario "
                    + " JOIN telefono on cuentaU.idPaciente = telefono.idUsuario "
                    + " JOIN tarjeta on cuentaU.idPaciente = tarjeta.idUsuario "
                    + " JOIN histMed on cuentaU.idPaciente = histMed.idPaciente "
                    + " where cuentaU.idPaciente =" + usuario.getIdUsuario() + "; ");
            if (res.next()) {
                paciente.setNombre(usuario.getNombre());
                paciente.setApellido(usuario.getApellido());
                paciente.setEdad(res.getShort("edad"));
                paciente.setPeso(res.getDouble("peso"));
                paciente.setAltura(res.getDouble("estatura"));
                paciente.setEmail(res.getString("correo"));
                paciente.setTelefono(res.getString("digitos"));
                tarjetaContenedor tarjeta = new tarjetaContenedor();
                tarjeta.setTarjeta(res.getString("noTarjeta"));
                paciente.setTarjeta(tarjeta);
            }
            base.cierraConexion();
        } catch (SQLException e) {
            System.out.println("error en clase  ventanaPricnipal paciente: " + e.toString());
        }
    }
}
