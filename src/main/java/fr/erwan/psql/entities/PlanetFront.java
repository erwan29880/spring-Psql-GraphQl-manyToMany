package fr.erwan.psql.entities;
import java.util.Set;


/**
 * classe pour le parsage json d'une planète provenant du front end
 * cette objet est transformé en objet Planet par la méthode toPlanet()
 */
public class PlanetFront {
    private String name;
    private long population;
    private Set<String> terrains;
    private Set<String> climates;

    public PlanetFront() {
    }

    public PlanetFront(String name, long population, Set<String> terrains, Set<String> climates) {
        this.name = name;
        this.population = population;
        this.terrains = terrains;
        this.climates = climates;
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

    public Set<String> getTerrains() {
        return this.terrains;
    }

    public void setTerrains(Set<String> terrains) {
        this.terrains = terrains;
    }

    public Set<String> getClimates() {
        return this.climates;
    }

    public void setClimates(Set<String> climates) {
        this.climates = climates;
    }

    public Planet toPlanet() {
        Planet planet = new Planet();
        planet.SetName(name);
        planet.SetPopulation(population);
        
        terrains.forEach(e -> {
            planet.addTerrain(new Terrains(null, e, null));
        });

        climates.forEach(e -> {
            planet.addClimate(new Climates(null, e, null));
        });
        return planet;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name);
        sb.append(" - ");
        sb.append(population);
        sb.append(" - ");
        for (String t: terrains) {
            sb.append(t);
            sb.append(", ");
        }
        for (String t: climates) {
            sb.append(t);
            sb.append(", ");
        }
        return sb.toString();
    }
}
