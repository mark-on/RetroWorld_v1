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

import Dao.UtenteDAO;
import Database.Database;
import entity.Utente;

/**
 * Servlet implementation class allProduct
 */
@WebServlet("/alluser")
public class allUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO;
       
	 @Override
	   public void init() throws ServletException{
		 	super.init();
		 	 Database connection = new Database();
		     utenteDAO = new UtenteDAO(connection);
	   }
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
   	  Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
   	  if(isAdmin!= null && isAdmin == true) {
		List<Utente> userList = null;
		
		try {
			userList = utenteDAO.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

	    request.setAttribute("userList", userList);

	    request.getRequestDispatcher("/userlist.jsp").forward(request, response);
   	  }
   	  else
   		 request.getRequestDispatcher("/home.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
