import java.awt.List;
import java.awt.Desktop.Action;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class User {

  public ArrayList<IGroup> subscribedGroups;

  public static String name;

  public static String type;
  
  public static String email;

  public static String password;

  public static String recieved;
  
  public static int friendsCount,likedPages;
  
  public static User user;

  public static Scanner sc = new Scanner(System.in);
  
  public static UserModel userModel = new UserModel();
  
  public static MessageModel msgModel = new MessageModel();
  
  public static Message message;

  public static Message chatmsg = new ChatMessage();
  
  public static Message groupmsg = new GroupChatMessage();


  protected void User() {
	  
  }
  
  public static void setData(String n, String e, String p, String t){
	  name = n;
	  email = e;
	  password = p;
	  type = t;
  }
  
  public abstract void sendFriendRequest(String email) throws Exception;

  public abstract void addFriend() throws Exception;

  
  public static void signUp(String name,String type,String email,String password) throws Exception {
	  
	  if(type.equals("Normal") || type.equals("normal") ){
			User u = NormalUser.makeInstance(name,type,email,password);
			userModel.CreateUser(u);
		
	  }
	  else if (type .equals("Premium") || type .equals("premium") ){
		  User u = NormalUser.makeInstance(name,type,email,password);
		  userModel.CreateUser(u);
	  }
	  else System.out.println("Invalid type !!");
	  
  }

  public static User getCurrentActiveUser() {
	  if(user == null){
		  return null;
	  }
	  return user;
  }
  

  public static void login(String email, String password) throws Exception {
	  if (getCurrentActiveUser() == null){
		  	user = userModel.getUser(email,password);
		  	if(user != null){
		  	System.out.println("log in success");
	  
		  	}
	  }
	  else{
		  System.out.println("there is an active user");
	  } 
  }

  public static void logout(){
	   user = null;
	   System.out.println("U are logged out");
  }

}