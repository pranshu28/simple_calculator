package login;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public boolean valid(String user, String pass) {
		return user.matches(pass) && "admin".matches(user)
			   ? true
			   : false;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String click = (String)request.getParameter("click");
		String user_id = (String)request.getParameter("user_id");
		String password = (String)request.getParameter("password");
		HttpSession session = request.getSession();
		if (click.matches("Login")) {
			if ( valid(user_id,password) ) {
				session.setAttribute("allow","Yes");
				request.getRequestDispatcher("/CalcWeb.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
			}
		}
		else {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("allow") == null) {
			response.sendRedirect("Login.jsp");
		} else {
			response.sendRedirect("CalcWeb.jsp");
		}
	}
}
