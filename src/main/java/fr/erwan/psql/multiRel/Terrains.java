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
public class Terrains {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String terrains;

    // @ManyToMany(fetch = FetchType.LAZY,
    //   cascade = {
    //       CascadeType.PERSIST,
    //       CascadeType.MERGE
    //   }, mappedBy = "terrains")
    @ManyToMany
    private List<Planet> planets;


    public Terrains(Long id, String terrains, List<Planet> planets) {
        this.id = id;
        this.terrains = terrains;
        this.planets = planets;
    }

    public Terrains() {
    }


    public Long getId() {
        return this.id;
    }

    public void SetId(Long id) {
        this.id = id;
    }

    public String getTerrains() {
        return this.terrains;
    }

    public void SetTerrains(String terrains) {
        this.terrains = terrains;
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
            ", terrains='" + getTerrains() + "'" +
            "}";
    }
}

