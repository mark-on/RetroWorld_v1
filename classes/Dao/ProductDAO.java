package Dao;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import Database.Database;
import entity.Product;


public class ProductDAO {
    private Database connection;

    public ProductDAO(Database database) {
        this.connection = database;
    }
    
    public List<Product> findAll() throws SQLException {
        List<Product> productList = new ArrayList<>();

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("product_name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                int amount = rs.getInt("amount");
                String tag = rs.getString("tag");
                String type = rs.getString("product_type");
                int brandId = rs.getInt("brand_id");
                int consoleId = rs.getInt("console_id");
                Blob imageBlob = rs.getBlob("image");
                InputStream inputStream = imageBlob.getBinaryStream();

                Product product = new Product(id, name, price, description, brandId, consoleId, amount, tag, type, inputStream);
                productList.add(product);
            }
        }

        return productList;
    }
    public List<Product> search(String searchTerm)  throws SQLException{
    	List<Product> productList = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    Product product = null;

	    conn = connection.getConnection();
	    String query = "SELECT * FROM product WHERE product_name LIKE ? OR description LIKE ? OR tag LIKE ?";
	    stmt = conn.prepareStatement(query);
	    stmt.setString(1, "%" + searchTerm + "%");
	    stmt.setString(2, "%" + searchTerm + "%");
	    stmt.setString(3, "%" + searchTerm + "%");
	    rs = stmt.executeQuery();
	    try { 	   
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("product_name");
	            double price = rs.getDouble("price");
	            String description = rs.getString("description");
	            int amount = rs.getInt("amount");
	            String tag = rs.getString("tag");
	            String type = rs.getString("product_type");
	            int brandId_ = rs.getInt("brand_id");
	            int consoleId_ = rs.getInt("console_id");
	            Blob imageBlob = rs.getBlob("image");
	            InputStream inputStream = imageBlob.getBinaryStream();
	            product = new Product(id, name, price, description, brandId_, consoleId_, amount, tag, type, inputStream);
	            productList.add(product);
	        }
	    } catch (SQLException e) {
	        return null;
	    }
	    return productList;
	}	

    
    public List<Product> related(String relatedBy, int id_rel) throws SQLException{
    	List<Product> productList = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    Product product = null;
	    
	    if(relatedBy == "brand") {
	    	conn = connection.getConnection();
	        String query = "SELECT * FROM product WHERE brand_id = ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, id_rel);
	        rs = stmt.executeQuery();
	    }
	    if(relatedBy == "console") {
	    	conn = connection.getConnection();
	        String query = "SELECT * FROM product WHERE console_id = ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, id_rel);
	        rs = stmt.executeQuery();
	    }
	    try { 	   
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("product_name");
	            double price = rs.getDouble("price");
	            String description = rs.getString("description");
	            int amount = rs.getInt("amount");
	            String tag = rs.getString("tag");
	            String type = rs.getString("product_type");
	            int brandId_ = rs.getInt("brand_id");
	            int consoleId_ = rs.getInt("console_id");
	            Blob imageBlob = rs.getBlob("image");
	            InputStream inputStream = imageBlob.getBinaryStream();
	            product = new Product(id, name, price, description, brandId_, consoleId_, amount, tag, type, inputStream);
	            productList.add(product);
	        }
	    } catch (SQLException e) {
	        
	    }
	    return productList;
	}	  
    public void updateProduct(Product product) throws SQLException {
    Connection conn = null;
    PreparedStatement stmt = null;
    
    try {
        conn = connection.getConnection();
        stmt = conn.prepareStatement("UPDATE product SET product_name = ?, description = ?, brand_id = ?, console_id = ?, product_type = ?, amount = ?, tag = ?, price = ?, image = ?  WHERE id = ?");
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getDescription());
        stmt.setInt(3, product.getBrandId());
        stmt.setInt(4, product.getConsoleId());
        stmt.setString(5, product.getType());
        stmt.setInt(6, product.getAmount());
        stmt.setString(7, product.getTag());
        stmt.setDouble(8, product.getPrice());
        stmt.setBinaryStream(9, product.getImage());
        stmt.setInt(10, product.getId());
        stmt.executeUpdate();
    } finally {
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}    
	public void addProduct(Product product) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        
        try {
        	conn = connection.getConnection();
            stmt = conn.prepareStatement("INSERT INTO product (product_name, description, brand_id, console_id, product_type, amount, tag, image, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getBrandId());
            stmt.setInt(4, product.getConsoleId());
            stmt.setString(5, product.getType());
            stmt.setInt(6, product.getAmount());
            stmt.setString(7, product.getTag());
            stmt.setBinaryStream(8, product.getImage());
            stmt.setDouble(9, product.getPrice());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
	
	public Product getProductById(int productId) throws SQLException {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    Product product = null;

	    try {
	        conn = connection.getConnection();
	        String query = "SELECT * FROM product WHERE id = ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setInt(1, productId);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	        	int id = rs.getInt("id");
                String name = rs.getString("product_name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                int amount = rs.getInt("amount");
                String tag = rs.getString("tag");
                String type = rs.getString("product_type");
                int brandId = rs.getInt("brand_id");
                int consoleId = rs.getInt("console_id");
                Blob imageBlob = rs.getBlob("image");
                InputStream inputStream = imageBlob.getBinaryStream();
                
	            // Creare l'oggetto Product con i dati ottenuti dal database
	            product = new Product(id, name, price, description, brandId, consoleId, amount, tag, type, inputStream);
	        }

	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        if (stmt != null) {
	            stmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }

	    return product;
	}

	public List<Product> findByParameter(String nav_type, String nav_consoleId, String nav_brandId, String sort) {
	    List<Product> productList = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    Product product = null;
	    int brandId = 0, consoleId =  0;
	    try {
	    	
	        conn = connection.getConnection();
	        StringBuilder query = new StringBuilder("SELECT * FROM product WHERE 1=1");
	        if(nav_type != null) {
	        	query.append(" AND product_type = ? ");
	        }
	        if (nav_brandId != null) {
	        	brandId = Integer.parseInt(nav_brandId);
	            query.append(" AND brand_id = ? ");
	        }
	        if (nav_consoleId != null) {
	        	consoleId = Integer.parseInt(nav_consoleId);
	            query.append(" AND console_id = ? ");
	        }
	        if (sort != null) {
	            if (sort.equals("price-cre")) {
	                query.append(" ORDER BY price ASC");
	            } else if (sort.equals("price-dre")) {
	                query.append(" ORDER BY price DESC");
	            }
	        }
	       
	        
	        stmt = conn.prepareStatement(query.toString());
	        int parameterIndex = 1;
	        if(nav_type != null) {
	        	stmt.setString(parameterIndex++, nav_type);
	        }
	        if (nav_brandId != null) {
	            stmt.setInt(parameterIndex++, brandId);
	        }
	        if (nav_consoleId != null) {
	            stmt.setInt(parameterIndex++, consoleId);
	        }
	        rs = stmt.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("product_name");
	            double price = rs.getDouble("price");
	            String description = rs.getString("description");
	            int amount = rs.getInt("amount");
	            String tag = rs.getString("tag");
	            String type = rs.getString("product_type");
	            int brandId_ = rs.getInt("brand_id");
	            int consoleId_ = rs.getInt("console_id");
	            Blob imageBlob = rs.getBlob("image");
	            InputStream inputStream = imageBlob.getBinaryStream();
	            product = new Product(id, name, price, description, brandId_, consoleId_, amount, tag, type, inputStream);
	            productList.add(product);
	        }
	    } catch (SQLException e) {
	        // Gestione dell'errore
	    }
	    return productList;
	}	
}
