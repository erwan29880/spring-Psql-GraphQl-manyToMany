package fr.erwan.psql.multiRel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long population;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "Planets_Terrains",
             joinColumns = @JoinColumn(name = "planet_id"),
             inverseJoinColumns = @JoinColumn(name = "terrain_id"))
    private List<Terrains> terrains;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "Planets_Climates",
             joinColumns = @JoinColumn(name = "planet_id"),
             inverseJoinColumns = @JoinColumn(name = "climate_id"))
    private List<Climates> climates;

    public Planet() {
    }

    public void addTerrain(Terrains t) {
        terrains.add(t);
    }

    public void addClimate(Climates t) {
        climates.add(t);
    }

    public Planet(Long id, String name, long population, List<Terrains> terrains, List<Climates> climates) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.terrains = terrains;
        this.climates = climates;
    }

    public Long getId() {
        return this.id;
    }

    public void ListId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return this.population;
    }

    public void SetPopulation(long population) {
        this.population = population;
    }

    public List<Terrains> getTerrains() {
        return this.terrains;
    }

    public void SetTerrains(List<Terrains> terrains) {
        this.terrains = terrains;
    }

    public List<Climates> getClimates() {
        return this.climates;
    }

    public void SetClimates(List<Climates> climates) {
        this.climates = climates;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append(" - ");
        sb.append(this.name);
        sb.append(" - ");
        sb.append(this.population);
        sb.append(" - ");
        for (Climates c: climates ) {
            sb.append(c.getClimate());
            sb.append(", ");
        }
        for (Terrains t: terrains) {
            sb.append(t.getTerrains());
            sb.append(", ");
        }
        return sb.toString();
    }
}
