package models;

import java.util.Date;
import models.utils.Model;

public class Session extends Model {

    public int sessionId;
    public User user;
    public String token;
    public Date date;
    public String ip;

    public Session(int sessionId) {
        this.sessionId = sessionId;
    }

    public Session build(User user, String token, Date date, String ip) {
        
        return this;
    }
}
