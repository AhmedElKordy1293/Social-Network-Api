import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PremiumUser extends User {

  public String creditCardNumber;

  public static void payByCredit() {
  }

  
public static PremiumUser makeInstance(String name,String type,String email,String password){
	  
	  PremiumUser n = new PremiumUser() ;
	  n.email = email;
	  n.name = name;
	  n.password = password;
	  n.type = type;
	  
	  return n;
  }

  public static PremiumUser getInstance(String name,String type,String email,String password,int likedpages,int friendsCount){
	  
	  PremiumUser n = new PremiumUser() ;
	  n.email = email;
	  n.name = name;
	  n.password = password;
	  n.type = type;
	  n.likedPages = likedpages;
	  n.friendsCount = friendsCount;
	  return n;
  }

  
  
@Override
public void sendFriendRequest(String email) throws Exception {
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/API", "root", "");
	  
	  PreparedStatement statment = con.prepareStatement("select * from User");
	  
	  ResultSet result = statment.executeQuery();
	  boolean flag = false;
	  while(result.next()){
		  
		  if(email.equals(result.getString(2))){
			  flag = true;
			  break;
		  }
		  
		  else{
			  flag = false;
		  }
	 }
	  if(flag){
		  PreparedStatement statment2 = con.prepareStatement("select * from friends");
		  
		  ResultSet result2 = statment2.executeQuery();
		  while(result2.next()){
			  System.out.println("ana hena ");
			  if( (email.equals(result2.getString(1) ) && getCurrentActiveUser().email.equals(result2.getString(2))) || (getCurrentActiveUser().email.equals(result2.getString(1) ) && email.equals(result2.getString(2)))){
				  System.out.println("you have already send request !");
				  return;
			  }
		  }
	    	  
		  PreparedStatement statment1 = con.prepareStatement( "INSERT INTO `friends`(`sender`, `reciever`, `status`) VALUES ( '"+getCurrentActiveUser().email+"' , '"+email+"' , '"+ "binding" + "' ) " ) ;
		  statment1.executeUpdate();
	  }
	  else{
		  System.out.println("Invalid account !");
	  }
}

@Override
public void addFriend() throws Exception{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/API", "root", "");
	  
	PreparedStatement statment = con.prepareStatement("select * from friends");
	  
	ResultSet result = statment.executeQuery();
	int choise = 0;
	Scanner sc = new Scanner(System.in);
	while (result.next()){
		
		if(getCurrentActiveUser().email.equals(result.getString(2))){
			System.out.println("Sender -->"+result.getString(1));
			System.out.println("1- Confirm\t2- Not Now\t3- Ignore.");
			choise = sc.nextInt();
			switch(choise){
				case 1:
					if((result.getString(3).equals("binding"))){
						
						PreparedStatement statment2 = con.prepareStatement("UPDATE `user` SET `friends` =?  WHERE name =? ");
						System.out.println("(getCurrentActiveUser().friendsCount) = "+(getCurrentActiveUser().friendsCount));
						statment2.setLong( 1, ((getCurrentActiveUser().friendsCount) + 1));
						statment2.setString( 2, getCurrentActiveUser().name);
						statment2.executeUpdate();
						PreparedStatement statment3 = con.prepareStatement("SELECT * FROM user");
						ResultSet result3 = statment3.executeQuery();
						int friendsSender = 0;
						while(result3.next()){
							if(result3.getString(1).equals(result.getString(1))){
								friendsSender = result3.getInt(6);
								break;
							}
						}
						PreparedStatement statment4 = con.prepareStatement("UPDATE `user` SET `friends` = ? WHERE email =?");
						statment4.setLong( 1, (friendsSender+1));
						statment4.setString( 2, result.getString(1));
						statment4.executeUpdate();
						
						PreparedStatement statment5 = con.prepareStatement("UPDATE `friends` SET `status` = ?  WHERE sender =?" );
						statment5.setString( 1, "active");
						statment5.setString( 2, result.getString(1) );
						statment5.executeUpdate();
						break;
					}
				
				case 2:
					System.out.println("Not Now!");
					break;
				case 3:
					PreparedStatement statement6 = con.prepareStatement("DELETE FROM `friends` WHERE reciever = ?");
					statement6.setString(1, getCurrentActiveUser().email);
					statement6.executeUpdate();
					System.out.println("Ignored!");
					break;
					
			}
		}
	}

}

}