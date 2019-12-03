/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
 */
package models;

import models.utils.Model;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */
public class Phone extends Model {

    public String phone;
    public int phoneId;
    public boolean isActive;
    public boolean isPrincipal;
    public boolean isValid;
    public int userId;

    public Phone(int phoneId) {
        this.phoneId = phoneId;
    }

    public Phone build(String phone, boolean isActive, boolean isPrincipal, boolean isValid, int userId) {
        this.phone = phone;
        this.isActive = isActive;
        this.isPrincipal = isPrincipal;
        this.isValid = isValid;
        this.userId = userId;
        return this;
    }

}
