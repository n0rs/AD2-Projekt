package webshop.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import webshop.db.DatenbankManager;

public class AutomatischerExecutor {
    public static void automatischerExecutor() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            DatenbankManager.abgelaufeneEmailVerificationsUndNutzerLoeschen();
        }, 0, 10, TimeUnit.MINUTES);
    }
}
