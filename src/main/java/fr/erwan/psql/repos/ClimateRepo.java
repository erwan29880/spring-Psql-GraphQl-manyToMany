package fr.erwan.psql.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.erwan.psql.multiRel.Climates;

@Repository
public interface ClimateRepo extends CrudRepository<Climates, Long> {
    Optional<Climates> findByClimate(String t);

}
