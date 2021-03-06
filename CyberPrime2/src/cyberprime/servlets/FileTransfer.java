package cyberprime.servlets;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import cyberprime.entities.Clients;
import cyberprime.entities.Files;
import cyberprime.entities.Notifications;
import cyberprime.entities.Sessions;
import cyberprime.entities.dao.NotificationsDAO;
import cyberprime.util.FileEncryption;

@WebServlet("/FileTransfer")
public class FileTransfer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 1024 * 1024 * 50; // 50mb size
	static File file;
	private String Id = null;
	private static final int BUFSIZE = 4096;
	public static Notifications n = null;

	Files files = new Files();

	public void init() {
		/*
		 * DOWNLOAD FROM C:\Users\Tan Wai Kit\Desktop\MAIN
		 * DESKTOP\workspace\.metadata\.plugins\org.eclipse.wst.server.core\
		 * tmp1\wtpwebapps\CyberPrime2
		 */
		filePath = getServletContext().getInitParameter("file-upload");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		java.io.PrintWriter out = response.getWriter();
		
		// Check that we have a file upload request
				isMultipart = ServletFileUpload.isMultipartContent(request);
				response.setContentType("text/html");

				if (!isMultipart) {
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Servlet upload</title>");
					out.println("</head>");
					out.println("<body>");
					out.println("<p><strong>Thank you for waiting</strong></p>");
					out.println("<p>No file uploaded</p>");
					out.println("</body>");
					out.println("</html>");
					return;
				}

				else {

					out.println("<html>");
					out.println("<head>");
					out.println("<title>Servlet upload</title>");
					out.println("<style>");
					out.println("body {width:775px; height:570px; background-color:grey; color:white}");
					out.println("</style>");
					out.println("</head>");
					out.println("<body>");
				}
				
		File repo = new File(filePath);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxFileSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(repo);
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);
		
		HttpSession session = request.getSession();
		Clients client = (Clients) session.getAttribute("c");
		
		try {
			if (repo != null) {
				RequestContext req = new ServletRequestContext(request);
				// Parse the request to get file items.
				List<FileItem> items = upload.parseRequest(req);
				// Process the uploaded file items
				Iterator<FileItem> iterator = items.iterator();
				Set sessions = (Set) getServletContext().getAttribute(
						"cyberprime.sessions");
				Iterator sessionIt = sessions.iterator();
				boolean check = false;
				while (iterator.hasNext()) {
					FileItem item = iterator.next();
					if (item.isFormField()) {
						String fieldName = item.getFieldName();

						if (fieldName.equalsIgnoreCase("Id"))
							Id = item.getString();
						
						
						if (Id.isEmpty()) {
							out.println("<p><strong>Please enter a username.</strong></p>");
							out.println("</body>");
							out.println("</html>");
							return;
						}
					}

					else {

						while (sessionIt.hasNext()) {
							Sessions sess = (Sessions) sessionIt.next();

							if (Id.equalsIgnoreCase(sess.getClientId())) {
								// Get the uploaded file parameters
								check = true;
								 n = new Notifications(
										client.getUserId(), sess.getClientId(),
										"FileTransfer");

								try {
									NotificationsDAO.createNotification(n);
									String fileName = item.getName();
									int i = fileName.lastIndexOf("\\");
									fileName = fileName.substring(i+1,fileName.length());
									file = new File(filePath + fileName);
									long sizeInBytes = item.getSize();
									String mimeType = item.getContentType();

										out.println("<p><strong>Thank You For Waiting</strong></p>");

										item.write(file);
										
										//encrypt
										file = new FileEncryption("AES", file.getAbsolutePath()).encrypt();
										System.out.println(file.getAbsolutePath());
										out.println("Uploaded Filename: "
												+ fileName + "<br>");
										out.println("<p>File Size: "  
												+ (sizeInBytes/(1000*1000)) + "mb " + "</p>");
									out.println("<b/><b/><b/><b/><b/><p><strong>Uploading in " +
												"progress, please do not redirect " +
												"your browser to another page.</strong></p>");
									out.println("</body>");
									out.println("</html>");
									
									files.setFileName(fileName);
									files.setFilePath(filePath + fileName);
									files.setLength((int)sizeInBytes);
									files.setMimeType(mimeType);
									
								} catch (Exception ex) {
									out.print("<p><strong>No file found, please try again.</strong></p>");
									out.println("</body>");
									out.println("</html>");
								}
								
								return;
							}

							else {
								check = false;

							}
						}
					}
				}
				
				if(check = false){
					out.println("<p><strong>Please put a a valid ID.</strong></p>");
					out.println("</body>");
					out.println("</html>");
				}
			}
		} catch (FileUploadException e) {
			
			int length = 0;
			length = request.getContentLength();
			if (length > maxFileSize) {

				out.println("<p><strong>Posted content length of " + (length/(1000 * 1000)) + "mb "
						+ " exceeds limit of " + (maxFileSize/(1000*1000)) + "mb " + " by "
						+ ((length - maxFileSize)/(1000*1000)) + "mb " + "</strong></p>" + "<br/>");
				out.println("<p>Please try again.</p>");
				System.out.println("length is " + ((length - maxFileSize)/(1000*1000)) + "mb "
						+ " bigger than " + (maxFileSize/(1000*1000)) + "mb ");
				
				return;
				
			} else {
				e.printStackTrace();
			}
		}
	}

	public Object notifications() {
		Object postNotifications = null;

		return postNotifications;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
	
		try{
		//decrypt 	
		file = new FileEncryption("AES", files.getFilePath() + ".enc").decrypt();
		int length = 0;
		ServletOutputStream outStream = response.getOutputStream();
		ServletContext context = getServletConfig().getServletContext();
		String mimetype = context.getMimeType(file.getAbsolutePath());

		// sets response content type
		if (mimetype == null) {
			mimetype = "application/octet-stream";
		}
		response.setContentType(mimetype);
		response.setContentLength((int) file.length());

		String fileName = (new File(file.getAbsolutePath())).getName();

		// sets HTTP header
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");

		byte[] byteBuffer = new byte[BUFSIZE];
		DataInputStream in = new DataInputStream(new FileInputStream(file));

		// reads the file's bytes and writes them to the response stream
		while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
			outStream.write(byteBuffer, 0, length);
		}

		in.close();
		outStream.flush();
		outStream.close();
		file.delete();
		
		System.out.println(n.getSender()+n.getContent()+n.getReceiver());
		NotificationsDAO.deleteNotification(n);
				
		} catch(IOException e){
			e.printStackTrace();
			System.out.println(e);
			System.out.println(files.getFilePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}