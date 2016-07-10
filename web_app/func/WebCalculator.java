package func;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/WebCalculator")
public class WebCalculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Logic operate = new Logic();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("start")!=null){
			operate.restartCalculator();
			operate.note="";
		}
		if(request.getParameter("clicked") != null) {
			operate.caller((String)request.getParameter("clicked"));
			request.setAttribute("editor",operate.editor);
			request.setAttribute("status",operate.status);
			request.setAttribute("note",operate.note);
		}
				
		if (session.getAttribute("allow")!=null) {
			request.getRequestDispatcher("/CalcWeb.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("Login.jsp");
		}
			
	}
}
