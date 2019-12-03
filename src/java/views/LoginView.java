package views;

import models.User;

/**
 *
 * @author luis
 */
public interface LoginView extends iView{
    
    public abstract void newAccount();

    public abstract void showPasswordError();

    public abstract void showEmailError();
    
    public abstract void showError();
    
    public abstract void submit(User user);

    public abstract void showDisableAccount();
    
    public abstract void showNoAccount();
    
    public abstract void showError(String text);
    
    public abstract void showVerified();
    
}
