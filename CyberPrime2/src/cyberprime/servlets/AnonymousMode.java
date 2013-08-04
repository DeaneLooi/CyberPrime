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
import cyberprime.util.FileMethods;

/**
 * Servlet implementation class AnonymousMode
 */
@WebServlet("/AnonymousMode")
public class AnonymousMode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		System.out.println("AnonymousMode servlet called.");
		HttpSession existingHttpSession = request.getSession();
		Clients existingClient = (Clients)existingHttpSession.getAttribute("client");
		String anonymousMode=request.getParameter("anonSwitch");
		System.out.println("anonymousMode = "+anonymousMode);
//		if (){
//			Sessions existingSessions = new Sessions(existingHttpSession.getId(), existingClient.getUserId());
//			Set sessionArray = (Set) getServletContext().getAttribute("cyberprime.sessions");
//			Iterator sessionIt = sessionArray.iterator();
//					while(sessionIt.hasNext()) {
//					Sessions sess = (Sessions)sessionIt.next();
//					System.out.println("Client id ="+sess.getClientId());
//					if(sess.getClientId().equals(existingClient.getUserId())){
//						Object obj = new Object();
//						obj = "<p style='color:red'>*Your account is already logged in</p>";
//						request.setAttribute("loginResult", obj);
//						FileMethods.fileDelete(image);
//						request.getRequestDispatcher("templateLogin.jsp").forward(request, response);
//						return;
//					}				
//					}
//		}
	}

}
