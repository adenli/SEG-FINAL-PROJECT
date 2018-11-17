package ca.uottawa.uvaug070.homerepair;

import java.util.ArrayList;

public class ServiceProvider extends Account {
    private Profile myProfile;
    public ServiceProvider(String username, String password, Role role, String uid) {
        super(username, password, role, uid);
    }
    public ServiceProvider(){
        super();
    }
    public ServiceProvider(String username, String password, Role role, String uid,Profile myProfile) {
        super(username, password, role, uid);
        this.myProfile=myProfile;
    }
    public Profile getServiceProviderProfile(){return myProfile;}
    public void setServiceProviderProfile(Profile newProfile){this.myProfile=newProfile;}
}
