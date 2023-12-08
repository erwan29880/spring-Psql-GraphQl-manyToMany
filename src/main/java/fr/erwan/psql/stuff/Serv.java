package fr.erwan.psql.stuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class Serv {

    @Autowired
    private Repo repo;

    public Planets save(Planets p) {
        return this.repo.save(p);
    }

    public List<Planets> getAll() {
        List<Planets> planets = new ArrayList<Planets>();
        this.repo.findAll().forEach(planets::add);
        return planets;
    }

    public Planets getOne(Long id) {
        return this.repo.findById(id).orElse(new Planets());
    }

    public boolean delete(Planets p) {
        try{
            this.repo.delete(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(Planets p) {
        Planets inDb = this.getOne(p.getId());
        if (inDb.getId() == 0L) return false;

        this.repo.save(p);
        return true;
    }
    
}
