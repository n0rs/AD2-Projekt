package webshop.businessLayer.Objekte;

public class Kunde {
    private int id; // 
    private String vorname;
    private String nachname;
    private String email;
    private String password;

    // Konstruktor mit ID (beim Laden aus der DB)
    public Kunde(int id, String vorname, String nachname, String email, String password) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.password = password;
    }

    // Konstruktor ohne ID (beim Anlegen eines neuen Kunden)
    public Kunde(String vorname, String nachname, String email, String password) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.password = password;
    }

    // Getter und Setter 
    public int getId() { 
        return id; 
    }
    public void setId(int id) {
        this.id = id; 
    }

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
