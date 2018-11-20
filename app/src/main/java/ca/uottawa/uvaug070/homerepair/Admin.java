package ca.uottawa.uvaug070.homerepair;

public class Admin extends Account {
    public Admin(String username, String password, Role role, String uid) {
        super(username, password, role, uid);
    }
    public Admin(){
        super();
    }
}
