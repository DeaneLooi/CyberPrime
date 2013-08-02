package cyberprime.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ha.ClusterMessage;

import cyberprime.entities.ChatMessages;
import cyberprime.entities.Clients;
import cyberprime.entities.Sessions;
import cyberprime.util.Algorithms;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/Chat")
public class Chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sessionId = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");
	    System.out.println("Getting value from virtual database");
	    PrintWriter writer = response.getWriter();
	     
		HttpSession session = request.getSession();
		Clients client = (Clients)session.getAttribute("c");
		
		Set<Sessions> users = (Set)getServletContext().getAttribute("cyberprime.users");
		Iterator<Sessions> userIt = users.iterator();
		
		Set<ChatMessages> msg = (Set)getServletContext().getAttribute("cyberprime.msg");
		ArrayList<ChatMessages> messages = new ArrayList<ChatMessages>();

		while(userIt.hasNext()){
			
			Sessions user = (Sessions)userIt.next();
			if(user.getClientId().equals(client.getUserId())){
				sessionId = user.getSessionId();
				
			}
			
			else{
				
			}
			
			
		}
		
		Iterator<ChatMessages> msgIt = msg.iterator();
		
		while(msgIt.hasNext()){
			ChatMessages message = (ChatMessages)msgIt.next();
			if(message.getSessionId().equals(sessionId)){
				messages.add(message);
			}
		}
		

		String html = "<div>";
		String content = "";
		Collections.sort(messages);
		for(int i=0; i<messages.size();i++){
			ChatMessages message = messages.get(i);
			String decryptedMessage = "";
			try {
				decryptedMessage = Algorithms.decrypt(message.getMessage(), sessionId.substring(0,16));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			content += "<p>"+message.getClientId(); 
			content += "      :"+"    "+decryptedMessage;
			content += "</p>";
			
		}
		
		html+= content;
		html+="</div>";
		
		writer.println(html);
		
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String message = (String)request.getParameter("msg");
			HttpSession session = request.getSession();
			Clients client = (Clients)session.getAttribute("c");
			
			System.out.println(message);
			Set<Sessions> users = (Set)getServletContext().getAttribute("cyberprime.users");
			Iterator<Sessions> userIt = users.iterator();
			
			Set<ChatMessages> msg = (Set)getServletContext().getAttribute("cyberprime.msg");
			
			while(userIt.hasNext()){
				
				Sessions user = (Sessions)userIt.next();
				if(user.getClientId().equals(client.getUserId())){
					String sessionId = user.getSessionId();
					String encryptedMessage = "";
					try {
						encryptedMessage = Algorithms.encrypt(message,sessionId.substring(0,16));
						System.out.println(encryptedMessage);
					} catch (Exception e) {
						e.printStackTrace();
					}
					ChatMessages chatMsg = new ChatMessages(user.getSessionId(),client.getUserId(),encryptedMessage,new Date().getTime());
					msg.add(chatMsg);
				}
				
				else{
					System.out.println("User not in database");
				}
				
			}
			
		
	}

}
