package fr.erwan.psql.multiRel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity 
public class Climates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String climate;
    
    // @ManyToMany(fetch = FetchType.LAZY,
    //   cascade = {
    //       CascadeType.PERSIST,
    //       CascadeType.MERGE
    //   } ,mappedBy = "climates")
    @ManyToMany
    private List<Planet> planets;

    public Climates() {
    }

    public Climates(Long id, String climate, List<Planet> planets) {
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

    public List<Planet> getPlanets() {
        return this.planets;
    }

    public void SetPlanets(List<Planet> planets) {
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
