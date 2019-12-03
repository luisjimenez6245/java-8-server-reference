package views;

import models.User;

/**
 *
 * @author luis
 */
public interface IndexView extends iView {
    
    public void showError(String message);
    
    public void setUser(User user);
    
    

}
