package fr.erwan.psql.multiRel;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.tool.schema.spi.ContributableMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import fr.erwan.psql.gql.DataLoaderService;
import fr.erwan.psql.repos.ClimateRepo;
import fr.erwan.psql.repos.PlanetRepo;
import fr.erwan.psql.repos.TerrainRepo;

@Service
public class multiRelService {
    
    @Autowired
    private PlanetRepo repo;

    @Autowired
    private ClimateRepo climateRepo;

    @Autowired
    private TerrainRepo terrainRepo;

    private DataLoaderService dataLoader;

    public multiRelService() {
        this.dataLoader  = new DataLoaderService();
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
            List<Terrains> terrains = new ArrayList<>();
            List<Climates> climates = new ArrayList<>();

            p.SetName(jsn.get("name").asText());
            String population = jsn.get("population").asText();
            long populatio = 0;
            if (population != "null") {
                populatio = Long.parseLong(population);
            }
            p.SetPopulation(populatio);

            ArrayNode terrs = (ArrayNode) jsn.get("terrains");
            for (JsonNode j: terrs) {
                Terrains t = new Terrains();
                t.SetTerrains(j.asText());
                terrains.add(t);
            }

            ArrayNode clims = (ArrayNode) jsn.get("climates");
            for (JsonNode j: clims) {
                Climates c = new Climates();
                c.SetClimate(j.asText());
                climates.add(c);
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
}