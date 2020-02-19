package be.vdab.groenetenen.services;

import be.vdab.groenetenen.entities.Offerte;

public interface OfferteService {
    void create(Offerte offerte, String offertesURL);
}