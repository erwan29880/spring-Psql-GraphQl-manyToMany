package fr.erwan.psql.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.erwan.psql.multiRel.Terrains;

@Repository
public interface TerrainRepo extends CrudRepository<Terrains, Long>{
    Optional<Terrains> findByTerrains(String t);
}
