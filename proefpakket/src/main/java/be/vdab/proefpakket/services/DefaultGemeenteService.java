package be.vdab.proefpakket.services;

import be.vdab.proefpakket.domain.Gemeente;
import be.vdab.proefpakket.repositories.GemeenteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultGemeenteService implements GemeenteService {
    private final GemeenteRepository gemeenteRepository;

    DefaultGemeenteService(GemeenteRepository gemeenteRepository) {
        this.gemeenteRepository = gemeenteRepository;
    }

    @Override
    public List<Gemeente> findAll() {
        return gemeenteRepository.findAll(Sort.by("naam", "postcode"));
    }
}