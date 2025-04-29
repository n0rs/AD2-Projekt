// Implementiert Pruefer
package webshop.pruefer;

public class EmailPruefer implements Pruefer {
    public boolean pruefe(String email) {
        // Überprüfen, ob die E-Mail-Adresse ein @-Zeichen enthält
        if (!email.contains("@")) {
            return false;
        }
        // Überprüfen, ob die E-Mail-Adresse einen Punkt nach dem @-Zeichen enthält
        String domain = email.substring(email.indexOf("@") + 1);
        if (!domain.contains(".")) {
            return false;
        }
        // Überprüfen, ob die E-Mail-Adresse eine gültige Länge hat
        if (email.length() < 5 || email.length() > 50) {
            return false;
        }
        return true;
    }
}
