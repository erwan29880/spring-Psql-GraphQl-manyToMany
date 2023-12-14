package fr.erwan.psql.multiRel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class DataLoaderService {

    private final String graphqlEndpoint = "https://swapi-graphql.netlify.app/.netlify/functions/index";
    
     /**
     * récupérer les données
     * @return le body de la requête à l'api
     */
    private String fetchData() {               
        RestTemplate restTemplate = new RestTemplate();
        String yourGraphqlQuery = "{\"query\":\"{allPlanets{planets{name population terrains climates}}}\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<String>(yourGraphqlQuery, headers);
        return restTemplate.postForObject(graphqlEndpoint, entity, String.class);
    }

    /**
     * Recupère le body du fetch data et récupère les informations au format Json
     * Les données sont hiérarchisées de la sort : 
     * data -> 
     *      allPlanets -> 
     *                  liste de planetes
     * @return une liste de jsonNode
     * @throws JsonProcessingException en cas de problême de parsage
     */
    public List<JsonNode> loadData() throws JsonProcessingException{
        String graphqlResponse = fetchData();
        List<JsonNode> jsn = new ArrayList<>();

        // Parse JSON response
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataNode = objectMapper.readTree(graphqlResponse).get("data");
        JsonNode allPlanets = dataNode.get("allPlanets");

        if (dataNode != null) {
            for (JsonNode item : allPlanets.get("planets")) {
                jsn.add(item);
            }
        }
        return jsn;
    }
}
