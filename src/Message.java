import java.util.Vector;

public abstract class Message {
	
	public static MessageModel msgModel = new MessageModel();
	public static String message;
	
	public static void sendMessage(String msg) throws Exception{
		//System.out.println(msg + rec);
		message = msg;
	  //msgModel.createMessage(msg,rec);
	}

	public abstract void addReceiver() throws Exception;

}