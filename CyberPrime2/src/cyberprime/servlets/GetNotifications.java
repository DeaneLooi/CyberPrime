package cyberprime.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cyberprime.entities.Clients;
import cyberprime.entities.Notifications;
import cyberprime.entities.dao.NotificationsDAO;

/**
 * Servlet implementation class GetNotifications
 */
@WebServlet("/GetNotifications")
public class GetNotifications extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNotifications() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Clients client = (Clients) session.getAttribute("c");
		
		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");

	     PrintWriter writer = response.getWriter();
	   
	    

	   ArrayList<Notifications> displayNotifications = null;
	   ArrayList<Notifications> displaySenderNotifications = null;

	    try {

	          displayNotifications =  NotificationsDAO.searchNotifications(client.getUserId());
	          displaySenderNotifications = NotificationsDAO.searchNotificationsSender(client.getUserId());
	     

	    }catch(Exception e)
	    { 
	    	System.out.println("Could not read from database");
	    }


	    String html="";

	    //  html="<Table border=1>";

	    if(displayNotifications.size()==0 && displaySenderNotifications.size()==0){
	    	html+="<p>There is currently no notifications</p>";
	    }
	    
	    else{
		     for(int i=0; i<displayNotifications.size();i++){
		    	 
		    	 Notifications n = displayNotifications.get(i);
		    	 String content = "";
		    	 if(n.getContent().equalsIgnoreCase("FileTransfer")){
		    		 content = n.getSender()+" has requested to send you a file";
		    	 }
		    	 
		    	 else if(n.getContent().equalsIgnoreCase("AddUser")){
		    		 content = n.getSender()+" has requested you to join his/her room";
		    	 }
		    	 html+="<div style='border-style:solid;border-color:black;border-width:1px;padding:5px'>";
		    	 html+=content+"<br>";
		    	 html+="<button>Accept</button><button>Reject</button>";
		    	 html+="</div>";
		     }
		     
		     for(int i=0; i<displaySenderNotifications.size();i++){
		    	 
		    	 Notifications n = displaySenderNotifications.get(i);
		    	 String content = "";
		    	 if(n.getContent().equalsIgnoreCase("FileTransfer")){
		    		 content = "You have requested to send a file to "+n.getReceiver();
		    	 }
		    	 
		    	 else if(n.getContent().equalsIgnoreCase("AddUser")){
		    		 content = "You have requested to add "+n.getReceiver()+" to join your room";
		    	 }
		    	 html+="<div style='border-style:solid;border-color:gray;border-width:1px;padding:5px'>";
		    	 html+=content;
		    	 html+="</div>";
		     }
	    }


	                              //  html=html+"</Table>";

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
