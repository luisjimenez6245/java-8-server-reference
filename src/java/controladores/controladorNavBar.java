/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import extras.logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author luis
 */
public class controladorNavBar {

    private int selecionado = 0;
    private String resultado = "";
    private String izquierdo;
    private String enMedio;
    private String derecho;
    private String clase;
    private final logger errores = new logger();

    public controladorNavBar() {
        this.enMedio = "";
        this.derecho = "";
        this.izquierdo = "";
        this.clase = "principalNav";
    }

    public String costruye(boolean tipo, int opcion, int _idUsuario, int _idPersona, int _idMenu, String direccion, int _status) {
        resultado += tipo ? "<nav class='" + clase + "' id ='principalNav'>" : "";
        selecionado = _idMenu;
        getDatos(opcion, _idUsuario, _idPersona, _idMenu, direccion, _status);
        resultado += ""
                + "<div>\n"
                + "   <div class='navIzquierda' onclick=''>\n"
                + "       <div class='contenedor'  >\n"
                + izquierdo
                + "        </div>\n"
                + "   </div>\n"
                + "   <div class='navCentro' id = 'navCentro'>\n"
                + "      <div>\n"
                + "          <div class='cerrarMenu' id ='cerrarMenu' onclick='menuAbrir()'>\n"
                + "              x\n"
                + "          </div> \n"
                + enMedio
                + "       </div>\n"
                + "   </div>\n"
                + "   <div class='navDerecho'>\n"
                + derecho
                + "    <div class='menuBars' id='menuBars' onclick='menuAbrir()'>\n"
                + "         <span>-----------</span><br>\n"
                + "         <span>-----------</span>\n"
                + "     </div>\n"
                + "   </div>\n"
                + "</div>";
        resultado += tipo ? "</nav>" : "";

        return resultado;
    }

    private void colores() {

        

    }

    private void getDatos(int _opcion, int _idUsuario, int _idPersona, int _idMenu, String direccion, int _status) {
        try {
            controladorBD base = new controladorBD();
            base.conectar();
            PreparedStatement state = base.getStatement("call sp_Menu( ?, ?, ?, ?, ?, ?);");
            state.setInt(1, _opcion);
            state.setInt(2, _idUsuario);
            state.setInt(3, _idPersona);
            state.setInt(4, _idMenu);
            state.setString(5, direccion);
            state.setInt(6, _status);
            ResultSet res = base.realiza(state);
            while (res.next()) {
                switch (res.getInt("posicion")) {
                    case 1:
                        enMedio += (linea(res.getInt("idMenu"), res.getString("nombreMenu"), res.getString("direccion"), res.getString("accion")));
                        break;
                    case 2:
                        derecho += (linea(res.getInt("idMenu"), res.getString("nombreMenu"), res.getString("direccion"), res.getString("accion")));
                        break;
                    case 3:
                        izquierdo += (linea(res.getInt("idMenu"), res.getString("nombreMenu"), res.getString("direccion"), res.getString("accion")));
                        break;
                }
            }
            res.close();
            state.close();
            base.cierraConexion();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            errores.errorBd(ex);
        }
    }

    private String linea(int idMenu, String nombre, String direccion) {
        return "<div class='" + ((idMenu == selecionado ? "activo" : "")) + "' id='navMenu-" + idMenu + "' onclick=\"funcMenu(" + idMenu + ",'" + direccion + "')\">\n"
                + "    <label>\n"
                + nombre
                + "    </label>\n"
                + "</div>\n";
    }
    
    private String linea(int idMenu, String nombre, String direccion, String accion) {
        System.out.println(accion);
        if(accion == null || accion.equals(""))
            return linea(idMenu, nombre, direccion);
        else
        return "<div class='" + ((idMenu == selecionado ? "activo" : "")) + "' id='navMenu-" + idMenu + "' onclick=\""+accion+"\">\n"
                + "    <label>\n"
                + nombre
                + "    </label>\n"
                + "</div>\n";
    }

    public void setSelecionado(int selecionado) {
        this.selecionado = selecionado;
    }


    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

}
