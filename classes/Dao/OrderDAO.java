package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.Database;
import entity.Order;
import entity.Product;
import entity.CartItem;

public class OrderDAO {

    private Database connection; // Aggiungi un membro per il Database

    public OrderDAO(Database database) {
        this.connection = database;
    }

    // Metodo per inserire un nuovo ordine nel database
    public void insertOrder(Order order, List<CartItem> cartItems) throws SQLException {
        Connection conn = null;
        PreparedStatement stmtOrder = null;
        PreparedStatement stmtCartItem = null;
        
        try {
            conn = connection.getConnection();
            conn.setAutoCommit(false); // Imposta l'autocommit su false per gestire la transazione manualmente
            
            // Inserisci l'ordine nella tabella orderTable
            stmtOrder = conn.prepareStatement("INSERT INTO orderTable (utente_id, order_date, total_amount, order_status, address, name_card, card_num, cvc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmtOrder.setInt(1, order.getUserId());
            stmtOrder.setString(2, order.getDate());
            stmtOrder.setDouble(3, order.getTotal());
            stmtOrder.setString(4, order.getStatus());
            stmtOrder.setString(5, order.getAddress());
            stmtOrder.setString(6, order.getName());
            stmtOrder.setString(7, order.getCard());
            stmtOrder.setString(8, order.getCvc());
            stmtOrder.executeUpdate();

            // Ottieni l'id dell'ordine appena inserito
            int orderId = -1;
            ResultSet generatedKeys = stmtOrder.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            } else {
                // L'ottenimento dell'id dell'ordine è fallito, annulla la transazione
                conn.rollback();
                throw new SQLException("Failed to get the generated order ID.");
            }

            // Inserisci i dettagli dell'ordine nella tabella cartItem
            stmtCartItem = conn.prepareStatement("INSERT INTO cartItem (order_id, product_id, quantity, price) VALUES (?, ?, ?,?)");
            for (CartItem cartItem : cartItems) {
            	Product product = cartItem.getproduct();
            	int productId = product.getId();
            	Double price = product.getPrice();
                stmtCartItem.setInt(1, orderId);
                stmtCartItem.setInt(2, productId);
                stmtCartItem.setInt(3, cartItem.getQuantity());
                stmtCartItem.setDouble(4, price);
                stmtCartItem.executeUpdate();
            }

            conn.commit(); // Conferma la transazione dopo aver inserito l'ordine e i dettagli

        } catch (SQLException e) {
            conn.rollback(); // In caso di errori, annulla la transazione e ripristina lo stato precedente
            e.printStackTrace();
        } finally {
            // Chiudi le risorse
            if (stmtOrder != null) {
                stmtOrder.close();
            }
            if (stmtCartItem != null) {
                stmtCartItem.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public List<Order> findOrdersByUserId(int userId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = connection.getConnection();
            String query = "SELECT * FROM orderTable WHERE utente_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("id");
                String date = rs.getString("order_date");
                double total = rs.getDouble("total_amount");
                String name = rs.getString("name_card");
                String card = rs.getString("card_num");
                String cvc = rs.getString("cvc");
                String address = rs.getString("address");
                String status = rs.getString("order_status");

                Order order = new Order(orderId, userId, date, total, name, card, cvc, address, status);
                orders.add(order);
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

        return orders;
    }
    
    public List<Order> getAllOrder() throws SQLException {
        List<Order> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = connection.getConnection();
            String query = "SELECT * FROM orderTable";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("id");
                int userId = rs.getInt("utente_id");
                String date = rs.getString("order_date");
                double total = rs.getDouble("total_amount");
                String name = rs.getString("name_card");
                String card = rs.getString("card_num");
                String cvc = rs.getString("cvc");
                String address = rs.getString("address");
                String status = rs.getString("order_status");

                Order order = new Order(orderId, userId, date, total, name, card, cvc, address, status);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Puoi gestire l'errore in modo appropriato, ad esempio loggando l'errore o sollevando un'eccezione personalizzata
        } finally {
            // Chiudi le risorse
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

        return orders;
    }

}
