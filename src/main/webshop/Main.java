package webshop;

import webshop.pruefer.EmailPruefer;
import webshop.pruefer.PasswortPruefer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean istGueltig = false;

        while(!istGueltig) {
            System.out.print("Wie lautet Ihre E-Mail-Adresse: ");
            String email = scanner.nextLine();

            EmailPruefer emailPruefer = new EmailPruefer();
            if (emailPruefer.pruefe(email)) {
                System.out.println("Gültige E-Mail-Adresse: " + email);
                istGueltig = true;
            } else {
                System.out.println("Ungültige E-Mail-Adresse. Bitte versuchen Sie es erneut.");
            }
        }

        System.out.print("Wie lautet Ihr Passwort: ");
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