package fr.erwan.psql.multiRel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.erwan.psql.entities.Planet;
import fr.erwan.psql.entities.PlanetFront;


/**
 * Controlleur à tester avec le fichier curl.txt en console
 */
@RestController
@RequestMapping("/multi")
public class MultiRelControlleur {
    
    @Autowired
    private multiRelService serv;

    @GetMapping("/init")
    public List<Planet> insertInitialData() {
        return serv.savePlanets();
    } 

    @GetMapping("/getall")
    public List<Planet> getAll() {
        List<Planet> planets = serv.getAll();
        return planets;
    } 

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Planet> delete(@PathVariable final long id) {
        return new ResponseEntity<Planet>(serv.delete(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public Planet savePlanet(@RequestBody final PlanetFront planet) {
        return serv.savePlanet(planet.toPlanet());
    }

    @GetMapping("/get/{id}")
    public Planet getById(@PathVariable final long id) {
        return serv.getById(id);
    }

    @PutMapping("/update")
    public Planet update(@RequestBody final PlanetFront planetFront) {
        return serv.update(planetFront);
    }
}
