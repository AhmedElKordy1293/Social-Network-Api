import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

public class Page extends IPage {
	

	public ArrayList<User>UsersName ;
	public static PageModel pageModel;
	  
	  
	public String pageName, pageType;
	public int id;
	public boolean flag = false;
	
	Page(String pageName, String pageType){
		this.pageName = pageName;
		this.pageType = pageType;
	}
	
	@Override
	public void likeAPage(Page page , User user) throws Exception {
	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/API", "root", "");
		 
		PreparedStatement statment = con.prepareStatement("SELECT `id`,`#OfLikes` FROM `page` WHERE name = '"+ page.pageName +"' and type = '"+ page.pageType +"'");
		ResultSet result = statment.executeQuery();
		//if(!result.next())System.out.println("Invalid PageName Or PageType");
		while(result.next()){
			flag = true;
			System.out.println("ana fe while !");
			System.out.println(result.getInt("id"));
			
			PreparedStatement statment2 = con.prepareStatement("SELECT `userEmail`, `pageID`, `pageName` FROM `likedpages` WHERE userEmail = '"+ user.email +"' and pageID = '"+ result.getInt("id") +"'");
			ResultSet result2 = statment2.executeQuery();
			if(!result2.next()){
				PreparedStatement statment3 = con.prepareStatement("INSERT INTO `likedpages`(`userEmail`, `pageID`, `pageName`) VALUES ('"+user.email +"','"+ result.getInt("id") +"','"+ page.pageName+"')");
				statment3.executeUpdate();
				
				PreparedStatement statment4 = con.prepareStatement("UPDATE `page` SET `#OfLikes`=? WHERE id =?");
				statment4.setLong(1,(result.getInt("#OfLikes")+1));
				statment4.setInt(2, result.getInt("id"));
				statment4.executeUpdate();
				System.out.println("2bl res 3");
				
				/*PreparedStatement statment6 = con.prepareStatement("SELECT `likedPages` FROM `user` WHERE email=?");
				statment6.setString(1, user.email);
				ResultSet result3 = statment6.executeQuery();
				System.out.println("b3d res 3");
				System.out.println("result3= "+result3.getInt("likedPages"));*/

				PreparedStatement statment5 = con.prepareStatement("UPDATE `user` SET `likedPages` = ?  WHERE email =?");
				statment5.setLong( 1, (user.likedPages)+1);
				statment5.setString( 2, user.email );
				statment5.executeUpdate();
				
				System.out.println("Done !");
				return;
			}
		}
		if(!flag){
			System.out.println("Invalid PageName Or PageType");
			return;
		}
		System.out.println("You liked this page before !");
	}


	public static void CreatePage(String pageName, String pageType , User user) throws Exception{
		//System.out.println(user.name);
		pageModel.createPage(pageName,pageType,user);
	}

	public static void DeletePage(String pageName ,User user) throws Exception{
		System.out.println(user.name);
		pageModel.deletePage(pageName, user);
	}
	


}