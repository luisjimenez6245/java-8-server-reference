/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
*/

package controllers.utils;

import com.google.gson.Gson;
import controllers.security.Logger;
import models.utils.Model;
import sources.mysql.repositoryMysql;
import sources.requests.RepositoryRequests;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */

public abstract class iController{
    
    public final Gson JSON = new Gson();
    public final Logger ERRORES = new Logger();
    protected String res = "";
    public repositoryMysql source;
    
    protected iController(repositoryMysql source){
        this.source = source;
    }
    
    public abstract void get(String name, RepositoryRequests repository);
    public abstract void getList(String name, RepositoryRequests repository);
    public abstract void save(String name, RepositoryRequests repository);
    public abstract void delete(String name, RepositoryRequests repository);
    public abstract void update(String name, RepositoryRequests repository);
    
    public String getContent(){
        return res;
    }

}
