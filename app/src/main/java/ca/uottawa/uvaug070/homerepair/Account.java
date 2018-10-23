package ca.uottawa.uvaug070.homerepair;

public class Account {
    String username;
    String password;
    Role role;
    public Account(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
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
    public void setRole(Role role) {
        this.role = role;
    }
    public Role getRole() {
        return role;
    }
}