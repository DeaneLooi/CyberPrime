package cyberprime.servlets;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cyberprime.entities.Clients;
import cyberprime.entities.Sessions;

/**
 * Application Lifecycle Listener implementation class HttpServletListener
 *
 */
@WebListener
public class HttpServletListener extends HttpServlet implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public HttpServletListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
       System.out.println("Session created");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
    	System.out.println("Session deleted");
    	HttpSession session = arg0.getSession();
    	System.out.println(session.getId());
		Set sessions = (Set) session.getServletContext().getAttribute("cyberprime.sessions");
		Iterator sessionIt = sessions.iterator();
				while(sessionIt.hasNext()) {
				Sessions sess = (Sessions)sessionIt.next();

				if(sess.getSessionId().equals(session.getId())){
					System.out.println("Listener Client id ="+sess.getClientId());
					sessionIt.remove();
					sessions.remove(sess);
				}
				

				}
				
    	}
}
    
