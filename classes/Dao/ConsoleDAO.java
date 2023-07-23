package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Console;
import Database.Database;

public class ConsoleDAO {

    private Database connection;

    public ConsoleDAO(Database connection) {
        this.connection = connection;
    }

    public void save(Console console) throws SQLException {
        PreparedStatement stmt = null;
        try {
            Connection conn = connection.getConnection();
            stmt = conn.prepareStatement("INSERT INTO console (console_name, brand_id) VALUES (?, ?, ?)");
            stmt.setString(1, console.getName());
            stmt.setInt(3, console.getBrandId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void update(Console console) throws SQLException {
        PreparedStatement stmt = null;
        try {
            Connection conn = connection.getConnection();
            stmt = conn.prepareStatement("UPDATE console SET console_name = ?, brand_id = ? WHERE id = ?");
            stmt.setString(1, console.getName());
            stmt.setInt(3, console.getBrandId());
            stmt.setInt(4, console.getId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(Console console) throws SQLException {
        PreparedStatement stmt = null;
        try {
            Connection conn = connection.getConnection();
            stmt = conn.prepareStatement("DELETE FROM console WHERE id = ?");
            stmt.setInt(1, console.getId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public Console findById(int id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Connection conn = connection.getConnection();
            stmt = conn.prepareStatement("SELECT console_name, brand_id FROM console WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
            	int brand = rs.getInt("brand_id");
                Console console = new Console();
                console.setId(id);
                console.setName(rs.getString("name"));
               
                console.setBrandId(brand);
                return console;
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

    public List<Console> findConsolesByBrand(String brandName) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Console> consoles = new ArrayList<>();
        
        try {
            Connection conn = connection.getConnection();
            stmt = conn.prepareStatement("SELECT id, name, description, brand_id FROM console WHERE brand_id IN (SELECT id FROM brand WHERE brand_name = ?)");
            stmt.setString(1, brandName);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int brand = rs.getInt("brand_id");
                Console console = new Console(id, name, brand);
                consoles.add(console);
            }
        } finally {
            // Chiudi le risorse
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return consoles;
    }

    public Console findByName(String name) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Connection conn = connection.getConnection();
            stmt = conn.prepareStatement("SELECT id, console_name, brand_id FROM console WHERE console_name = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String consoleName = rs.getString("console_name");
                int brandId = rs.getInt("brand_id");
                return new Console(id, consoleName, brandId);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
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