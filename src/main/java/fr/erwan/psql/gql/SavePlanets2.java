package fr.erwan.psql.gql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SavePlanets2 {
    
    private DaoFactory dao;

    SavePlanets2() {
        this.dao = DaoFactory.getInstance();
    }

    public void insert(Planets2 p) {
        try (Connection conn = dao.getConnection()) {
            String[] pop = p.getTerrains().toArray(String[]::new);
            java.sql.Array pops = conn.createArrayOf("varchar", pop);
            String[] clim = p.getClimates().toArray(String[]::new);
            java.sql.Array clims = conn.createArrayOf("varchar", clim);


            String sql = "INSERT INTO planets2 (name, population, terrains, climates) values (?, ?, ?, ?);";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, p.getName());
            st.setLong(2, p.getPopulation());
            st.setArray(3, pops);
            st.setArray(4, clims);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
}
