package webshop;

import webshop.pruefer.Pruefer;
import webshop.pruefer.EmailPruefer;
import webshop.pruefer.PasswortPruefer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wie lautet deine E-Mail-Adresse: ");
        String email = scanner.nextLine();
        // E-Mail-Prüfung

        EmailPruefer emailPruefer = new EmailPruefer();
        if (emailPruefer.pruefe(email)) {
            System.out.println("Gültige E-Mail-Adresse");
        } else {
            System.out.println("Ungültige E-Mail-Adresse");
        }

        System.out.print("Wie lautet dein Passwort: ");
        String pw = scanner.nextLine();

        PasswortPruefer pruefer = new PasswortPruefer(); // Instanz erstellen
        if (pruefer.pruefe(pw)) {
            System.out.println("Gutes Passwort");
        } else {
            System.out.println("Schlechtes Passwort");
        }

        scanner.close();
    }
}