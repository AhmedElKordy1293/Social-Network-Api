import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import java.sql.Statement;
public class PublicPrivacy extends GroupPrivacy {

  public Integer importance;

  public void setImportance() {
  }

  public void getImportance() {
  }

@Override
public void getAllowedMembers(String privacy) throws Exception {
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/API", "root", "");
	
	PreparedStatement statment = con.prepareStatement("SELECT * FROM `user`");
	ResultSet result = statment.executeQuery();
	Group.allowedUsers=new Vector<String>();
	
	User user;
	while(result.next()){
		if(result.getString("type").equals("normal") || result.getString("type").equals("Normal")){
			Group.allowedUsers.add(result.getString("email"));
			
			
		}
		else if(result.getString("type").equals("premium") || result.getString("type").equals("Premium")){
			Group.allowedUsers.add(result.getString("email"));
	
		}	
	}
	
	for (int i = 0; i < Group.allowedUsers.size(); i++) {
		System.out.println(Group.allowedUsers.elementAt(i));
	}
	
	Group.myGroupModel.createGroup(Group.groupName,Group.privacy,Group.allowedUsers);
	
	
	String m = "SELECT `groupID` FROM `group` WHERE owner = '"+User.getCurrentActiveUser().email+"' and groupName = '"+Group.privacy+"';";
	PreparedStatement statment2 = con.prepareStatement(m);
	 
	ResultSet rs = statment2.executeQuery();
	int generatedKey = 0;
	if (rs.next()) {
	    generatedKey = (int) rs.getLong(1);
	}
	
	System.out.println(m);
	System.out.println("groupID = " + generatedKey);
	
	PreparedStatement statment3 = con.prepareStatement("INSERT INTO `groupmembers`(`groupID`, `memberEmail`, `role`) VALUES (" +generatedKey+ ",'" +User.getCurrentActiveUser().email+"','admin')");
														
	statment3.executeUpdate();
	
}
  
  
  

}