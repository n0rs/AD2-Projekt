package webshop.businessLayer.service;

import jakarta.mail.MessagingException;
import webshop.dataAccessLayer.DatenbankManager;


public class PasswortZuruecksetzen {

    public void sendeZuruecksetzenEmail(String email) {

        DatenbankManager.verbindungAufbauen();

        int userId = DatenbankManager.findeKundenId(email);
        if (userId == -1) {
            System.out.println("E-Mail nicht gefunden.");
            return;
        }

        String token = TokenErstellung.erstelleToken();
        DatenbankManager.passwortResetEintragErstellen(userId, token);

        String resetLink = "http://localhost:8080/passwort-zuruecksetzen?token=" + token;

        String betreff = "Passwort zurücksetzen";
        String inhalt = """
            <html>
              <body>
                <h2>Passwort zurücksetzen</h2>
                <p>Klicke auf den folgenden Link, um dein Passwort neu zu setzen:</p>
                <p><a href="%s">Passwort zurücksetzen</a></p>
                <p>Wenn du das nicht angefordert hast, ignoriere diese Mail.</p>
              </body>
            </html>
            """.formatted(resetLink);

        try {
            EmailVersand.sendeEmail(email, betreff, inhalt);
        } catch (MessagingException e) {
            System.err.println("Passwort-Zurücksetzen-E-Mail konnte nicht gesendet werden: " + e.getMessage());
        }

        DatenbankManager.verbindungTrennen();

    }
}