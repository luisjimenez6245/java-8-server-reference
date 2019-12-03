package controllers.security;

import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.User;
import models.enums.UserType;

/**
 *
 * @author luis
 */
public class Users {
    
    public Users(){
    }

    public User getUser(HttpServletRequest request) {
        Base64.Decoder d = Base64.getDecoder();
        HttpSession session = request.getSession(true);
        String aux;
        if (request.getParameter("x") != null && request.getParameter("r") != null && request.getParameter("a") != null && request.getParameter("token") != null && request.getParameter("t") != null) {
            aux = new String(d.decode(request.getParameter("x").getBytes()));
            try {
                return new User(Integer.parseInt(aux)).build(null,
                        request.getParameter("r"),
                        null,
                        Boolean.parseBoolean(request.getParameter("a")),
                        null,
                        UserType.valueOf(request.getParameter("u_t")),
                        request.getParameter("token") == null ? "" : request.getParameter("token"),
                        request.getParameter("t"));
            } catch (NumberFormatException ex) {
            }
        }
        if (!session.isNew()) {
            if (session.getAttribute("x") != null && session.getAttribute("r") != null && session.getAttribute("t") != null && session.getAttribute("a") != null && session.getAttribute("t") != null) {
                try {
                    aux = ("" + session.getAttribute("x"));
                    return new User(Integer.parseInt(aux)).build(null,
                            request.getParameter("r"),
                            null,
                            Boolean.parseBoolean(request.getParameter("a")),
                            null,
                            UserType.valueOf(request.getParameter("u_t")),
                            request.getParameter("token") == null ? "" : request.getParameter("token"),
                            request.getParameter("t"));
                } catch (NumberFormatException ex) {
                }
            }
            session.invalidate();
        }
        return null;
    }

    public User getEncryptedUser(HttpServletRequest request) {
        if (request.getParameter("x") != null) {
            Base64.Decoder d = Base64.getDecoder();
            String aux = new String(d.decode(request.getParameter("x").getBytes()));
            try {
                return new User(Integer.parseInt(aux)).build(null,
                        request.getParameter("r") == null ? "" : new String(d.decode(request.getParameter("r").getBytes())),
                        null,
                        request.getParameter("a") == null ? false : Boolean.parseBoolean(new String(d.decode(request.getParameter("a").getBytes()))),
                        null,
                        request.getParameter("u_t") == null ? UserType.BLOCKED : UserType.valueOf(request.getParameter("u_t")),
                        request.getParameter("token") == null ? "" : request.getParameter("token"),
                        request.getParameter("t") == null ? "" : new String(d.decode(request.getParameter("t").getBytes())));
            } catch (NumberFormatException e) {
                System.out.println("ora que paso");
            }
        }
        return null;

    }

    public static String getToken(User user) {
        String res = Manager.getInstance().getProperty("URL") + "/login%c_action=auth#x=" + user.userId;
        return res;
    }

}
