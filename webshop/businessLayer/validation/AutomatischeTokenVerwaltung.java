package webshop.businessLayer.validation;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import webshop.dataAccessLayer.DatenbankManager;

// Diese Klasse läuft auf einem separaten Thread und führt alle 10 Minuten die Methode aus
public class AutomatischeTokenVerwaltung {
    public static void automatischerExecutorEmail() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            if(DatenbankManager.abgelaufeneEmailTokenfinden() != -1) {
                DatenbankManager.kundeLoeschenId(DatenbankManager.abgelaufeneEmailTokenfinden());
            }
        }, 0, 2, TimeUnit.MINUTES);
    }

    public static void automatischerExecutorPasswort() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            if(DatenbankManager.abgelaufenePasswortTokenfinden() != -1) {
                DatenbankManager.kundeLoeschenId(DatenbankManager.abgelaufenePasswortTokenfinden());
            }
        }, 0, 2, TimeUnit.MINUTES);
    }
}
