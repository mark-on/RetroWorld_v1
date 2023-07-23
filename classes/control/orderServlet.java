package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.OrderDAO;

import Database.Database;
import entity.CartItem;
import entity.Order;
import entity.Product;

/**
 * Servlet implementation class orderServlet
 */
@WebServlet("/order")
public class orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderServlet() {
        super();
        Database connection = new Database();

        orderDAO = new OrderDAO(connection);
    }

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer utenteId = (Integer) session.getAttribute("utenteId");
		 List<CartItem> cartList = (List<CartItem>) session.getAttribute("cartList");
		 double totalPrice = 0.0;
		String address = request.getParameter("address");
		String name_card = request.getParameter("Name");
		String cardNum = request.getParameter("cardNumber");
		String cvc = request.getParameter("cvc");
		
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(currentDate);
        
        

        if (cartList != null && !cartList.isEmpty()) {
            for (CartItem cartItem : cartList) {
                Product product = cartItem.getproduct();
                int quantity = cartItem.getQuantity();
                double productPrice = product.getPrice();
                double totalProductPrice = productPrice * quantity;
                totalPrice += totalProductPrice;
            }
        }
        
		Order order = new Order(utenteId,dateString,totalPrice, name_card, cardNum,cvc, address );
		try {
			orderDAO.insertOrder(order,cartList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.removeAttribute("cartList");
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

}
