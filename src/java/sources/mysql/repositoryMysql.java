/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
 */
package sources.mysql;

import models.Email;
import models.Phone;
import models.User;
import models.Session;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */
public interface repositoryMysql {

    public Email saveEmail(Email object);

    public Email setEmail(Email object);

    public Email getEmail(Email object);

    public Email[] getEmailList(Email object);

    public int deleteEmail(int key);

    public Phone savePhone(Phone object);

    public Phone setPhone(Phone object);

    public Phone getPhone(Phone object);

    public Phone[] getPhoneList(Phone object);

    public int deletePhone(int key);

    public User saveUser(User object);

    public User setUser(User object);

    public User getUser(User object);

    public User[] getUserList(User object);

    public int deleteUser(int key);

    public Session getSession(Session object);

    public Session[] getSessionList(Session object);

    public int deleteSession(int key);

    public Session saveSession(Session object);

    public Session updateSession(Session object);

}
