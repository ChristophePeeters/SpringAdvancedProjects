package be.vdab.groenetenen.services;

import be.vdab.groenetenen.domain.Filiaal;

import java.util.List;

public interface FiliaalService {
    List<Filiaal> findByPostcode(int van, int tot);
    void delete(Filiaal filiaal);
}