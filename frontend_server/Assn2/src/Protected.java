import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Protected extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher view = request.getRequestDispatcher("/protected.jsp");
    	if (request.isUserInRole("admin")) {
        	request.setAttribute("message", "Welcome, administrator.");
    	} else {
    		request.setAttribute("message", "Welcome, user.");
    	}
    	request.setAttribute("username", request.getUserPrincipal().getName());
		view.forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
