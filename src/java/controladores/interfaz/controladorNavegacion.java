/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.interfaz;

import objetos.Dao.antecedenteDao;
import objetos.Dao.navDao;
import objetos.nav;
import objetos.usuario;

/**
 *
 * @author luis
 */
public class controladorNavegacion extends controladores.controladorBase {

    public controladorNavegacion() {
    }

    public String verficaPermiso(nav _nav, usuario _usuario) {
        navDao navD = new navDao();
        return JSON.toJson(navD.traerDetalles(_nav, _usuario));
    }

    public String traeAutorizados( usuario _usuario) {
        navDao navD = new navDao();
        return JSON.toJson(navD.traer(_usuario));
    }
    
    public String traeAntecedentes(){
        antecedenteDao antecedenteD = new antecedenteDao();
        return JSON.toJson(antecedenteD.traer(null));
    }
    
}
