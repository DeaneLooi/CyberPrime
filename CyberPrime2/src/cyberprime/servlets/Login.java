package cyberprime.servlets;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cyberprime.entities.Clients;
import cyberprime.entities.Sessions;
import cyberprime.entities.dao.ClientsDAO;

/**
 * Servlet implementation class Login
 */
//@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession session = request.getSession();
		Clients client = (Clients) session.getAttribute("client");
		String pattern = (String)request.getParameter("pattern");
		
		Clients c = ClientsDAO.retrieveClient(client);
		if(c == null){
			
			Object obj = new Object();
			obj = "<p style='color:red'>*There is no user registered with the image uploaded</p>";
			request.setAttribute("loginResult", obj);
			request.getRequestDispatcher("templateLogin.jsp").forward(request, response);
			
			}
		
		else if(c != null){
			
		client.setUserId(c.getUserId());	
		if(pattern.length() != 0){
			
			try {
				client.setPattern(pattern);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		
		else{
			Object obj = new Object();
			obj = "<p style='color:red'>*You did not choose a pattern</p>";
			request.setAttribute("loginResult", obj);
			request.getRequestDispatcher("patternLogin.jsp").forward(request, response);
			return;
		}
		
			if(client.getImageHash().equals(c.getImageHash()) && client.getPattern().equals(c.getPattern())){
				Sessions s = new Sessions(session.getId(),c.getUserId());
//				s = SessionsDAO.createSession(s);
//				Set sess = Collections.synchronizedSet(new HashSet());
//				getServletContext().setAttribute("cyberprime.sessions", sess);
				//WeakReference sessionRef = new WeakReference(s);
				Set sessions = (Set)getServletContext().getAttribute("cyberprime.sessions");
				sessions.add(s);
				session.setAttribute("c", c);
				//session.setMaxInactiveInterval(10);
				request.getRequestDispatcher("secured/newHome.jsp").forward(request, response);
			}	
			
			else{
				Object obj = new Object();
				obj = "<p style='color:red'>*Login failed, wrong pattern / image</p>";
				request.setAttribute("loginResult", obj);
				request.getRequestDispatcher("patternLogin.jsp").forward(request, response);
				return;
			}
		
		}

		
	}

}
