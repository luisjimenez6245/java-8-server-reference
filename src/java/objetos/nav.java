/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import com.google.gson.Gson;
import controladores.seguridad.logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author luis
 */
public class nav implements objeto<nav> {

    public long id;
    public String nombreMenu;
    public String direccion;
    public String accion;
    public int posicion;
    public int status;

    public nav() {
    }

    public nav(Long id, String nombreMenu, String direccion, String accion, int posicion, int status) {
        this.id = id;
        this.nombreMenu = nombreMenu;
        this.direccion = direccion;
        this.accion = accion;
        this.posicion = posicion;
        this.status = status;
    }

    @Override
    public String toJSON() {
        return "{ nav_id: " + id + ", nombre_menu: '" + nombreMenu + "' ,direccion:'" + direccion + "', accion: '" + accion + "', posicion: " + posicion + ", status: " + status + " }";
    }

    @Override
    public nav parse(HttpServletRequest request) {
        return new nav(
                request.getParameter("id_nav") == null ? 0 : Long.parseLong(request.getParameter("id_nav")),
                request.getParameter("nombreMenu") == null ? "" : request.getParameter("nombreMenu"),
                request.getParameter("direccion") == null ? "" : request.getParameter("direccion"),
                request.getParameter("accion") == null ? "" : request.getParameter("accion"),
                request.getParameter("posicion") == null ? 0 : Integer.parseInt(request.getParameter("posicion")),
                request.getParameter("status") == null ? 0 : Integer.parseInt(request.getParameter("status"))
        );
    }

    @Override
    public nav parse(ResultSet res) {
        try {
            if (res.next()) {
                 return new nav(res.getLong("idMenu"), res.getString("nombreMenu"), res.getString("direccion"), res.getString("accion"), res.getInt("posicion"), res.getInt("status"));
            } else {
                return new nav(0L, "", "", "", 0, 0);
            }
        } catch (SQLException ex) {
            new logger().error(ex);
            return null;
        }
    }

    @Override
    public List<nav> parseList(HttpServletRequest request) {
        ArrayList<nav> contenedorNav = new ArrayList<>();
        if (request.getParameter("list_nav") != null) {
            new Gson().fromJson(request.getParameter("list_nav"), nav.class);
        } 

        return contenedorNav;
    }

    @Override
    public List<nav> parseList(ResultSet res) {
        ArrayList<nav> contenedorNav = new ArrayList<>();
        try {
            while (res.next()) {
                contenedorNav.add(new nav(res.getLong("idMenu"), res.getString("nombreMenu"), res.getString("direccion"), res.getString("accion"), res.getInt("posicion"), res.getInt("status")));
            }
            res.close();
        } catch (SQLException ex) {
            new logger().error(ex);
        }
        return contenedorNav;
    }

}
