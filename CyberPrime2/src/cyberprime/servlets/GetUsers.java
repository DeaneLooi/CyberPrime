package cyberprime.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
import cyberprime.entities.dao.NotificationsDAO;

/**
 * Servlet implementation class GetUsers
 */
@WebServlet("/GetUsers")
public class GetUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");
	    //System.out.println("Getting value from virtual database users");
	    PrintWriter writer = response.getWriter();
	    String sessionId = "";
		HttpSession session = request.getSession();
		Clients client = (Clients)session.getAttribute("c");

		ArrayList<Sessions>getUsers = new ArrayList<Sessions>();
		Set<Sessions> users = (Set)getServletContext().getAttribute("cyberprime.users");
		Iterator<Sessions> userIt = users.iterator();
		String content = "";
		if(!users.isEmpty()){
			while(userIt.hasNext()){
				Sessions user = (Sessions)userIt.next();
				System.out.print("Client " +client.getUserId());
				System.out.println("User "+user.getClientId());
				if(user.getClientId().equals(client.getUserId())){
					System.out.println("Correct user");
					sessionId = user.getSessionId();
					
					userIt = users.iterator();
					
					while(userIt.hasNext()){
						Sessions user1 = (Sessions)userIt.next();
						
						if(user1.getSessionId().equals(sessionId)){
							System.out.println("Correct user "+user1.getClientId());
							getUsers.add(user1);
						}
					}

				}
				

			}
			

			for(int i=0; i<getUsers.size();i++){
				Sessions user = getUsers.get(i);
				content += "<p align:'center'>&nbsp;&nbsp;";
				content += user.getClientId();
				content += "</p>";
			}

			
		}
		
		else{
			System.out.println("User database is empty");
		}
		

		String html = "<div>";

		

		html += content;
		html += "</div>";
		
		writer.println(html);
		
		writer.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
