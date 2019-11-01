import java.io.ObjectInputStream.GetField;
import java.util.Scanner;


public class APICont {

	public static void main(String[] args) throws Exception {


		
		String name,type,email,password,cont="";
		Scanner sc = new Scanner(System.in);
		int choise = 0;

		do {
			 
			System.out.println("1-Sign UP\t2-Log in\tLog out");
			choise = sc.nextInt();
			
			switch (choise) {
			case 1:
				System.out.println("enter UR name : ");
				sc.nextLine();
				name = sc.nextLine();
			  	System.out.println("enter UR type : ");
			  	type = sc.nextLine();
			  	System.out.println("enter UR e-mail : ");
			  	email = sc.nextLine();
			  	System.out.println("enter UR password : ");
			  	password = sc.nextLine();
			  
			  	User.signUp(name,type,email,password);
				break;
			
			case 2:
				System.out.println("enter UR e-mail : ");
				sc.nextLine();
				email = sc.nextLine();
			  	System.out.println("enter UR password : ");
			  	password = sc.nextLine();
			  	User.login(email,password);
				
			  	System.out.println("1-SendFriendRequest\t2-AddFriend\n3-CreatePage\t4-LikePage\t5-DeletePage\n6-CreateGroup\t7-AddMember\t8-RemoveMember");
			  	System.out.println("9-send a msg \t10-get recieved msgs \t11-delete a msg");
			  	int ch = sc.nextInt();
			  	
			  	switch (ch) {
				case 1:
					System.out.println("enter reciever email :");
					sc.nextLine();
				  	String reciver = sc.nextLine();
				  	User.getCurrentActiveUser().sendFriendRequest(reciver);
					break;
					
				case 2:
					User.getCurrentActiveUser().addFriend();
					break;
				case 3:
				  	System.out.println("enter Page Name :");
				  	sc.nextLine();
				  	String pageName = sc.nextLine();
				  	
				  	System.out.println("enter Page Type :");
				  	String pageType = sc.nextLine();
				  	
				  	Page.CreatePage(pageName, pageType, User.getCurrentActiveUser());
									
					break;
				case 4:
					System.out.println("enter Page Name :");
				  	sc.nextLine();
				  	String pageName1 = sc.nextLine();
				  	
				  	System.out.println("enter Page Type :");
				  	String pageType1 = sc.nextLine();

				  	Page page = new Page(pageName1,pageType1);
				  	page.likeAPage(page, User.getCurrentActiveUser());
					
					break;
				case 5:
					System.out.println("enter Page Name :");
				  	sc.nextLine();
				  	String pageName2 = sc.nextLine();
				  	Page.DeletePage(pageName2 , User.getCurrentActiveUser());
					break;

				case 6:
				  	System.out.println("enter GroupName :");
				  	sc.nextLine();
				  	String groupName = sc.nextLine();
				  	
				  	System.out.println("enter GroupPrivacy :");
				  	String groupPrivacy = sc.nextLine();
				  	Group.createGroup(groupName, groupPrivacy, User.getCurrentActiveUser());

					break;
				
				case 7:	
					Group group = new Group();
				  	String email1 ="";
					System.out.println("enter memberEmail to add");
					sc.nextLine();
					email1=sc.nextLine();
				  	group.addMember(email1); 

					break;
					
				case 8:
					Group group1 = new Group();
				  	String email2 ="";
					System.out.println("enter memberEmail to add");
					sc.nextLine();
					email2=sc.nextLine();
					group1.removeMember(email2);
				  	
					break;
					
				case 9:	
					System.out.println("what's your Message ?");
				  	String m="";
				  	sc.nextLine();
				  	m = sc.nextLine();
				  	int c = 0;
				  	System.out.println("1-chat message\t2-group msg");
				  	c = sc.nextInt(); 
				  	switch (c) {
					case 1:
						User.getCurrentActiveUser().message.sendMessage(m);
		  			  	User.getCurrentActiveUser().chatmsg.addReceiver();
						break;
					case 2:
						User.getCurrentActiveUser().message.sendMessage(m);
			  			User.getCurrentActiveUser().groupmsg.addReceiver();
						break;

					default:
						break;
					}
					
					break;
				
				case 10:
					System.out.println("1-get chat messags \t 2-get group msgs");
					int x=0;
		  			x=sc.nextInt();
		  			switch (x) {
					case 1:
						User.getCurrentActiveUser().msgModel.getMessage();
						break;
					case 2:
						User.getCurrentActiveUser().msgModel.getGroupMessage();
						break;
					default:
						break;
					}
					
					break;
					
					
				case 11:
					System.out.println("1-delete from chat messags \t 2-delete from group msgs");
		  			int y=0;
		  			y=sc.nextInt();
					switch (y) {
					case 1:
						User.getCurrentActiveUser().msgModel.deleteMessage();
						break;
					case 2:
						User.getCurrentActiveUser().msgModel.deleteGroupMessage();
						break;
					default:
						break;
					}
					
					break;
				default:
					break;
				}
				
			  	break;
			
			case 3:
				User.logout();
				break;
			default:
				break;
			}
			
			System.out.println("do you continue ? (y/n)");
	  		cont = sc.next();	
		} while (cont.equals("y") || cont.equals("Y"));
		
		
	  	//System.out.println("enter UR name : ");
	  	//name = sc.nextLine();
	  	//System.out.println("enter UR type : ");
	  	//type = sc.nextLine();
		
//	  	System.out.println("enter UR e-mail : ");
//	  	email = sc.nextLine();
//	  	System.out.println("enter UR password : ");
//	  	password = sc.nextLine();
	  
	  	//User.signUp(name,type,email,password);
	  	//User.login(email,password);
	  	//User.login(email,password);
	  	
//	  	String reciver = sc.nextLine();
//	  	User.getCurrentActiveUser().sendFriendRequest(reciver);
//		
		
//	  	User.logout();
//	  	
//	  	System.out.println("enter UR e-mail : ");
//	  	email = sc.nextLine();
//	  	System.out.println("enter UR password : ");
//	  	password = sc.nextLine();
//	  	
//	  	User.login(email,password);
//	  	
//	  	
//	  	User.getCurrentActiveUser().addFriend();
//	  	
//	  	
//	  	System.out.println("enter Page Name :");
//	  	String pageName = sc.nextLine();
//	  	
//	  	System.out.println("enter Page Type :");
//	  	String pageType = sc.nextLine();
//	  	
//	  	Page.CreatePage(pageName, pageType, User.getCurrentActiveUser());
//	  	
//	  	Page page = new Page(pageName,pageType);
//	  	
//	  	page.likeAPage(page, User.getCurrentActiveUser());

	  	
	  	/*System.out.println("what's your Message ?");
	  	String m;
	  	m = sc.nextLine();
	  	
	  	String cont;
	  	do{
	  		
			  	
	  		String choice = "";
	  		System.out.println("1-send a msg \t 2-get recieved msgs \t 3-delete a msg");
	  		choice = sc.next();
	  		
	  		if(choice.equals("1")){
	  			System.out.println("1-chat message \t 2-group msg");
	  			String c;
	  			c=sc.next();
	  			if(c.equals("1")){
	  				User.getCurrentActiveUser().message.sendMessage(m);
	  			  	User.getCurrentActiveUser().chatmsg.addReceiver();
	  			}
	  			else if(c.equals("2")){
	  				User.getCurrentActiveUser().message.sendMessage(m);
	  			  User.getCurrentActiveUser().groupmsg.addReceiver();
	  			}
	  			else{
	  				System.out.println("selection is wrong");
	  			}
	  		}
	  	
	  		if(choice.equals("2")){
	  			System.out.println("1-get chat messags \t 2-get group msgs");
	  			String x;
	  			x=sc.next();
	  			if(x.equals("1")){
	  				User.getCurrentActiveUser().msgModel.getMessage();
	  			}
	  			else if(x.equals("2")){
	  				User.getCurrentActiveUser().msgModel.getGroupMessage();
	  			}
	  			else{
	  				System.out.println("selection is wrong");
	  			}
	  		}
	  			
	  		if(choice.equals("3")){
	  			System.out.println("1-delete from chat messags \t 2-delete from group msgs");
	  			String y;
	  			y=sc.next();
	  			if(y.equals("1")){
	  				User.getCurrentActiveUser().msgModel.deleteMessage();
	  			}
	  			else if(y.equals("2")){
	  				User.getCurrentActiveUser().msgModel.deleteGroupMessage();
	  			}
	  			else{
	  				System.out.println("selection is wrong");
	  			}
	  		
	  		}
	  		
	  		
	  		System.out.println("do you continue ? (y/n)");
	  		cont = sc.next();
	  	}
	  	while(cont.equals("y") || cont.equals("Y"));
*/
	  	
	  	//Page.DeletePage(pageName , User.getCurrentActiveUser());
	  	
//	  	System.out.println("enter GroupName :");
//	  	String groupName = sc.nextLine();
//	  	
//	  	System.out.println("enter GroupPrivacy :");
//	  	String groupPrivacy = sc.nextLine();
//	  	Group.createGroup(groupName, groupPrivacy, User.getCurrentActiveUser());

	  	
	  	//	  	Group group = new Group();
//	  	System.out.println("enter memberEmail to add");
//	  	String email1 ="";
//	  	email1=sc.nextLine();
	  	//group.addMember(email1); 
	  	//group.removeMember(email1);
	  	
	  	
//	  	Page page = new Page(pageName,pageType); 
//	  	page.likeAPage(page, User.getCurrentActiveUser());
//	  	
//		User.logout();
//	User.login(email,password);
		  
	}

	
}
