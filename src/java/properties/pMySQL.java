/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import controladores.seguridad.logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author luis
 */
public class pMySQL {

    private String usrBD = "luisjimenez6245";
    private String passBD = "Siul6245";
    private String dbName = "medicall";
    private String urlBD = "jdbc:mysql://187.221.96.156:3306/dbName?allowPublicKeyRetrieval=true&useSSL=false";
    private final String DRIVERCLASSNAME = "com.mysql.jdbc.Driver";
    protected Connection conn = null;
    protected PreparedStatement state = null;
    protected ResultSet res = null;

    public pMySQL(String usuarioBD, String passwordBD, String url, String dbName) {
        this.usrBD = usuarioBD;
        this.passBD = passwordBD;
        this.urlBD = url;
        this.dbName = dbName;
    }

    public pMySQL(String url) {
        this.urlBD = url;
    }

    public pMySQL() {
    }

    public pMySQL(String usuarioBD, String passwordBD) {
        this.usrBD = usuarioBD;
        this.passBD = passwordBD;
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

}
