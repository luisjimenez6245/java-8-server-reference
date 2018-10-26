/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas.inicio;


/**
 *
 * @author luisjimenezdelgado
 */
public class ventanaFormularioMedico extends objetos.objetoVentana  {

    private String altas = "";

    public ventanaFormularioMedico(Boolean html, String posicionRelativa) {
        super(html, posicionRelativa);
        ajustar();
    }

    private void ajustar() {

        clase = "";
        primeraParte = "<div class='spaceFix'>\n"
                + "                &nbsp;\n"
                + "            </div>"
                + "<form id='msform'  class='formulario' name='formulario' method='POST' action='" + altas + "../altas/registroMedico' enctype='multipart/form-data' accept-charset='UTF-8' > \n"
                + "                <div id='barrita'  class='barrita'>\n"
                + "                    <ul class='' id='progressbar'>\n"
                + "                        <!-- progressbar -->\n"
                + "                        <li id='li1' class='active1 active'></li>\n"
                + "                        <li id='li2'></li>\n"
                + "                        <li id='li3'></li>\n"
                + "                        <li id='li4'></li>\n"
                + "                    </ul>\n"
                + "                </div>\n"
                + "                <div id='form'>";
        segundaParte = "<fieldset>\n"
                + "<div class='titulo'>\n"
                + "                            Crea tu cuenta\n"
                + "                        </div>\n"
                + "                        <div class='content'>\n"
                + "                            <div class='divAbsoluto'>\n"
                + "                                <label  for='nombre' class=''>Nombre(s):</label>\n"
                + "                                <input type='text' id='nombre' placeholder='Nombre' name = 'nombre' maxlength='50' onkeypress='return soloLetras(event)'/>\n"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto'>\n"
                + "                                <label  for='apellido' class=''>Apellidos:</label>\n"
                + "                                <input type='text' id='apellido' placeholder='Apellidos' name = 'apellido' maxlength='50' onkeypress='return soloLetras(event)'/>\n"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto'>\n"
                + "                                <label  for='email' class=''>Correo:</label>\n"
                + "                                <input type='text' id='email' placeholder='Email' name='email' maxlength='254' autocapitalize=\"off\" />\n"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto'>\n"
                + "                                <label for='telefono' class=''>Telefono:</label>\n"
                + "                                <input type='text' id='telefono' placeholder='Telefono de 10 digitos' onkeypress='return enteros(event)' name='telefono' maxlength='10'/>\n"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto' style='z-index:10'>\n"
                + "                                <label> Escuela:</label>\n"
                + "                                <div class='containerResultado' style=' float:right ;z-index:10; '>\n"
                + "                                      <input type='text' id='EscuelaNom' name='EscuelaNom' placeholder='Escuela de Egreso'  autocomplete='off'  maxlength='254'/>\n"
                + "                                      <div id=\"resultadosBusqueda\" >"
                + "                                         <div class='flex' id=''>\n"
                + "                                               <div class='' id='contenedorResultado'>\n"
                + "                                                         "
                + "                                               </div>"
                + "                                         </div>\n"
                + "                                      </div>\n"
                + "                                 </div>"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto' style='display: none'>\n"
                + "                                <input type='text' id='escuelaId' name='escuelaId'  maxlength='24'/>\n"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto'>\n"
                + "                                <label  for ='pass' class=''>Contraseña:</label>\n"
                + "                                <input type='password'  placeholder='Contraseña de mínimo 8 dígitos' id='pass' name='pass'  maxlength='24'/>\n"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto'>\n"
                + "                                <label for = 'cpass' class=''> Contraseña:</label>\n"
                + "                                <input type='password' placeholder='Repite tu contraseña' id='cpass'name='cpass'/>\n"
                + "                            </div>\n"
                + "                            <div class='foto' id='divTarjetaFoto'  >\n"
                + "                                <div class='izquierdo' style='height:  100%'>\n"
                + "                                    <div class='arriba'>\n"
                + "                                        <label id='nombreMostrado'>Nombre</label>\n"
                + "                                        <label id='emailMostrado'>Email</label>\n"
                + "                                    </div>\n"
                + "                                    <div class='abajo'>\n"
                + "                                        <input type='file' name='fotoUsu' id='fotoUsu' onchange='cambiar(this)' accept='image/*'>\n"
                + "                                        <label for ='fotoUsu'> Adjuntar foto</label>\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "                                <div class='derecha'>\n"
                + "                                    <div class='fotito' id='fotoUsuario'>\n"
                + "                                        &nbsp;\n"
                + "                                    </div>\n"
                + "                                </div> \n"
                + "                            </div>\n"
                + "                            <div class='divBotones'>\n"
                + "                                <div class='derecha'>\n"
                + "                                    <span class='next rojoso' name='next' onclick='verificaM(1)' >\n"
                + "                                        Siguiente\n"
                + "                                    </span>\n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                        </div>"
                + "                    </fieldset>";
        terceraParte = "<fieldset>\n"
                + "<div class='titulo'>\n"
                + "                            Datos del servicio\n"
                + "                        </div>\n"
                + "                        <div class='content'>\n"
                + "                            <div class='divLargo'> \n"
                + "                                <label for='cedula'>Cédula profesional: </label>\n"
                + "                                <input type='text' id='cedula' name='cedula' onkeypress='' maxlength='7'/>\n"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto'>\n"
                + "                                <label>Carrera:</label>\n"
                + "                                <select id='especialidad' style='width: 40%;' name='carrera'>\n"
                + "                                    <option value='null' selected name= 'tipo'>Indica tu carrera</option>\n"
                + "                                    <option value='Med'>Médico</option>\n"
                + "                                    <option value='Enf'>Enfermero</option>\n"
                + "                                </select>\n"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto'>\n"
                + "                                <label class=''>Costo:</label>\n"
                + "                                <input type='text' id='costo'  name = 'costo' maxlength='6' placeholder='Costo por consulta' onkeypress='return enteros(event)' maxlength='6'/>\n"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto' style='display:none;'>\n"
                + "                                <input type='text' id='latitud'  name = 'latitu'/>\n"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto' style='display:none;'>\n"
                + "                                <input type='text' id='longitud'  name = 'longitu' />\n"
                + "                            </div>\n"
                + "                            <div class='divLargo'>\n"
                + "                                <label class='' style='width: 100%; font-weight: 400'>Por favor. Adjunta un archivo con tu acta de nacimiento,una identificación oficial y tu título universitario en formato PDF.</label> <br>\n"
                + "                                <input type='file' name='archives' id='archivoMed'/ accept='application/pdf'>\n"
                + "                            </div>\n"
                + "                            <div class='divLargo'>\n"
                + "                                <label> Servicio a ofrecer:</label>\n"
                + "                                <input type='radio' name='tipoC' value='0' id='rad1' onclick='domicilio();'>\n"
                + "                                <label>Consultas a domicilio</label>\n"
                + "                                <input type='radio' name='tipoC' value='1' id='rad1'  onclick='consultorio();'>\n"
                + "                                <label>Citas en consultorio</label>\n"
                + "                            </div>\n"
                + "                            <div class='divLargo' id='transp' style='display: none'>\n"
                + "                                  <div class='divAbsoluto'>\n"
                + "                                      <label> Transporte:</label>\n"
                + "                                      <select id='transp' style='width: 40%;color:#666' name='mtransporte' >\n"
                + "                                         <option value='null' selected name = 'transporte'>Disponibilidad de transporte</option>\n"
                + "                                         <option value='1'>Cuento con automóvil</option>\n"
                + "                                           <option value='2'>Utilizo transporte público</option>\n"
                + "                                      </select>\n"
                + "                                 </div>\n"
                + "                                 <div class='divAbsoluto'>\n"
                + "                                     <label>Horario :</label>\n"
                + "                                     <select id='transp' style='width: 40%;color:#666' name='tipoDeServicio' >\n"
                + "                                         <option value='null' selected name = 'servicio'>Tipo de horario de trabajo</option>\n"
                + "                                         <option value='1'>Abierto</option>\n"
                + "                                         <option value='2'>Preferentemente con horario</option>\n"
                + "                                     </select>\n"
                + "                                 </div>\n"
                + "                            </div>"
                + "                            <div class='divBotones'>\n"
                + "                                <div class='izquierda'>\n"
                + "                                    <span class='previous rojoso' name='previous'>\n"
                + "                                        Anterior\n"
                + "                                    </span>\n"
                + "                                </div>\n"
                + "                                <div class='derecha'>\n"
                + "                                    <span class='next azulBotones' name='next' onclick='verificaM(2)' >\n"
                + "                                        Siguiente\n"
                + "                                    </span>\n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                        </div>"
                + "                    </fieldset>";
        cuartaParte = " <fieldset>\n"
                + "<div class='titulo'>\n"
                + "                            Horario de trabajo\n"
                + "                        </div>\n"
                + "                        <div class='content' >\n"
                + "                            <div class='divTexto'>\n"
                + "                                <label>\n"
                + "                                    Selecciona los dias de atención de tu preferencias:\n"
                + "                                </label>\n"
                + "                            </div>\n"
                + "                            <div class='divCheck'>\n"
                + "                                    <div class=''>\n"
                + "                                        <input type='checkbox' value='Lunes' id='H2' name='cbox2[]'>\n"
                + "                                        <label for='H2'>Lunes</label>\n"
                + "                                    </div>\n"
                + "                                    <div class=''>\n"
                + "                                        <input type='checkbox' value='Martes' id='H2' name='cbox2[]'>\n"
                + "                                        <label for='H2'>Martes</label>\n"
                + "                                    </div>\n"
                + "                                    <div class=''>\n"
                + "                                        <input type='checkbox' value='Miercoles' id='H2' name='cbox2[]'>\n"
                + "                                        <label for='H2'>Miercoles</label>\n"
                + "                                    </div>\n"
                + "                                    <div class=''>\n"
                + "                                        <input type='checkbox' value='Jueves' id='H2' name='cbox2[]'>\n"
                + "                                        <label for='H2'>Jueves</label>\n"
                + "                                    </div>\n"
                + "                                    <div class=''>\n"
                + "                                        <input type='checkbox' value='Viernes' id='H2' name='cbox2[]'>\n"
                + "                                        <label for='H2'>Viernes</label>\n"
                + "                                    </div>\n"
                + "                                    <div class=''>\n"
                + "                                        <input type='checkbox' value='Sabado' id='H2' name='cbox2[]'>\n"
                + "                                        <label for='H2'>Sabado</label>\n"
                + "                                    </div>\n"
                + "                                    <div class=''>\n"
                + "                                        <input type='checkbox' value='Domingo' id='H2' name='cbox2[]'>\n"
                + "                                        <label for='H2'>Domingo</label>\n"
                + "                                    </div>\n"
                + "                            </div>\n"
                + "                            <div class='divTexto'>\n"
                + "                                <label>\n"
                + "                                    Selecciona los horarios de atención de los dias que selecionaste:\n"
                + "                                </label>\n"
                + "                            </div>\n"
                + "                            <div class='divCheck'>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='0' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>00:00 am-01:00 am</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='1' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>01:00 am-02:00 am</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='2' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>02:00 am-03:00 am</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='3' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>03:00 am-04:00 am</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='4 id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>04:00 am-05:00 am</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='5' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>05:00 am-06:00 am</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='6' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>06:00 am-07:00 am</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='7' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>07:00 am-08:00 am</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='8' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>08:00 am-09:00 am</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='9' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>09:00 am-10:00 am</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='10' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>10:00 am-11:00 am</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='11' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>11:00 am-12:00 pm</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='12' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>12:00 pm-13:00 pm</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='13' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>13:00 pm-14:00 pm</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='14' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>14:00 pm-15:00 pm</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='15' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>15:00 pm-16:00 pm</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='16' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>16:00 pm-17:00 pm</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='17' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>17:00 pm-18:00 pm</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='18' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>18:00 pm-19:00 pm</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='19' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>19:00 pm-20:00 pm</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='20' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>20:00 pm-21:00 pm</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='21' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>21:00 pm-22:00 pm</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='22' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>22:00 pm-23:00 pm</label>\n"
                + "                                </div>\n"
                + "                                <div class=''>\n"
                + "                                    <input type='checkbox' value='23' id='H1' name='cbox[]'>\n"
                + "                                    <label for='H1'>23:00 pm-00:00 am</label>\n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                        <div class='content'>\n"
                + "                            <div class='divBotones'>\n"
                + "                                <div class='izquierda'>\n"
                + "                                    <span class='previous rojoso' name='previous'>\n"
                + "                                        Anterior\n"
                + "                                    </span>\n"
                + "                                </div>\n"
                + "                                <div class='derecha'>\n"
                + "                                    <span class='next verdeBotones'  name='next' onclick='verificaM(3)'>\n"
                + "                                        Siguiente\n"
                + "                                    </span>\n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                        </div>"
                + "                    </fieldset>";
        quintaParte = "<fieldset>\n"
                + "                        <div class='titulo'>\n"
                + "                            Método de Pago\n"
                + "                        </div>\n"
                + "                        <div class='content'>\n"
                + "                            <div class=\"divLargo\">\n"
                + "                                <label for = 'tarjeta' class='labels'>Número de tarjeta:</label>\n"
                + "                                <input type='text' id='tarjeta' maxlength=\"16\" name='tarjeta' onkeypress='return enteros(event)' />\n"
                + "                            </div>\n"
                + "                            <div class='divLargo'>\n"
                + "                                <label  for = 'nomprop' class='labels'>Nombre del propietario:</label>\n"
                + "                                <input type='text' id='nomprop' name='nomProp' onkeypress='return soloLetras(event)'/>\n"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto'>\n"
                + "                                <label for = 'fven' class='labels'>Fecha de vencimiento :</label>\n"
                + "                                <input type='text' id='fven'  name='fdv' maxlength=\"8\" placeholder='DD/MM/AAAA' onkeypress='return enteros(event)' />\n"
                + "                            </div>\n"
                + "                            <div class='divAbsoluto'>\n"
                + "                                <label for='ccv' class='labels'>CCV:</label>\n"
                + "                                <input type='password' id='ccv' name='CCV' maxlength=\"3\" placeholder ='Ultimos 3 digitos' onkeypress='return enteros(event)' />\n"
                + "                            </div>\n"
                + "                            <div class='divBotones'>\n"
                + "                                <div class='izquierda'>\n"
                + "                                    <span class='previous rojoso' name='previous'>\n"
                + "                                        Anterior\n"
                + "                                    </span>\n"
                + "                                </div>\n"
                + "                                <div class='derecha'>\n"
                + "                                    <span class='next moradoBotones'   name='next' onclick='verifica(4)'>\n"
                + "                                        Enviar\n"
                + "                                    </span>\n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </fieldset>";
        quintaParte += "     </div>\n"
                + "            </form>\n"
                + "        </div>";
        JS = "";

    }

}
