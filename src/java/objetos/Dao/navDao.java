/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.Dao;

import controladores.controladorBD;
import controladores.seguridad.logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import objetos.nav;
import objetos.usuario;

/**
 *
 * @author luis
 */
public class navDao extends iDao<nav, Long> {

    private final controladorBD base = new controladorBD();
    private final logger errores = new logger();

    @Override
    public nav subir(nav objeto, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public nav modificar(nav objeto, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public nav traerDetalles(nav objeto, usuario _usuario) {
        try {
            base.conectar();
            ArrayList<String> param = new ArrayList<>();
            String sta;
            if (_usuario == null) {
                if (objeto.id != 0) {
                    sta = "select idMenu, nombreMenu, direccion, accion, posicion, status from ctg_Menu "
                            + "where status !=?  and idMenu = ?; ";
                    param.add("" + 0);
                    param.add("" + objeto.id);
                } else {
                    sta = "select idMenu, nombreMenu, direccion, accion, posicion, status from ctg_Menu "
                            + "where status !=?  and (nombreMenu like ? or direccion like ?) ;";
                    param.add("" + 0);
                    param.add(objeto.nombreMenu.equals("") ? "%%" : "%" + objeto.nombreMenu + "%");
                    param.add(objeto.direccion.equals("") ? "%%" : "%" + objeto.direccion + "%");
                }
            } else {
                if (objeto.id != 0) {
                    sta = "select ctM.idMenu, nombreMenu, direccion, accion, posicion, ctM.status from ctg_Menu as ctM "
                            + "inner join rel_AccesoMenu as rAM on rAM.idMenu = ctM.idMenu  "
                            + "where cTM.status !=0 and rAM.idPersona =? and rAM.status!= 0 and cTM.idMenu = ?;";
                    param.add("" + _usuario.idUsuario);
                    param.add("" + objeto.id);

                } else {
                    sta = "select ctM.idMenu, nombreMenu, direccion, accion, posicion, ctM.status from ctg_Menu as ctM "
                            + "inner join rel_AccesoMenu as rAM on rAM.idMenu = ctM.idMenu"
                            + "where cTM.status !=0 and rAM.idPersona =? and rAM.status!= 0 and (nombreMenu like ? or direccion like ?) ; ";
                    param.add("" + _usuario.idUsuario);
                    param.add(objeto.nombreMenu.equals("") ? "%%" : "%" + objeto.nombreMenu + "%");
                    param.add(objeto.direccion.equals("") ? "%%" : "%" + objeto.direccion + "%");
                }
            }
            nav con = new nav().parse(base.preparedS(sta, param.toArray(new String[param.isEmpty() ? 0 : param.size() - 1])));
            base.cierraConexion();
            return con;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            errores.error(ex, base);
            return null;
        }
    }

    @Override
    public List<nav> traer(usuario _usuario) {
        try {
            base.conectar();
            String state;
            ArrayList<String> param = new ArrayList<>();
            if (_usuario == null) {
                state = ("select idMenu, nombreMenu, direccion, accion, posicion, status from ctg_Menu "
                        + "where status =2; ");
            } else {
                state = ("select ctM.idMenu, nombreMenu, direccion, accion, posicion, ctM.status from ctg_Menu as ctM "
                        + "inner join rel_AccesoMenu as rAM on rAM.idMenu = ctM.idMenu  "
                        + "where cTM.status !=0 and rAM.idPersona =? and rAM.status!= 0; ");
                param.add("" + _usuario.idPersona);
            }
            List<nav> contenedorNav= new nav().parseList(base.preparedS(state, param.toArray(new String[param.isEmpty() ? 0 : param.size() - 1])));
            base.cierraConexion();
            return contenedorNav;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            errores.error(ex, base);
            return null;
        }

    }

    @Override
    public Long borrar(Long llave, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
