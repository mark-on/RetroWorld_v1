package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import Dao.ProductDAO;
import Database.Database;
import entity.Product;

/**
 * Servlet implementation class allProduct
 */
@WebServlet("/productlist_admin")
public class allProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
       
	 @Override
	   public void init() throws ServletException{
		 	super.init();
		 	 Database connection = new Database();
		     productDAO = new ProductDAO(connection);
	   }
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
   	  Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
   	  if(isAdmin!= null && isAdmin == true) {
		List<Product> productList = null;
		
		try {
			productList = productDAO.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	    // Setta la lista di prodotti come attributo della richiesta
	    request.setAttribute("productList", productList);

	    // Passa il controllo alla pagina JSP per visualizzare tutti i prodotti
	    request.getRequestDispatcher("/productlist_admin.jsp").forward(request, response);
   	  }
   	  else
   		 request.getRequestDispatcher("/home.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
