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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
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
		// Log the user out if they were logged in before hitting Register
		request.getSession().invalidate();
		
		// Connect and get parameters
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String hashedPassword = HibernateUtil.sha256(password);
		System.out.println("hashed: " + hashedPassword); // TODO: remove
		
		Session session = HibernateConnection.getSession();
		
//		String hql = "SELECT U.id FROM User U WHERE U.email = '" + email + "'";
		String hql = "FROM User U WHERE U.email = '" + email + "'";
		Query<User> query = session.createQuery(hql, User.class);
		List<User> users = query.getResultList();
		
		if (users.size() > 0) {
			PrintWriter out = response.getWriter();
			out.append("<p class='error' style='color:red;'>Registration Failed. User Already Exists.</p>");
			request.getRequestDispatcher("registration.jsp").include(request, response);
		} else {
			session.beginTransaction();
			User newUser = new User();
			newUser.setEmail(email);
			newUser.setName(name);
			newUser.setPassword(hashedPassword);
			
			Integer result = (Integer) session.save(newUser);
			session.getTransaction().commit();
//			// log in
//			request.getSession().setAttribute("loggedIn", Boolean.valueOf(true));
//			request.getSession().setAttribute("user", newUser.getId());
//			request.getSession().setAttribute("name", newUser.getName());
			
			System.out.println("Result of adding new user: " + result); // TODO remove test
			response.sendRedirect("index.jsp");
		}
	}
}
