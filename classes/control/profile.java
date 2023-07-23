package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UtenteDAO;
import Database.Database;
import entity.Utente;

/**
 * Servlet implementation class profile
 */
@WebServlet("/profile")
public class profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public profile() {
        super();
        Database connection = new Database();

        utenteDAO = new UtenteDAO(connection);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = null;
		HttpSession session = request.getSession();
		int utenteId = (Integer) session.getAttribute("utenteId");
		try {
			utente = utenteDAO.readUtente(utenteId);
			request.setAttribute("utente", utente);

		    request.getRequestDispatcher("myprofile.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
