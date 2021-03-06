package cyberprime.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import cyberprime.entities.Clients;
import cyberprime.entities.dao.ClientsDAO;
import cyberprime.util.Constants;
import cyberprime.util.EmailValidator;
import cyberprime.util.ImageEncryption;
import cyberprime.util.ImageValidator;
import cyberprime.util.RandomString;



/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
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
		HttpSession session = request.getSession();
		String repos = Constants.SAMUEL_PATH;
		Clients client = new Clients();
		ImageEncryption en = null;
		File repo = new File(repos);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String saveFileName = "";
		String newFileName = "";
		boolean renameFile;
			try{
			factory.setSizeThreshold(2*1024*1024);
			factory.setRepository(repo);
	//		if(repo != null)
	//		System.out.println("Repository :"+repo.getAbsolutePath());
			ServletFileUpload upload = new ServletFileUpload(factory);
			RequestContext req = new ServletRequestContext(request);
			List<FileItem> items = upload.parseRequest(req);
			Iterator<FileItem> iterator =  items.iterator();
			
			while(iterator.hasNext()){
			FileItem item = iterator.next();
				if(item.isFormField()){
					String fieldName = item.getFieldName();
					if(fieldName.equalsIgnoreCase("email")){
						client.setEmail(item.getString());
					}
					
					else if(fieldName.equalsIgnoreCase("captcha")){
						String captcha = item.getString();
						if(captcha.equalsIgnoreCase("false")){
							Object obj = new Object();
							obj = "<p style='color:red'>*Please click on submit</p>";
							request.setAttribute("regResult", obj);
							request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
							return;
						}
						
						else{
							continue;
						}
					}

					EmailValidator ev = new EmailValidator();
					if(!ev.validate(client.getEmail())){
						Object obj = new Object();
						obj = "<p style='color:red'>*Invalid email address</p>";
						request.setAttribute("regResult", obj);
						request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
						return;
					}
					
					else{
						System.out.println("Email validated");
					}
					

				}
				
				else{
					String fileName = item.getName();
					
					
					if(!fileName.isEmpty()){
						

						ImageValidator iv = new ImageValidator();
						int i=fileName.lastIndexOf("\\");
						fileName=fileName.substring(i+1,fileName.length());
						saveFileName = repos + fileName;
						File uploadedFile = new File(saveFileName);
						
						System.out.println("fileName = "+fileName);
						
						
						if(iv.validate(fileName)){
							try{
								item.write(uploadedFile);
								newFileName = session.getId()+fileName.substring(fileName.length()-4); 
								if(uploadedFile.renameTo(new File(repos+newFileName))){
									System.out.println("File has been renamed");
									renameFile = true;
								}
								
								else{
									System.out.println("File rename failed");
									renameFile = false;

								}
								
							}catch(Exception e){
								Object obj = new Object();
								obj = "<p style='color:red'>*Access denied</p>";
								request.setAttribute("regResult", obj);
								request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
								return;
							}

							if(renameFile)
								en = new ImageEncryption(repos+newFileName);

							else
								en = new ImageEncryption(uploadedFile.getAbsolutePath());
							
							client.setImageHash(en.getHash());
							client.setImageSize(en.getSize());
							client.setImageExtension(en.getExtension());
							session.setAttribute("image",newFileName);

						}
						else{
							Object obj = new Object();
							obj = "<p style='color:red'>*File Name not validated</p>";
							request.setAttribute("regResult", obj);
							request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
							return;
						}


					}

					else{
						Object obj = new Object();
						obj = "<p style='color:red'>*Please choose a file</p>";
						request.setAttribute("regResult", obj);
						request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
						return;
					}


				}
			}
				response.reset();
				response.setHeader("Content-Disposition", "inline");
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Expires", "0");
				response.setContentType("image/jpg");
				
			}catch(FileUploadException e){
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				System.gc();
			}
				try{
					Clients c = null;
					c = ClientsDAO.retrieveClient(client);
					if(c.getImageHash()==null){
						String userId = RandomString.generateRandomString();
						client.setUserId(userId);
						boolean check = ClientsDAO.checkUser(client);
						boolean checkEmail = ClientsDAO.checkEmail(client);
						if(!check && !checkEmail){
								session.setAttribute("client", client);
								request.getRequestDispatcher("patternRegister.jsp").forward(request, response);	
	
						}
						
						else if(check && !checkEmail){
							Object obj = new Object();
							obj = "<p style='color:red'>*Registration failed as there is a similar userId.. Please register again</p>";
							request.setAttribute("regResult", obj);
							request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
						}
						
						else if(!check && checkEmail){
							Object obj = new Object();
							obj = "<p style='color:red'>*The email entered has registered with us</p>";
							request.setAttribute("regResult", obj);
							request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
						}
						
						else if(check && checkEmail){
							Object obj = new Object();
							obj = "<p style='color:red'>*The email entered and userId has registered with us</p>";
							request.setAttribute("regResult", obj);
							request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
						}
		

					}
					
					else{
						Object obj = new Object();
						obj = "<p style='color:red'>*Registration Failed as there is a similar image in our database</p>";
						request.setAttribute("regResult", obj);
						request.getRequestDispatcher("templateRegister.jsp").forward(request, response);
					}	
					
					}catch(Exception e){
					e.printStackTrace();
				}
	}

}
