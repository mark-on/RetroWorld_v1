package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.CartItem;
import entity.Product;

/**
 * Servlet implementation class checkoutServlet
 */
@WebServlet("/checkout")
public class checkoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public checkoutServlet() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		    List<CartItem> cartList = (List<CartItem>) session.getAttribute("cartList");
		    double totalPrice = 0;
		    if (cartList != null && !cartList.isEmpty()) {
	            for (CartItem cartItem : cartList) {
	                Product product = cartItem.getproduct();
	                int quantity = cartItem.getQuantity();
	                double productPrice = product.getPrice();
	                double totalProductPrice = productPrice * quantity;
	                totalPrice = totalPrice + totalProductPrice;
	            }
	        }
		    if(totalPrice < 1) {
		    	 request.getRequestDispatcher("cart.jsp").forward(request, response);
		    }
		    else {
			    request.setAttribute("totalPrice", totalPrice);
			    request.getRequestDispatcher("Checkout.jsp").forward(request, response);
		    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
