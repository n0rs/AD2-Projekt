// Implementiert Pruefer
package webshop.pruefer;

import java.util.Scanner;

public class EmailPruefer implements Pruefer {
    public boolean pruefe(String email) {
        if (!email.contains("@")) {
            return false;
        }
        String domain = email.substring(email.indexOf("@") + 1);
        if (!domain.contains(".")) {
            return false;
        }
        if (email.length() < 5 || email.length() > 50) {
            return false;
        }
        return true;
    }

    // Führt eine Schleife durch, in der der Benutzer E-Mail-Adressen eingeben kann
    public void starteEmailPruefung() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Wie lautet Ihre E-Mail-Adresse: ");
            String email = scanner.nextLine();

            EmailPruefer EmailPruefer = new EmailPruefer(); // Erstellt ein neues Objekt vom Typ EmailPruefer zur Überprüfung der E-Mail
            if (EmailPruefer.pruefe(email)) { // Ruft die Methode "pruefe" auf und prüft die eingegebene E-Mail
                System.out.println("Gültige E-Mail-Adresse: " + email);
                break;
            } else {
                System.out.println("Ungültige E-Mail-Adresse. Bitte versuchen Sie es erneut.");
            }
        }
    }
}
