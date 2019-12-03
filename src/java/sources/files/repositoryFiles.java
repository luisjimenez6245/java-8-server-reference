/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
*/

package sources.files;

import java.io.File;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */

public interface repositoryFiles {

    public String readFile(String file);

    public boolean writeFile(File file);

    public boolean writeFile(String file);
    
    public boolean createDirectory(String name);

}
