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
    public void checkAccountPassword() {
        Account testAccount = new Account("simon","cat",Role.ADMIN,"abcde");
        assertEquals("Check that the proper password is output","cat", testAccount.getPassword());
    }
    @Test
    public void checkAccountUsername() {
        Account testAccount = new Account("simon","cat",Role.ADMIN,"abcde");
        assertEquals("Check that the proper username is output","simon", testAccount.getUsername());
    }
    @Test
    public void checkAccountUid() {
        Account testAccount = new Account("simon","cat",Role.ADMIN,"abcde");
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
    public void checkProfileAddress(){
        Profile testProfile = new Profile("SegProject","19 Brian Crescent","6125555555","this is the description for the profile",true);
        ServiceProvider testServiceProvider = new ServiceProvider("Simon","walker",Role.SERVICEPROVIDER,"abcde",testProfile);
        assertEquals("Check that the proper profile address is output","19 Brian Crescent", testServiceProvider.getServiceProviderProfile().getAddress());
    }
    @Test
    public void checkProfilePhoneNum(){
        Profile testProfile = new Profile("SegProject","19 Brian Crescent","6125555555","this is the description for the profile",true);
        ServiceProvider testServiceProvider = new ServiceProvider("Simon","walker",Role.SERVICEPROVIDER,"abcde",testProfile);
        assertEquals("Check that the proper profile phone number is output","6125555555", testServiceProvider.getServiceProviderProfile().getPhoneNumber());
    }
    @Test
    public void checkProfileCompanyName() {
        Profile testProfile = new Profile("SegProject","19 Brian Crescent","6125555555","this is the description for the profile",true);
        ServiceProvider testServiceProvider = new ServiceProvider("Simon","walker",Role.SERVICEPROVIDER,"abcde",testProfile);
        assertEquals("Check that the proper profile company is output", "SegProject", testServiceProvider.getServiceProviderProfile().getCompanyName());
    }
    @Test
    public void checkProfileDescription() {
        Profile testProfile = new Profile("SegProject","19 Brian Crescent","6125555555","this is the description for the profile",true);
        ServiceProvider testServiceProvider = new ServiceProvider("Simon","walker",Role.SERVICEPROVIDER,"abcde",testProfile);
        assertEquals("Check that the proper profile description is output", "this is the description for the profile", testServiceProvider.getServiceProviderProfile().getDescription());
    }
}