/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import objetos.usuario;
import controladores.seguridad.logger;

/**
 *
 * @author luis
 * @param <T>
 * @param <K>
 */
public abstract class iDao<T, K> {

    public long tipo = 0;
    public String msj = "";

    public abstract T subir(T objeto, usuario _usuario);

    public abstract T modificar(T objeto, usuario _usuario);

    public abstract T traerDetalles(T objeto, usuario _usuario);

    public abstract List<T> traer(usuario _usuario);

    public abstract K borrar(K llave, usuario _usuario);

    public boolean sp(ResultSet res) {
        try {
            if (res.next()) {
                tipo = res.getLong("tipo");
                msj = res.getString("msj");
            }
            return tipo != 0;
        } catch (SQLException ex) {
            logger log = new controladores.seguridad.logger();
            log.error(ex);
        }
        return false;
    }

    public String getSP() {
        return "{ \"msj\" : \"" + msj + "\", \"tipo\":" + tipo + "}";
    }

}
