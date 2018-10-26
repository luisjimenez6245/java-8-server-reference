/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author luis
 */
public class controladorBD {

    private String usrBD = "root";
    private String passBD = "";
    private String urlBD = "jdbc:mysql://127.0.0.1:3306/?allowPublicKeyRetrieval=true&useSSL=false";
    private String driverClassName = "com.mysql.jdbc.Driver";;
    private Connection conn = null;
    private Statement estancia;

    public controladorBD(String usuarioBD, String passwordBD, String url, String driverClassName) {
        this.usrBD = usuarioBD;
        this.passBD = passwordBD;
        this.urlBD = url;
        this.driverClassName = driverClassName;
    }

    public controladorBD(String url) {
        this.urlBD = url;
    }
    
    public controladorBD() {
    }

    //metodos para establecer los valores de conexion a la BD
    public void setUsuarioBD(String usuario) throws SQLException {
        this.usrBD = usuario;
    }

    public void setPassBD(String pass) {
        this.passBD = pass;
    }

    public void setUrlBD(String url) {
        this.urlBD = url;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    //Conexion a la BD
    public void conectar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName(this.driverClassName).newInstance();
        this.conn = DriverManager.getConnection(this.urlBD, this.usrBD, this.passBD);
    }

    //Cerrar la conexion de BD
    public void cierraConexion() throws SQLException {
        this.conn.close();
    }
    
    

    //Metodos para ejecutar sentencias SQL
    public PreparedStatement getStatement(String sentencia) throws SQLException {
        return this.conn.prepareStatement(sentencia);
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
    public ResultSet consulta(String consulta) throws SQLException {
        this.estancia = (Statement) conn.createStatement();
        return this.estancia.executeQuery(consulta);
    }

    public void actualizar(String actualiza) throws SQLException {
        this.estancia = (Statement) conn.createStatement();
        estancia.executeUpdate(actualiza);
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
