package ca.uottawa.uvaug070.homerepair;

import java.util.ArrayList;

public class ServiceProvider extends Account {
    ArrayList<Service> services;
    public ServiceProvider(String username, String password) {
        super(username, password);
    }

}
