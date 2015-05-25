import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/fakeShipping")
public class FakeShipping extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SHIPPING_VIEW_ORDERS = "users/orders";
	private static final String SHIPPING_CALC_PRICE = "";
	
	/*
	 * This will serve fake data so that Chi can write front-end code without running a shipping server.
	 * Examples of how to use the API Fake:
	 * http://localhost:8080/Assn2/fakeShipping?method=viewOrders
	 * http://localhost:8080/Assn2/fakeShipping?method=shippingPrice&country=Australia
	 */
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
		} else if (method.equals("shippingPrice")) {
			shippingMethod = SHIPPING_CALC_PRICE;
			args.put("country", request.getParameter("country"));
		} else {
			response.getWriter().print("Error: Invalid method " + method);
		}

		String resp = query(shippingMethod, args);
		response.getWriter().print(resp);

	}

	public String query(String method, HashMap<String, String> args) {	
		URL callURL;
		try {
			callURL = getURL(method, args);
		} catch (MalformedURLException e) {
			return e.getMessage();
		}
		System.out.println("Calling " + callURL.toString());
		try {
			if (method.equals(SHIPPING_VIEW_ORDERS)) {
				return fakeViewOrders(args);
			} else if (method.equals(SHIPPING_CALC_PRICE)) {
				return fakeCalcShipping(args);
			}
			return "No such method " + method;
		} catch (IOException e) {
			return e.getMessage();
		}
	}
	
	/* Get the URL for a shipping server request. */
	public static URL getURL(String method, HashMap<String, String> args) throws MalformedURLException {
		String url = "http://shipping_server/" + method + "?";
		for (Map.Entry<String, String> kv: args.entrySet()) {
			url += kv.getKey() + "=" + kv.getValue() + "&";
		}
		return new URL(url);
	}
	
	public String fakeCalcShipping(HashMap<String, String> args) throws IOException {
		if (args.get("country") == null) throw new IOException("Argument 'country' was never provided.");
		return "{ \"price\": 200 }";
	}

	public String fakeViewOrders(HashMap<String, String> args) throws IOException {
		if (args.get("user") == null) throw new IOException("Argument 'user' was never provided. Is the user logged in?");
		return "{  \"data\": [    {      \"address\": \"hello world\",       \"order_id\": 1,       \"order_price\": 0.0,       \"order_state\": \"processing\",       \"quantity\": 6,       \"shipping_price\": 0.0    },     {      \"address\": \"world hello\",       \"order_id\": 2,       \"order_price\": 0.0,       \"order_state\": \"processing\",       \"quantity\": 5,       \"shipping_price\": 0.0    },     {      \"address\": \"elloh world\",       \"order_id\": 3,       \"order_price\": 0.0,       \"order_state\": \"shipped\",       \"quantity\": 1,       \"shipping_price\": 0.0    }  ],   \"error\": \"\",   \"message\": \"success\"}";
	}



}


