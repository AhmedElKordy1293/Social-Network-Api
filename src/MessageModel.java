import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MessageModel {

  public void createMessage(String msg , String reciever) throws Exception {

	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/api", "root", "");
	  
	  PreparedStatement statment = con.prepareStatement("select * from user");
	  
	  ResultSet result = statment.executeQuery();
	  //check if the receiver email is exist or not
	  boolean flag = true;
	  while(result.next()){
		  if(reciever.equals(result.getString("email")) ){
			  PreparedStatement statment1 = con.prepareStatement( "INSERT INTO `message`(`sender`, `reciever`, `msg`, `status`) VALUES ('"+User.getCurrentActiveUser().email+"','"+reciever+"','"+msg+ "','" + "not seen"+"');");
			  statment1.executeUpdate();
			  flag = true;
			  System.out.println("message is sent to " + reciever);
			  break;
		  }
		  else{
			  flag = false;
		  }
	 }
	 if(!flag){
		 System.out.println("reciever isn't exists in our social network!");
	 } 
	  
	  
  }
  
  public void createGroupMessage(String msg , String reciever, String name) throws Exception {

	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/api", "root", "");
	  
	  PreparedStatement statment = con.prepareStatement("select * from user");
	  
	  ResultSet result = statment.executeQuery();
	  //check if the receiver email is exist or not
	  boolean flag = true;
	  while(result.next()){
		  if(reciever.equals(result.getString("email")) ){
			  PreparedStatement statment1 = con.prepareStatement( "INSERT INTO `groupchat`(`name`,`sender`, `reciever`, `msg`, `status`) VALUES ('"+name+"','"+User.getCurrentActiveUser().email+"','"+reciever+"','"+msg+ "','" + "not seen"+"');");
			  statment1.executeUpdate();
			  flag = true;
			  System.out.println("message is sent to " + reciever);
			  break;
		  }
		  else{
			  flag = false;
		  }
	 }
	 if(!flag){
		 System.out.println("reciever isn't exists in our social network!");
	 } 
	  
	  
  }
  

  public void deleteMessage() throws Exception {
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/api", "root", "");
	  String rec;
	  System.out.println("what's your reciever mail you want to clear msgs between you ");
	  Scanner in = new Scanner(System.in);
	  rec = in.next();
	  	PreparedStatement statement6 = con.prepareStatement("DELETE FROM `message` WHERE reciever = ? and sender = ?");
		statement6.setString(1, rec );
		statement6.setString(2, User.getCurrentActiveUser().email );
		statement6.executeUpdate();
		System.out.println("deleted..");
  }
  
  public void deleteGroupMessage() throws Exception {
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/api", "root", "");
	  String convname;
	  System.out.println("what's the conversation name you wanna clear msgs ");
	  Scanner in = new Scanner(System.in);
	  convname = in.next();
	  	PreparedStatement statement6 = con.prepareStatement("DELETE FROM `groupchat` WHERE name = ?");
		statement6.setString(1, convname );
		statement6.executeUpdate();
		System.out.println("deleted..");
  }

  public void getMessage()throws Exception {
	  
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/api", "root", "");
	  
	  PreparedStatement statment = con.prepareStatement("select * from message");
	  
	  ResultSet result = statment.executeQuery();
	  
	  String recievedMsg;
	  String sender;
	  while(result.next()){
		  if(User.getCurrentActiveUser().email.equals(result.getString("reciever"))){
			  recievedMsg = result.getString("msg");
			  sender = result.getString("sender");
			  System.out.println("you have new message From : " + sender + " : \n" + recievedMsg);
			  
			  System.out.println("considered as seen message ? (y/n)");
			  String choice;
			  Scanner in = new Scanner(System.in);
			  choice = in.next();
			  if(choice.equals("y") || choice.equals("Y")){
				  PreparedStatement statment1 = con.prepareStatement("UPDATE message SET status = ? WHERE msg = ? and reciever = ?");
				  statment1.setString(1, "seen");
				  statment1.setString(2,recievedMsg);
				  statment1.setString(3,User.getCurrentActiveUser().email);
				  statment1.executeUpdate();
			  }
			  
		  }
	  }
	  
  }
  
public void getGroupMessage()throws Exception {
	  
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/api", "root", "");
	  
	  PreparedStatement statment = con.prepareStatement("select * from groupchat");
	  
	  ResultSet result = statment.executeQuery();
	  
	  String recievedMsg;
	  String sender;
	  String convName;
	  while(result.next()){
		  if(User.getCurrentActiveUser().email.equals(result.getString("reciever"))  ){
			  recievedMsg = result.getString("msg");
			  sender = result.getString("sender");
			  convName = result.getString("name");
			  System.out.println("you have new message From : " + sender +"From Group msg "+ convName +  " : \n" + recievedMsg);
			  
			  System.out.println("considered as seen message ? (y/n)");
			  String choice;
			  Scanner in = new Scanner(System.in);
			  choice = in.next();
			  if(choice.equals("y") || choice.equals("Y")){
				  PreparedStatement statment1 = con.prepareStatement("UPDATE groupchat SET status = ? WHERE msg = ? and reciever = ? and name = ? ");
				  statment1.setString(1, "seen");
				  statment1.setString(2,recievedMsg);
				  statment1.setString(3,User.getCurrentActiveUser().email);
				  statment1.setString(4,convName);
				  statment1.executeUpdate();
			  }
			  
		  }
	  }
	  
  }



	public void updateMessage() {
		  
	}  

}
