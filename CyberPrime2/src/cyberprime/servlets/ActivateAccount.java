package cyberprime.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cyberprime.entities.Clients;
import cyberprime.entities.dao.ClientsDAO;
import cyberprime.util.Algorithms;

/**
 * Servlet implementation class ActivateAccount
 */

@WebServlet("/ActivateAccount")
public class ActivateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**r4ew
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		Clients client = new Clients();
		client.setUserId(userId);
		String tokenc = ClientsDAO.retrieveToken(client);
			try {
				tokenc = Algorithms.decrypt(tokenc,userId.substring(0,16));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		

		
		if(tokenc.equalsIgnoreCase("null")){
			Object obj = new Object();
			obj = "<p style='color:lime'>*Your account has already been activated!</p>";
			request.setAttribute("regResult", obj);
			request.getRequestDispatcher("templateLogin.jsp").forward(request, response);
		}
		
		else{
			
			if(token.equals(tokenc)){
				client.setActivation("Active");
				ClientsDAO.activateClients(client);
				Object obj = new Object();
				obj = "<p style='color:lime'>*Your account has been activated! Please log in</p>";
				request.setAttribute("regResult", obj);
				request.getRequestDispatcher("templateLogin.jsp").forward(request, response);
			}
			
			else {
				Object obj = new Object();
				obj = "<p style='color:lime'>*Your account has not been activated! Please request for another activation link</p>";
				request.setAttribute("regResult", obj);
				request.getRequestDispatcher("templateLogin.jsp").forward(request, response);
			}
			
		}

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
