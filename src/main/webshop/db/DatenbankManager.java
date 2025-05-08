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

    private static Connection connection;

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
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Ergebnisse ausgeben
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
