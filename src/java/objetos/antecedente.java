/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

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
public class antecedente implements objeto<antecedente> {

    public int id;
    public String nombre;
    public int valor;

    public antecedente() {
    }

    public antecedente(int id) {
        this.id = id;
    }

    public antecedente(int id, String nombre, int valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    @Override
    public antecedente parse(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public antecedente parse(ResultSet res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<antecedente> parseList(HttpServletRequest request) {
        ArrayList<antecedente> con = new ArrayList<>();
        if (request.getParameterValues("antecedentes") != null) {
            for (String a : request.getParameterValues("antecedentes")) {
                String[] aCon = a.split("-").length > 0 ? a.split("-") : null;
                if (aCon != null) {
                    con.add(new antecedente(Integer.parseInt(aCon[1]), "", 1));
                }
            }
        }
        return con;
    }

    @Override
    public List<antecedente> parseList(ResultSet res) {
        ArrayList<antecedente> con = new ArrayList<>();
        try {
            while (res.next()) {
                con.add(new antecedente(
                        res.getInt("id"),
                        res.getString("nombre"),
                        res.getInt("valor")
                )
                );
            }
            res.close();
        } catch (SQLException ex) {
            new logger().error(ex);
        }
        return con;
    }

}
