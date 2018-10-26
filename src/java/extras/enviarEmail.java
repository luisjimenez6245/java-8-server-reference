/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

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
 * @author luis
 */
public class enviarEmail {

    private String cuerpo;
    private String para;
    private String asunto;

    private final String MAILER = "";
    private final String PSW = "";
    private final String SERVER = "smtpout..net";
    private final int PUERTO = 80;

    private final logger errores = new logger();
    private MimeMessage correo;
    private Transport t;

    public enviarEmail(String cuerpo, String para, String asunto) {
        this.cuerpo = cuerpo;
        this.para = para;
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
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
            correo.addRecipient(Message.RecipientType.TO, new InternetAddress(this.para));
            correo.setSubject(this.asunto);
            correo.setContent(this.cuerpo, "text/html");
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

    public boolean mandaEmail(String para, String asunto, String cuerpo) {
        this.para = para;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        return mandaEmail();
    }

    public boolean conDocumento(String fileString) {
        try {
            inicializa();
            correo.addRecipient(Message.RecipientType.TO, new InternetAddress(this.para));
            correo.setSubject(this.asunto);
            correo.setContent(this.cuerpo, "text/html");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(this.cuerpo, "text/html");
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

    public boolean conDocumento(String para, String asunto, String cuerpo, String fileString) {
        this.para = para;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        return conDocumento(fileString);
    }

}
