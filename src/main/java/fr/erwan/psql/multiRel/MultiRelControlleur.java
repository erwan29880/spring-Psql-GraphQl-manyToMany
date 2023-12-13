package fr.erwan.psql.multiRel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multi")
public class MultiRelControlleur {
    
    @Autowired
    private multiRelService serv;


    @GetMapping("/init")
    public List<Planet> insertInitialData() {
        return serv.savePlanets();
    } 

}
