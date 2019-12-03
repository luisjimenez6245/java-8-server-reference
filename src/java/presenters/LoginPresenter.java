package presenters;

import sources.mysql.repositoryMysql;
import views.LoginView;

/**
 *
 * @author luis
 */
public class LoginPresenter {

    private final LoginView view;
    private final repositoryMysql source;

    public LoginPresenter(LoginView view, repositoryMysql source) {
        this.view = view;
        this.source = source;
    }

}
