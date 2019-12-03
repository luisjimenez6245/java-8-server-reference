/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.utils;

import org.apache.commons.text.StringEscapeUtils;
import com.google.gson.Gson;
import controllers.security.Manager;
import models.User;
import sources.requests.RepositoryRequests;

/**
 *
 * @author luis
 */
public abstract class iViewController {

    protected static final String URL = Manager.getInstance().getProperty("URL");

    protected final RepositoryRequests rSource;

    public User user;

    protected String scripts = "";

    protected String titulo = "";

    protected String alertas = "";

    protected String scriptsFinal = "";

    protected String content = "";

    protected String nav = "";

    protected String css = "";

    protected String modal = "";

    protected iViewController(RepositoryRequests rSource) {
        this.rSource = rSource;
        this.scripts += "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>\n";
        this.scripts += "<script src=\"" + URL + "js/codemirror.js\"></script>\n";
        this.scripts += "<script src=\"" + URL + "js/sql.js\"></script>\n";
        this.css += "<link rel=\"stylesheet\" href=\"" + URL + "css/general.css\" />\n";
        this.scripts += "<script src=\"" + URL + "js/requestHandler.js\"></script>\n";
        this.scripts += "<script src=\"" + URL + "js/models.js\"></script>\n";
        this.scripts += "<script src=\"" + URL + "js/actions.js\"></script>\n";
        this.nav += "      <nav class=\"nav\">\n"
                + "        <div class=\"container\">\n"
                + "          <div class=\"logo\"></div>\n"
                + "          <div class=\"urls\">\n"
                + "            <div class=\"\" onclick=\"window.location.href='index'\">\n"
                + "              index\n"
                + "            </div>\n"
                + "          </div>\n"
                + "          <div class=\"user-info\"></div>\n"
                + "          <div class='panel' onclick=\"showLeft()\">\n"
                + "            <div></div>\n"
                + "            <div></div>\n"
                + "            <div></div>\n"
                + "            <div></div>\n"
                + "          </div>\n"
                + "        </div>\n"
                + "      </nav>\n";
    }

    public String createContent() {

        String resultado
                = "<!DOCTYPE html>"
                + "\n"
                + "<html>"
                + "\n";
        if (titulo.equals("")) {
            resultado += head();
        } else {
            resultado += head(titulo);
        }
        resultado
                += "<body>\n"
                + "    <div class=\"no-js\"></div>\n"
                + "    <div class=\"container-hidden\" id='modal-loading'>\n"
                + "      <div class=\"lds-ellipsis\">\n"
                + "        <div></div>\n"
                + "        <div></div>\n"
                + "        <div></div>\n"
                + "        <div></div>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "    <div class='container-hidden' id='modal-container'>\n"
                + StringEscapeUtils.escapeJava(
                        this.modal
                        + "     </div>\n"
                        + this.nav
                        + "    <div class=\"main\">\n"
                        + content
                        + "    </div>\n"
                        + ""
                ).replace("\\n", "").replace("\\\"", "\"")
                + "<div id='js-handler' class='container-hidden'></div>\n"
                + "</body>"
                + "\n"
                + scriptsFinal
                + "\n"
                + "</html>";
        return resultado;
    }

    protected String head() {
        String resultado = ""
                + "<head>"
                + "\n "
                + "<title>SQL</title> \n"
                + "\n "
                + css + " \n"
                + scripts + " \n"
                + "<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.8.1/css/all.css' integrity='sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf' crossorigin='anonymous'>\n"
                + "<meta charset='utf-8'>\n"
                + "<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'> \n"
                + "<meta charset='UTF-8'> \n"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'> \n"
                + "\n"
                + ""
                + "</head> \n"
                + "\n";
        return resultado;
    }

    protected String head(String titulo) {
        String resultado = ""
                + "<head>"
                + "\n "
                + "<title>" + titulo + "</title> "
                + "\n "
                + css
                + scripts
                + "<meta charset='UTF-8'>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "\n"
                + "</head>"
                + "\n";
        return resultado;
    }

    public String getJson() {
        return new Gson().toJson(new html(content, scriptsFinal, modal));
    }

    public String getErrorJson() {
        return new Gson().toJson(new html("", "<script>exit();</script>", ""));
    }

    public abstract void main();

    private class html {

        public String content;
        public String script;
        public String modal;

        public html(String content, String script, String modal) {
            this.content = content;
            this.script = script;
            this.modal = modal;
        }
    }

}
