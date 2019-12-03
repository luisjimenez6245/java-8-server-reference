package presenters;

import models.User;
import sources.mysql.repositoryMysql;
import views.IndexView;

/**
 *
 * @author luis
 */
public class IndexPresenter {

    private final IndexView view;
    private final repositoryMysql source;

    public IndexPresenter(IndexView view, repositoryMysql source) {
        this.view = view;
        this.source = source;
    }

    public void loadView(User user) {
        
    }

    public void loadView() {
        this.view.view();
    }

    

}
