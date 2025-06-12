package webshop.businessLayer.service;

import webshop.businessLayer.Objekte.Kunde;
import webshop.dataAccessLayer.DatenbankManager;
import jakarta.mail.MessagingException;

public class Kundenregistrierung {

    public static void registriereKunde(Kunde kunde) {
        // Datenbankverbindung aufbauen
        DatenbankManager.verbindungAufbauen();

        //Kunde speichern
        DatenbankManager.kundeAnlegen(kunde.getEmail(), kunde.getPassword());

        int user_id = DatenbankManager.findeKundenId(kunde.getEmail());

        String token = TokenErstellung.erstelleToken();  // Token erstellen
        DatenbankManager.emailVerificationEintragErstellen(user_id, token); // Token in DB speichern


        String link = "http://localhost:8080/verify?token=" + token;

        String betreff = "Bitte bestätige deine E-Mail";
        String inhalt = """
            <html>
              <body>
                <h2>Hi!</h2>
                <p>Bitte bestätige deine Registrierung, indem du auf den folgenden Link klickst:</p>
                <p><a href="%s">E-Mail bestätigen</a></p>
                <p>Falls du dich nicht registriert hast, kannst du diese E-Mail ignorieren.</p>
              </body>
            </html>
            """.formatted(link);

        try {
            EmailVersand.sendeEmail(kunde.getEmail(), betreff, inhalt);
        } catch (MessagingException e) {
            System.err.println("Fehler beim Senden der Registrierungsmail: " + e.getMessage());
        }

        // Verbindung trennen
        DatenbankManager.verbindungTrennen();

    }
}
