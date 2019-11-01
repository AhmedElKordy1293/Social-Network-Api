import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Vector;

public class GroupModel {

    public Group myGroup;
    

  public String convert(Vector<String>allow){
	  String res = "{";
	  for (int i = 0; i < allow.size(); i++) {
		  //System.out.println("allow.get(i).email = "+allow.get(i).email);
		  if(i < allow.size()-1){
			  res += i + ":" + allow.get(i) + ",";
		  }
		  else res += i + ":" + allow.get(i) ;
 	}
	  
	  res += "}" ;
	  return res;
  }
  
  public void createGroup(String groupName, String groupPrivacy, Vector<String>allowedUsers) throws Exception {
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/API", "root", "");
	  String s = "INSERT INTO `group`(`allowedmembers` , `groupName`, `groupPrivacy`, `#OfAllowed`, `owner` ) VALUES "
	  													+ "('"+convert(allowedUsers)+"','"+groupPrivacy+"','"+groupName+"','"+ allowedUsers.size() +"','"+ User.getCurrentActiveUser().email +"');";
	  PreparedStatement statment = con.prepareStatement(s,Statement.RETURN_GENERATED_KEYS);
	  statment.executeUpdate();
	 
  }

  public void updateGroup() {
  }

  public void getGroup() {
  }

  public void deleteGroup() {
  }

}