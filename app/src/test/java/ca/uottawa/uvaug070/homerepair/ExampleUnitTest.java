package ca.uottawa.uvaug070.homerepair;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void checkAdminAccountPassword() {
        Admin testAccount = new Admin("simon","cat",Role.ADMIN,"abcde");
        assertEquals("Check that the proper password is output","cat", testAccount.getPassword());
    }
    @Test
    public void checkAdminAccountUsername() {
        Admin testAccount = new Admin("simon","cat",Role.ADMIN,"abcde");
        assertEquals("Check that the proper username is output","simon", testAccount.getUsername());
    }
    @Test
    public void checkAdminAccountUid() {
        Admin testAccount = new Admin("simon","cat",Role.ADMIN,"abcde");
        assertEquals("Check that the proper uid is output","abcde", testAccount.getUid());
    }
    @Test
    public void checkServiceName() {
        Service testService = new Service("Plumbing",4,"A2hI");
        assertEquals("Check that the proper name is output","Plumbing", testService.getName());
    }
    @Test
    public void checkServicerRate() {
        Service testService = new Service("Plumbing",4,"A2hI");
        assertEquals("Check that the proper rate is output","4.0", (""+testService.getRate()));
    }
    @Test
    public void checkServiceUid() {
        Service testService = new Service("Plumbing",4,"A2hI");
        assertEquals("Check that the proper username is output","A2hI", testService.getUid());
    }
    @Test
    public void checkServiceProviderProfileAddress(){
        Profile testProfile = new Profile("SegProject","19 Brian Crescent","6125555555","this is the description for the profile",true);
        ServiceProvider testServiceProvider = new ServiceProvider("Simon","walker",Role.SERVICEPROVIDER,"abcde",testProfile);
        assertEquals("Check that the proper profile address is output","19 Brian Crescent", testServiceProvider.getMyProfile().getAddress());
    }
    @Test
    public void checkServiceProviderProfilePhoneNum(){
        Profile testProfile = new Profile("SegProject","19 Brian Crescent","6125555555","this is the description for the profile",true);
        ServiceProvider testServiceProvider = new ServiceProvider("Simon","walker",Role.SERVICEPROVIDER,"abcde",testProfile);
        assertEquals("Check that the proper profile phone number is output","6125555555", testServiceProvider.getMyProfile().getPhoneNumber());
    }
    @Test
    public void checkServiceProviderProfileCompanyName() {
        Profile testProfile = new Profile("SegProject","19 Brian Crescent","6125555555","this is the description for the profile",true);
        ServiceProvider testServiceProvider = new ServiceProvider("Simon","walker",Role.SERVICEPROVIDER,"abcde",testProfile);
        assertEquals("Check that the proper profile company is output", "SegProject", testServiceProvider.getMyProfile().getCompanyName());
    }
    @Test
    public void checkServiceProviderProfileDescription() {
        Profile testProfile = new Profile("SegProject","19 Brian Crescent","6125555555","this is the description for the profile",true);
        ServiceProvider testServiceProvider = new ServiceProvider("Simon","walker",Role.SERVICEPROVIDER,"abcde",testProfile);
        assertEquals("Check that the proper profile description is output", "this is the description for the profile", testServiceProvider.getMyProfile().getDescription());
    }
    @Test
    public void checkServiceProviderProfileLicence() {
        Profile testProfile = new Profile("SegProject","19 Brian Crescent","6125555555","this is the description for the profile",true);
        ServiceProvider testServiceProvider = new ServiceProvider("Simon","walker",Role.SERVICEPROVIDER,"abcde",testProfile);
        assertEquals("Check that the proper profile licensed is output","true",""+testServiceProvider.getMyProfile().getLicensed());
    }
    @Test
    public void checkServiceProviderProfile(){
        Profile testProfile = new Profile("SegProject","19 Brian Crescent","6125555555","this is the description for the profile",true);
        ServiceProvider testServiceProvider = new ServiceProvider("Simon","walker",Role.SERVICEPROVIDER,"abcde",testProfile);
        assertEquals("Check that the proper profile address is output",testProfile.toString(), testServiceProvider.getMyProfile().toString());
    }
    @Test
    public void checkServiceProviderAccountPassword() {
        Profile testProfile = new Profile("SegProject","19 Brian Crescent","6125555555","this is the description for the profile",true);
        ServiceProvider testServiceProvider = new ServiceProvider("Simon","walker",Role.SERVICEPROVIDER,"abcde",testProfile);
        assertEquals("Check that the proper password is output","Walker", testServiceProvider.getPassword());
    }
    @Test
    public void checkServiceProviderAccountUsername() {
        Profile testProfile = new Profile("SegProject","19 Brian Crescent","6125555555","this is the description for the profile",true);
        ServiceProvider testServiceProvider = new ServiceProvider("Simon","walker",Role.SERVICEPROVIDER,"abcde",testProfile);
        assertEquals("Check that the proper username is output","Simon", testServiceProvider.getUsername());
    }
    @Test
    public void checkServiceProviderAccountUid() {
        Profile testProfile = new Profile("SegProject","19 Brian Crescent","6125555555","this is the description for the profile",true);
        ServiceProvider testServiceProvider = new ServiceProvider("Simon","walker",Role.SERVICEPROVIDER,"abcde",testProfile);
        assertEquals("Check that the proper uid is output","abcde", testServiceProvider.getUid());
    }
    @Test
    public void checkUserAccountPassword() {
        User testUser = new User("Simon","walker",Role.USER,"abcde");
        assertEquals("Check that the proper password is output","walker", testUser.getPassword());
    }
    @Test
    public void checkUserAccountUsername() {
        User testUser = new User("Simon","walker",Role.USER,"abcde");
        assertEquals("Check that the proper username is output","Simon", testUser.getUsername());
    }
    @Test
    public void checkUserAccountUid() {
        User testUser = new User("Simon","walker",Role.USER,"abcde");
        assertEquals("Check that the proper uid is output","abcde", testUser.getUid());
    }
}