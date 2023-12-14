package fr.erwan.psql.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/**
 * Table climates 
 * utilisée en manyToMany par Planet
 */
@Entity 
public class Climates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String climate;
    
    @ManyToMany
    private Set<Planet> planets = new HashSet<>();

    public Climates() {
    }

    public Climates(Long id, String climate, Set<Planet> planets) {
        this.id = id;
        this.climate = climate;
        this.planets = planets;
    }


    public Long getId() {
        return this.id;
    }

    public void SetId(Long id) {
        this.id = id;
    }

    public String getClimate() {
        return this.climate;
    }

    public void SetClimate(String climate) {
        this.climate = climate;
    }

    public Set<Planet> getPlanets() {
        return this.planets;
    }

    public void SetPlanets(Set<Planet> planets) {
        this.planets = planets;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", climate='" + getClimate() + "'" +
            "}";
    }
}
