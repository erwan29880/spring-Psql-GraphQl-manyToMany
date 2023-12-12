package fr.erwan.psql.gql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Planets2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long population;
    private List<String> terrains;
    private List<String> climates;

    public Planets2() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return this.population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public List<String> getTerrains() {
        return this.terrains;
    }

    public void setTerrains(List<String> terrains) {
        this.terrains = terrains;
    }

    public List<String> getClimates() {
        return this.climates;
    }

    public void setClimates(List<String> climates) {
        this.climates = climates;
    }
}
