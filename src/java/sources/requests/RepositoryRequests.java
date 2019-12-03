/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
 */
package sources.requests;

import models.Email;
import models.Phone;
import models.User;
import models.Session;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */
public interface RepositoryRequests {

    public Email getEmail();

    public Email[] getEmailList();

    public Phone getPhone();

    public Phone[] getPhoneList();

    public User getUser();

    public User getSessionUser();

    public Session getSession();

    public User[] getUserList();

    public String getAction();

}
