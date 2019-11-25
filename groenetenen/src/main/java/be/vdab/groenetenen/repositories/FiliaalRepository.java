package be.vdab.groenetenen.repositories;

import be.vdab.groenetenen.domain.Filiaal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FiliaalRepository extends JpaRepository<Filiaal, Long> {
    List<Filiaal> findByAdresPostcodeBetweenOrderByAdresPostcode(
            int van, int tot);
}