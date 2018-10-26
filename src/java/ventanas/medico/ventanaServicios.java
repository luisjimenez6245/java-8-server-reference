/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas.medico;

import BD.cDatos;
import agenda.agendaMedico;
import interfaceP.fechas;
import java.sql.ResultSet;
import paciente.pacienteContenedor;
import servicios.servicioContenedor;

/**
 *
 * @author luisjimenezdelgado
 */
public class ventanaServicios extends interfaceP.ventanaPadre {

    private agendaMedico alta = new agendaMedico();
    private servicioContenedor servicio = new servicioContenedor();
    private pacienteContenedor paciente = new pacienteContenedor();

    public ventanaServicios() {
        super();
    }

    public void crear() {
        ajustar();
    }

    private void ajustar() {
        if (servicio()) {
            clase = "serviciosUsuario";
            JS = "";
            servicio = alta.getServicioUnico();
            ultimoServicio();

            primeraParte = "<div class='sinCargar' id='cargando'>\n"
                    + "        <div class=\"loader amarilloSvg loader--style3\" >\n"
                    + "            <svg version=\"1.1\" id=\"loader-1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\"\n"
                    + "                 width=\"50px\" height=\"50px\" viewBox=\"0 0 50 50\" style=\"enable-background:new 0 0 50 50;\" xml:space=\"preserve\">\n"
                    + "                <path fill=\"#000\" d=\"M43.935,25.145c0-10.318-8.364-18.683-18.683-18.683c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615c8.072,0,14.615,6.543,14.615,14.615H43.935z\">\n"
                    + "                <animateTransform attributeType=\"xml\"\n"
                    + "                                  attributeName=\"transform\"\n"
                    + "                                  type=\"rotate\"\n"
                    + "                                  from=\"0 25 25\"\n"
                    + "                                  to=\"360 25 25\"\n"
                    + "                                  dur=\"0.9s\"\n"
                    + "                                  repeatCount=\"indefinite\"/>\n"
                    + "               </path>\n"
                    + "            </svg>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "    \n<div class='mapa  mapaMedico'>\n"
                    + "        &nbsp;\n"
                    + "        <div class='map ' id='map'>&nbsp;</div>\n"
                    + "    </div>\n";
            segundaParte = ""
                    + "\n<div class='opciones opcionesMedico ' id='primeroServicio' >\n"
                    + "   <div class='titulo amarilloTitulos'>\n"
                    + "          <div class='fotoMedico'> \n"
                    + "             <span style='background-image: url(" + puntos + "getImage.jsp?id=" + paciente.getIdUsuario() + ")' > "
                    + "                 \n&nbsp;"
                    + "             </span> \n"
                    + "         </div> \n"
                    + "         <div class='infoMedico' style= 'color: white;'>\n"
                    + "                <label class='nombreMed' > Nombre: " + paciente.getNombre() + " " + paciente.getApellido() + " </label> \n"
                    + "                <label class='nombreMed' style='opacity:.8;' >Años:  " + paciente.getEdad() + "</label> \n"
                    + "                <label class='nombreMed' style='opacity:.8;' >Telefono: </label> \n"
                    + "         </div>\n"
                    + "    </div>\n"
                    + "    <div class='flexibleContent'>\n"
                    + "         <div class='divConfirmacion' id='segundoServicioMedicos'>\n"
                    //
                    + ventanas()
                    + "         </div>\n"
                    + "     </div>\n"
                    + "</div>\n";
            JS = new mapa.mapa().jsVentana6(servicio.getLongitud(), servicio.getLatitud());

        } else {
            clase = "familia";
            primeraParte = sinFamilia();
            segundaParte = "";

            JS = "";
        }
        terceraParte = "";
        cuartaParte = "";
        quintaParte = "";

    }

    private String sinFamilia() {
        String respuesta = ""
                + "<div class='sinFamilia'>\n"
                + "      <br>"
                + "      <br>"
                + "     <div class='container'>\n"
                + "          <div class='imagen'>\n"
                + "               <span><span id='personasAmarillas'>&nbsp;</span></span>\n"
                + "               <label class='grueso'>\n"
                + "                  Por el momento no tienes ningún servicio\n"
                + "               </label>\n"
                + "               <label class='delgado'>\n"
                + "                   Regresa más tarde\n"
                + "               </label>\n"
                + "          </div>\n"
                + "     </div>\n"
                + "</div>";
        return respuesta;
    }

    private boolean servicio() {
        try {
            return alta.consultaProxima(usuario.getIdUsuario()) >= 1; // servicio  = alta.getServicioUnico();
        } catch (Exception e) {
            System.out.println("Error en medico.ventanaServicios.servicio() : " + e.toString());
            return false;
        }

    }

    private void ultimoServicio() {
        cDatos base = new cDatos();
        ResultSet res;
        try {
            base.conectar();
            res = base.consulta("Select * from cuentaU join histMed on cuentaU.idPaciente = histMed.idPaciente where cuentaU.idPaciente = " + servicio.getPaciente().getIdUsuario() + ";");
            if (res.next()) {
                paciente.setIdUsuario(servicio.getPaciente().getIdUsuario());
                paciente.setNombre(res.getString("nombre"));
                paciente.setApellido(res.getString("apellido"));
                paciente.setEdad(res.getShort("edad"));
                paciente.setAlergias(res.getString("alergias"));
                //  paciente.setTelefono(res.getString(""));
                paciente.setPeso(res.getDouble("peso"));
                paciente.setAltura(res.getDouble("estatura"));
                paciente.setTipoSangre(res.getShort("tipoSangre"));
                paciente.setGenero(res.getShort("sexo"));
            }
            base.cierraConexion();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private String ventanas() {
        serviciosPartesMedico partes = new serviciosPartesMedico();
        partes.setPaciente(paciente);
        partes.setServicio(servicio);
        String resultado = partes.ventana3();
        return resultado;
    }

}
