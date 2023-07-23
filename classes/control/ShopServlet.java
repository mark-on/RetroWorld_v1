package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Dao.ProductDAO;
import Database.Database;
import entity.Product;


/**
 * Servlet implementation class ShopServlet
 */
@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

	
	
	 @Override
	   public void init() throws ServletException{
		 	super.init();
		 	 Database connection = new Database();
		     productDAO = new ProductDAO(connection);

	   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search_text = request.getParameter("search");

		try {
		    List<Product> productList = null;
			if(search_text != null) { //Ricerca da searchbar
					productList = productDAO.search(search_text);
			}
			else { //shop normale
				String brandIdString = request.getParameter("brandId");
			    String type = request.getParameter("type");
			    String consoleIdString = request.getParameter("consoleId");
			    String sort = request.getParameter("sort");
	
			
				productList = productDAO.findByParameter(type, consoleIdString, brandIdString, sort); 
				  // Setta la lista di prodotti come attributo della richiesta
			    
			}
			request.setAttribute("productList", productList);
		    request.getRequestDispatcher("/shop_list.jsp").forward(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
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
