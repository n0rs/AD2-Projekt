// javac -cp lib/postgresql-42.7.5.jar -d ./bin ./webshop/Main.java ./webshop/businessLayer/Objekte/*.java ./webshop/businessLayer/service/*.java ./webshop/businessLayer/validation/*.java ./webshop/dataAccessLayer/*.java ./webshop/presentationLayer/*.java kompiliert Programm
// java -cp "./bin;lib/postgresql-42.7.5.jar" webshop.Main füht Main.java aus

package webshop;

// eigene Imports
import webshop.businessLayer.service.*;

import java.util.Scanner;

import webshop.businessLayer.Objekte.*;
import webshop.businessLayer.validation.*;
import webshop.dataAccessLayer.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String email;
        String passwort;
        // Verbindung zur Datenbank herstellen und eine Abfrage durchführen
        DatenbankManager.verbindungAufbauen();
        AutomatischeTokenVerwaltung.automatischerExecutorEmail();
        AutomatischeTokenVerwaltung.automatischerExecutorPasswort();

        email = EmailPruefer.starteEmailPruefung(scanner);
        passwort = PasswortPruefer.startePasswortPruefung(scanner);

        Kunde kunde = new Kunde("maxi", "muster", email, passwort);
        DatenbankManager.kundeAnlegen(kunde.getVorName(), kunde.getNachName(), kunde.getEmail(), kunde.getPassword());
        kunde = DatenbankManager.findeKundeNachEmail(kunde.getEmail());
        System.out.println(kunde.getId());
        DatenbankManager.emailVerificationEintragErstellen(kunde.getId(), TokenErstellung.erstelleToken());
        DatenbankManager.passwortResetEintragErstellen(kunde.getId(), TokenErstellung.erstelleToken());

        System.out.println(DatenbankManager.findeEmailTokenMitEmail(email)); 
        System.out.println(DatenbankManager.findePasswortTokenMitEmail(email));


        // Zum Testen Läuft das Programm 10 Minuten
        try {
            Thread.sleep(600_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DatenbankManager.verbindungTrennen();
        scanner.close();
    }
}