import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/shipping")
public class Shipping extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String SHIPPING_URL = "http://127.0.0.1:5000/api/v1/";
	private static final String SHIPPING_VIEW_ORDERS = "users/orders";

	/* Get the URL for a shipping server request. */
	public static URL getURL(String method, HashMap<String, String> args) {
		try {
			String url = SHIPPING_URL + method + "?";
			for (Map.Entry<String, String> kv: args.entrySet()) {
				url += kv.getKey() + "=" + kv.getValue() + "&";
			}
			return new URL(url);
		} catch (MalformedURLException e) {
			return null;
		}
	}
	
	public String query(String method, HashMap<String, String> args) {		
		
		try{
			URL callURL = getURL(method, args);
			System.out.println("Calling " + callURL.toString());
			HttpURLConnection urlConnection = (HttpURLConnection) callURL.openConnection();
			InputStream urlStream = urlConnection.getInputStream();
		
			BufferedReader in = new BufferedReader(new InputStreamReader(urlStream));
			String response = "";
			String line;
			while ((line = in.readLine()) != null) response += line;
			
			return response;
	
		} catch (IOException e){
			return "IO Error: " + e.getMessage();
		}
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getUserPrincipal() == null) {
			response.getWriter().print("Error: you aren't logged in.");
		}
		// We want to ping api/v1/  users/orders?user=user1
		
		// Check which method they want, read the appropriate parameters.
    	String method = request.getParameter("method");
    	if (method == null) response.getWriter().print("You must supply a method!");
    	
    	String shippingMethod = null;
    	HashMap<String, String> args = new HashMap<String, String>();
    	if (method.equals("viewOrders")) {
    		shippingMethod = SHIPPING_VIEW_ORDERS;
        	args.put("user", request.getUserPrincipal().getName());
    	} else {
    		response.getWriter().print("Error: Invalid method " + method);
    	}
    	
		String resp = query(shippingMethod, args);
    	response.getWriter().print(resp);
    		
	}
	
}


