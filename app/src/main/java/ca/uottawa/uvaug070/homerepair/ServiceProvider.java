package ca.uottawa.uvaug070.homerepair;

public class ServiceProvider extends Account {
    Profile myProfile;
    public ServiceProvider(String username, String password, Role role, String uid) {
        super(username, password, role, uid);
        this.myProfile = new Profile();
    }
    public ServiceProvider(){
        super();
    }
    public ServiceProvider(String username, String password, Role role, String uid,Profile myProfile) {
        super(username, password, role, uid);
        this.myProfile=myProfile;
    }
    public Profile getMyProfile(){return myProfile;}
    public void setMyProfile(Profile newProfile){this.myProfile=newProfile;}
}
