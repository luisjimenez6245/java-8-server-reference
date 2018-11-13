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
import objetos.antecedente;
import objetos.usuario;

/**
 *
 * @author luis
 */
public class antecedenteDao extends iDao<antecedente, Long>{
    
    private final controladorBD base = new controladorBD();
    private final logger errores = new logger();

    @Override
    public antecedente subir(antecedente objeto, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public antecedente modificar(antecedente objeto, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public antecedente traerDetalles(antecedente objeto, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<antecedente> traer(usuario _usuario) {
        try {
            base.conectar();
            String state;
            ArrayList<String> param = new ArrayList<>();
            if (_usuario == null) {
                state = ("select idDatoAntecedente as id, descripcionAntecedente as nombre, '' as valor from ctg_DatosAntecedentes;");
            } else {
                state = ("select ctD.idDatoAntecedente as id, ctD.descripcionAntecedente as nombre, reA.valor as valor "
                        + "from rel_antecedentes  reA "
                        + "inner join ctg_DatosAntecedentes ctD on reA.idAntecedente = ctD.idDatoAntecedente "
                        + "where ctD.status != 0 and reA.status != 0 and reA.idPersona = ?  order by fechaRegistro; ");
                param.add("" + _usuario.idPersona);
            }
            List<antecedente> con= new antecedente().parseList(base.preparedS(state, param.toArray(new String[param.isEmpty() ? 0 : param.size() - 1])));
            base.cierraConexion();
            return con;
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
