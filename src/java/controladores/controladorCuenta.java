/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.google.gson.Gson;
import extras.logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import objetos.contenedorRespuestaSP;

/**
 *
 * @author luis
 */
public class controladorCuenta {

    private final controladorBD base = new controladorBD();
    private final logger errores = new logger();
    private String contendor =  "";
    private Gson json = new Gson();
    
    public boolean existenciaCorreo(String email) {
        return metodo(1, email, 0);
    }
    
    private boolean metodo(int opcion, String email, int id){
        contenedorRespuestaSP conRes  = new contenedorRespuestaSP();
        try {
            base.conectar();
            PreparedStatement state = base.getStatement("call sp_controlEmail(?,?,?);");
            state.setInt(1, opcion);
            state.setString(2, email);
            state.setInt(3, id);
            ResultSet res = base.realiza(state);
            boolean respuesta = false;
            if (res.next()) {
                respuesta = res.getInt("tipo") == 1;
                conRes.setMsj(res.getString("msj"));
                conRes.setTipo(res.getInt("tipo"));
                contendor  = json.toJson(conRes);
            }
            state.close();
            res.close();
            base.cierraConexion();
            return respuesta;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            errores.error(ex, base);
            return false;
        }
       
    }

    public String getContendor() {
        return contendor;
    }

    public void setContendor(String contendor) {
        this.contendor = contendor;
    }

}
