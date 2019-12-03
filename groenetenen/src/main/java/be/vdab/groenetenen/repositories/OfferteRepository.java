package be.vdab.groenetenen.repositories;

import be.vdab.groenetenen.entities.Offerte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferteRepository extends JpaRepository<Offerte, Long> {
}