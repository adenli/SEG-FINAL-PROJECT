package ca.uottawa.uvaug070.homerepair;

public class User extends Account {
    public User(String username, String password, Role role, String uid) {
        super(username, password, role, uid);
    }
    public User(){
        super();
    }
}
