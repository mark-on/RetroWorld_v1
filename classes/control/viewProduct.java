package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ProductDAO;
import Database.Database;
import entity.Product;

@WebServlet("/product")
public class viewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
       
   @Override
   public void init() throws ServletException{
	   super.init();
	Database connection = new Database();
	productDAO = new ProductDAO(connection);
   }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
		
		try {
			if (isAdmin != null && isAdmin.booleanValue() && isAdmin == true ){
				Product product = productDAO.getProductById(productId);
				request.setAttribute("product", product);
				request.getRequestDispatcher("/product_edit.jsp").forward(request, response);
			}
			else{
				Product product = productDAO.getProductById(productId);
				request.setAttribute("product", product);
				request.getRequestDispatcher("/product.jsp").forward(request, response);
			}
			
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

	

