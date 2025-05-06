package webshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatenbankManager {

    private static final String URL = "jdbc:mysql://localhost:3306/deineDatenbank"; // Beispiel-URL
    private static final String BENUTZERNAME = "Superuser";
    private static final String PASSWORT = "DBPasswort";

    private static Connection connection;

    // Verbindung aufbauen
    public static void verbinden() {
        try {
            connection = DriverManager.getConnection(URL, BENUTZERNAME, PASSWORT);
            System.out.println("Verbindung erfolgreich!");
        } catch (SQLException e) {
            System.err.println("Fehler beim Aufbau der Verbindung: " + e.getMessage());
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
    public static void sqlAbfrage(String sql) {
        if (connection == null) {
            System.out.println("Keine Verbindung vorhanden. Bitte zuerst verbinden.");
            return;
        }

        try (Statement statement = connection.createStatement()) {
            boolean isResultSet = statement.execute(sql);

            if (isResultSet) {
                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        // Beispiel: Ausgabe der ersten Spalte
                        System.out.println(resultSet.getString(1));
                    }
                }
            } else {
                int updateCount = statement.getUpdateCount();
                System.out.println("Abfrage erfolgreich ausgeführt. Betroffene Zeilen: " + updateCount);
            }
        } catch (SQLException e) {
            System.err.println("Fehler bei der SQL-Abfrage: " + e.getMessage());
        }
    }
}
