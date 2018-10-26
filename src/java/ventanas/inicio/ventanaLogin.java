package ventanas.inicio;

/**
 *
 * @author luisjimenezdelgado
 */
public class ventanaLogin extends objetos.objetoVentana {

    public ventanaLogin(Boolean html, String posicionRelativa) {
        super(html, posicionRelativa);
        ajustar();
    }

    private void ajustar() {
        clase = "login";
        primeraParte = ""
                + "<div class='izquierda'  id ='loginDiv' style=''>\n"
                + "     <div class='content'>\n"
              //  + "                        <div class='icon'><img src='" + puntos + "../Images/Logo.png' style='width:100%; heigth:100%'></div>\n"
                + "         <label class='title'> Acceso</label>\n"
                + "         <div class='inputs'>\n"
                + "            <span>\n"
                + "               <input type='text' id='email' placeholder='E-mail' name='' >\n"
                + "               <input type='password' id='pass'  name='' placeholder='Contraseña' maxlength='24' >\n"
                + "            </span>\n"
                + "         </div>\n"
                + "         <div class='botones'>\n"
                + "              <span class='rojoso' onclick='logIn()'>\n"
                + "                    Acceder\n"
                + "              </span>\n"
                + "         </div>\n"
                + "         <label class='ocultoLabel' id='idError'>Usuario o Contraseña incorrecta </label>\n"
                + "         <a  class='primero' style='text-decoration: none'onclick='registro()'>¿Aún no tienes una cuenta? ¡Crea una!</a><br>\n"
                + "         <a  style='text-decoration: none'onclick='recuperarCuenta()'>Olvidé mi contraseña</a>\n"
                + "     </div>\n"
                + "</div>";
        segundaParte = ""
                + "<div class='izquierda'  id ='recuperacionDiv' style='display:none;'>\n"
                + "     <div class='content'>\n"
                + "                        <div class='icon'><img src='" + puntos + "../Images/Logo.png' style='width:100%; heigth:100%'></div>\n"
                + "         <label class='title'> Recuperación de tu cuenta</label>\n"
                + "         <div class='inputs'>\n"
                + "            <span>\n"
                + "               <input type='text' id='emailRecuperacion' placeholder='E-mail' name='' >\n"
                + "            </span>\n"
                + "         </div>\n"
                + "         <div class='botones'>\n"
                + "              <span class='rojoso' onclick='recuperacion()'>\n"
                + "                    Recuperar\n"
                + "              </span>\n"
                + "         </div>\n"
                + "         <label class='ocultoLabel' id='idErrorRecuperacion'>Usuario o Contraseña incorrecta </label>\n"
                + "         <a  class='primero' style='text-decoration: none'onclick='recuperarCuenta()'>Atras</a><br>\n"
                + "     </div>\n"
                + "</div>";
        terceraParte = ""
                + "<div class='derecha'>\n"
                + "     <div class='blur'>\n"
                + "           &nbsp;\n"
                + "     </div>\n"
                + "</div> ";
        cuartaParte = "";
        quintaParte = "";
        JS = "";
    }

}
