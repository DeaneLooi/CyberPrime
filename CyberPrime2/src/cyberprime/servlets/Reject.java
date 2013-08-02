package cyberprime.servlets;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cyberprime.entities.dao.NotificationsDAO;

/**
 * Servlet implementation class Reject
 */
@WebServlet("/Reject")
public class Reject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reject() {
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
	    
	    String content = (String)request.getParameter("content");
	    
	    if(content.equalsIgnoreCase("AddUser")){
			System.out.println(AddUsers.n.getSender()+AddUsers.n.getContent()+AddUsers.n.getReceiver());
			NotificationsDAO.deleteNotification(AddUsers.n);
	    }
	    
	    else if(content.equalsIgnoreCase("FileTransfer")){
			System.out.println(FileTransfer.n.getSender()+FileTransfer.n.getContent()+FileTransfer.n.getReceiver());
			FileTransfer.file.delete();
			NotificationsDAO.deleteNotification(FileTransfer.n);
	
	    }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
