/* 
Erstellt bei pgadmin4 eine neue Datenbank mit dem Namen AD2-Projekt
(Rechtsklick auf den Server -> Create -> Database...)
unter Rechtsklick Properties auf den Server seht sollte wie im Bild die Infos stehen
bzw. der Username und das Passwort legt ihr in der config.properties an
führt die folgenden SQL-Befehle aus, um die Tabellen zu erstellen
CREATE TABLE nutzer (
    id SERIAL PRIMARY KEY,
    vorname VARCHAR(50) NOT NULL,
    nachname VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);
 CREATE TABLE email_verification (
    user_id INT REFERENCES nutzer(id) ON DELETE CASCADE,
    token VARCHAR(255) NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id)
);
CREATE TABLE password_reset (
    user_id INT REFERENCES nutzer(id) ON DELETE CASCADE,
    token VARCHAR(255) NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id)
);
config.properties
db.url=jdbc:postgresql://localhost/AD2-Projekt
db.user=hier kommt euer Username rein (z.B. postgres)
db.password=hier kommt euer Passwort rein
(alles ohne "" und leerzeichen)
*/


package webshop.dataAccessLayer;

// Java-Importe
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class DatenbankManager {

    private static String URL;
    private static String BENUTZERNAME;
    private static String PASSWORT;

    // Connection ist eine Klasse, die eine Verbindung zu einer Datenbank darstellt
    private static Connection connection;

    // Liest Datenbankkonfiguration aus Datei config.properties
    // Dadurch müssen wir passwort und Benutzername nicht im Code speichern
    // Die Datei config.properties gebe ich euch so, da es unsinnig wäre, sie auf GitHub zu speichern :)
    static {
        try (FileInputStream file = new FileInputStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(file);

            URL = properties.getProperty("db.url");
            BENUTZERNAME = properties.getProperty("db.user");
            PASSWORT = properties.getProperty("db.password");
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Konfigurationsdatei: " + e.getMessage());
        }
    }

    // Verbindung aufbauen
    public static void verbindungAufbauen() {
        try {
            // DriverManager ist eine Klasse, die für die Verwaltung von JDBC-Treibern verantwortlich ist
            // JDBC ist eine API, die es Java-Anwendungen ermöglicht, auf verschiedene Datenbanken zuzugreifen
            // In unserem Fall ist der Treiber in der lib/postgresql-42.7.5.jar Datei enthalten
            connection = DriverManager.getConnection(URL, BENUTZERNAME, PASSWORT);
            System.out.println("Verbindung erfolgreich!");
        } catch (SQLException e) {
            System.err.println("Fehler bei der Verbindung zur Datenbank: " + e.getMessage());
        }
    }

    // Verbindung trennen
    public static void verbindungTrennen() {
        if (connection != null) {
            try {
                // close() ist eine Methode der Connection Klasse
                connection.close();
                System.out.println("Verbindung getrennt!");
            } catch (SQLException e) {
                System.err.println("Fehler beim Schließen der Verbindung: " + e.getMessage());
            }
        } else {
            System.out.println("Keine Verbindung zum Trennen vorhanden.");
        }
    }


    // Kunde anlegen
    public static void kundeAnlegen(String vorname, String nachname, String email, String password) {
        // ? ist ein Platzhalter für einen Parameter in der SQL-Abfrage
        // PreparedStatement ist eine Schnittstelle, die SQL-Abfragen mit Platzhaltern unterstützt
        String query = "INSERT INTO nutzer (vorname, nachname, email, password_hash) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vorname);
            preparedStatement.setString(2, nachname);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            System.out.println("Kunde " + vorname + " " + nachname + " wurde angelegt.");
        } catch (SQLException e) {
            System.err.println("Fehler beim Anlegen des Kunden: " + e.getMessage());
        }
    }
    // Kunde anhand id löschen
    public static void kundeLoeschenId(int userId) {
        String query = "DELETE FROM nutzer WHERE id = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(query)) {
            deleteStmt.setInt(1, userId);
            deleteStmt.executeUpdate();
            System.out.println("Nutzer mit ID " + userId + " wurde gelöscht (Verifizierung abgelaufen).");
        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen des Kunden: " + e.getMessage());
        }
    }

    
    // E-Mail-Verifizierungseintrag erstellen
    public static void emailVerificationEintragErstellen(String email, String token) {
        String selectQuery = "SELECT id FROM nutzer WHERE email = ?";
        String insertQuery = "INSERT INTO email_verification (user_id, token, expires_at) VALUES (?, ?, ?)";
        try (PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
            selectStmt.setString(1, email);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("id");
                    try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                        insertStmt.setInt(1, userId);
                        insertStmt.setString(2, token);
                        insertStmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis() + 3600000)); // 3600000 1 Stunde gültig
                        insertStmt.executeUpdate();
                        System.out.println("E-Mail-Verifizierungseintrag für User-ID " + userId + " wurde erstellt.");
                    }
                } else {
                    System.err.println("Kein Nutzer mit der E-Mail " + email + " gefunden.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Erstellen des E-Mail-Verifizierungseintrags: " + e.getMessage());
        }
    }

    
    // Löscht alle Nutzer, deren E-Mail-Verifizierung abgelaufen ist.
    public static void abgelaufeneEmailVerificationsUndNutzerLoeschen() {
        String query = "SELECT user_id FROM email_verification WHERE expires_at < NOW()";
        try (PreparedStatement selectStmt = connection.prepareStatement(query);
             ResultSet rs = selectStmt.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                // Nutzer löschen (durch ON DELETE CASCADE werden auch Verifizierungen gelöscht)
                
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen abgelaufener Nutzer: " + e.getMessage());
        }
    }

    // Passwort-Reset-Eintrag erstellen
    public static void passwordResetEintragErstellen(String user_id, String token) {
        String query = "INSERT INTO password_reset (user_id, token, expires_at) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, token);
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis() + 3600000)); // 1 Stunde gültig
            preparedStatement.executeUpdate();
            System.out.println("Passwort-Reset-Eintrag für " + user_id + " wurde erstellt.");
        } catch (SQLException e) {
            System.err.println("Fehler beim Erstellen des Passwort-Reset-Eintrags: " + e.getMessage());
        }
    }

    // Passwort-Reset-Eintrag löschen... Sollte automatisiert werden, wenn der Nutzer sein Passwort zurücksetzt oder wenn der Token abgelaufen ist
    public static void passwordResetEintragLoeschen(String user_id) {
        String query = "DELETE FROM password_reset WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user_id);
            preparedStatement.executeUpdate();
            System.out.println("Passwort-Reset-Eintrag für " + user_id + " wurde gelöscht.");
        } catch (SQLException e) {
            System.err.println("Fehler beim Löschen des Passwort-Reset-Eintrags: " + e.getMessage());
        }
    }

    
}
