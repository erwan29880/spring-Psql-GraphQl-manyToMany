package fr.erwan.psql.stuff;

import org.springframework.data.repository.CrudRepository;

public interface Repo extends CrudRepository<Planets, Long> {
}
