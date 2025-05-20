package webshop.kunden;

public class Kunde {
    private String name;
    private String email;
    private String password;

    // Konstruktor
    public Kunde(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter und Setter
    public String getName() {
         return name; 
        }
    public void setName(String name) {
         this.name = name; 
        }

    public String getEmail() {
         return email; 
        }
    public void setEmail(String email) {
         this.email = email; 
        }

    public String getPassword() {
         return password; 
        }
    public void setPassword(String adresse) {
         this.password = adresse; 
        }
}
