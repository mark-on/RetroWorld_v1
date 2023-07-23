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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtenteDAO utenteDAO;
	@Override
    public void init() throws ServletException {
        super.init();
        Database connection = new Database();

        utenteDAO = new UtenteDAO(connection);

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
			String mail = request.getParameter("mail");
			String username_x = request.getParameter("username");
			try {
				if( utenteDAO.readUtente_bymail(mail, username_x) != true) {
					String f_name = request.getParameter("name");
					String l_name = request.getParameter("lastname");
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					int admin = 0;
					Utente utente = new Utente(username, f_name, l_name, password, mail, admin);
					utenteDAO.createUtente(utente);
					
					HttpSession session = request.getSession();
					session.setAttribute("isLogged", true);
					session.setAttribute("isAdmin", false);
					request.getRequestDispatcher("home.jsp").forward(request, response);
				}
				else {
					response.sendRedirect("already_in.jsp");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
