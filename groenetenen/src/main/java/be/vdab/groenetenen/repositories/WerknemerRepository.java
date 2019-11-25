package be.vdab.groenetenen.repositories;

import be.vdab.groenetenen.domain.Werknemer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {
    @Override
    @EntityGraph(Werknemer.MET_FILIAAL)
    List<Werknemer> findAll(Sort sort);

    @Override
    @EntityGraph(Werknemer.MET_FILIAAL)
    Page<Werknemer> findAll(Pageable pageable);
}
