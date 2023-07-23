package Dao;
import Database.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.Brand;


public class BrandDAO {

	private Database connection; // Aggiungi un membro per il Database

    public BrandDAO(Database database) {
        this.connection = database;
    }

	    public void save(Brand brand) throws SQLException {
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            conn = connection.getConnection();
	            stmt = conn.prepareStatement("INSERT INTO brand (brand_name) VALUES (?)");
	            stmt.setString(1, brand.getName());
	            stmt.executeUpdate();
	        } finally {
	            // Chiudi le risorse
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        }
	    }

	    public void update(Brand brand) throws SQLException {
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            conn = connection.getConnection();
	            stmt = conn.prepareStatement("UPDATE brand SET brand_name = ? WHERE id = ?");
	            stmt.setString(1, brand.getName());
	            stmt.setInt(2, brand.getId());
	            stmt.executeUpdate();
	        } finally {
	            // Chiudi le risorse
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        }
	    }

	    public void delete(Brand brand) throws SQLException {
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        
	        try {
	            conn = connection.getConnection();
	            stmt = conn.prepareStatement("DELETE FROM brand WHERE id = ?");
	            stmt.setInt(1, brand.getId());
	            stmt.executeUpdate();
	        } finally {
	            // Chiudi le risorse
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        }
	    }


	    public Brand findByName(String brandName) throws SQLException {
	        // Utilizza il parametro connection per eseguire la query
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        Brand brand = null;

	        try {
	            Connection conn = connection.getConnection(); // Ottieni la connessione corretta da Database
	            String query = "SELECT * FROM brand WHERE brand_name = ?";
	            stmt = conn.prepareStatement(query);
	            stmt.setString(1, brandName);
	            rs = stmt.executeQuery();

	            if (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("brand_name");

	                // Altri campi da estrarre dal ResultSet, se necessario

	                // Crea un oggetto Brand con i dati estratti dal ResultSet
	                brand = new Brand(id, name);
	            }
	            else 
	            	return null;
	        } finally {
	            // Chiudi le risorse
	            if (rs != null) {
	                rs.close();
	            }
	            if (stmt != null) {
	                stmt.close();
	            }
	        }

	        return brand;
	    }



	    public Brand findById(int id) throws SQLException {
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        Connection conn = null;
	        
	        try {
	        	conn = connection.getConnection();
	            stmt = conn.prepareStatement("SELECT brand_name FROM brand WHERE id = ?");
	            stmt.setInt(1, id);
	            rs = stmt.executeQuery();
	            if (rs.next()) {
	                Brand brand = new Brand();
	                brand.setId(id);
	                brand.setName(rs.getString("brand_name"));
	                return brand;
	            }
	            return null;
	        } finally {
	            if (rs != null) {
	                rs.close();
	            }
	            if (stmt != null) {
	                stmt.close();
	            }
	        }
	    }
}
