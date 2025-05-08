// javac -cp lib/postgresql-42.7.5.jar -d ./bin ./src/main/webshop/Main.java ./src/main/webshop/pruefer/*.java ./src/main/webshop/db/*.java kompiliert Programm
// java -cp ./bin;lib/postgresql-42.7.5.jar webshop.Main füht Main.java aus

package webshop;

import webshop.pruefer.EmailPruefer;
import webshop.pruefer.PasswortPruefer;
import webshop.db.DatenbankManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DatenbankManager.verbinden();
        DatenbankManager.sqlAbfrage("SELECT * FROM nutzer;");
        DatenbankManager.verbindungTrennen();

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print("Wie lautet Ihre E-Mail-Adresse: ");
            String email = scanner.nextLine();

            EmailPruefer emailPruefer = new EmailPruefer();
            if (emailPruefer.pruefe(email)) {
                System.out.println("Gültige E-Mail-Adresse: " + email);
                break;
            } else {
                System.out.println("Ungültige E-Mail-Adresse. Bitte versuchen Sie es erneut.");
            }
        }
        while(true) {
            System.out.print("Wie lautet Ihr Passwort: ");
            String pw = scanner.nextLine();

            PasswortPruefer pruefer = new PasswortPruefer();
            if (pruefer.pruefe(pw)) {
                System.out.println("Starkes Passwort");
                break;
            } else {
                System.out.println("Schlechtes Passwort. Bitte versuchen Sie es erneut.");
            }
        }
        scanner.close();
    }
}