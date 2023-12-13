package fr.erwan.psql.repos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.erwan.psql.multiRel.Planet;

@Repository
public interface PlanetRepo extends CrudRepository<Planet, Long>{
}
