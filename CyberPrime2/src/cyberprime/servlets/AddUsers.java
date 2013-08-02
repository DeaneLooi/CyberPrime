package cyberprime.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cyberprime.entities.Clients;
import cyberprime.entities.Notifications;
import cyberprime.entities.Sessions;
import cyberprime.entities.dao.NotificationsDAO;

/**
 * Servlet implementation class AddUsers
 */
//@WebServlet("/AddUsers")
public class AddUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static Notifications n = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");

		String username = request.getParameter("username");
		HttpSession sess = request.getSession();
		Clients client = (Clients)sess.getAttribute("c");
		n = new Notifications(client.getUserId(),username,"AddUser");
	
	    // Check for online users before creating notifications
		Set sessions = (Set) getServletContext().getAttribute("cyberprime.sessions");
		Iterator sessionIt = sessions.iterator();
		
		while(sessionIt.hasNext()){
			Sessions sex = (Sessions) sessionIt.next();
			if (sex.getClientId().equalsIgnoreCase(username)){
				NotificationsDAO.createNotification(n);
				return;
			}
			
			else{
				// give feedback
				
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		
	}

}
