package fr.erwan.psql.multiRel;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import fr.erwan.psql.entities.Climates;
import fr.erwan.psql.entities.Planet;
import fr.erwan.psql.entities.PlanetFront;
import fr.erwan.psql.entities.Terrains;
import fr.erwan.psql.repos.ClimateRepo;
import fr.erwan.psql.repos.PlanetRepo;
import fr.erwan.psql.repos.TerrainRepo;


/**
 * Classe de service qui implémente les repos
 * Crud sur Planet et ses tables associées (n n) climates, terrains, tables d'associations
 */
@Service
public class multiRelService {
    
    @Autowired
    private PlanetRepo repo;

    @Autowired
    private ClimateRepo climateRepo;

    @Autowired
    private TerrainRepo terrainRepo;

    // load graphQl data from APi
    private DataLoaderService dataLoader;

    public multiRelService() {
        this.dataLoader  = new DataLoaderService();
    }

    public List<Planet> getAll() {
        List<Planet> planets = new ArrayList<>();
        repo.findAll().forEach(planets::add);
        return planets;
    }

    
    public List<Planet> savePlanets() {
        List<JsonNode> jsns = new ArrayList<>();
        List<Planet> planets = new ArrayList<>();
        
        try {
            jsns = dataLoader.loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (JsonNode jsn: jsns) {

            Planet p = new Planet();
            Set<Terrains> terrains = new HashSet<>();
            Set<Climates> climates = new HashSet<>();

            p.SetName(jsn.get("name").asText());
            String population = jsn.get("population").asText();
            long populatio = 0;
            if (population != "null") {
                populatio = Long.parseLong(population);
            }
            p.SetPopulation(populatio);

            ArrayNode terrs = (ArrayNode) jsn.get("terrains");
            for (JsonNode j: terrs) {
                Terrains t = terrainRepo.findByTerrains(j.asText()).orElseGet(() -> {
                    Terrains d = new Terrains();
                    d.SetTerrains(j.asText());
                    return d;
                });
                
                terrains.add(t);
            }

            ArrayNode clims = (ArrayNode) jsn.get("climates");
            for (JsonNode j: clims) {
                Climates t = climateRepo.findByClimate(j.asText()).orElseGet(() -> {
                    Climates d = new Climates();
                    d.SetClimate(j.asText());
                    return d;
                });
                climates.add(t);
            }

            p.SetClimates(climates);
            p.SetTerrains(terrains);
            System.out.println(p);
            planets.add(repo.save(p));
        }
        return planets;
    }

    public Planet savePlanet(Planet planet) {
        return repo.save(planet);
    }

    public Planet delete(long id) {
        repo.deleteById(id);
        return repo.findById(id).orElse(new Planet());
    }

    public Planet getById(long id) {
        return repo.findById(id).orElse(new Planet());
    }

    public Planet update(PlanetFront planetFront) {
        Planet planet = repo.findByName(planetFront.getName()).orElse(new Planet());
        if (planet.getName() == null) {
            return new Planet();
        }

        delete(planet.getId());
        return repo.save(planetFront.toPlanet());
    }
}
