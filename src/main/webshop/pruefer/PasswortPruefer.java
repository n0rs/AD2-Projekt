// Implementiert Pruefer

package webshop.pruefer;

public class PasswortPruefer implements Pruefer {
    
    public boolean pruefe(String password) {
        if (password.length() < 8) return false;
        boolean hasLetter = password.matches(".*[a-zA-Z]+.*");
        boolean hasDigit = password.matches(".*\\d+.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>]+.*");
        return hasLetter && hasDigit && hasSpecial;
    }
}