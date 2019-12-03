package routes;

import controllers.LoginController;
import javax.servlet.annotation.WebServlet;
import routes.utils.iServlet;

/**
 *
 * @author luis
 */
@WebServlet(name = "login", urlPatterns = {"/login/*"})
public class Login extends iServlet {

    private LoginController controller;

    @Override
    protected void get() throws Exception {
        controller = new LoginController(repository);
        controller.main();
        result = controller.createContent();
    }

    @Override
    protected void post() throws Exception {
        controller = new LoginController(repository);
        result = controller.getJson();
    }
}
