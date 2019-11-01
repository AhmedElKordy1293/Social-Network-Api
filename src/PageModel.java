import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PageModel {


  public static void createPage(String name, String type , User user) throws Exception {
	  
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/API", "root", "");
	  
	  PreparedStatement statment = con.prepareStatement("INSERT INTO `page`(`name`, `type`, `#OfLikes`, `adminEmail`) VALUES  ('" +name+ "','" +type+ "',0,'" +user.email+"')");
	  statment.executeUpdate();
	  
  }

  public static void deletePage(String pageName , User user) throws Exception {
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/API", "root", "");
	  
	  PreparedStatement statment = con.prepareStatement("DELETE FROM `page` WHERE adminEmail = '"+ user.email +"' and name = '" + pageName +"'");
	  
	  statment.executeUpdate();
	  
	  
  }

  public void getPage() {
  }

  public void updatePage() {
  }

}