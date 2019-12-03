/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
 */
package sources.mysql;

import controllers.security.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Email;
import models.Phone;
import models.User;

import models.Session;
import models.enums.UserType;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */
public class MapperMysql {

    private final Logger LOGGER = new Logger();

    public Email[] emailList(ResultSet res) {
        try {
            List<Email> li = new ArrayList<>();
            while (res.next()) {
                li.add(new Email(res.getInt("email_id")).build(res.getString("email"), res.getBoolean("is_active"), res.getBoolean("is_principal"), res.getBoolean("is_valid"), res.getInt("user_id")));
            }
            res.close();
            return (li.size() >= 1) ? li.toArray(new Email[li.size()]) : new Email[0];
        } catch (SQLException ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    public Phone[] phoneList(ResultSet res) {
        try {
            List<Phone> li = new ArrayList<>();
            while (res.next()) {
                li.add(new Phone(res.getInt("phone_id")).build(res.getString("phone"), res.getBoolean("is_active"), res.getBoolean("is_principal"), res.getBoolean("is_valid"), res.getInt("user_id")));
            }
            res.close();
            return (li.size() >= 1) ? li.toArray(new Phone[li.size()]) : new Phone[0];
        } catch (SQLException ex) {
            LOGGER.error(ex);

        }
        return null;
    }

    public User[] userList(ResultSet res) {
        try {
            List<User> li = new ArrayList<>();
            while (res.next()) {
                li.add(new User(res.getInt("user_id")).build(null, res.getString("surname"), res.getDate("creation_date"), res.getBoolean("is_active"), null, UserType.valueOf(res.getString("user_type")),
                        res.getString("password"), res.getString("name")));
            }
            res.close();
            return (li.size() >= 1) ? li.toArray(new User[li.size()]) : new User[0];
        } catch (SQLException ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    public Session[] sessionList(ResultSet res) {
        try {
            List<Session> li = new ArrayList<>();
            while (res.next()) {
                li.add(new Session(res.getInt("session_id")).build(new User(res.getInt("user_id")), res.getString("token"), res.getDate("date"), res.getString("ip")));
            }
            res.close();
            return (li.size() >= 1) ? li.toArray(new Session[li.size()]) : new Session[0];
        } catch (SQLException ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    public Email email(ResultSet res) {
        Email[] l = emailList(res);
        if (l != null && l.length > 0) {
            return l[0];
        }
        return new Email(0);
    }

    public Phone phone(ResultSet res) {
        Phone[] l = phoneList(res);
        if (l != null && l.length > 0) {
            return l[0];
        }
        return new Phone(0);
    }

    public User user(ResultSet res) {
        User[] l = userList(res);
        if (l != null && l.length > 0) {
            return l[0];
        }
        return new User(0);
    }

}
