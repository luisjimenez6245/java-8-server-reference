/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author luis
 */
public  class controladorContenido {
    
    private String scripts = "";

    private String metaData = ""
            + "<meta charset='UTF-8'>"
            + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
    
    private String titulo = "";

    private String alertas = "";

    private String scriptsFinal = "";

    private String contenido = "";

    private String nav = "";

    private String css = "";

    private String contenedorOculto = "";

    private String noJS = "";

    private String oculto = "";

    public controladorContenido() {
        oculto = "";
        noJS =  "";
    }

    public String crear() {
        String resultado = "<!DOCTYPE html>\n"
                + "<html>\n";
        if (titulo.equals("")) {
            resultado += head();
        } else {
            resultado += head(titulo);
        }
        resultado += "<body>" + nav + "\n<div class='contenedorExtra oculto' id='contenedorOcultoAlertas'>\n" + contenedorOculto + "</div>\n" + contenido + "\n" + noJS + "\n" + oculto + "</body>\n";
        resultado += scriptsFinal + "</html>";
        return resultado;
    }

    private String head() {
        String resultado = ""
                + "<head> \n "
                + "<title>MediCall</title> \n "
                + css
                + scripts + metaData
                + "\n\n</head>\n";
        return resultado;
    }

    private String head(String titulo) {
        String resultado = ""
                + "<head> \n"
                + "<title>" + titulo + "</title> \n"
                + css
                + scripts + "\n" + metaData
                + "\n</head>\n";
        return resultado;
    }

    private String meta() {
        String resultado = "<meta charset='UTF-8'>" + metaData;
        return resultado;
    }

    /**
     * @param scripts the scripts to set
     */
    public void setScripts(String scripts) {
        this.scripts += scripts;
    }

    /**
     * @param metaData the metaData to set
     */
    public void setMetaData(String metaData) {
        this.metaData += metaData;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido += contenido;
    }

    /**
     * @param scriptsFinal the scriptsFinal to set
     */
    public void setScriptsFinal(String scriptsFinal) {
        this.scriptsFinal += scriptsFinal;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @param nav the nav to set
     */
    public void setNav(String nav) {
        this.nav += nav;
    }

    /**
     * @param contenedorOculto the contenedorOculto to set
     */
    public void setContenedorOculto(String contenedorOculto) {
        this.contenedorOculto += contenedorOculto;
    }

    /**
     * @param css the css to set
     */
    public void setCss(String css) {
        this.css = css;
    }

    /**
     * @return the alertas
     */
    public String getAlertas() {
        return alertas;
    }

    /**
     * @param alertas the alertas to set
     */
    public void setAlertas(String alertas) {
        this.alertas = alertas;
    }

    /**
     * @return the noJS
     */
    public String getNoJS() {
        return noJS;
    }

    /**
     * @param noJS the noJS to set
     */
    public void setNoJS(String noJS) {
        this.noJS = "<div class='noJS' id='noJS'>\n"
                + "            Se requiere de JavaScript para poder usar Medicall.\n"
                + noJS
                + "        </div>";
    }

    /**
     * @return the oculto
     */
    public String getOculto() {
        return oculto;
    }

    /**
     * @param oculto the oculto to set
     */
    public void setOculto(String oculto) {
        this.oculto = "<div class='oculto' id='postJS'>\n"
                + "            <script>$('#noJS').addClass('oculto');</script>\n"
                + oculto
                + "        </div>";
    }
}
