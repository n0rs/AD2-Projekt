// Implementiert Pruefer
package webshop.pruefer;

public class PasswortPruefer implements Pruefer {
    
    public boolean pruefe(String password) {
        if (password.length() < 8) return false;
        // Nutzt regular expressions um zu prüfen ob das Passwort mindestens einen Buchstaben, eine Zahl und ein Sonderzeichen enthält
        // .* bedeutet beliebig viele Zeichen, [a-zA-Z] bedeutet ein Buchstabe, \\d bedeutet eine Zahl und [!@#$%^&*(),.?":{}|<>] bedeutet ein Sonderzeichen
        // + bedeutet mindestens einmal
        boolean hasLetter = password.matches(".*[a-zA-Z]+.*");
        boolean hasDigit = password.matches(".*\\d+.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>]+.*");
        return hasLetter && hasDigit && hasSpecial;
    }
}