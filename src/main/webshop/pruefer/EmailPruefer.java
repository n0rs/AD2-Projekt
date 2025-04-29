// Implementiert Pruefer
package webshop.pruefer;

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
}
