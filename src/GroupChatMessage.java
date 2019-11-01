import java.util.ArrayList;
import java.util.Scanner;

public class GroupChatMessage extends Message {

  public static ArrayList<String> UsersEmail = new ArrayList<String>();
  String reciever;
  Scanner in = new Scanner(System.in);
  int numOfRecievers;
  public static String title;

  @Override
  public void addReceiver() throws Exception {
	  System.out.println("Enter num of recievers to propagate your msg : ");
	  numOfRecievers = in.nextInt();
	  System.out.println("Enter the recievers Emails");
	  for (int i = 0; i < numOfRecievers; i++) {
		  reciever = in.next();
		UsersEmail.add(reciever);
	}
	  System.out.println("Enter the conversation name");
	  Scanner in = new Scanner(System.in);
	  
	  title = in.next();
	  
	  for (int i = 0; i < UsersEmail.size(); i++) {
		  String rec;
		  rec = UsersEmail.get(i);
		  // System.out.println("message + receiver = " + message + receiver);
		  msgModel.createGroupMessage(message,rec,title);//message variable in the parent class the Message
	}
	 
  }

}