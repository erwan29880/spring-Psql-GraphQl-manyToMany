package fr.erwan.psql.repos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.erwan.psql.multiRel.Planet;

@Repository
public interface PlanetRepo extends JpaRepository<Planet, Long>{

    @Query(value="select p.id, p.name, p.population, t.terrains, c.climate from planet p join planets_terrains pt on p.id=pt.planet_id join terrains t on pt.terrain_id = t.id join planets_climates pc on p.id=pc.planet_id join climates c on c.id=pc.climate_id;", nativeQuery=true)
    List<Planet> findAllRel();
}
