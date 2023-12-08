package fr.erwan.psql.stuff;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Planets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String population;
    private String terrains;
    private String climates;

    public Planets() {}

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

    public String getPopulation() {
        return this.population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getTerrains() {
        return this.terrains;
    }

    public void setTerrains(String terrains) {
        this.terrains = terrains;
    }

    public String getClimates() {
        return this.climates;
    }

    public void setClimates(String climates) {
        this.climates = climates;
    }
}
