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
 * Servlet implementation class updateUser
 */
@WebServlet("/updateUser")
public class updateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO;
    public updateUser() {
        super();
        Database connection = new Database();

        utenteDAO = new UtenteDAO(connection);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int utenteId = (Integer) session.getAttribute("utenteId");
		String mail = request.getParameter("mail");
		String f_name = request.getParameter("name");
		String l_name = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int admin = 0;
		Utente utente = new Utente(utenteId, username, f_name, l_name, password, mail, admin);
		try {
			utenteDAO.updateUtente(utente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
