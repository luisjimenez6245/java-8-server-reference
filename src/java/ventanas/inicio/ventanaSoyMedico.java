/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas.inicio;

import objetos.objetoVentana;

/**
 *
 * @author luisjimenezdelgado
 */
public class ventanaSoyMedico extends objetoVentana {

    public ventanaSoyMedico(Boolean html, String posicionRelativa) {
        super(html, posicionRelativa);
        ajustar();
    }

    private void ajustar() {
        clase = "soyMedico";
        primeraParte = ("<section data-section-title='primeraSeccion'class='primeraSeccion' id = 'primeraSeccion'>")
                + ("<div>")
                + (" <div class='imagenFondo'>")
                + (" <div class='imagen'>&nbsp;</div>")
                + (" <div class='blur'>")
                + ("  <div>")
                + (" <h1>Médicos</h1>")
                + (" </div>")
                + ("</div>")
                + (" </div>")
                + (" <div class='imagenOla'>&nbsp;</div>")
                + ("</div>")
                + (" </section>");
        segundaParte = ""
                + ("<section data-section-title='segundaSeccion' class='segundaSeccion' id = 'segundaSeccion' data-section-name = 'segundaSeccion'>")
                + (" <div>")
                + (" <div class='derecho'>")
                + (" <div class='grande'>")
                + ("<div class='imagen'>")
                + ("<div></div>")
                + (" </div>")
                + (" <div class='titulo'>")
                + (" Te espera un gran futuro...")
                + ("</div>")
                + ("<div class='texto'>")
                + ("Las oportunidades no llegan siempre cuando las buscas."
                + ("<br>")
                + " Nosotros creemos en ti.")
                + ("</div>")
                + (" <div class='extra'>")
                + (" <div class='azulado'>")
                + (" <label>")
                + ("    Más información")
                + ("</label>")
                + (" </div>")
                + ("</div>")
                + (" </div>")
                + (" </div>")
                + (" </div>")
                + ("</section>");
        terceraParte = ""
                + ("<section data-section-title='terceraSeccion' class='terceraSeccion' id = 'terceraSeccion'>")
                + (" <div class='divContenedor'>")
                + ("<div class='titulo'>")
                + ("<h3> Tu oportunidad se encuentra aquí </h3>")
                + ("<label>")
                + (" Somos el medio que necesita tu potencial para ponerse en práctica. Ahora crecer no cuesta nada.")
                + ("</label>")
                + ("</div>")
                + ("<div class='contenedor'>")
                + (" <div>")
                + ("<div class=' miniContenedor'>")
                + (" <div class='otroDiv'>")
                + ("<div class='imagen'>")
                + ("<diV class='circulo rojoso'>a</diV>")
                + ("</div>")
                + ("<div class='textoContenedor'>")
                + (" <div class='tituloDiv'>Tu Servicio</div>")
                + ("<div class='textoDiv'>")
                + ("Recibe solicitudes de consultas médicas en tiempo real sin la necesidad de un administrador."
                + (" <br>")
                + "Sé dueño de tu propio horario de trabajo")
                + ("</div>")
                + (" </div>")
                + ("</div>")
                + ("<div class='otroDiv'>")
                + (" <div class='imagen'>")
                + ("<diV class='circulo rojoso'>a</diV>")
                + ("</div>")
                + ("<div class='textoContenedor'>")
                + ("<div class='tituloDiv'>Directorio Médico</div>")
                + ("<div class='textoDiv'>")
                + (" Tu contacto estará disponible para el todo público a cualquier hora del dia cuando requieran de tu servicio.")
                + ("</div>")
                + ("</div>")
                + ("</div>")
                + ("<div class='otroDiv'>")
                + (" <div class='imagen'>")
                + ("<diV class='circulo rojoso'>a</diV>")
                + (" </div>")
                + ("<div class='textoContenedor'>")
                + ("<div class='tituloDiv'>Descarga la app</div>")
                + ("<div class='textoDiv'>")
                + ("  No solo te ofrecemos una plataforma de trabajo en una página de Internet sino también lo llevamos a tu más próximo alcance mediante la app MediCall")
                + ("</div>")
                + ("</div>")
                + (" </div>")
                + (" </div>")
                + ("</div>")
                + ("</div>")
                + ("</div>")
                + ("</section>");
        cuartaParte = ""
                + ("<section  class='cuartaSeccion'>")
                + (" <div class='titulo'>")
                + ("¿Cómo funciona?")
                + ("</div>")
                + ("<div class='contenedor'>")
                + ("<div class='imagen'>")
                + ("<div>")
                + ("<picture>")
                //+ ("<img src='imagenes/imgiPhone.png'>")
                + ("</picture>")
                + ("</div>")
                + ("</div>")
                + ("<div class='pasos'>")
                + ("<div class=' pasitos'>")
                + ("<div class='divContenedor'>")
                + ("<span class=''>1</span>")
                + ("Registrate ")
                + ("</div>")
                + ("<p>")
                + ("Accede al modulo de registro, completa el formulario con tu información y envíalo ")
                + ("</p>")
                + ("<br>")
                + (" </div>")
                + ("<div class=' pasitos'>")
                + (" <div class='divContenedor'>")
                + ("<span class=''>2</span>")
                + (" Envía los documentos requeridos ")
                + ("</div>")
                + (" <p>")
                + ("Para poder validar tu registro, necesitamos documentos como tu CV, cédula, entre otros. ")
                + ("</p>")
                + ("<br>")
                + ("</div>")
                + ("<div class=' pasitos'>")
                + ("<div class='divContenedor'>")
                + ("<span class=''>3</span>")
                + ("Recibe tu confirmación")
                + ("</div>")
                + ("<p>")
                + ("Espera a que el administrador valide tus documentos para que seas aceptado.  ")
                + ("</p>")
                + ("<br>")
                + ("</div>")
                + ("<div class=' pasitos'>")
                + (" <div class='divContenedor'>")
                + ("<span class=''>4</span>")
                + ("¡Listo! Ahora puedes acceder libremente a tu cuenta ")
                + ("</div>")
                + ("<p>")
                + (" Una vez que hayas sido aceptado, puedes rechazar o confirmar las citas que estes dispuesto a tomar o no ")
                + ("</p>")
                + ("<br>")
                + ("</div>")
                + ("</div>")
                + ("</div>")
                + ("</section>");
        quintaParte = "<section data-section-title='pieDePagina' class='pieDePagina panel' id = 'pieDePagina'>\n"
                + "                    <div class='contenido'>\n"
                + "                        <div class='logoEmpresa'>\n"
                + "                            <h3>ILC ®</h3>\n"
                + "                            <div>\n"
                + "                                &nbsp;\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                        <div class='secciones'>\n"
                + "                            <h3>Secciones</h3>\n"
                + "                            <ul>\n"
                + "                                <li>a</li>\n"
                + "                                <li>a</li>\n"
                + "                            </ul>\n"
                + "                        </div>\n"
                + "                        <div class='links'>\n"
                + "                            <h3>&nbsp;</h3>\n"
                + "                            <ul>\n"
                + "                            </ul>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                </section>";
        JS = "";
    }

}
