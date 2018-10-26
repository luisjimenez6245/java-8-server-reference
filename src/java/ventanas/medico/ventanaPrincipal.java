/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas.medico;

import agenda.agendaMedico;


import java.util.ArrayList;
import servicios.servicioContenedor;

/**
 *
 * @author luisjimenezdelgado
 */
public class ventanaPrincipal extends objetos.objetoVentana {

    private int numeroServicios;
    private agendaMedico agenda = new agendaMedico();

    private String selecionado;

    public ventanaPrincipal(Boolean html, String posicionRelativa) {
        super(html, posicionRelativa);
        ajustar();
    }

    public void crear() {
        ajustar();
    }
    
    public void crearContenido() {
        primeraParte = ""
                + "{\"info\":{\"tipo\": 1,\"nombre\":" + "\"" + usuario.getNombre() + " " + usuario.getApellidos()+ "\",\"tipoEspecialista\": \"" + usuario.getOtro()+ "\"}, \"agenda\":" + agendaJSON() +",\"servicios\":" + listaJSON()+"}";
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
                + "                <h2>" + usuario.getNombre() + " " + usuario.getApellido() + "</h2>\n"
                + "                <h4>" + usuario.getOtro() + "</h4>\n"
                + "                <h4>" + estrellitas.estrellasDesactivado(4, JS) + "</h4>\n"
                + "                <h4>&nbsp;</h4>\n"
                + "          </div>\n"
                + "      </div>\n"
                + "</div>";
        terceraParte = agendaMedico();
        cuartaParte = crearReview();
        quintaParte = "</div>";
        JS = "<script>fila="+selecionado+"</script>";
    }
    
    
    private String agendaJSON() {
        ArrayList<servicioContenedor> listaFinal = agenda.consultarBase(usuario.getIdUsuario());
       // return json.toJson(listaFinal);
       return "";
    }

    private String listaJSON() {
        ArrayList<servicioContenedor> listaFinal = agenda.serviciosAnteriores(usuario.getIdUsuario());
// return json.toJson(listaFinal);
       return "";
    }

    private String crearReview() {
        numeroServicios = 0;
        ArrayList<servicioContenedor> listaFinal = agenda.serviciosAnteriores(usuario.getIdUsuario());
        String respuesta = "<div class='serviciosUsuario'>\n<div class='titulo'> \n"
                + "<div style='float: left' id='pagosTitulo' class='activo' onclick='pagosCalificaciones()'>\n"
                + "                            <span>Pagos</span>\n"
                + "                        </div>\n"
                + "                        <div style='float: right' id='calificacionesTitulo' class='' onclick='pagosCalificaciones()'>\n"
                + "                            <span>Calificaciones</span>\n"
                + "                        </div>\n"
                + "                    </div>"
                + "<div class='tabla'>\n"
                + "   <div class='contenedor'>\n"
                + "                            <div class='tablas'>";
        if (!listaFinal.isEmpty()) {
            respuesta += "<div class='fila tituloFila'>\n"
                    + "                                    <div class='indicador'>\n"
                    + "                                        <span class='flecha'>\n"
                    + "                                            &nbsp;\n"
                    + "                                        </span>\n"
                    + "                                    </div>\n"
                    + "                                    <div class='tipo'>\n"
                    + "                                        Tipo\n"
                    + "                                    </div>\n"
                    + "                                    <div class='persona'>\n"
                    + "                                        Persona\n"
                    + "                                    </div>\n"
                    + "                                    <div class='fecha'>\n"
                    + "                                        Fechas\n"
                    + "                                    </div>\n"
                    + "                                    <div class='especilista'>\n"
                    + "                                        Especialista\n"
                    + "                                    </div>\n"
                    + "                                    <div class='total'>\n"
                    + "                                        Total\n"
                    + "                                    </div>\n"
                    + "                                </div>";
            for (int i = 0; i < listaFinal.size(); ++i) {
                respuesta += reviewIndividual(listaFinal.get(i));
            }
        } else {
        }

        respuesta += "</div>\n"
                + "</div>\n"
                + "</div>\n";
        return respuesta;
    }

    private String reviewIndividual(servicioContenedor evento) {
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
        respuesta = "<div class='fila'  id='fila" + evento.getIdServicio() + "' onclick='tablaPagos(" + evento.getIdServicio() + ")'>\n"
                + "    <div class='indicador' >\n"
                + "       <span class='flecha " + activoFlecha + "' id='flecha" + evento.getIdServicio() + "'>\n"
                + "            &rsaquo;\n"
                + "        </span>\n"
                + "    </div>\n"
                + "    <div class='tipo'>\n"
                + "        <div class='ayuda'>\n"
                + "            Tipo:\n"
                + "        </div>\n"
                + "        MC\n"
                + "    </div>\n"
                + "    <div class='persona'>\n"
                + "        <div class='ayuda'>\n"
                + "            persona:\n"
                + "        </div>\n"
                + "       " + evento.getMedico().getNombre() + "\n"
                + "    </div>\n"
                + "    <div class='fecha'>\n"
                + "        <div class='ayuda'>\n"
                + "            fecha:\n"
                + "        </div>\n"
                + "      " + fecha.getFechaDiagonales() + "\n"
                + "    </div>\n"
                + "    <div class='especilista'>\n"
                + "        <div class='ayuda'>\n"
                + "            especilista:\n"
                + "        </div>\n"
                + "        Especialista\n"
                + "    </div>\n"
                + "    <div class='total'>\n"
                + "        <div class='ayuda'>\n"
                + "            total:\n"
                + "        </div>\n"
                + "        Total\n"
                + "    </div>\n"
                + "</div>\n"
                + "<div class='masInformacion' id='panel" + evento.getIdServicio() + "'style='" + display + "'>"
                + "    <div class='mapaPago'>\n"
                + "        <div class='mapaContenedor'>\n"
                + "            &nbsp;\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class='costosPago'> \n"
                + "        <div class=''>\n"
                + "            <label class='tituloLabel'>Dirección:</label> <br>\n"
                + "           <label>" + evento.getDireccion() + "</label>\n"
                + "        </div>\n"
                + "       <div class='boton amarilloBotones' onclick='reportarErrorServicio(" + evento.getIdServicio() + ")'>\n"
                + "            <label>\n"
                + "                reportar error\n"
                + "            </label>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <div class='costosPago'> \n"
                + "        <div>\n"
                + "            <label class='tituloLabel'>Costos:</label><br>\n"
                + "            <span>\n"
                + "                <label class='izquierda'> Consulta</label>\n"
                + "                <label class='derecha'>$100</label>\n"
                + "            </span> <br>\n"
                + "            <span>\n"
                + "                <label class='izquierda'> Consulta</label>\n"
                + "                <label class='derecha'>$100</label>\n"
                + "            </span> <br>\n"
                + "            <span>\n"
                + "                <label class='izquierda'> Consulta</label>\n"
                + "                <label class='derecha'>$100</label>\n"
                + "            </span> <br>\n"
                + "            <span class='line'>&nbsp;</span>\n"
                + "            <span>\n"
                + "                <label class='izquierda'> Consulta</label>\n"
                + "                <label class='derecha'>$100</label>\n"
                + "            </span>\n"
                + "            <br>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</div>";
        return respuesta;
    }

    private String agendaMedico() {
        ArrayList<servicioContenedor> servicios = agenda.consultarBase(usuario.getIdUsuario());
        String respuesta = "<div class='agendaUsuario'>\n"
                + "<div class=' contenedor'>\n";
        if (servicios.isEmpty()) {
            respuesta += "<div class='titulo'> No tienes citas próximas </div>";
        } else {
            respuesta += "<div class='titulo'> Citas Próximas </div>";
            for (int i = 0; i < servicios.size(); ++i) {
                respuesta += miAgenda(servicios.get(i));
            }
        }
        respuesta += "</div>\n"
                + "</div>\n";
        return respuesta;
    }

    private String miAgenda(servicioContenedor evento) {
        String resultado = "";
        fecha.setFecha(evento.getFecha());
        fecha.acomodarDia();
        setDia();
        resultado += "<div class='notificaciones azulado" + "' id='not" + evento.getIdServicio() + "'>\n"
                + "     <div class='fecha'>\n"
                + "          <label class='tituloH4'>" + fecha.getDia2Digitos() + "</label>\n"
                + "          <label class='textoLabel'>" + fecha.nombreMes() + "</label>\n"
                + "     </div>\n"
                + "     <div class=' contenido'>\n"
                + "         <label class='tituloH4'>" + evento.getMedico().getNombre() + " " + evento.getMedico().getApellido() + "</label>\n"
                + "         <label class='textoLabel'>" + fecha.getHorarioInicioFinal() + "</label>"
                + "         <label class='pequeño'>" + evento.getDireccion() + "</label>"
                + "     </div>\n"
                + "     <div class=' mass'  id='masNot" + evento.getIdServicio() + "'  onclick='masNot(" + evento.getIdServicio() + ");'>"
                + "          +"
                + "     </div>"
                + "     <br>"
                + "     <div class='ocultoDiv ocultoOculto' id='oculto" + evento.getIdServicio() + "'>"
                + "          <div class=' nombreNotificacion'>"
                + "                 <label class='tituloExtra'>Servicio en casa"
                + "                      <label class='nombreCap'></label>"
                + "                 </label>"
                + "                 <label class='opc'>" + "</label>"
                + "          </div>"
                + "          <div class='informacionNotificacion'>"
                + "             <div class='otro'>"
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


}
