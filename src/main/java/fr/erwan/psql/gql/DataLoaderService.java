package fr.erwan.psql.gql;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import fr.erwan.psql.multiRel.Climates;
import fr.erwan.psql.multiRel.Planet;
import fr.erwan.psql.multiRel.Terrains;

public class DataLoaderService {

    private GraphQLService graphQLService;
    private SavePlanets2 sv;

    public DataLoaderService() {
        this.graphQLService = new GraphQLService();
        this.sv = new SavePlanets2();
    }

    public void loadData(boolean insertion) throws JsonProcessingException{
        String graphqlResponse = graphQLService.fetchData();

        // Parse JSON response
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataNode = objectMapper.readTree(graphqlResponse).get("data");
        JsonNode allPlanets = dataNode.get("allPlanets");
        if (dataNode != null) {
            for (JsonNode item : allPlanets.get("planets")) {
                Planets2 p = this.jsonToPlanets2(item);
                if (insertion) {
                    sv.insert(p);
                }
            }
        }
    }

    public List<JsonNode> loadData() throws JsonProcessingException{
        String graphqlResponse = graphQLService.fetchData();
        // List<Planet> planetes = new ArrayList<>();
        List<JsonNode> jsn = new ArrayList<>();

        // Parse JSON response
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataNode = objectMapper.readTree(graphqlResponse).get("data");
        JsonNode allPlanets = dataNode.get("allPlanets");

        if (dataNode != null) {
            for (JsonNode item : allPlanets.get("planets")) {
                // planetes.add(this.jsonToPlanet(item));
                jsn.add(item);
            }
        }
        return jsn;
    }

    private Planet jsonToPlanet(JsonNode jsn) {
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
        }

        ArrayNode clims = (ArrayNode) jsn.get("climates");
        for (JsonNode j: clims) {
            Climates c = new Climates();
            c.SetClimate(j.asText());
        }

        p.SetClimates(climates);
        p.SetTerrains(terrains);

        return p;
    }

    private Planets2 jsonToPlanets2(JsonNode jsn) {
        Planets2 p = new Planets2();
        List<String> terrains = new ArrayList<>();
        List<String> climates = new ArrayList<>();

        p.setName(jsn.get("name").asText());
        
        String population = jsn.get("population").asText();
        long populatio = 0;
        if (population != "null") {
            populatio = Long.parseLong(population);
        }
        p.setPopulation(populatio);

        ArrayNode terrs = (ArrayNode) jsn.get("terrains");
        for (JsonNode j: terrs) {
            terrains.add(j.asText());
        }

        ArrayNode clims = (ArrayNode) jsn.get("climates");
        for (JsonNode j: clims) {
            climates.add(j.asText());
        }

        p.setTerrains(terrains);
        p.setClimates(climates);

        return p;
    }
}
