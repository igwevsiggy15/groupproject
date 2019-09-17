package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.query.Query;

import db.HibernateConnection;
import db.HibernateUtil;
import entities.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 *      This should never be accessed (only POST)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("How did you get here?");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Connect and get parameters
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String hashedPassword = HibernateUtil.sha256(password);
		
		Session session = HibernateConnection.getSession();
		
		// like SELECT *
		String hql = "FROM User U WHERE U.email = '" + email + "' AND U.password = '" + hashedPassword + "'";
		Query<User> query = session.createQuery(hql, User.class);
		List<User> users = query.getResultList();
		
		// Check if user exists
		
		if (users.size() > 0) {
			// Set user session
			User theUser = users.get(0);
			
			request.getSession().setAttribute("loggedIn", Boolean.valueOf(true));
			request.getSession().setAttribute("user", theUser.getId());
			request.getSession().setAttribute("name", theUser.getName());
			response.sendRedirect("home.jsp");
		} else {
			PrintWriter writer = response.getWriter();
			writer.append("<p class='error'>Login Failed</p>");
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}
}
