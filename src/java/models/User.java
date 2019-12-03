/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
 */
package models;

import java.util.Date;
import models.utils.Model;
import models.enums.UserType;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */
public class User extends Model {

    public Phone[] phones;
    public String surname;
    public int userId;
    public Date creationDate;
    public boolean isActive;
    public Email[] emails;
    public UserType userType;
    public String password;
    public String name;

    public User(int userId) {
        this.userId = userId;
    }

    public User build(Phone[] phones, String surname, Date creationDate, boolean isActive, Email[] emails, UserType userType, String password, String name) {
        this.phones = phones;
        this.surname = surname;
        this.creationDate = creationDate;
        this.isActive = isActive;
        this.emails = emails;
        this.userType = userType;
        this.password = password;
        this.name = name;
        return this;
    }

}
