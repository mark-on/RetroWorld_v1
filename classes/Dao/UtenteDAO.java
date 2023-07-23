package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entity.Utente;

import Database.*;

public class UtenteDAO {

    private Database connection;

    
    public UtenteDAO(Database connection) {
        this.connection = connection;
    }

    public void createUtente(Utente utente) throws SQLException {
        PreparedStatement stmt = null;
        try {
            // Prepara la query di inserimento
        	Connection conn = connection.getConnection();
            String sql = "INSERT INTO utente (first_name, last_name, email, username, user_password, admin) VALUES (?, ?, ?, ?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, utente.getName());
            stmt.setString(2, utente.getLastname());
            stmt.setString(3, utente.getEmail());
            stmt.setString(4, utente.getUsername());
            stmt.setString(5, utente.getPassword());
            stmt.setInt(6, utente.getisAdmin());
            // Esegui la query di inserimento
            stmt.executeUpdate();
        } finally {
            // Chiudi il PreparedStatement
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public boolean readUtente_bymail(String mail, String username) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	 Connection conn = connection.getConnection();
            // Prepara la query di selezione
            String sql = "SELECT * FROM utente WHERE email = ? OR username = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, mail);
            stmt.setString(2,username);

            // Esegui la query di selezione
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
            
            
        } finally {
            // Chiudi il PreparedStatement e il ResultSet
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    public Utente login(String username, String pass) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	 Connection conn = connection.getConnection();
            // Prepara la query di selezione
            String sql = "SELECT * FROM utente WHERE username = ? AND user_password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString (1, username);
            stmt.setString(2, pass);

            // Esegui la query di selezione
            rs = stmt.executeQuery();
            if (rs.next()) {
            	int id = rs.getInt("id");
                String name = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                String email = rs.getString("email");
                int admin = rs.getInt("admin");

                Utente utente = new Utente(id, username, name, lastname, pass, email, admin);
                return utente;
                
            } else {
                return null;
            }
        } finally {
            // Chiudi il PreparedStatement e il ResultSet
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    public Utente readUtente(int id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	 Connection conn = connection.getConnection();
            // Prepara la query di selezione
            String sql = "SELECT * FROM utente WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            // Esegui la query di selezione
            rs = stmt.executeQuery();
            if (rs.next()) {
                Utente utente = new Utente();
                utente.setId(rs.getInt("id"));
                utente.setName(rs.getString("first_name"));
                utente.setLastname(rs.getString("last_name"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("user_password"));
                utente.setUsername(rs.getString("username"));
                utente.setAdmin(rs.getInt("admin"));
                return utente;
            } else {
                return null;
            }
        } finally {
            // Chiudi il PreparedStatement e il ResultSet
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void updateUtente(Utente utente) throws SQLException {
        PreparedStatement stmt = null;
        try {
            // Prepara la query di aggiornamento
        	Connection conn = connection.getConnection();
            String sql = "UPDATE utente SET first_name = ?, last_name = ?, email = ?, user_password = ?, username = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, utente.getName());
            stmt.setString(2, utente.getLastname());
            stmt.setString(3, utente.getEmail());
            stmt.setString(4, utente.getPassword());
            stmt.setString(5, utente.getUsername());
            stmt.setInt(6, utente.getId());

            stmt.executeUpdate();
        } finally {
            // Chiudi il PreparedStatement
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteUtente(int id) throws SQLException {
        PreparedStatement stmt = null;
        try {
            // Prepara la query di cancellazione
        	Connection conn = connection.getConnection();
            String sql = "DELETE FROM utente WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            // Esegui la query di cancellazione
            stmt.executeUpdate();
        } finally {
            // Chiudi il PreparedStatement
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public List<Utente> findAll() throws SQLException {
        List<Utente> userList = new ArrayList<>();

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM utente");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String name = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                String password = rs.getString("user_password");
                String email = rs.getString("email");
                int admin = rs.getInt("admin");

                Utente utente = new Utente(id, username, name, lastname, password, email, admin);
                userList.add(utente);
            }
        }

        return userList;
    }
	    
    
}

			


	