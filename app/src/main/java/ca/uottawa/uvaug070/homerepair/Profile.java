package ca.uottawa.uvaug070.homerepair;

public class Profile {
    private String companyName,address,phoneNumber;
    private String description=null;
    private Boolean licensed=null;
    public Profile(String name,String address,String phoneNum,String description,Boolean licensed){
        this.companyName=name;
        this.address=address;
        this.phoneNumber=phoneNum;
        this.description=description;
        this.licensed=licensed;
    }
    public Profile(String name,String address,String phoneNum,Boolean licensed){
        this.companyName=name;
        this.address=address;
        this.phoneNumber=phoneNum;
        this.licensed=licensed;
    }
    public Profile(String name,String address,String phoneNum,String description){
        this.companyName=name;
        this.address=address;
        this.phoneNumber=phoneNum;
        this.description=description;
    }
    public Profile(String name,String address,String phoneNum){
        this.companyName=name;
        this.address=address;
        this.phoneNumber=phoneNum;
    }
    public String getCompanyName(){
        return this.companyName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Boolean isLicensed() {
        if(this.licensed!=null) {return this.licensed;}
        return false;
    }

    public String getDescription() {
        if(description==null){return "";}
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
    public void setLicensed(Boolean licensed){
        this.licensed=licensed;
    }
}
