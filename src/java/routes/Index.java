package routes;

import controllers.IndexController;
import javax.servlet.annotation.WebServlet;
import routes.utils.iServlet;

/**
 *
 * @author luis
 */
@WebServlet(name = "index", urlPatterns = {"/index/*"})
public class Index extends iServlet {

    private IndexController controller;

    @Override
    protected void get() throws Exception {
        controller = new IndexController(repository);
        controller.main();
        result = controller.createContent();
    }

    @Override
    protected void post() throws Exception {
        controller = new IndexController(repository);
        controller.main();
        result = controller.getJson();
    }

}
