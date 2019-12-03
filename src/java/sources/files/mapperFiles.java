/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
*/

package sources.files;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

import models.User;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */

public class mapperFiles {

    public final Gson json = new Gson();

    public List<User> listUser(String json) {
        return this.json.fromJson(json, new TypeToken<List<User>>() {
        }.getType());
    }

    public User user(String json) {
        return null;
    }

}