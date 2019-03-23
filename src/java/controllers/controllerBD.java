/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.security.logger;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class controllerBD{

    private String usrBD = "root";
    private String passBD = "Siul6245";
    private String dbName = "server_reference";
    private String urlBD = "jdbc:mysql://localhost:3306/dbName?allowPublicKeyRetrieval=true&useSSL=false";
    // private String urlBD = "jdbc:mysql://187.221.116.149:3306/dbName?allowPublicKeyRetrieval=true&useSSL=false";
    private final String DRIVERCLASSNAME = "com.mysql.jdbc.Driver";
    protected Connection conn = null;
    protected PreparedStatement state = null;
    protected ResultSet res = null;

    public controllerBD(String user, String pswd, String url, String dbName) {
        this.usrBD = user;
        this.passBD = pswd;
        this.urlBD = url;
        this.dbName = dbName;
    }

    public controllerBD(String url) {
        this.urlBD = url;
    }

    public controllerBD() {
    }

    public controllerBD(String user, String pswd) {
        this.usrBD = user;
        this.passBD = pswd;
    }

    public void conectar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        urlBD = urlBD.replace("dbName", dbName);
        Class.forName(this.DRIVERCLASSNAME).newInstance();
        this.conn = DriverManager.getConnection(this.urlBD, this.usrBD, this.passBD);
    }

    public void cierraConexion() throws SQLException {
        new Thread(() -> {
            try {
                if (state != null) {
                    if (!state.isClosed()) {
                        state.close();
                    }
                }
                if (res != null) {
                    if (!res.isClosed()) {
                        res.close();
                    }
                }
                conn.close();
            } catch (SQLException ex) {
                new logger().errorBd(ex);

            }
        }).start();
    }

    public void kill() throws SQLException {
        conn.abort((Runnable command) -> {
            try {
                Statement st = (Statement) conn.createStatement();
                res = st.executeQuery("select * from information_schema.processlist where DB =  '" + dbName + "';");
                ArrayList<Integer> id = new ArrayList<>();
                while (res.next()) {
                    id.add(res.getInt("id"));
                }
                for (int i = id.size() - 1; i > 0; --i) {
                    st.executeUpdate("KILL " + id.get(i) + ";");
                }
                state.close();
            } catch (SQLException ex) {
                new logger().errorBd(ex);
            }
        });
    }

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
