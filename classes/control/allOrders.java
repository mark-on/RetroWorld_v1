package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.OrderDAO;
import Database.Database;
import entity.Order;

@WebServlet("/allOrders")
public class allOrders extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;

    public allOrders() {
        super();
        Database connection = new Database();
        orderDAO = new OrderDAO(connection);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

        try {
            List<Order> userOrders;
            if (isAdmin != null && isAdmin) {
                // Se l'utente è admin, visualizza tutti gli ordini
                userOrders = orderDAO.getAllOrder();
            } else {
                // Altrimenti, l'utente è un cliente e visualizza solo i propri ordini
                int userId = (Integer) session.getAttribute("utenteId");
                userOrders = orderDAO.findOrdersByUserId(userId);
            }
            request.setAttribute("userOrders", userOrders);
            request.getRequestDispatcher("user_orders.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Puoi gestire l'errore in modo appropriato, ad esempio reindirizzando a una pagina di errore
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
