package fr.erwan.psql.gql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;


import java.util.List;
import java.util.ArrayList;

public class DataLoaderService {

    private GraphQLService graphQLService;
    private SavePlanets2 sv;

    public DataLoaderService() {
        this.graphQLService = new GraphQLService();
        this.sv = new SavePlanets2();
    }

    public void loadData() throws JsonProcessingException{
        String graphqlResponse = graphQLService.fetchData();
        System.out.println(graphqlResponse);

        // Parse JSON response
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataNode = objectMapper.readTree(graphqlResponse).get("data");
        JsonNode allPlanets = dataNode.get("allPlanets");
        System.out.println(dataNode == null);
        if (dataNode != null) {
            for (JsonNode item : allPlanets.get("planets")) {
                // repository.save(this.jsonToPlanets2(item));
                Planets2 p = this.jsonToPlanets2(item);
                sv.insert(p);
            }
        }
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
