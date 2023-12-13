package fr.erwan.psql.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.erwan.psql.multiRel.Terrains;

@Repository
public interface TerrainRepo extends CrudRepository<Terrains, Long>{
}
