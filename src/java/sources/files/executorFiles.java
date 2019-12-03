/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
*/

package sources.files;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */

public class executorFiles {

    
    public executorFiles() {
    }

    protected String saveDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("sjksjkdnjk");
            if (file.mkdir()) {
                return path;
            }
            else{
                return "";
            }
        }
        return null;
    }

    protected String saveFile(String name, String ext, List<String> content) throws IOException {
        Path file = Paths.get(name + ext);
        Files.write(file, content, Charset.forName("UTF-8"));
        return name + ext;
    }

    protected String getFile(String path) throws FileNotFoundException, IOException {
        String res = "";
        String helper = "";
        try (BufferedReader stdInput = new BufferedReader(new FileReader(path))) {
            while ((helper = stdInput.readLine()) != null) {
                res += helper;
            }
        }
        return res;
    }

    protected byte[] getBytesFromFile(File arch) {
        if (arch == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(arch);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1) {
                out.write(b, 0, n);
            }
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {

        }
        return null;
    }
}
