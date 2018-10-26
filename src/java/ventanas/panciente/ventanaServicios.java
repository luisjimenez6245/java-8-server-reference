/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas.panciente;

import paciente.familia.familiaObj;

/**
 *
 * @author luisjimenezdelgado
 */
public class ventanaServicios extends interfaceP.ventanaPadre {

    private final String authKey = "AIzaSyCz21FrJ-OmK-S2uqZc8FFe6oXU0IeJs9I";

    public ventanaServicios() {
        super();
    }

    public void crear() {
        ajustar();
    }

    private void ajustar() {
        clase = "serviciosUsuario";
        alertas = "";
        primeraParte = ""
                + "    <div class='sinCargar' id='cargando'>\n"
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
                + "    </div>"
                + "    <div class='mapa '>\n"
                + "        &nbsp;\n"
                + "        <div class='map ' id='map'>&nbsp;</div>\n"
                + "    </div>";
        segundaParte = "<div class='opciones opcionesPaciente' id='primeroServicio' >\n"
                + "    <div class='titulo amarilloTitulos'>\n"
                + "        <label class='grande'>&nbsp; &nbsp;&nbsp;Servicios</label>\n"
                + "        <label class='pequeño'>&nbsp;&nbsp; Seleccione el servicio que requiere</label>\n"
                + "    </div>\n"
                + "    <div class='flexibleContent'>\n"
                + "        <div class='divConfirmacion'>\n"
                + "            <br>\n"
                + "            <div class='tituloSuelto'>\n"
                + "                Tipo\n"
                + "            </div>\n"
                + "            <div class='opcBotones'>\n"
                + "                <span class=''  id='opcMedico' onclick='servicioSelec(0)'>\n"
                + "                     <label>\n"
                + "                          Servicio ahora\n"
                + "                     </label>\n"
                + "                </span>\n"
                + "                <span class='' id='opcEnfermero' onclick='servicioSelec(1)' style = ''> \n"
                + "                      <label>\n"
                + "                          Servicio agendado\n"
                + "                      </label>\n"
                + "                </span>\n"
                + "            </div>\n"
                + "            <div class='tituloSuelto'>\n"
                + "                paciente\n"
                + "            </div>\n"
                + "            <div class='imagenesTable'>\n"
                + "                <div class='flexCont'>\n"
                + "                    <div class='containerTable'>\n"
                + familia()
                + "                    </div>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "            <div class='divSolicitar'>\n"
                + "                <div class='amarilloBotones' onclick='siguienteServicio(0)'>\n"
                + "                    <label>\n"
                + "                       Aceptar\n"
                + "                    </label>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "         </div>\n"
                + "     </div>\n"
                + "</div>";
        terceraParte = ""
                + "<div class='opciones opcionesPaciente oculto' id='segundoServicio' >\n"
                + "    <div class='titulo amarilloTitulos'>\n"
                + "        <label class='grande'  id='segundoServicioGrande' onclick='anteriorServicio(1)'> <span>&nbsp;&nbsp;&nbsp;</span> Médico en casa</label>\n"
                + "        <label class='pequeño' id='segundoServicioPequeño'>&nbsp;&nbsp; Seleccione a un médico para continuar.</label>\n"
                + "    </div>\n"
                + "    <div class='flexibleContent'>\n"
                + "         <div class='divConfirmacion' id='segundoServicioMedicos'>\n"
                + "             &nbsp;"
                + "         </div>\n"
                + "    </div>    \n"
                + "</div>"
                + "<div class='opciones opcionesPaciente oculto' id='tercerServicio' >\n"
                + "    <div class='titulo amarilloTitulos'>\n"
                + "        <label class='grande'  id='tercerServicioTitulo' onclick='anteriorServicio(2)'><span>&nbsp;&nbsp;&nbsp;</span>  Nombre Médico</label>\n"
                + "        <label class='pequeño'>&nbsp; Seleccione a un horario para continuar.</label>\n"
                + "    </div>\n"
                + "    <div class='flexibleContent'>\n"
                + "        <div class='divConfirmacion' id ='tercerServicioHoriario'>\n"
                + "            <div class='medicoIndividual' id='tercerSeccionInfoMedico'>"
                + "                &nbsp;"
                + "            </div>"
                + "            <div class='medicoIndividual' id='tercerSeccionHorario'>"
                + "                &nbsp;"
                + "            </div>"
                + "            <div class='divSolicitar'>\n"
                + "                 <div class='amarilloBotones' onclick='siguienteServicio(2)'>\n"
                + "                     <label>\n"
                + "                          Aceptar\n"
                + "                     </label>\n"
                + "                 </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</div>";
        cuartaParte = "<div class='opciones opcionesPaciente oculto' id='cuartoServicio' >\n"
                + "    <div class='titulo amarilloTitulos'>\n"
                + "        <label class='grande' onclick='anteriorServicio(3)'><span>&nbsp;&nbsp;&nbsp;</span> Confirmar</label>\n"
                + "        <label class='pequeño'>Verifique su informacion</label>\n"
                + "    </div>\n"
                + "    <div class='flexibleContent'>"
                + "        <div class='divConfirmacion' id ='cuartoServicioContenido'>\n"
                + "            <div class='divSolicitar'>\n"
                + "                 <div class='amarilloBotones' onclick='siguienteServicio(3)'>\n"
                + "                     <label>\n"
                + "                         solicitar\n"
                + "                     </label>\n"
                + "                 </div>\n"
                + "           </div>\n"
                + "        </div>\n"
                + "    </div>/n"
                + "</div>"
                + "<div class='opciones opcionesPaciente  oculto' id='quintoServicio' >\n"
                + "    <div class='titulo amarilloTitulos'>\n"
                + "        <label class='grande'>&nbsp; Éxito</label>\n"
                + "        <label class='pequeño'>&nbsp;</label>\n"
                + "    </div>\n"
                + "    <div class='flexibleContent'>"
                + "        <div class='divConfirmacion' id ='quintoServicioContenido'>\n"
                + "            <div class='solicitarCita'>\n"
                + "            </div>\n"
                + "            <div class='divSolicitar'>\n"
                + "               <div class='amarilloBotones' onclick='siguienteServicio(4)'>\n"
                + "                   <label>\n"
                + "                       Aceptar\n"
                + "                   </label>\n"
                + "              </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "   </div>\n"
                + "</div>";
        quintaParte = "\n";
        JS += "<script>\n"
                + "function initMap() {\n"
                + "    var map = new google.maps.Map(document.getElementById('map') );\n"
                + "    var infoWindow = new google.maps.InfoWindow({map: map});\n"
                + "        if (navigator.geolocation) {\n"
                + "            navigator.geolocation.getCurrentPosition(function (position) {\n"
                + "               pos = {\n"
                + "                  lat: position.coords.latitude,\n"
                + "                  lng: position.coords.longitude\n"
                + "               };\n"
                + "                        infoWindow.setPosition(pos);\n"
                + "                        infoWindow.setContent('Ubicación actual');\n"
                + "                        map.setCenter(pos);\n"
                + "                        var bounds = new google.maps.LatLngBounds();\n"
                + "                        bounds.extend(pos);\n"
                + "                        map.fitBounds(bounds); \n"
                + "                        var marker = new google.maps.Marker({\n"
                + "                            position: pos,\n"
                + "                            map: map\n"
                + "                        });\n"
                + "                        $('#cargando').css('display', 'none');"
                + "                        pasar = true;"
                + "                    }, function () {\n"
                + "                        handleLocationError(true, infoWindow, map.getCenter());\n"
                + "                    });\n"
                + "                } else {\n"
                + "                    handleLocationError(false, infoWindow, map.getCenter());\n"
                + "                }\n"
                + "            }\n"
                + "            function handleLocationError(browserHasGeolocation, infoWindow, pos) {\n"
                + "                infoWindow.setPosition(pos);\n"
                + "                infoWindow.setContent(browserHasGeolocation ?\n"
                + "                        'Error: The Geolocation service failed.' :\n"
                + "                        'Error: Your browser doesn\\'t support geolocation.');\n"
                + "            }\n"
                + "        </script>\n"
                + "        <script async defer\n"
                + "                src=\"https://maps.googleapis.com/maps/api/js?key=" + authKey + "&callback=initMap\">\n"
                + "        </script>\n"
                + "<script>"
                + "servicioSelec(0);"
                + "</script>";
    }

    private String familia() {
        String respuesta = "";
        respuesta += respuesta + ""
                + "<div class='conImagenes' id='persona" + usuario.getIdUsuario() + "' onclick='personaSelec(" + usuario.getIdUsuario() + ")'>\n"
                + "    <span style='background-image: url(" + puntos + "getImage.jsp?id=" + usuario.getIdUsuario() + ")'> &nbsp;</span>\n"
                + "    <label>" + usuario.getNombre() + "</label>\n"
                + "</div>\n";
        familiaObj familiares = new familiaObj();
        familiares.setPacienteaChecar(usuario);
        if (familiares.buscarFamilia()) {
            if (familiares.isTieneFamilia() == 1) {
                for (int i = 0; i < familiares.getLista().size(); ++i) {
                    respuesta += ""
                            + "<div class='conImagenes' id='persona" + familiares.getLista().get(i).getIdUsuario() + "' onclick='personaSelec(" + familiares.getLista().get(i).getIdUsuario() + ")'>\n"
                            + "    <span style='background-image: url(" + puntos + "getImage.jsp?id=" + familiares.getLista().get(i).getIdUsuario() + ")'> &nbsp;</span>\n"
                            + "    <label>" + familiares.getLista().get(i).getNombre() + "</label>\n"
                            + "</div>\n";
                }
            } else {
                JS = "<script>personaSelec(" + usuario.getIdUsuario() + ");</script>";
            }
        } else {
            JS = "<script>personaSelec(" + usuario.getIdUsuario() + ");</script>";
        }
        return respuesta;
    }

}
