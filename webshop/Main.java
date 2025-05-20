// javac -cp lib/postgresql-42.7.5.jar -d ./bin ./src/main/webshop/Main.java ./src/main/webshop/pruefer/*.java ./src/main/webshop/db/*.java ./src/main/webshop/service/*.java ./src/main/webshop/kunden/*.java kompiliert Programm
// java -cp ./bin;lib/postgresql-42.7.5.jar webshop.Main füht Main.java aus

package webshop;

import webshop.businessLayer.AutomatischerExecutor;
import webshop.businessLayer.TokenErstellung;
import webshop.businessLayer.Objekte.Kunde;
import webshop.businessLayer.validation.EmailPruefer;
import webshop.businessLayer.validation.PasswortPruefer;
import webshop.dataAccessLayer.DatenbankManager;


public class Main {
    public static void main(String[] args) {

        String email;
        String passwort;
        // Verbindung zur Datenbank herstellen und eine Abfrage durchführen
        DatenbankManager.verbindungAufbauen();
        AutomatischerExecutor.automatischerExecutor();

        email = EmailPruefer.starteEmailPruefung();
        passwort = PasswortPruefer.startePasswortPruefung();

        Kunde kunde = new Kunde("maxi", "muster", email, passwort);

        DatenbankManager.kundeAnlegen(kunde.getVorName(), kunde.getNachName(), kunde.getEmail(), kunde.getPassword());
        DatenbankManager.emailVerificationEintragErstellen(kunde.getEmail(), TokenErstellung.erstelleToken());


        // Zum Testen Läuft das Programm 10 Minuten
        try {
            Thread.sleep(600_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DatenbankManager.verbindungTrennen();
    }
}