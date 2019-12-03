/*
* Contenido de sqlParser generado por Luis Diego Jiménez Delgado en el 2019
 */
package sources.requests;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import models.Email;
import models.Phone;
import models.User;
import models.Session;
import models.enums.UserType;

/**
 *
 * @author Luis Diego Jiménez Delgado
 */
public class SourceRequests extends executorRequests implements sources.requests.RepositoryRequests {
    
    private final mapperRequests MAPPER = new mapperRequests();
    
    public SourceRequests(HttpServletRequest request) {
        super(request);
    }
    
    @Override
    public Email getEmail() {
        return new Email(Integer.parseInt(request.getParameter("email_id") == null ? "0" : request.getParameter("email_id"))).build(
                request.getParameter("email") == null ? "0" : request.getParameter("email"),
                true,
                true,
                true,
                Integer.parseInt(request.getParameter("user_id") == null ? "0" : request.getParameter("user_id"))
        );
    }
    
    @Override
    public Email[] getEmailList() {
        return null;
    }
    
    @Override
    public Phone getPhone() {
        return new Phone(Integer.parseInt(request.getParameter("phone_id") == null ? "0" : request.getParameter("phone_id"))).build(
                request.getParameter("phone") == null ? "0" : request.getParameter("phone"),
                Boolean.parseBoolean(request.getParameter("email") == null ? "0" : request.getParameter("email")),
                Boolean.parseBoolean(request.getParameter("email") == null ? "0" : request.getParameter("email")),
                Boolean.parseBoolean(request.getParameter("email") == null ? "0" : request.getParameter("email")),
                Integer.parseInt(request.getParameter("user_id") == null ? "0" : request.getParameter("user_id"))
        );
    }
    
    @Override
    public Phone[] getPhoneList() {
        return null;
    }
    
    @Override
    public User getUser() {
        return new User(Integer.parseInt(request.getParameter("user_id") == null ? "0" : request.getParameter("user_id")))
                .build(
                        null,
                        request.getParameter("surname") == null ? "" : request.getParameter("surname"),
                        new Date(),
                        Boolean.parseBoolean(request.getParameter("email") == null ? "0" : request.getParameter("email")),
                        null,
                        UserType.AVILABLE,
                        request.getParameter("password") == null ? "" : request.getParameter("password"),
                        request.getParameter("name") == null ? "" : request.getParameter("name")
                );
    }
    
    @Override
    public User[] getUserList() {
        return null;
    }
    
    @Override
    public String getAction() {
        return request.getParameter("c_action") == null ? "" : request.getParameter("c_action");
    }
    
    @Override
    public User getSessionUser() {
        return new User(0);
    }
    
    @Override
    public Session getSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
