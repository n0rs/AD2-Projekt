// javac -cp lib/postgresql-42.7.5.jar -d ./bin ./src/main/webshop/Main.java ./src/main/webshop/pruefer/*.java ./src/main/webshop/db/*.java kompiliert Programm
// java -cp ./bin;lib/postgresql-42.7.5.jar webshop.Main füht Main.java aus

package webshop;

// import der von uns erstellten Klassen
import webshop.pruefer.EmailPruefer;
import webshop.pruefer.PasswortPruefer;
import webshop.db.DatenbankManager;


public class Main {
    public static void main(String[] args) {
        // Verbindung zur Datenbank herstellen und eine Abfrage durchführen
        DatenbankManager.verbinden();
        DatenbankManager.sqlAbfrage("SELECT * FROM nutzer;");
        DatenbankManager.verbindungTrennen();

        EmailPruefer.starteEmailPruefung();
        PasswortPruefer.startePasswortPruefung();
    }
}