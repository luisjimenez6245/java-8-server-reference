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
import objetos.usuario;

/**
 *
 * @author luis
 */
public class usuarioDao extends iDao<usuario, Long> {

    private final String CONSULTA = "select distinct tbU.idUsuario, tbP.idPersona, tbP.nombre, tbP.aPaterno as apellidos , tbP.fechaNacimiento, tbU.tipoUsuario as tipo, tbP.dependencia as dependencia, tbP.genero, tbU.password as otro, tbU.status,";
    private final String EMAIL = "concat('[', group_concat(concat(\"{'email':'\",  tbE.email, \"', 'id':\" ,tbE.idEmail, \",'status':'\", tbE.status , \"'}\" ) separator \",\" ), ']') as email";
    private final String TELEFONO = "concat( '[',group_concat(concat(\"{'telefono':'\", tbT.telefono,\"','id':\", tbT.idTelefono, \",'status':'\",tbT.status,\"'}\") separator \",\" ), ']') as telefono";
    private final controladorBD BASE = new controladorBD();
    private final logger ERRORES = new logger();

    @Override
    public Long borrar(Long llave, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public usuario subir(usuario objeto, usuario _usuario) {
        try {
            BASE.conectar();
            ArrayList<String> param = new ArrayList<>();
            String sta;
            if (_usuario == null) {
                //sin autizarion e independdientes

                sta = " call sp_RegistroPersona( ? ,  ? ,  ? ,  ? ,  ? ,?,  ? ,  ? );";
                param.add("" + 0);
                param.add("" + objeto.idPersona);
                param.add(objeto.nombre);
                param.add(objeto.apellidos);
                param.add(objeto.fechaNacimiento);
                param.add("" + objeto.genero);
                param.add("" + objeto.dependencia);
                param.add("" + 1);

                if (sp(BASE.preparedS(sta, param.toArray(new String[param.isEmpty() ? 0 : param.size() - 1])))) {
                    param = new ArrayList<>();

                    sta = " call sp_RegistroUsuario(? , ? ,  ? ,  ? , ? ,  ? , ? , ? ,  ?, ?);";
                    param.add("" + 0);
                    param.add("" + objeto.idUsuario);
                    param.add("" + tipo);
                    param.add("" + objeto.email);
                    param.add("" + 1);
                    param.add("" + objeto.telefono);
                    param.add("" + 1);
                    param.add("" + objeto.otro);
                    param.add("" + objeto.tipo);
                    param.add("" + objeto.tipo);

                } else {
                    System.out.println(msj);
                    sta = "";
                }

            } else {
                //con aturizacion
                sta = " call sp_RegistroPersona( ? ,  ? ,  ? ,  ? ,  ? ,  ? ,   );";
                param.add("" + _usuario.idUsuario);
                param.add("" + objeto.idPersona);
                param.add(objeto.nombre);
                param.add(objeto.apellidos);
                param.add(objeto.fechaNacimiento);
                param.add("" + objeto.genero);
                param.add("" + objeto.dependencia);
                param.add("" + 1);

            }
            if (!sta.equals("")) {
                if (sp(BASE.preparedS(sta, param.toArray(new String[param.isEmpty() ? 0 : param.size() - 1])))) {

                    objeto = new usuario(tipo);
                } else {
                    System.out.println(msj);
                    objeto = new usuario();
                }
                objeto = traerDetalles(objeto, objeto);
            } else {
                objeto = new usuario();
            }
            BASE.cierraConexion();
            return objeto;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            ERRORES.error(ex, BASE);
            return null;
        }

    }

    @Override
    public usuario modificar(usuario objeto, usuario _usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public usuario traerDetalles(usuario objeto, usuario _usuario) {
        try {
            BASE.conectar();
            ArrayList<String> param = new ArrayList<>();
            String sta;
            if (_usuario == null) {
                //sin autizarion
                sta = CONSULTA
                        + EMAIL + ","
                        + TELEFONO + " "
                        + "from tbl_usuarios tbU "
                        + "inner join tbl_email  tbE on tbU.idUsuario = tbE.idUsuario "
                        + "inner join  tbl_Telefono  tbT on tbU.idUsuario = tbT.idUsuario "
                        + "inner join tbl_Personas tbP on tbP.idPersona = tbU.persona "
                        + "where tbE.status !=0 and tbT.status != 0 and tbU.status != 0 and (tbE.email like ?  or tbT.telefono like ?)"
                        + "group by tbU.idUsuario;";
                param.add(objeto.email);
                param.add("%" + objeto.telefono);

            } else {
                //con aturizacion
                if (objeto.idUsuario == _usuario.idUsuario) {
                    sta = CONSULTA
                            + EMAIL + ","
                            + TELEFONO + " "
                            + "from tbl_usuarios tbU "
                            + "inner join tbl_email  tbE on tbU.idUsuario = tbE.idUsuario "
                            + "inner join  tbl_Telefono  tbT on tbU.idUsuario = tbT.idUsuario "
                            + "inner join tbl_Personas tbP on tbP.idPersona = tbU.persona "
                            + "where tbE.status !=0 and tbT.status != 0 and tbU.status != 0 and tbU.idUsuario = ?"
                            + "group by tbU.idUsuario;";
                    param.add("" + objeto.idUsuario);

                } else {
                    sta = "";
                }

            }
            usuario con = new usuario().parse(BASE.preparedS(sta, param.toArray(new String[param.isEmpty() ? 0 : param.size() - 1])));
            BASE.cierraConexion();
            return con;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            ERRORES.error(ex, BASE);
            return null;
        }
    }

    @Override
    public List<usuario> traer(usuario _usuario) {
        try {
            BASE.conectar();
            ArrayList<String> param = new ArrayList<>();
            String sta;
            if (_usuario == null) {
                //sin autizarion
                sta = CONSULTA
                        + EMAIL + ","
                        + TELEFONO + " "
                        + "from tbl_usuarios tbU "
                        + "inner join tbl_email  tbE on tbU.idUsuario = tbE.idUsuario "
                        + "inner join  tbl_Telefono  tbT on tbU.idUsuario = tbT.idUsuario "
                        + "inner join tbl_Personas tbP on tbP.idPersona = tbU.persona "
                        + "where tbE.status !=0 and tbT.status != 0 and tbU.status != 0 and (tbE.email like ?  or tbT.telefono like ?)"
                        + "group by tbU.idUsuario;";
            } else {
                sta = CONSULTA
                        + EMAIL + ","
                        + TELEFONO + " "
                        + "from tbl_usuarios tbU "
                        + "inner join tbl_email  tbE on tbU.idUsuario = tbE.idUsuario "
                        + "inner join  tbl_Telefono  tbT on tbU.idUsuario = tbT.idUsuario "
                        + "inner join tbl_Personas tbP on tbP.idPersona = tbU.persona "
                        + "where tbE.status !=0 and tbT.status != 0 and tbU.status != 0 and (tbE.email like ?  or tbT.telefono like ?)"
                        + "group by tbU.idUsuario;";

            }

            List<usuario> con = new usuario().parseList(BASE.consulta(sta, null));
            BASE.cierraConexion();
            return con;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            ERRORES.error(ex, BASE);
            return null;
        }
    }

}
