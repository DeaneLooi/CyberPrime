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
import cyberprime.entities.Sessions;

/**
 * Servlet implementation class RemoveUsers
 */
@WebServlet("/RemoveUsers")
public class RemoveUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");
	    
			HttpSession session = request.getSession();
			Clients client = (Clients) session.getAttribute("c");
			
			Set<Sessions> users = (Set) getServletContext().getAttribute("cyberprime.users");
			Iterator userIt = users.iterator();
			
			if(!users.isEmpty()){
				while(userIt.hasNext()){
					Sessions user = (Sessions) userIt.next();
					if(user.getClientId().equals(client.getUserId())){
						System.out.print(user.getClientId());
						System.out.println("User removed");
						userIt.remove();
						users.remove(user);
						
					}
					
					else{
						System.out.print(user.getClientId());
						System.out.println("Wrong user");
					}
				}
			}
			
			else{

				System.out.println("database empty");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");

		String username = request.getParameter("username");
		HttpSession sess = request.getSession();
	
		Set<Sessions> sessions = (Set) getServletContext().getAttribute("cyberprime.sessions");
		Iterator<Sessions> sessionIt = sessions.iterator();
		
		while(sessionIt.hasNext()){
			Sessions sex = (Sessions) sessionIt.next();
			if (sex.getClientId().equalsIgnoreCase(username)){
				
				Set<Sessions> users = (Set)getServletContext().getAttribute("cyberprime.users");
				Iterator<Sessions> userIt = sessions.iterator();
				if(!users.isEmpty()){
					while(userIt.hasNext()){
						Sessions user = (Sessions) userIt.next();
						if(user.getClientId().equals(username)){		
							users.remove(user);
							userIt.remove();
							System.out.println("User removed");
						}
						
						else{
							System.out.println("Wrong user");
						}
					}
				}
				
				else{

					System.out.println("database empty");
				}

				return;
			}
			
			else{
				// give feedback
				
			}
			
		}
		
	}

}
