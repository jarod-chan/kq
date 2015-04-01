package cn.fyg.kq.interfaces.web.shared.message;

public class Message {
	
	private Message(){}
	
	public static Message create(){
		return new Message();
	}
	
	private Level level;
	private String message;
	
	
	public Message info() {
		this.level = Level.info;
		return this;
	}
	
	public Message Error() {
		this.level = Level.error;
		return this;
	}
	
	public Message message(String message,Object...args) {
		this.message = String.format(message, args);
		return this;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public String getMessage() {
		return message;
	}
	
}
