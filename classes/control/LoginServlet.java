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

@WebServlet("/logged")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtenteDAO utenteDAO;
	@Override
    public void init() throws ServletException {
        super.init();
        Database connection = new Database();

        utenteDAO = new UtenteDAO(connection);

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String pass = request.getParameter("password");
	
			Utente utente = null;
			boolean isAdmin = false;
			try {
				utente = utenteDAO.login(username, pass);
				int utenteId = utente.getId();
				int admin = utente.getisAdmin();
				if(admin == 1) {
					isAdmin = true;
				}
				if (utente != null) {
				    HttpSession session = request.getSession();
				    session.setAttribute("utenteId", utenteId);
				    session.setAttribute("username", username);
				    session.setAttribute("isLogged", true);
				    session.setAttribute("isAdmin", isAdmin);
				    request.getRequestDispatcher("home.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				request.setAttribute("errorMessage", "Username o password errati");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}finally {
			request.getRequestDispatcher("home.jsp");
		}
		
	}

}
