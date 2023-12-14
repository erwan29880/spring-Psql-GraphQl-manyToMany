# Spring boot - Psql - graphQl - ManyToMany 

### installation de la base de données Postresql : 
```bash
docker-compose up -d --build
```

L'application web créé des entités et tables dans une base de données 'jee' :   
- table planet 
- table terrains 
- table climates  
- table d'association planet_terrains  
- table d'association planet_climates  

L'application, avec la route http://localhost:8080/multi/init récupère des données d'une api et les enregistre en base de données.  
Les différentes routes du controlleur sont testables avec les commandes du fichier curl.txt
