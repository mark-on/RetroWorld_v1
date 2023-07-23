package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ProductDAO;
import Database.Database;
import entity.CartItem;
import entity.Product;

@WebServlet("/addToCart") //aggiunge CartItem to session
public class addToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private ProductDAO productDAO;
	  
    public addToCart() {
        super();
        Database connection = new Database();

        productDAO = new ProductDAO(connection);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		    List<CartItem> cartList = (List<CartItem>) session.getAttribute("cartList");

		    if (cartList == null) {
		        cartList = new ArrayList<>();
		        session.setAttribute("cartList", cartList);
		    }

		    // Recupera i parametri dalla richiesta (productId, quantity, orderId)
		    int productId = Integer.parseInt(request.getParameter("productId"));
		    int quantity = Integer.parseInt(request.getParameter("quantity"));
		    // Cerca se esiste già un CartItem con lo stesso productId nella lista
		    boolean isExistingItem = false;
		    Product product = null;
			try {
				product = productDAO.getProductById(productId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    for (CartItem cartItem : cartList) {
		    	Product prod_1 = cartItem.getproduct();
		    	int product_id2 = prod_1.getId();
		    	if (product_id2 == productId) {
		            // Se esiste già, incrementa la quantità invece di aggiungere un nuovo elemento
		            cartItem.setQuantity(cartItem.getQuantity() + quantity);
		            isExistingItem = true;
		            break;
		        }
		    }

		    if (!isExistingItem) {
		    	CartItem cartItem = new CartItem(product, quantity);
				cartList.add(cartItem);
		    }

		    // Aggiorna la lista di CartItem nella sessione
		    session.setAttribute("cartList", cartList);
    }

}
