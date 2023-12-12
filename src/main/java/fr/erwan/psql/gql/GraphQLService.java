package fr.erwan.psql.gql;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


public class GraphQLService {
    private final String graphqlEndpoint = "https://swapi-graphql.netlify.app/.netlify/functions/index";

    public String fetchData() {        
       
        // Implement logic to fetch data from the GraphQL endpoint
        RestTemplate restTemplate = new RestTemplate();
        String yourGraphqlQuery = "{\"query\":\"{allPlanets{planets{name population terrains climates}}}\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<String>(yourGraphqlQuery,headers);
        return restTemplate.postForObject(graphqlEndpoint,entity, String.class);
    }
}