package fr.erwan.psql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import fr.erwan.psql.gql.DataLoaderService;


@SpringBootApplication
public class PsqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsqlApplication.class, args);
		// DataLoaderService s = new DataLoaderService();
		// try {
		// 	s.loadData(true);
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }
	}

}
