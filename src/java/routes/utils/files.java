/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
 */
package routes.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */
@WebServlet(name = "files", urlPatterns = {"/files/*"})
public class files extends iServlet {

    private String contentType;
    private byte[] archivo;

    public files() {
        this.archivo = null;
    }

    @Override
    protected void envia(HttpServletResponse response) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType((contentType == null || contentType.equals("")) ? "application/json;charset=UTF-8" : contentType);
        resStatus = resStatus != 0 ? resStatus : archivo == null ? 404 : 0;
        if (result.equals("")) {
            if (archivo != null && resStatus == 0) {
                OutputStream os = response.getOutputStream();
                os.write(archivo);
                os.flush();
                os.close();
            } else {
                response.sendError(resStatus);
            }
        } else {
            PrintWriter out = response.getWriter();
            if (!result.equals("null")) {
                out.print(result);
            } else {
                response.sendError(400);
            }
        }
        this.destroy();
    }

    @Override
    protected void get() throws Exception {

    }

    @Override
    protected void post() throws Exception {

    }

}
