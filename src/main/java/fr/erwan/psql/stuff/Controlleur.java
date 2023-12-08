package fr.erwan.psql.stuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/planets")
public class Controlleur {

    @Autowired 
    private Serv serv;

    @GetMapping("/all")
    @CrossOrigin(originPatterns = "http://localhost**")
    public List<Planets> getAll() {
        return this.serv.getAll();
    }

    @GetMapping("/{id}")
    @CrossOrigin(originPatterns = "http://localhost**")
    public Planets getOne(@PathVariable final Long id) {
        return this.serv.getOne(id);
    }

    @PostMapping("/save")
    @CrossOrigin(originPatterns = "http://localhost**")
    public Planets save(@RequestBody final Planets p) {
        return this.serv.save(p);
    }

    @DeleteMapping("/del")
    @CrossOrigin(originPatterns = "http://localhost**")
    public Planets delete(@RequestBody final Planets p) {
        this.serv.delete(p);
        return new Planets();
    }

    @PutMapping("/update")
    @CrossOrigin(originPatterns = "http://localhost**")
    public Planets update(@RequestBody final Planets p) {
        this.serv.update(p);
        return new Planets();
    }
}
