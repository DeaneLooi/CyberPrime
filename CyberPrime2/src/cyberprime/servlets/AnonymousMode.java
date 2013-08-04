package cyberprime.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cyberprime.entities.Clients;
import cyberprime.entities.Notifications;
import cyberprime.entities.Sessions;
import cyberprime.entities.dao.ClientsDAO;
import cyberprime.entities.dao.NotificationsDAO;
import cyberprime.util.FileMethods;

/**
 * Servlet implementation class AnonymousMode
 */
@WebServlet("/AnonymousMode")
public class AnonymousMode extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int anonymousUserNo=0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnonymousMode() {
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
		HttpSession existingHttpSession = request.getSession();
		Clients existingClient = (Clients)existingHttpSession.getAttribute("c");
		String anonymousMode=request.getParameter("anonSwitch");
		System.out.println("anonymousMode = "+anonymousMode);
		if (anonymousMode.equals("on")){
			Set sessionArray = (Set) getServletContext().getAttribute("cyberprime.sessions");
			Iterator sessionIt = sessionArray.iterator();
					while(sessionIt.hasNext()) {
					Sessions sess = (Sessions)sessionIt.next();
					System.out.println("Client id ="+sess.getClientId());
					if(sess.getClientId().equals(existingClient.getUserId())){
						existingClient.setUserId("Anonymous"+anonymousUserNo);
						anonymousUserNo++;
						Notifications n = new Notifications(existingClient.getUserId(),existingClient.getUserId(),"Anon");
						NotificationsDAO.createNotification(n);
						Sessions anonSessions = new Sessions(existingHttpSession.getId(), existingClient.getUserId());
						sessionArray.remove(sess);
						sessionArray.add(anonSessions);
					}				
					}
					existingHttpSession.removeAttribute("c");
					existingHttpSession.setAttribute("c", existingClient);
					
		}
		else {
			Clients newClient = ClientsDAO.retrieveClient(existingClient);
			System.out.println("UserId to change back to = "+newClient.getUserId());
			Set sessionArray = (Set) getServletContext().getAttribute("cyberprime.sessions");
			Iterator sessionIt = sessionArray.iterator();
					while(sessionIt.hasNext()) {
					Sessions sess = (Sessions)sessionIt.next();
					System.out.println("Client id ="+sess.getClientId());
					if(sess.getClientId().equals(existingClient.getUserId())){
						Sessions normalSessions = new Sessions(existingHttpSession.getId(), newClient.getUserId());
						sessionArray.remove(sess);
						sessionArray.add(normalSessions);
					}				
					}
					existingHttpSession.removeAttribute("c");
					existingHttpSession.setAttribute("c", newClient);
					
		}
		Clients currentClient=(Clients)existingHttpSession.getAttribute("c");
		System.out.println("current userID = "+ currentClient.getUserId());
		request.getRequestDispatcher("secured/templateNewHome.jsp").forward(request, response);
		System.out.println("Refreshed page to show new user ID");
	}

}
