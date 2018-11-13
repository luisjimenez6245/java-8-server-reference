/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author luis
 */
public class controladorBD extends properties.pMySQL {

    public ResultSet preparedS(String consulta, String[] parametros) throws SQLException {
        state = this.conn.prepareStatement(consulta);
        for (int i = 1; i < parametros.length + 1; i++) {
            state.setString(i, parametros[i - 1]);
        }
        res = state.executeQuery();
        return res;
    }

    public PreparedStatement getCall(String sentencia) throws SQLException {
        return this.conn.prepareCall(sentencia);
    }

    public ResultSet realiza(PreparedStatement consulta) throws SQLException {
        return consulta.executeQuery();
    }

    public void sinRes(PreparedStatement consulta) throws SQLException {
        consulta.executeQuery();
    }

    public ResultSet consulta(String consulta, ArrayList<Pair<String, String>> parametros) throws SQLException {
        if (parametros != null) {
            String valores = "where true = true";
            valores = parametros.stream().map((param) -> "and " + param.getKey() + " = ?").reduce(valores, String::concat);
            consulta += valores + ";";
        }

        state = this.conn.prepareStatement(consulta);
        if (parametros != null) {
            for (int i = 1; i <= parametros.size(); i++) {
                state.setString(i, parametros.get(i - 1).getValue());
            }
        }
        Statement estancia;
        estancia = (Statement) conn.createStatement();
        
        return estancia.executeQuery(consulta);
        //res = state.executeQuery();
        //return res;
    }

    public void actualizar(String actualiza, Pair<String, String>[] parametros) throws SQLException {
        String valores = "";
        for (Pair<String, String> param : parametros) {
            valores += param.getKey() + " = ?";
        }
        actualiza = actualiza.replace("?val", valores);
        state = this.conn.prepareStatement(actualiza);
        for (int i = 1; i <= parametros.length; ++i) {
            state.setString(i, parametros[i - 1].getValue());
        }
        state.execute();
    }

    public void borrar(String borra) throws SQLException {
        Statement st = (Statement) this.conn.createStatement();
        st.execute(borra);
    }

    public int insertar(String inserta) throws SQLException {
        Statement st = (Statement) this.conn.createStatement();
        return st.executeUpdate(inserta);
    }

}
