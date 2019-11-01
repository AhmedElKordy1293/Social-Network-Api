import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Group extends IGroup {

  public String title;

  public String coverPicture;

  public Map<User,String>users;

  public static String privacy,groupName;

  public static Vector<String> allowedUsers;
  public static GroupModel myGroupModel = new GroupModel();
    
  public static void setPrivacy(String p,String n){
	  privacy = p;
	  groupName = n;
  }
  
  public static void createGroup(String groupName, String privacy, User user) throws Exception{

	 
	  if(privacy.equals("public") || privacy.equals("Public")){
		  setPrivacy(groupName, privacy);
		myGroupPrivacy = new PublicPrivacy();
		myGroupPrivacy.getAllowedMembers(privacy);
	  }
	  else if(privacy.equals("closed") || privacy.equals("Closed")){
		  setPrivacy(groupName, privacy);
		  myGroupPrivacy = new ClosedPrivacy();
			myGroupPrivacy.getAllowedMembers(privacy);
		  }
	  else System.out.println("Invalid Privacy");
	  
  }
  
  
	@Override
	public void setRole(String role) {
		
		
	}
	
	@Override
	public void removeMember(String email) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/API", "root", "");
		boolean flag = false,flag1 = false,flag2 = false;
		Scanner sc=new Scanner(System.in);
		String GroupName="";
		int generatedKey =0 ;
		
		PreparedStatement statment = con.prepareStatement("SELECT * FROM `user` where email = '"+email+"'");	
		ResultSet result = statment.executeQuery();
		while (result.next()) {
			flag=true;
			System.out.println("enter group name ");
			GroupName=sc.nextLine();
			String m="SELECT `groupID` , `owner` FROM `group` WHERE   groupName = '"+GroupName+"';";
			System.out.println(m);
			PreparedStatement statement1=con.prepareStatement(m);
			ResultSet result1 =statement1.executeQuery();
			while(result1.next()){
				flag1 = true;
				
				generatedKey=result1.getInt("groupID");	

				if(User.getCurrentActiveUser().email.equals(result1.getString("owner"))){
					String t="SELECT * FROM `groupmembers`";// WHERE 'groupID' = "+generatedKey+" and 'memberEmail'  = '"+email+"'";
					System.out.println(t);
					PreparedStatement statement3=con.prepareStatement(t);
					ResultSet result3= statement3.executeQuery();
					
					while(result3.next()){
						if(result3.getString("role").equals("member") && result3.getInt("groupID") == generatedKey && result3.getString("memberEmail").equals(email)){
							flag2 = true;
							
							String h="DELETE FROM `groupmembers` WHERE groupID = "+generatedKey+" and memberEmail  = '"+email+"'";
							System.out.println(h);
							PreparedStatement statement4=con.prepareStatement(h);
							statement4.executeUpdate();
						}
					}
					if(flag2 == false)System.out.println("this user is already not a member");
				}			
			}
			if(flag1==false)System.out.println("there is no group with this name");
		}
		if(flag == false)System.out.println("there is no User !");
	}
	
	@Override
	public void addMember(String email) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/API", "root", "");
		
		boolean flag = false,flag1=false;
		int subscribedGroups = 0 ;
		Scanner sc=new Scanner(System.in);
		String GroupName="";
		int generatedKey =0 ;
		
		PreparedStatement statment = con.prepareStatement("SELECT * FROM `user` where email = '"+email+"'");	
		ResultSet result = statment.executeQuery();
		while(result.next()){
			System.out.println(result.getString("name") +"\t"+ result.getInt("subscribedGroups"));
			flag=true;
			subscribedGroups = result.getInt("subscribedGroups");
			System.out.println("enter group name ");
			GroupName=sc.nextLine();
			String m="SELECT `groupID` , `owner` FROM `group` WHERE   groupName = '"+GroupName+"';";
			System.out.println(m);
			PreparedStatement statement1=con.prepareStatement(m);
			ResultSet result1 =statement1.executeQuery();
			
			while(result1.next()){
				System.out.println("groupID = "+ result1.getInt("groupID") + "owner = "+result1.getString("owner"));
				flag1 = true;
				
				generatedKey=result1.getInt("groupID");	

				if(User.getCurrentActiveUser().email.equals(result1.getString("owner"))){
					String t="SELECT  `role` FROM `groupmembers` WHERE 'groupID' = "+generatedKey+" and 'memberEmail'  = '"+email+"'";
					PreparedStatement statement3=con.prepareStatement(t);
					ResultSet result3= statement3.executeQuery();
					
					while(result3.next()){
						System.out.println("this person is member in the group =D");
						return;
						
					}
					 
					PreparedStatement statment4 = con.prepareStatement("INSERT INTO `groupmembers`(`groupID`, `memberEmail`, `role`) VALUES (" +generatedKey+ ",'" +email+"','member')");
					statment4.executeUpdate();
					
					PreparedStatement statment5 = con.prepareStatement("UPDATE `user` SET subscribedGroups = ?  WHERE email =?");
					statment5.setLong( 1, (result.getInt("subscribedGroups"))+1);
					statment5.setString( 2, email );
					statment5.executeUpdate();
				}
			}
			if(flag1==false)System.out.println("there is no group with this name");
		}
		if(flag == false)System.out.println("there is no User !");
	}
}