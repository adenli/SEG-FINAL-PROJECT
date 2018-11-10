package ca.uottawa.uvaug070.homerepair;

public class Service {
    String name;
    int rate;
    public Service(String name, int rate) {
        this.name = name;
        this.rate = rate;
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
    public int getRate() {
        return rate;
    }
}
