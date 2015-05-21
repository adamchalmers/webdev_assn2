import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@WebServlet("/flickr")
public class FlickrAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String API_KEY = "2566a4dc33862f83b5b9072310f95533";
	private static final String METHOD = "flickr.photos.search";
	private static final String REST_ENDPOINT = "https://api.flickr.com/services/rest/";
	private static final String DEFAULT_NUMBER="4";

	/* Get the URL for a flickr request. */
	public static URL getURL(String tags, String extras) {
		try {
			return new URL(REST_ENDPOINT + "?method=" + METHOD + "&format=rest&per_page=" + DEFAULT_NUMBER + "&api_key=" + API_KEY
					+ "&extras=" + extras + "&tags="+tags);
		} catch (MalformedURLException e) {
			return null;
		}
	}
	
	/* Helper function to get node attributes */
	public static String attrOf(Node n, String attr) {
		return n.getAttributes().getNamedItem(attr).getTextContent();
	}
	
	/* Get the image URL of a node */
	public static String urlOf(Node n) {
		return "https://farm" 
				+ attrOf(n, "farm") + ".staticflickr.com/" + attrOf(n, "server") + "/" 
				+ attrOf(n, "id") + "_" + attrOf(n, "secret") + ".jpg";
	}
	

	public String call(String tags) {		
		
		try{
			URL callURL = getURL(tags, "tags");
			HttpURLConnection urlConnection = (HttpURLConnection) callURL.openConnection();
			InputStream urlStream = urlConnection.getInputStream();
		
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document response = db.parse(urlStream);
			
			NodeList nl = response.getElementsByTagName("photo");
			JSONArray json = new JSONArray();
			for (int i = 0; i < nl.getLength(); i ++){
				Node node = nl.item(i);
				JSONObject obj = new JSONObject();
				obj.put("url", urlOf(node));
				for (String s : new String[]{"title", "tags"}) obj.put(s, attrOf(node, s));
				json.add(obj);
			}
			urlConnection.disconnect();
			return json.toJSONString();
			
		} catch (IOException e){
			return "IO Error: " + e.getMessage();
		} catch (ParserConfigurationException e) {
			return "Parse Error: " + e.getMessage();
		} catch (SAXException e) {
			return "Parse Error: " + e.getMessage();
		}
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tags = request.getParameter("tags");
		System.out.println("Flickr API request: " + tags);
		if (tags == null) {
			tags = "fire";
		}
		String resp = call(tags);
    	response.getWriter().print(resp);
	}

}
