/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
 */
package models;

import models.utils.Model;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */
public class Email extends Model {

    public int emailId;
    public String email;
    public boolean isActive;
    public boolean isPrincipal;
    public boolean isValid;
    public int userId;

    public Email(int emailId) {
        this.emailId = emailId;
    }

    public Email build(String email, boolean isActive, boolean isPrincipal, boolean isValid, int userId) {
        this.email = email;
        this.isActive = isActive;
        this.isPrincipal = isPrincipal;
        this.isValid = isValid;
        this.userId = userId;
        return this;
    }

}
