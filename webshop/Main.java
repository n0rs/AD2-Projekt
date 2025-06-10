// javac -cp lib/postgresql-42.7.5.jar -d ./bin ./webshop/Main.java ./webshop/businessLayer/Objekte/*.java ./webshop/businessLayer/service/*.java ./webshop/businessLayer/validation/*.java ./webshop/dataAccessLayer/*.java ./webshop/presentationLayer/*.java kompiliert Programm
// java -cp "./bin;lib/postgresql-42.7.5.jar" webshop.Main füht Main.java aus

package webshop;

// eigene Imports
import java.util.Scanner;
import webshop.businessLayer.Objekte.*;
import webshop.businessLayer.service.*;
import webshop.businessLayer.validation.*;
import webshop.dataAccessLayer.*;


public class Main {
    public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
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
    }
    }
}