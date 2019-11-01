import java.util.Scanner;

public class ChatMessage extends Message {
	public static MessageModel msgModel = new MessageModel();
  public String receiver;
  public Scanner in = new Scanner(System.in);

  @Override
  public void addReceiver() throws Exception {
	  System.out.println("please,Enter UR reciever email");										
	  receiver = in.nextLine();  
	 // System.out.println("message + receiver = " + message + receiver);
	  msgModel.createMessage(message,receiver);//message variable in the parent class the Message
    }

}