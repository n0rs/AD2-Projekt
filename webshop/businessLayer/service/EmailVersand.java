package webshop.businessLayer.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EmailVersand {
    // Absender-Adresse und Passwort werden beim Laden der Klasse initialisiert
    private static String ABSENDER;
    private static String PASSWORT;

    static {
        try (FileInputStream file = new FileInputStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(file);

            ABSENDER = properties.getProperty("email.absender");
            PASSWORT = properties.getProperty("email.passwort");
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Konfigurationsdatei: " + e.getMessage());
            }
        }

    public static void sendeEmail(String empfaenger, String betreff, String inhalt) throws MessagingException {

        // SMTP-Konfiguration (hier für Gmail ausgelegt
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");    // Authentifizierung aktivieren
        props.put("mail.smtp.starttls.enable", "true"); // TLS-Verschlüsselung aktivieren
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP-Server von Gmail
        props.put("mail.smtp.port", "587"); // TLS-Port

        // Session mit Authentifizierung erzeugen
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ABSENDER, PASSWORT);
            }
        });
        // E-Mail-Nachricht erstellen
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(ABSENDER));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(empfaenger));
        message.setSubject(betreff);
        message.setContent(inhalt, "text/html; charset=utf-8");

        // Nachricht senden
        System.out.println("E-Mail wird versendet...");
        Transport.send(message);
        System.out.println("E-Mail gesendet an " + empfaenger + "!");
    }
}