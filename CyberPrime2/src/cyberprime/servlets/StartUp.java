package cyberprime.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StartUp
 */
@WebServlet("/StartUp")
public class StartUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartUp() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set sess = Collections.synchronizedSet(new HashSet());
		getServletContext().setAttribute("cyberprime.sessions", sess);
		
		Set users = Collections.synchronizedSet(new HashSet());
		getServletContext().setAttribute("cyberprime.users", users);
		
		Set msg = Collections.synchronizedSet(new HashSet());
		getServletContext().setAttribute("cyberprime.msg", msg);
		
		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");

	     PrintWriter writer = response.getWriter();
	     
		    String html="<h1>You have officially started your server</h1>";
		    

		      writer.println(html);

		      writer.close();     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
