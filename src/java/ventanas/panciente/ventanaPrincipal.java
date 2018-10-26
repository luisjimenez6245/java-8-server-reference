/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas.panciente;

import BD.cDatos;
import agenda.agendaPaciente;
import agenda.eventosContenedorPaciente;
import interfaceP.fechas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import servicios.servicioContenedor;
import usuarios.usuarioObj;

/**
 *
 * @author luisjimenezdelgado
 */
public class ventanaPrincipal extends objetos.objetoVentana {

    private pacienteContenedor paciente;

    private agendaPaciente agenda = new agendaPaciente();
    private int personasFamilia;
    private int numeroServicios;
    private String selecionado;

    private final String coloresLeves[];
    // amarilloNav 0
    // azulado 1
    // moradoBotones 2
    // verdeBotones 3
    // rojoso 4

    private int colorInt = -1;

    public ventanaPrincipal() {
        super();
        this.paciente = new pacienteContenedor();
        this.coloresLeves = new String[]{"rojoso", "azulado", "moradoBotones", "verdeBotones", "amarilloBotones", ""};
    }

    public void crear() {
        pacienteBD();
        ajustar();
    }

    public void crearContenido() {
        pacienteBD();
        primeraParte = ""
                + "{\"info\":{\"tipo\": 0,\"nombre\":" + "\"" + paciente.getNombre() + " " + paciente.getApellido() + "\",\"edad\": \"" + paciente.getOtro() + "\"}, \"agenda\":" + agendaJSON() + ",\"servicios\":" + listaJSON() + "}";
    }

    private String agendaJSON() {
        agenda.crear(usuario);
        ArrayList<eventosContenedorPaciente> listaFinal = agenda.getListaFinal();
        return json.toJson(listaFinal);
    }

    private String listaJSON() {
        agenda.crear(usuario);
        ArrayList<eventosContenedorPaciente> listaFinal = agenda.getListaFinalAnteriores();
        return json.toJson(listaFinal);
    }

    private void ajustar() {
        clase = "principalUsuario";
        primeraParte = "";
        segundaParte = "<div class='presentacionUsuario'>\n"
                + "     <div class='contenedor'>\n"
                + "          <div class=' imagen'>\n"
                + "               <div style='background-image: url(" + puntos + "getImage.jsp?id=" + usuario.getIdUsuario() + ")'>\n"
                + "                    &nbsp;\n"
                + "               </div>\n"
                + "          </div>\n"
                + "          <div class=' informacionUsuario'>\n"
                + "                <h2>" + paciente.getNombre() + " " + paciente.getApellido() + "</h2>\n"
                + "                <h4>Edad: " + paciente.getEdad() + " años</h4>\n"
                + "                <h4>Tipo de sangre: " + paciente.tipoSangreE(paciente.getTipoSangre()) + "</h4>\n"
                + "                <h4>&nbsp;</h4>\n"
                + "          </div>\n"
                + "      </div>\n"
                + "</div>";
        terceraParte = agendaPacienteM() + crearReview();
        cuartaParte = "</div>";
        quintaParte = "";
        JS = "<script>fila=" + selecionado + "</script>";
    }

    private String agendaPacienteM() {
        agenda.crear(usuario);
        numeroServicios = 0;
        ArrayList<eventosContenedorPaciente> listaFinal = agenda.getListaFinal();
        String respuesta = "<div class='agendaUsuario'>\n"
                + "<div class=' contenedor'>\n";
        personasFamilia = listaFinal.size();
        for (int i = 0; i < personasFamilia; ++i) {
            respuesta += agendaXPersona(listaFinal.get(i), i);
        }
        if(numeroServicios ==0){
            respuesta += "<div class='titulo'> No tienes citas próximas.  </div>";
        }
        respuesta += "</div>\n"
                + "</div>\n";
        return respuesta;
    }

    private String agendaXPersona(eventosContenedorPaciente eventosPersona, int j) {
        String resultado = "";
        if (eventosPersona.isTieneEventos()) {
            if (j == 0) {

                resultado += "<div class='titulo'> Citas próximas </div>";

            } else {

                resultado += "<div class='titulo'> Citas de " + eventosPersona.getPersona().getNombre() + " </div>";

            }
            ++colorInt;
            for (int i = 0; i < eventosPersona.getLista().size(); ++i) {
                resultado += eventosFam(eventosPersona.getLista().get(i), eventosPersona.getPersona());
            }
        } 
        return resultado;
    }

    private String eventosFam(servicioContenedor evento, usuarioObj persona) {
        String resultado = "";
        fecha.setFecha(evento.getFecha());
        fecha.acomodarDia();
        ++numeroServicios;
        resultado += "<div class='notificaciones " + coloresLeves[colorInt] + "' id='not" + evento.getIdServicio() + "'>\n"
                + "     <div class='fecha'>\n"
                + "          <label class='tituloH4'>" + fecha.getDia2Digitos() + "</label>\n"
                + "          <label class='textoLabel'>" + fecha.nombreMes() + "</label>\n"
                + "     </div>\n"
                + "     <div class=' contenido'>\n"
                + "         <label class='tituloH4'>" + evento.getMedico().getNombre() + " " + evento.getMedico().getApellido() + "</label>\n"
                + "         <label class='textoLabel'>" + porEstado(evento.getEstado()) + "</label>"
                + "         <label class='pequeño'>" + evento.getDireccion() + "</label>"
                + "     </div>\n"
                + "     <div class=' mass'  id='masNot" + evento.getIdServicio() + "'  onclick='masNot(" + evento.getIdServicio() + ");'>"
                + "          +"
                + "     </div>"
                + "     <br>"
                + "     <div class='ocultoDiv ocultoOculto' id='oculto" + evento.getIdServicio() + "'>"
                + "          <div class=' nombreNotificacion'>"
                + "                 <label class='tituloExtra'>Cita de "
                + "                      <label class='nombreCap'>" + persona.getNombre() + "</label>"
                + "                 </label>"
                + "                 <label class='opc'>Médico en casa" + "</label>"
                + "          </div>"
                + "          <div class='informacionNotificacion'>"
                + "             <div class='otro'>"
                + "                  <span>Costo aproximado: <label>$" + evento.getCosto() + "</label></span>"
                + "                     <br>"
                + "                 <span>Se atenderá en el sig. lugar :</span>"
                + "                     <br>"
                + "                 <span>" + evento.getDireccion() + "</span>"
                + "             </div>"
                + "             <div class='imagen'>"
                + "                 <div style='background-image: url(" + puntos + "getImage.jsp?id=" + evento.getMedico().getIdUsuario() + ")'>&nbsp; </div>"
                + "                 <br>"
                + "            </div>"
                + "         </div>"
                + "     </div>"
                + "</div>";
        return resultado;
    }

    private String crearReview() {
        numeroServicios = 0;
        ArrayList<eventosContenedorPaciente> listaFinal = agenda.getListaFinalAnteriores();
        String respuesta = "<div class='serviciosUsuario'>\n"
                + "<div class=' contenedor'>\n";
        for (int i = 0; i < listaFinal.size(); ++i) {
            respuesta += reviewXPersona(listaFinal.get(i), i);
        }
        if (numeroServicios == 0) {
            respuesta += "<div class='titulo'> Sin Servicios</div>\n";
        }
        respuesta += "</div>\n"
                + "</div>\n";
        return respuesta;
    }

    private String reviewXPersona(eventosContenedorPaciente eventosPersona, int j) {
        String resultado = "";
        if (eventosPersona.isTieneEventos()) {
            if (!eventosPersona.getLista().isEmpty() && numeroServicios == 0) {
                resultado += "<div class='titulo'> Servicios Anteriores</div>\n"
                        + "   <div class='tabla'>\n"
                        + "      <div class='fila tituloFila'>\n"
                        + "           <div class='indicador'>\n"
                        + "               <span class='flecha'>\n"
                        + "                   &nbsp;\n"
                        + "               </span>\n"
                        + "            </div>\n"
                        + "            <div class='tipo'>\n"
                        + "                 Tipo\n"
                        + "             </div>\n"
                        + "             <div class='persona'>\n"
                        + "                 Persona\n"
                        + "             </div>\n"
                        + "             <div class='fecha'>\n"
                        + "                 Fechas\n"
                        + "             </div>\n"
                        + "             <div class='especilista'>\n"
                        + "                 Especialista\n"
                        + "             </div>\n"
                        + "             <div class='total'>\n"
                        + "                 Total\n"
                        + "             </div>\n"
                        + "      </div>";

            }
            for (int i = 0; i < eventosPersona.getLista().size(); ++i) {
                resultado += reviewIndividual(eventosPersona.getLista().get(i), eventosPersona.getPersona());
            }
        }

        return resultado;
    }

    private String reviewIndividual(servicioContenedor evento, usuarioObj persona) {
        String respuesta = "";
        String activoFlecha = "";
        String display = "display:none";
        if (numeroServicios == 0) {
            activoFlecha = "activoFlecha";
            display = "";
            selecionado = evento.getIdServicio();
        }
        ++numeroServicios;
        fecha.setFecha(evento.getFecha());
        respuesta = respuesta + ""
                + "<div class='fila'  id='fila" + evento.getIdServicio() + "' onclick='tablaPagos(" + evento.getIdServicio() + ")'>"
                + "   <div class='indicador' >\n"
                + "       <span class='flecha " + activoFlecha + "' id='flecha" + evento.getIdServicio() + "'>\n"
                + "           &rsaquo;\n"
                + "       </span>\n"
                + "   </div>"
                + "   <div class='tipo'>\n"
                + "       <div class='ayuda'>\n"
                + "           Tipo:\n"
                + "       </div>\n"
                + "       MC\n"
                + "   </div>"
                + "   <div class='persona'>\n"
                + "       <div class='ayuda'>\n"
                + "           persona:\n"
                + "       </div>\n"
                + "       " + persona.getNombre() + "\n"
                + "   </div>"
                + "   <div class='fecha'>\n"
                + "       <div class='ayuda'>\n"
                + "           fecha:\n"
                + "       </div>\n"
                + "      " + fecha.getFechaDiagonales() + "\n"
                + "   </div>"
                + "   <div class='especilista'>\n"
                + "      <div class='ayuda'>\n"
                + "          especilista:\n"
                + "      </div>\n"
                + "           " + evento.getMedico().getNombre() + "\n"
                + "   </div>"
                + "   <div class='total'>\n"
                + "       <div class='ayuda'>\n"
                + "           total:\n"
                + "       </div>\n"
                + "       Total\n"
                + "   </div>"
                + "</div>"
                + "<div class='masInformacion' id='panel" + evento.getIdServicio() + "'style='" + display + "'>"
                + "   <div class='mapaPago'>\n"
                + "       <div class='mapaContenedor'>\n"
                + "           &nbsp;\n"
                + "       </div>\n"
                + "       <div class='boton amarilloBotones' onclick='reportarErrorServicio(" + evento.getIdServicio() + ")'>\n"
                + "           <label>\n"
                + "              reportar error\n"
                + "           </label>\n"
                + "       </div>\n"
                + "   </div>"
                + "   <div class='costosPago'> \n"
                + "       <div class=''>\n"
                + "           <label class='tituloLabel'>Dirección:</label> <br>\n"
                + "           <label>" + evento.getDireccion() + "</label>\n"
                + "       </div>\n"
                + "       <div>\n"
                + "           <label class='tituloLabel'>Costos:</label><br>\n"
                + "           <span>\n"
                + "               <label class='izquierda'> Consulta</label>\n"
                + "               <label class='derecha'>$100</label>\n"
                + "           </span> <br>\n"
                + "           <span>\n"
                + "                <label class='izquierda'> Consulta</label>\n"
                + "                <label class='derecha'>$100</label>\n"
                + "           </span> <br>\n"
                + "           <span>\n"
                + "                 <label class='izquierda'> Consulta</label>\n"
                + "                 <label class='derecha'>$100</label>\n"
                + "           </span> <br>\n"
                + "           <span class='line'>&nbsp;</span>\n"
                + "           <span>\n"
                + "                <label class='izquierda'> Consulta</label>\n"
                + "                <label class='derecha'>$100</label>\n"
                + "           </span> <br>\n"
                + "        </div>\n"
                + "   </div>"
                + verificaComentario(evento.getIdServicio())
                + "</div>";
        return respuesta;
    }

    private String verificaComentario(String idServicio) {
        String resultado = "";
        cDatos base = new cDatos();
        ResultSet res;
        try {
            base.conectar();
            res = base.consulta("select * from comentarios where idCita = " + idServicio + ";");
            if (res.next()) {
                resultado = "<div class='comentario'>\n"
                        + "      <span class='line '>\n"
                        + "          &nbsp;\n"
                        + "      </span>\n"
                        + "      <div>\n"
                        + "          <label class='tituloLabel'>\n"
                        + "              Calificación al especialista:\n"
                        + "          </label>\n"
                        + "      </div>\n"
                        + "      <div>\n"
                        + estrellitas.estrellasDesactivado(res.getInt("calif"), idServicio + "")
                        + "       </div>\n"
                        + "       <div>\n"
                        + "           &nbsp;\n"
                        + "       </div>\n"
                        + "       <div>\n"
                        + "           <label  class='tituloLabel'>Comentario:</label>\n"
                        + "       </div>\n"
                        + "       <div>\n"
                        + "           <textarea maxlength='150' disabled=''>" + res.getString("mensaje") + "</textarea>\n"
                        + "       </div>\n"
                        + "       <div>\n"
                        + "            <label class='anotaciones'>Tu comentario y calificación son anónimas.</label>\n"
                        + "       </div>\n"
                        + "       <div class='boton azulBotones ' style='opacity:0;'>\n"
                        + "            <label>Enviar</label>\n"
                        + "       </div>\n"
                        + "   </div>";
            } else {
                resultado = "<div class='comentario'>\n"
                        + "      <span class='line '>\n"
                        + "          &nbsp;\n"
                        + "      </span>\n"
                        + "      <div>\n"
                        + "          <label class='tituloLabel'>\n"
                        + "              Calificar al especialista:\n"
                        + "          </label>\n"
                        + "      </div>\n"
                        + "      <div>\n"
                        + estrellitas.estrellasActivo(idServicio + "")
                        + "       </div>\n"
                        + "       <div>\n"
                        + "           &nbsp;\n"
                        + "       </div>\n"
                        + "       <div>\n"
                        + "           <label  class='tituloLabel'>Comentario:</label>\n"
                        + "       </div>\n"
                        + "       <div>\n"
                        + "           <textarea maxlength='150' id='contenidoComentario-" + idServicio + "' name='contenidoComentario-" + idServicio + "' placeholder='Máximo 150 caracteres.'></textarea>\n"
                        + "       </div>\n"
                        + "       <div>\n"
                        + "            <label class='anotaciones'>Tu comentario y calificación serán anónimas.</label>\n"
                        + "       </div>\n"
                        + "       <div class='boton azulBotones ' style='' onclick='comentario(" + idServicio + ")' >\n"
                        + "            <label>Enviar</label>\n"
                        + "       </div>\n"
                        + "   </div>";
            }
            base.cierraConexion();
        } catch (Exception e) {
            System.err.println("Error en paciente.ventanaPrincipal.verificaComentario():" + e.toString());
            resultado = "";
        }
        return resultado;
    }

    private void pacienteBD() {
        cDatos base = new cDatos();
        ResultSet res;
        try {
            base.conectar();
            res = base.consulta("SELECT * FROM cuentaU JOIN histMed ON cuentaU.idPaciente = histMed.idPaciente where cuentaU.idPaciente = " + usuario.getIdUsuario() + ";");
            if (res.next()) {
                paciente.setNombre(usuario.getNombre());
                paciente.setApellido(usuario.getApellido());
                paciente.setEdad(res.getShort("edad"));
                paciente.setTipoSangre(res.getShort("tipoSangre"));
            }
            base.cierraConexion();
        } catch (SQLException e) {
            System.out.println("error en clase  ventanaPricnipal paciente: " + e.toString());
        }
    }

}
