package cyberprime.entities;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import cyberprime.entities.dao.ClientsDAO;
import cyberprime.util.EmailSender;


public class ChangePattern {

	private ArrayList<String> pattern = new ArrayList<String>();
	private String newPattern = "";
	private String content;
	
	public ChangePattern() {
		getNewPattern();
	}
	
	public String getChangedPattern(){
		return newPattern;
	}
	
	public String getContent(){
		return content;
	}
	
	public String getNewPattern(){
		content = "";
		ArrayList<String> patterns = new ArrayList<String>();

		for(int i=0; i<8;i++){
			
			for(int j=0; j<6;j++){
				
				String str = Integer.toString(i)+j;
				patterns.add(str);
			}
		}
		
		Random r = new Random();
		int []randomInt = new int[3];
		randomInt[0]		=  r.nextInt(48);
		randomInt[1]		=  r.nextInt(48);
		randomInt[2]		=  r.nextInt(48);
		
		
		for(int i=0; i<3;i++){
			int index = randomInt[i];
			String str = patterns.get(index);
			pattern.add(str);
		}
		
		for(int i=0; i<pattern.size();i++){
			newPattern += pattern.get(i);
		}
		
		for(int i=0; i<pattern.size();i++){
			int index = i+1;
			content += "Your new pattern for your image is position "+index+" :"+pattern.get(i)+"<br/>";
		}
		
		content+="<br/>";
		for(int j=0; j<6; j++){
			
			
			for(int i=0; i<8;i++){
				String id = i + Integer.toString(j);
				if(id.equals(pattern.get(0)) || id.equals(pattern.get(1)) || id.equals(pattern.get(2))){
					content+= "<b>"+id+"</b>";
				}
				
				else
					content+= id+" ";
				
			}
			
			content+="<br/>";
			
		}
		

		return content;
	}
	
	
	public static void main(String[]args){
		
/*		Rectangle rect1=new Rectangle();
		double area;
		
		rect1.setLength(2);
		rect1.setWidth(5);
		area=rect1.findArea();
		
		System.out.print(area);*/
		
		Clients c = new Clients("PAUL","imageHash",100,"jpg","email","pattern");
		c.setActivation("Pending");
		c.setToken();
		//ClientsDAO.registerClient(c);
		ChangePattern cp = new ChangePattern();
		System.out.println(cp.getChangedPattern()+"\n"+cp.getContent());
		try {
			c.setPattern(cp.getChangedPattern());
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String oldPattern = ClientsDAO.changePattern(c);
		System.out.println(oldPattern);
		System.out.println(c.getPattern());
		c.setDBPattern(oldPattern);
		ClientsDAO.changePattern(c);
		System.out.println(c.getPattern());
				
	
	}

}
