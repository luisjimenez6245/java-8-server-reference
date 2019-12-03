/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
 */
package routes.utils;

import controllers.utils.Devs;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */
@WebServlet(name = "dev", urlPatterns = {"/dev/*"})
public class dev extends iServlet {

    private final Devs control = Factory.createDev();

    @Override
    protected void get() throws Exception {
        String helper = page.toLowerCase();
        System.out.println(helper);
        if (helper.contains("get")) {
            control.get(helper.replace("get", ""), repository);
        } else if (helper.contains("save")) {
            control.save(helper.replace("save", ""), repository);

        } else if (helper.contains("getList")) {
            control.getList(helper.replace("getlist", ""), repository);

        } else if (helper.contains("delete")) {
            control.delete(helper.replace("delete", ""), repository);

        } else if (helper.contains("set")) {
            control.update(helper.replace("set", ""), repository);
        }
        result = control.getContent();

    }

    @Override
    protected void post() throws Exception {
        String helper = page.toLowerCase();
        if (helper.contains("get")) {
            control.get(page.toLowerCase().replace("", "get/"), repository);
        } else if (helper.contains("save")) {
            control.save(page.toLowerCase().replace("", "save/"), repository);

        } else if (helper.contains("getList")) {
            control.getList(page.toLowerCase().replace("", "getList/"), repository);

        } else if (helper.contains("delete")) {
            control.delete(page.toLowerCase().replace("", "delete/"), repository);

        } else if (helper.contains("set")) {
            control.update(page.toLowerCase().replace("", "set/"), repository);
        }
        result = control.getContent();
    }
}
