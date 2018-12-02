package ca.uottawa.uvaug070.homerepair;

import java.util.ArrayList;

public class ServiceProvider extends Account {
    private Profile myProfile;
    /*private int numRatings;
    private int cumulativeRating;*/
    public ServiceProvider(String username, String password, Role role, String uid) {
        super(username, password, role, uid);
        this.myProfile = new Profile();
        /*numRatings = 0;
        cumulativeRating = 0;*/
    }
    public ServiceProvider(){
        super();
        /*numRatings = 0;
        cumulativeRating = 0;*/
    }
    public ServiceProvider(String username, String password, Role role, String uid,Profile myProfile) {
        super(username, password, role, uid);
        this.myProfile=myProfile;
        /*numRatings = 0;
        cumulativeRating = 0;*/
    }
    public ServiceProvider(String username, String password, Role role, String uid,Profile myProfile, int numRatings, int cumulativeRating) {
        super(username, password, role, uid);
        this.myProfile=myProfile;
        /*this.numRatings = numRatings;
        this.cumulativeRating = cumulativeRating;*/
    }
    public Profile getMyProfile(){return myProfile;}
    public void setMyProfile(Profile newProfile){this.myProfile=newProfile;}
   /* public void rate(int rating) {
        cumulativeRating += rating;
        numRatings++;
    }
    public double getRating() {
        return (double)cumulativeRating/(double)numRatings;
    }
    public int getNumRatings() {
        return numRatings;
    }
    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }
    public int getCumulativeRating() {
        return cumulativeRating;
    }
    public void setCumulativeRating(int cumulativeRating) {
        this.cumulativeRating = cumulativeRating;
    }*/
}
