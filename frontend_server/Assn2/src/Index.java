import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Survey
 * This class handles survey submitted by the user and displays results in another JSP
 */

@WebServlet("/index.html")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
    	if (request.isUserInRole("admin")) {
        	request.setAttribute("message", "Welcome, administrator.");
    	} else {
    		request.setAttribute("message", "Server message working!");
    	}
    	request.setAttribute("username", request.getUserPrincipal().getName());
		view.forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
