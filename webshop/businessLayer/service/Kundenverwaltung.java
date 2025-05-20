package webshop.businessLayer.service;

import webshop.businessLayer.Objekte.Kunde;

public interface Kundenverwaltung {
    void kundenAnlegen(Kunde kunde);
    Kunde kundenSuchen(String email);
    void kundenAktualisieren(Kunde kunde);
    void kundenLoeschen(String email);
}

