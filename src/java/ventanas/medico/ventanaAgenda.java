/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas.medico;

import agenda.agendaMedico;
import interfaceP.ventanaPadre;
import java.util.ArrayList;
import servicios.servicioContenedor;

/**
 *
 * @author luisjimenezdelgado
 */
public class ventanaAgenda extends ventanaPadre {

    private agendaMedico agenda = new agendaMedico();

    public ventanaAgenda() {
        super();
    }

    public void crear() {
        ajustar();
    }

    private void ajustar() {

        clase = "principalUsuario";

        JS = "";

        primeraParte = ""
                + ""
                + "<div class='presentacionUsuario'>\n"
                + "    <div class='contenedor'>\n"
                + "         <div class=' imagen'>\n"
                + "             <div style='background-image: url(" + puntos + "getImage.jsp?id=" + usuario.getIdUsuario() + ")'>\n"
                + "                   &nbsp;\n"
                + "             </div>\n"
                + "         </div>\n"
                + "         <div class=' informacionUsuario'>\n"
                + "            <h2>" + usuario.getNombre() + " " + usuario.getApellido() + "</h2>\n"
                + "            <h4>" + usuario.getOtro() + "</h4>\n"
                + "            <h4>" + estrellitas.estrellasDesactivado(4, JS) + "</h4>\n"
                + "            <h4>&nbsp;</h4>\n"
                + "         </div>\n"
                + "         <div class='textoS'>\n"
                + "             <div class='boton '  >\n"
                + "                  <label id='editar'>\n"
                + "                      Numero de Servicios  del mes:  <label class='grueso'>10</label>\n"
                + "                  </label>\n"
                + "             </div>\n"
                + "             <div class='boton'>\n"
                + "                  <label id='eliminarFam'>\n"
                + "                     Ingresos generados: <label class='grueso'>$1000.00</label>\n"
                + "                  </label>\n"
                + "             </div>\n"
                + "         </div>\n"
                + "    </div>\n"
                + "</div>";
        segundaParte = ""
                + "<div class='agendaMedico'>\n"
                + "    <div class='header'>\n"
                + "        <div>\n"
                + "            <span >\n"
                + "                Día\n"
                + "            </span>\n"
                + "            <span class='activo'>\n"
                + "                Semana\n"
                + "            </span>\n"
                + "        </div>\n"
                + "    </div>\n";
        terceraParte = porSemana();
        cuartaParte = porDia();
        quintaParte = "</div>\n";
        JS = "";
    }

    private String porSemana() {
        String resultado = ""
                + "<div class='semana' style=\"\">\n"
                + "    <div class=\"tablita\">\n"
                + "        <div class='flex'>\n"
                + "            <div class='tablas'>";
        fecha.fechaActual();
        fecha.diaAnterior();
        agenda.dias(usuario.getIdUsuario());
        for (int i = 0; i < 7; ++i) {
            if (agenda.getListaDias()[i]) {
                fecha.diaSiguiente();
                fecha.diaSinHoras();
                System.out.println(fecha.getFechaDiagonales());
                resultado += Dia();
            } else {

            }

        }
        resultado += "             </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</div>";
        return resultado;
    }

    private String Dia() {
        String resultado = "";
        ArrayList<servicioContenedor> servicios = agenda.consultarBasePorDia(usuario.getIdUsuario(), fecha.getFecha().getTime());
        if (!servicios.isEmpty()) {
            fecha.acomodarDia();
            resultado += ""
                    + "<div class='container'>\n"
                    + "     <div class='titulo " + fecha.getColorFuerteDia() + " '>\n"
                    + "         " + fecha.nombreDia() + "\n"
                    + "     </div>"
                    + "     <div class='content'>\n"
                    + "             <div class='flexY'>\n"
                    + "                  <div class=\"tabla\">";
            for (int i = 0; i < servicios.size(); ++i) {
                resultado += porEvento(servicios.get(i));
            }
            resultado += "               </div>\n"
                    + "             </div>\n"
                    + "     </div>\n"
                    + "</div>\n";

        }
        return resultado;
    }

    private String porEvento(servicioContenedor evento) {
        fecha.setFecha(evento.getFecha());
        String respuesta = ""
                + "<div class='notificaciones notificacionesAgenda " + fecha.getColorLeveDia() + "' id='not" + evento.getIdServicio() + "'>\n"
                + "    <div class='fecha'>\n"
                + "          <label class='tituloH4'>" + fecha.getDiaReal() + "</label>\n"
                + "          <label class='textoLabel'>" + fecha.nombreMes() + "</label>\n"
                + "    </div>\n"
                + "    <div class=' contenido'>\n"
                + "         <label class='tituloH4'>" + evento.getMedico().getNombre() + " " + evento.getMedico().getApellido() + "</label>\n"
                + "         <label class='textoLabel'>" + fecha.getHorarioInicioFinal() + "</label>"
                + "         <label class='pequeño'>" + evento.getDireccion() + "</label>"
                + "    </div>\n"
                + "    <div class=' mass'  id='masNot" + evento.getIdServicio() + "'  onclick='masNot(" + evento.getIdServicio() + ");'>\n"
                + "          +\n"
                + "    </div>\n"
                + "    <br>\n"
                + "     <div class='ocultoDiv ocultoOculto' id='oculto" + evento.getIdServicio() + "'>"
                + "        <div class=' nombreNotificacion'>\n"
                + "            <label class='tituloExtra'>Cita de\n"
                + "                <label class='nombreCap'>Luis diego</label>\n"
                + "            </label>\n"
                + "            <label class='opc'>\n"
                + "                Médico en casa\n"
                + "            </label>\n"
                + "        </div>\n"
                + "        <div class='informacionNotificacion'>\n"
                + "            <div class='otro'>\n"
                + "                <span>Costo aproximado: <label>$400.00</label></span>\n"
                + "                <br>\n"
                + "                <span>Se atenderá en el sig. lugar :</span>\n"
                + "                <br>\n"
                + "                <span>" + evento.getDireccion() + "</span>\n"
                + "            </div>\n"
                + "            <div class='imagen'>\n"
                + "                 <div style='background-image: url(" + puntos + "getImage.jsp?id=" + evento.getMedico().getIdUsuario() + ")'>&nbsp; </div>"
                + "                    &nbsp;\n"
                + "                </div>\n"
                + "                <br>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "";
        return respuesta;
    }

    private String porDia() {
        String resultado = "";

        return resultado;
    }

}
