package ca.uottawa.uvaug070.homerepair;

public class Service {
    String name;
    double rate;
    String uid;
    public Service(String name, double rate, String uid) {
        this.name = name;
        this.rate = rate;
        this.uid = uid;
    }
    public Service() {

    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }
    public double getRate() {
        return rate;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String toString() {
        return name + " for $" + Double.toString(rate) + "/hour";
    }
}
