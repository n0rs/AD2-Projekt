package webshop.businessLayer.Objekte;

public class Kunde {
    private String vorname;
    private String nachname;
    private String email;
    private String password;

    // Konstruktor
    public Kunde(String vorname, String nachname, String email, String password) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.password = password;
    }

    // Getter und Setter
    public String getVorName() {
         return vorname; 
        }
    public void setVorName(String vorname) {
         this.vorname = vorname; 
        }

    public String getNachName() {
         return nachname; 
        }

    public void setNachName(String nachname) {
         this.nachname = nachname; 
        }
    public String getName() {
         return vorname + " " + nachname; 
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
