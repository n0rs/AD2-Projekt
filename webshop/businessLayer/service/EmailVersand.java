package webshop.businessLayer.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EmailVersand {
    private static String ABSENDER;
    private static String PASSWORT;

    static {
        try (FileInputStream file = new FileInputStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(file);

            ABSENDER = properties.getProperty("email.absender");
            PASSWORT = properties.getProperty("email.passwort"); // TODO: APP-Passwort benötigt
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Konfigurationsdatei: " + e.getMessage());
            }
        }

    public static void sendeEmail(String empfaenger, String betreff, String text) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ABSENDER, PASSWORT);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(ABSENDER));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(empfaenger));
        message.setSubject(betreff);
        message.setText(text);

        Transport.send(message);
        System.out.println("E-Mail gesendet!");
    }
}