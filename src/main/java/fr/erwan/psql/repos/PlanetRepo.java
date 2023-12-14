package fr.erwan.psql.repos;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.erwan.psql.entities.Planet;

@Repository
public interface PlanetRepo extends JpaRepository<Planet, Long>{
    Optional<Planet> findByName(String name);
}
