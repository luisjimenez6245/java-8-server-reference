/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
 */
package sources.mysql;

import java.util.HashMap;

import models.Email;
import models.Phone;
import models.User;
import models.Session;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */
public class SourceMysql extends ExecutorMysql implements sources.mysql.repositoryMysql {

    private final MapperMysql MAPPER = new MapperMysql();

    public SourceMysql(String user, String password, String dbName, String url, String port) {
        super(user, password, dbName, url, port);
    }

    @Override
    public int deleteEmail(int key) {
        String query = "email";
        HashMap<String, Object> lista = new HashMap<>();
        if (key != 0) {
            lista.put("emailId", key);
        }

        this.delete(query, lista);
        Email helper = getEmail(new Email(key));
        if (helper == null || helper.emailId == 0) {
            return key;
        }
        return 0;
    }

    @Override
    public int deletePhone(int key) {
        String query = "phone";
        HashMap<String, Object> lista = new HashMap<>();
        if (key != 0) {
            lista.put("phoneId", key);
        }

        this.delete(query, lista);
        Phone helper = getPhone(new Phone(key));
        if (helper == null || helper.phoneId == 0) {
            return key;
        }
        return 0;
    }

    @Override
    public int deleteUser(int key) {
        String query = "user";
        HashMap<String, Object> lista = new HashMap<>();
        if (key != 0) {
            lista.put("userId", key);
        }
        this.delete(query, lista);
        User helper = getUser(new User(key));
        if (helper == null || helper.userId == 0) {
            return key;
        }
        return 0;
    }



    @Override
    public Email[] getEmailList(Email object) {
        String query = "select e.* from email e";
        HashMap<String, Object> lista = new HashMap<>();
        if (object != null) {
            if (object.email != null) {
                lista.put("email", object.email);
            }
            if (object.emailId != 0) {
                lista.put("email_id", object.emailId);
            }
            lista.put("is_principal", object.isPrincipal);

            lista.put("is_active", object.isActive);

            if (object.emailId != 0) {
                lista.put("email_id", object.emailId);
            }
        }
        return MAPPER.emailList(this.get(query, lista));
    }

    @Override
    public Phone[] getPhoneList(Phone object) {
        String query = "select p.* from phone p";
        HashMap<String, Object> lista = new HashMap<>();
        if (object != null) {
            if (object.phoneId != 0) {
                lista.put("phone_id", object.phoneId);
            }
            lista.put("phone", object.phone);
            lista.put("is_principal", object.isPrincipal);
            lista.put("is_active", object.isActive);
            if (object.phoneId != 0) {
                lista.put("phone_id", object.phoneId);
            }
        }
        return MAPPER.phoneList(this.get(query, lista));
    }

    @Override
    public User[] getUserList(User object) {
        String query = "select u.* from user u";
        HashMap<String, Object> lista = new HashMap<>();
        if (object != null) {
            if (object.password != null) {
                lista.put("password", object.password);
            }
            if (object.userId != 0) {
                lista.put("user_id", object.userId);
            }
            if (object.surname != null) {
                lista.put("surname", object.surname);
            }
            if (object.creationDate != null) {
                lista.put("creation_date", object.creationDate);
            }
            if (object.name != null) {
                lista.put("name", object.name);
            }
            if (object.userType != null) {
                lista.put("user_type", object.userType.toString());
            }
        }
        return MAPPER.userList(this.get(query, lista));
    }


    @Override
    public Email saveEmail(Email object) {
        String query = " email ";
        HashMap<String, Object> lista = new HashMap<>();
        if (object != null) {
            lista.put("is_valid", object.isValid);
            lista.put("is_active", object.isActive);
            lista.put("is_principal", object.isPrincipal);
            if (object.emailId != 0) {
                lista.put("email_id", object.emailId);
            }
            if (object.userId != 0) {
                lista.put("user_id", object.userId);
            }
            if (object.email != null) {
                lista.put("email", object.email);
            }
        }
        object = MAPPER.email(this.save(query, lista));
        object = getEmail(object);
        return object;
    }

    @Override
    public Phone savePhone(Phone object) {
        String query = " phone ";
        HashMap<String, Object> lista = new HashMap<>();
        if (object != null) {
            lista.put("is_active", object.isActive);
            if (object.phoneId != 0) {
                lista.put("phone_id", object.phoneId);
            }
            if (object.phoneId != 0) {
                lista.put("phone_id", object.phoneId);
            }
            if (object.phone != null) {
                lista.put("phone", object.phone);
            }
            if (object.userId != 0) {
                lista.put("user_id", object.userId);
            }
            lista.put("is_valid", object.isValid);
            lista.put("is_principal", object.isPrincipal);

        }
        object = MAPPER.phone(this.save(query, lista));
        object = getPhone(object);
        return object;
    }

    @Override
    public User saveUser(User object) {
        String query = " user ";
        HashMap<String, Object> lista = new HashMap<>();
        if (object != null) {
            if (object.password != null) {
                lista.put("password", object.password);
            }
            if (object.name != null) {
                lista.put("name", object.name);
            }
            if (object.emails != null) {
                lista.put("emails", object.emails);
            }
            if (object.userId != 0) {
                lista.put("user_id", object.userId);
            }
            lista.put("is_active", object.isActive);

            if (object.creationDate != null) {
                lista.put("creation_date", object.creationDate);
            }
            if (object.phones != null) {
                lista.put("phones", object.phones);
            }
            if (object.userType != null) {
                lista.put("user_type", object.userType);
            }
            if (object.surname != null) {
                lista.put("surname", object.surname);
            }
        }
        object = MAPPER.user(this.save(query, lista));
        object = getUser(object);
        return object;
    }

    

    @Override
    public Email setEmail(Email object) {
        String query = " update email set ";
        HashMap<String, Object> params = new HashMap<>();
        HashMap<String, Object> conditions = new HashMap<>();
        if (object != null) {
            conditions.put("email_id", object.emailId);
            params.put("is_principal", object.isPrincipal);
            if (object.email != null) {
                params.put("email", object.email);
            }
            params.put("is_active", object.isActive);
        }
        object = MAPPER.email(this.set(query, params, conditions));
        object = getEmail(object);
        return object;
    }

    @Override
    public Phone setPhone(Phone object) {
        String query = " update phone set ";
        HashMap<String, Object> params = new HashMap<>();
        HashMap<String, Object> conditions = new HashMap<>();
        if (object != null) {
            conditions.put("phone_id", object.phoneId);
            params.put("is_principal", object.isPrincipal);
            if (object.phone != null) {
                params.put("phone", object.phone);
            }
        }
        params.put("is_active", object.isActive);
        object = MAPPER.phone(this.set(query, params, conditions));
        object = getPhone(object);
        return object;
    }

    @Override
    public User setUser(User object) {
        String query = " update user set ";
        HashMap<String, Object> params = new HashMap<>();
        HashMap<String, Object> conditions = new HashMap<>();
        if (object != null) {
            conditions.put("user_id", object.userId);
            if (object.creationDate != null) {
                params.put("creation_date", object.creationDate);
            }
            if (object.password != null) {
                params.put("password", object.password);
            }
            if (object.phones != null) {
                params.put("phones", object.phones);
            }
            if (object.name != null) {
                params.put("name", object.name);
            }

            if (object.surname != null) {
                params.put("surname", object.surname);
            }
            if (object.userType != null) {
                params.put("user_type", object.userType);
            }
            params.put("is_active", object.isActive);
            if (object.emails != null) {
                params.put("emails", object.emails);
            }
        }
        object = MAPPER.user(this.set(query, params, conditions));
        object = getUser(object);
        return object;
    }

   
    @Override
    public Email getEmail(Email object) {
        Email[] res = getEmailList(object);
        if (res != null && res.length > 0) {
            return res[0];
        }
        return new Email(0);
    }

    @Override
    public Phone getPhone(Phone object) {
        Phone[] res = getPhoneList(object);
        if (res != null
                && res.length > 0) {
            return res[0];
        }
        return new Phone(0);
    }

    @Override
    public User getUser(User object) {
        User[] res = getUserList(object);
        if (res != null
                && res.length > 0) {
            return res[0];
        }
        return new User(0);
    }

  

    @Override
    public Session getSession(Session object) {
        Session[] res = getSessionList(object);
        if (res != null
                && res.length > 0) {
            return res[0];
        }
        return new Session(0);
    }

    @Override
    public int deleteSession(int key) {
        String query = "session";
        HashMap<String, Object> lista = new HashMap<>();
        if (key != 0) {
            lista.put("session_id", key);
        }

        this.delete(query, lista);
        Email helper = getEmail(new Email(key));
        if (helper == null || helper.emailId == 0) {
            return key;
        }
        return 0;
    }

    @Override
    public Session saveSession(Session object) {
         
        return object;
    }
    

    @Override
    public Session updateSession(Session object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Session[] getSessionList(Session object) {
        String query = "select s.* from session s";
        HashMap<String, Object> lista = new HashMap<>();
        if (object != null) {
            if (object.sessionId != 0) {
                lista.put("session_id", object.sessionId);
            }
        }
        return MAPPER.sessionList(this.get(query, lista));
    }
}
