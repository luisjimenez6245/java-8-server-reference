package extras;

import controllers.security.logger;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Luis Diego Jiménez Delgado 2018
 */
public class emailSender {

    private String body;
    private String to;
    private String subject;

    private final String MAILER = "administrador@luisjimenez6245.mx";
    private final String PSW = "Siul6245.";
    private final String SERVER = "smtpout.secureserver.net";
    private final int PUERTO = 80;

    private final logger errores = new logger();
    private MimeMessage correo;
    private Transport t;

    public emailSender(String body, String to, String subject) {
        this.body = body;
        this.to = to;
        this.subject = subject;
    }

    public String getCuerpo() {
        return body;
    }

    public void setCuerpo(String body) {
        this.body = body;
    }

    public String getPara() {
        return to;
    }

    public void setPara(String to) {
        this.to = to;
    }

    public String getAsunto() {
        return subject;
    }

    public void setAsunto(String subject) {
        this.subject = subject;
    }

    private void inicializa() throws UnsupportedEncodingException, MessagingException {
        Properties confMail = new Properties();
        confMail.setProperty("mail.smtp.ssl.trust", SERVER);
        confMail.setProperty("mail.smtp.host", SERVER);
        confMail.setProperty("mail.smtp.starttls.enable", "true");
        confMail.setProperty("mail.smtp.port", "" +PUERTO);
        confMail.setProperty("mail.smtp.user", MAILER);
        confMail.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(confMail);
        correo = new MimeMessage(session);
        correo.setFrom(new InternetAddress(MAILER, "Luis Diego Jiménez Delgado"));
        t = session.getTransport("smtp");
        t.connect(MAILER, PSW);
    }

    public boolean mandaEmail() {
        try {
            inicializa();
            correo.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
            correo.setSubject(this.subject);
            correo.setContent(this.body, "text/html");
            t.sendMessage(correo, correo.getAllRecipients());
            return cierraConexion();
        } catch (UnsupportedEncodingException | MessagingException ex) {
            errores.errorEmail(ex);
            return false;
        }
    }

    private boolean cierraConexion() {
        try {
            t.close();
            return true;
        } catch (MessagingException ex) {
            errores.errorEmail(ex);
            return false;
        }
    }

    public boolean mandaEmail(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        return mandaEmail();
    }

    public boolean conDocumento(String fileString) {
        try {
            inicializa();
            correo.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
            correo.setSubject(this.subject);
            correo.setContent(this.body, "text/html");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(this.body, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(fileString);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(source.getName());
            multipart.addBodyPart(messageBodyPart);
            correo.setContent(multipart);
            t.sendMessage(correo, correo.getAllRecipients());
            return cierraConexion();
        } catch (UnsupportedEncodingException | MessagingException ex) {
            errores.errorEmail(ex);
            return false;
        }
    }

    public boolean conDocumento(String to, String subject, String body, String fileString) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        return conDocumento(fileString);
    }

}
