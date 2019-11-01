import java.util.Vector;

public abstract class IGroup {

  public GroupPrivacy P;
     
  public static GroupPrivacy myGroupPrivacy;
    

  public abstract void addMember(String email) throws Exception; 

  public abstract void removeMember(String email) throws Exception;

  public abstract void setRole(String role);
  
}