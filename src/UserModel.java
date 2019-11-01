import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import com.mysql.jdbc.Connection;

public class UserModel {
	
	  public void CreateUser(User u) throws Exception {
		  
		  
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/API", "root", "");
		  
		  PreparedStatement statment = con.prepareStatement("select * from User");
		  
		  ResultSet result = statment.executeQuery();
		  
		  while(result.next()){
			  if(u.email .equals(result.getString(2)) ){
				  System.out.println("user already exist !");
				  return;
			  }
		 }
		  PreparedStatement statment1 = con.prepareStatement( "INSERT INTO `user`(`name`, `email`, `password`, `subscribedGroups`, `likedPages`, `friends` , `type`) VALUES ('" +u.name+ "','" +u.email+"','"+u.password+"',0,0,0,'"+u.type+"')") ;
		  statment1.executeUpdate();
	  }
	
	  public User getUser(String email, String password) throws Exception {
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/API", "root", "");
		  
		  PreparedStatement statment = con.prepareStatement("select * from User");
		  
		  ResultSet result = statment.executeQuery();
		  
		  while(result.next()){
			  if(email.equals(result.getString(2)) && password.equals(result.getString(3))){
				  
				if(result.getString(7).equals("Normal") || result.getString(7).equals("normal") ){
					User u = NormalUser.getInstance(result.getString(1),result.getString(7),email,password,result.getInt("likedPages"),result.getInt("friends"));
					return u;
				  }
				else if (result.getString(7).equals("Premium") || result.getString(7).equals("premium") ){
					User u = NormalUser.getInstance(result.getString(1),result.getString(7),email,password,result.getInt("likedPages"),result.getInt("friends"));
					return u;
				}
				else{
					System.out.println("Invalid type !!");
					return null;
				}

			  }
			  
		 }
		  System.out.println("Invalid email OR password !");
		  return null;
	  }
	
	  public void updateUser() {
		  
	  }
	  
	  public void DeleteUser() {
	  }
	 
	  

}