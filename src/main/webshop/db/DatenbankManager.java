package webshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
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
    static {
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(fis);

            URL = properties.getProperty("db.url");
            BENUTZERNAME = properties.getProperty("db.user");
            PASSWORT = properties.getProperty("db.password");
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Konfigurationsdatei: " + e.getMessage());
        }
    }

    // Verbindung aufbauen
    public static void verbinden() {
        try {
            // Driver Manager ist eine Klasse, die für die Verwaltung von JDBC-Treibern verantwortlich ist
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

    // SQL-Abfrage ausführen
    public static void sqlAbfrage(String query) {
        // createStatement() ist eine Methode der Connection Klasse, die SQL-Abfragen an die Datenbank sendet
        // Statement ist eine Schnittstelle, die SQL-Abfragen an die Datenbank sendet und Ergebnisse zurückgibt
        // ResultSet gibt Ergebnisse einer SQL-Abfrage als Set aus
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Loop durch alle Zeilen des ResultSets .next() gibt true zurück, wenn es eine weitere Zeile gibt
            // getMetaData() gibt Metadaten über die Spalten des ResultSets zurück (z.B. Spaltennamen, Datentypen)
            // getColumnCount() gibt die Anzahl der Spalten im ResultSet zurück
            while (resultSet.next()) {
                int spaltenAnzahl = resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= spaltenAnzahl; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.err.println("Fehler bei der SQL-Abfrage: " + e.getMessage());
        }
    }
}
