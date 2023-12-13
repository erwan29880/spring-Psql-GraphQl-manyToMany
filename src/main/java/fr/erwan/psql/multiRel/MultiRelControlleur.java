package fr.erwan.psql.multiRel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multi")
public class MultiRelControlleur {
    
    @Autowired
    private multiRelService serv;


    @GetMapping("/init")
    public ResponseEntity<List<Planet>> insertInitialData() {
        List<Planet> planets = serv.savePlanets();

        if (planets.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(planets, HttpStatus.OK);
        // if (tutorials.isEmpty()) {
        //       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //     }

        //     return new ResponseEntity<>(tutorials, HttpStatus.OK);
        // return serv.savePlanets();
    } 

    @GetMapping("/getall")
    public List<Planet> getAll() {
        List<Planet> planets = serv.getAll();
        // if (planets.isEmpty()) {
        //     return new ResponseEntity(HttpStatus.NO_CONTENT);
        // }
        // return new ResponseEntity(planets, HttpStatus.OK);
        return planets;
    } 

}
