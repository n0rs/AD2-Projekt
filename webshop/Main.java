// javac -cp "lib/*" -d ./bin ./webshop/Main.java ./webshop/businessLayer/Objekte/*.java ./webshop/businessLayer/service/*.java ./webshop/businessLayer/validation/*.java ./webshop/dataAccessLayer/*.java ./webshop/presentationLayer/*.java kompiliert Programm
// java -cp ".\bin;lib\*" webshop.Main führt Main.java aus

package webshop;



// eigene Imports

import webshop.businessLayer.Objekte.Kunde;
import webshop.businessLayer.service.EmailVersand;
import webshop.businessLayer.service.Kundenregistrierung;


public class Main {
    public static void main(String[] args) {

     /*try (Scanner scanner = new Scanner(System.in)) {
        String email;
        String passwort;
        // Verbindung zur Datenbank herstellen und eine Abfrage durchführen
        DatenbankManager.verbindungAufbauen();
        AutomatischeTokenVerwaltung.automatischerExecutorEmail();
        AutomatischeTokenVerwaltung.automatischerExecutorPasswort();

        email = EmailPruefer.starteEmailPruefung(scanner);
        passwort = PasswortPruefer.startePasswortPruefung(scanner);

       Kunde kunde = new Kunde(email, passwort);
        Kunde kunde2 = new Kunde("asas@123.de", "asghasgh123!*");
        DatenbankManager.kundeAnlegen(kunde.getEmail(), kunde.getPassword());
        DatenbankManager.kundeAnlegen(kunde2.getEmail(), kunde2.getPassword());
        kunde = DatenbankManager.findeKundeNachEmail(kunde.getEmail());
        kunde2 = DatenbankManager.findeKundeNachEmail(kunde2.getEmail());
        System.out.println(kunde.getId());
        System.out.println(kunde2.getId());
        DatenbankManager.emailVerificationEintragErstellen(kunde.getId(), TokenErstellung.erstelleToken());
        DatenbankManager.passwortResetEintragErstellen(kunde.getId(), TokenErstellung.erstelleToken());
        DatenbankManager.emailVerificationEintragErstellen(kunde2.getId(), TokenErstellung.erstelleToken());
        DatenbankManager.passwortResetEintragErstellen(kunde2.getId(), TokenErstellung.erstelleToken());

        System.out.println(DatenbankManager.findeEmailTokenMitEmail(email)); 
        System.out.println(DatenbankManager.findePasswortTokenMitEmail(email));

        System.out.println(DatenbankManager.findeEmailTokenMitEmail("asas@123.de")); 
        System.out.println(DatenbankManager.findePasswortTokenMitEmail("asas@123.de"));


        // Zum Testen Läuft das Programm 10 Minuten
        try {
            Thread.sleep(600_000);            
        } catch (InterruptedException e) {
            System.out.println("Sleep fehlgeschlagen");
        }
        DatenbankManager.verbindungTrennen();
        System.exit(0);
    }*/

        Kundenregistrierung.registriereKunde(new Kunde("lisa-pham@gmx.de", "1234567890!A"));

        try {
            EmailVersand.sendeEmail("lisa-pham@gmx.de", "Test", "TESTTESTTEST");
        } catch (jakarta.mail.MessagingException e) {
            System.out.println("Fehler beim Senden der E-Mail: " + e.getMessage());
            e.printStackTrace();
        }
    }

    }
