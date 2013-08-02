package cyberprime.entities;

import java.sql.Timestamp;
import java.util.Date;

public class ChatMessages implements Comparable<ChatMessages>{
	
	private String sessionId;
	private String clientId;
	private String message;
	private long currentTime;
	
	public ChatMessages(String sessionId, String clientId, String message,long currentTime){
		super();
		this.sessionId = sessionId;
		this.clientId = clientId;
		this.message = message;
		this.currentTime = currentTime;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getCurrentTime(){
		return currentTime;
	}

	@Override
	public int compareTo(ChatMessages cm) {
		int i = 0;
		if(this.getCurrentTime() < cm.getCurrentTime()){
			i = -1;
		}
		
		else if(this.getCurrentTime() == cm.getCurrentTime()){
			i = 0;
		}

		else if(this.getCurrentTime() > cm.getCurrentTime()){
			i = 1;
		}
		
		return i;
	}
	

}
