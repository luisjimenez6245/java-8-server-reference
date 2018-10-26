/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas.panciente;

import BD.cDatos;
import java.sql.ResultSet;
import paciente.familia.familiaObj;
import pagos.tarjetaContenedor;

/**
 *
 * @author luisjimenezdelgado
 */
public class ventanaFamilia extends interfaceP.ventanaPadre {

    private pacienteContenedor paciente = new pacienteContenedor();
    private familiaObj familia = new familiaObj();
    private tarjetaContenedor tarjeta = new tarjetaContenedor();

    public ventanaFamilia() {
        super();
    }

    public void crear() {
        ajustar();
    }

    private void ajustar() {
        System.out.println(usuario.getIdUsuario());
        familia.setPacienteaChecar(usuario);
        clase = "familia";
        if (familia.isTieneFamilia() == 1) {
            pacienteBD();
            familia.buscarFamilia();
            JS = "";

            primeraParte = parteArriba();
            segundaParte = " <div class='cabezas'>\n"
                    + "     <div class='contenedor'>\n";
            terceraParte = cabezas();
            cuartaParte = "</div>\n"
                    + "</div>";
        } else {
            primeraParte = sinFamilia();
            segundaParte = "";
            terceraParte = "";
            cuartaParte = "";
            JS = "";
        }

        quintaParte = "";

    }

    private String parteArriba() {
        String respuesta = ""
                + "<div class='presentacionUsuario'>\n"
                + "    <div class='contenedor'>\n"
                + "        <div class=' imagen'>\n"
                + "            <div style='background-image: url(" + puntos + "getImage.jsp?id=" + usuario.getIdUsuario() + ")'>\n"
                + "                &nbsp;\n"
                + "            </div>\n"
                + "        </div>\n"
                + "        <div class=' informacionUsuario'>\n"
                + "            <h2>" + usuario.getNombre() + " " + usuario.getApellido() + "</h2>\n"
                + "            <h4>" + usuario.getOtro2() + "</h4>\n"
                + "            <h4>Código de Familia:<strong> " + usuario.getOtro() + "</strong></h4>\n"
                + "            <h4 class='pequeño'> "+tarjeta.tipoTarjeta()+" <strong> " + tarjeta.getUltimoDigitos() + "</strong> </h4>\n"
                + "            <br>\n"
                + "         </div>\n"
                + "         <div class='botones'>\n"
                + "             <div class='boton moradoBotones' onclick='editarFamilia()'>\n"
                + "                 <label id='editar'>\n"
                + "                       Editar\n"
                + "                 </label>\n"
                + "             </div>\n";
        if (familia.getLista().isEmpty()) {
            respuesta += "       <div class='boton rojosoFuerte' id = 'contenedoreliminarFam' style= 'display: none;'>\n"
                    + "             <label id='eliminarFam'>\n"
                    + "                 Agregar a un familiar\n"
                    + "             </label>\n"
                    + "         </div>\n";
            JS += "<script> "
                    + "var sinFamiliares = true;\n"
                    + "</script>";
        } else {
            respuesta += "       <div class='boton rojosoFuerte'>\n"
                    + "             <label id='eliminarFam'>\n"
                    + "                 Agregar a un familiar\n"
                    + "             </label>\n"
                    + "         </div>\n";
            JS += "<script> "
                    + "var sinFamiliares = false;\n"
                    + "</script>";
        }
        respuesta += "      </div>\n"
                + "    </div>\n"
                + "</div>\n"
                + "<br>\n"
                + "<br>";
        return respuesta;
    }

    private String cabezas() {
        String respuesta = "";
        if (familia.getLista().isEmpty()) {
            respuesta += ""
                    + "<div class='container' id='agregar' style=''>\n"
                    + "    <div class='imagen' onclick='agregaFamiliar()'>\n"
                    + "        <div class='moradoBotones'>\n"
                    + "            <div class='agregarUltimo'>\n"
                    + "                &boxvh;\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "    <div>\n"
                    + "         <label class='nombre '>"
                    + "            Agregar a un familiar\n"
                    + "        </label>\n"
                    + "        <label class='titulo'>\n"
                    + "            &nbsp;\n"
                    + "        </label>\n"
                    + "     </div>\n"
                    + "   </div>";
        } else {
            for (int i = 0; i < familia.getLista().size(); ++i) {
                respuesta = respuesta
                        + "<div class='container'>\n"
                        + "    <div class='imagen'>\n"
                        + "        <div style='background-image: url(" + puntos + "getImage.jsp?id=" + familia.getLista().get(i).getIdUsuario() + ")'>\n"
                        + "            <div class='borrar' onclick='eliminarFamiliar(" + familia.getLista().get(i).getIdUsuario() + ");' style='display: none'>\n"
                        + "                &times;\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "    <div>\n"
                        + "        <label class='nombre nombreCap'>\n"
                        + "            " + familia.getLista().get(i).getNombre() + "\n"
                        + "        </label>\n"
                        + "        <label class='titulo'>\n"
                        + familia.getLista().get(i).getOtro()
                        + "        </label>\n"
                        + "    </div>\n"
                        + "</div>\n";
            }
            respuesta += ""
                    + "<div class='container' id='agregar' style=' display: none'>\n"
                    + "    <div class='imagen' onclick='agregaFamiliar()'>\n"
                    + "        <div class='moradoBotones'>\n"
                    + "            <div class='agregarUltimo'>\n"
                    + "                &boxvh;\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "    <div>\n"
                    + "       <label class='nombre '>"
                    + "            Agregar a un familiar\n"
                    + "        </label>\n"
                    + "        <label class='titulo'>\n"
                    + "            &nbsp;\n"
                    + "        </label>\n"
                    + "     </div>\n"
                    + "   </div>";

        }
        return respuesta;
    }

    private String sinFamilia() {
        String respuesta = ""
                + "<div class='sinFamilia'>\n"
                + "     <div class='container'>\n"
                + "          <div class='imagen'>\n"
                + "               <span><span>&nbsp;</span></span>\n"
                + "               <label class='grueso'>\n"
                + "                   Aún no haz configurado tú familia.\n"
                + "               </label>\n"
                + "               <label class='delgado'>\n"
                + "                   La opcion de familia esta diseñada para:\n"
                + "               </label>\n"
                + "          </div>\n"
                + "          <div class='botones'>\n"
                + "              <span onclick='configurarFamilia()' class='moradoBotones'>Configurar ahora</span>\n"
                + "          </div>\n"
                + "     </div>\n"
                + "</div>";
        return respuesta;
    }

    private void pacienteBD() {
        cDatos base = new cDatos();
        ResultSet res;
        try {
            base.conectar();
            res = base.consulta("select familiares.tipo, familia.codigoFam, noTarjeta from familia join familiares on familia.idFam = familiares.idFam join tarjeta on familia.idTarjeta = tarjeta.idTarjeta  where familiares.idPaciente = " + usuario.getIdUsuario() + ";");
            if (res.next()) {
                usuario.setOtro(res.getString("codigoFam") + "");
                usuario.setOtro2(res.getInt("tipo") + "");
                tarjeta.setTarjeta(res.getString("noTarjeta"));
            }
            usuario.setOtro2(familia.tipoFamiliar(usuario.getOtro2()));
            base.cierraConexion();
        } catch (Exception e) {
            System.out.println("Error a la hora de crear la ventana de familia: " + e.toString());
        }

    }

    
}
