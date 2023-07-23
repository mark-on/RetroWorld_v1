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
 * Servlet implementation class remove
 */
@WebServlet("/remove")
public class remove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public remove() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		 List<CartItem> cartList = (List<CartItem>) session.getAttribute("cartList");
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		if(productId != null) {
			CartItem cartItemToRemove = null;
			 for (CartItem cartItem : cartList) { 
				 Product product = cartItem.getproduct();
				 int p_id = product.getId();
				 if(productId == p_id) {
					 cartItemToRemove = cartItem;
			        break;
				 }
			 }
			 if (cartItemToRemove != null) {
				    cartList.remove(cartItemToRemove);
				}
		}
		response.sendRedirect("cart.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
