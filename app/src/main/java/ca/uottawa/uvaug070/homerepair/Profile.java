package ca.uottawa.uvaug070.homerepair;

public class Profile {
    private String companyName,address,phoneNumber;
    private String description;
    private boolean licensed;
    public Profile(String name,String address,String phoneNum,String description,boolean licensed){
        this.companyName=name;
        this.address=address;
        this.phoneNumber=phoneNum;
        this.description=description;
        this.licensed=licensed;
    }
    public Profile (){}
    public String getCompanyName(){
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean getLicensed() {
        return licensed;
    }

    public String getDescription() {
        return description;
    }
    public void setCompanyName(String name){
        this.companyName=name;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public void setPhoneNumber(String phoneNum){
        this.phoneNumber=phoneNum;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setLicensed(boolean licensed){
        this.licensed=licensed;
    }
}
