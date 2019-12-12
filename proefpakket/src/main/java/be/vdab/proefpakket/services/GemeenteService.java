package be.vdab.proefpakket.services;

import be.vdab.proefpakket.domain.Gemeente;

import java.util.List;

public interface GemeenteService {
    List<Gemeente> findAll();
}