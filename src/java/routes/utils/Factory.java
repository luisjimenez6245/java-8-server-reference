/*
* Contenido de sqlParser generado por $author$
 */
package routes.utils;

import controllers.security.Manager;
import controllers.utils.Devs;
import presenters.IndexPresenter;
import presenters.LoginPresenter;
import sources.mysql.repositoryMysql;
import sources.mysql.SourceMysql;
import views.IndexView;
import views.LoginView;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */
public class Factory {

    private static final Manager MAN = Manager.getInstance();
    private static final repositoryMysql SOURCE = new SourceMysql(MAN.getProperty("dbUser"), MAN.getProperty("dbPassword"), MAN.getProperty("dbName"), MAN.getProperty("dbUrl"), MAN.getProperty("dbPort"));

    public static LoginPresenter createLoginPresenter(LoginView view) {
        return new LoginPresenter(view, SOURCE);
    }

    public static IndexPresenter createIndexPresenter(IndexView view) {
        return new IndexPresenter(view, SOURCE);
    }

    public static Devs createDev() {
        return new Devs(SOURCE);
    }

}
