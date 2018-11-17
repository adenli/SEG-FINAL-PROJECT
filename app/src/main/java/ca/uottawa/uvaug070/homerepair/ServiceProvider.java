package ca.uottawa.uvaug070.homerepair;

import java.util.ArrayList;

public class ServiceProvider extends Account {
    ArrayList<Service> services;
    public ServiceProvider(String username, String password, Role role, String uid) {
        super(username, password, role, uid);
    }
    public ServiceProvider(String username, String password, Role role, ArrayList<Service> services, String uid) {
        super(username, password, role, uid);
        this.services = services;
    }
    public ServiceProvider(){
        super();
    }
    public ArrayList<Service> getServices() {
        return services;
    }
    public void addService(Service toAdd) {
        services.add(toAdd);
    }
    public boolean removeService(Service toRemove) {
        return services.remove(toRemove);
    }
}
