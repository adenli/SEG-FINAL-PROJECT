package ca.uottawa.uvaug070.homerepair;

public class Account {
    String username;
    String password;
    Role role;
    String uid;
    public Account(String username, String password, Role role, String uid) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.uid = uid;
    }
    public Account() {

    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public Role getRole() {return role;}
    public void setRole(Role role) {this.role = role;}
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
}
