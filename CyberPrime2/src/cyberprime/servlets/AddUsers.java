package cyberprime.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
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
@WebServlet("/AddUsers")
public class AddUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static Notifications n = null;
    static String sessionId;
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
		
		response.setContentType("application/stream");
	    response.setHeader("Cache-Control", "no-cache");
	    
	    ServletOutputStream outStream = response.getOutputStream();
	    outStream.write(new byte[0],0,0);
	    outStream.flush();
	    outStream.close();
	    HttpSession session = request.getSession();
	    Clients client = (Clients)session.getAttribute("c");
	    
	    Set<Sessions> users = (Set<Sessions>)getServletContext().getAttribute("cyberprime.users");
	    Iterator<Sessions> userIt = users.iterator();
	    Sessions newUser = new Sessions(sessionId,client.getUserId());
	    
	    boolean check = false;
	    
	    if(!users.isEmpty()){
		    while(userIt.hasNext()){
		    	
		    	Sessions user = (Sessions)userIt.next();
		    	
		    	if(user.getSessionId().equals(newUser.getSessionId()) && !user.getClientId().equals(client.getUserId())){
		    		check = true;
		    	}
		    	
		    	else{
		    		check = false;
		    		break;
		    	}
		    	
		    }
		    
		    if(check){
//	    		System.out.println("Requested user added");
	    		users.add(newUser);
		    }
		    
		    else{
//		    	System.out.println("User already in database");
		    }
	    }
	    
	    else{
//	    	System.out.println("User database is empty");
	    }

	    
	    NotificationsDAO.deleteNotification(n);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");

		String username = request.getParameter("username");
		HttpSession sess = request.getSession();
		Clients client = (Clients)sess.getAttribute("c");
		n = new Notifications(client.getUserId(),username,"AddUser");
	
		Sessions newUser = new Sessions(sess.getId(),client.getUserId());
	    // Check for online users before creating notifications
		Set<Sessions> sessions = (Set) getServletContext().getAttribute("cyberprime.sessions");
		Iterator<Sessions> sessionIt = sessions.iterator();
		boolean check = false;
		boolean check2 = false;
		while(sessionIt.hasNext()){
			Sessions sex = (Sessions) sessionIt.next();
			if (sex.getClientId().equalsIgnoreCase(username)){

				Set<Sessions> users = (Set)getServletContext().getAttribute("cyberprime.users");
				Iterator<Sessions> userIt = sessions.iterator();
				if(!users.isEmpty()){
					while(userIt.hasNext()){
						Sessions user = (Sessions) userIt.next();
				    	if(user.getSessionId().equals(newUser.getSessionId()) && user.getClientId().equals(newUser.getClientId())){
				    		check = true;
				    		break;
				    	}
						
						else if(!user.getSessionId().equals(newUser.getSessionId()) && !user.getClientId().equals(newUser.getClientId())){
							check2 = true;
							break;
						}

					}
					
					if(check2){
						return;
					}
					
					else if(check && !check2){
						NotificationsDAO.createNotification(n);
//						System.out.println("User is added");
					}
					
					else if(!check && !check2){
						sessionId = sess.getId();
//						System.out.println("Primary User added to user database with "+sessionId);
						Sessions userAdded = new Sessions(sessionId,client.getUserId());
						users.add(userAdded);
						NotificationsDAO.createNotification(n);
						
					}
					

				}
				
				else{
					sessionId = sess.getId();
					users.add(newUser);
//					System.out.println("user added");
					NotificationsDAO.createNotification(n);
					
				}

				return;
			}
			
			else{
				// give feedback
				
			}
			
		}

		
	}

}
