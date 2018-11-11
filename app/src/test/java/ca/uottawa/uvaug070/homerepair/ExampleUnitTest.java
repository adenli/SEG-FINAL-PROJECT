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
        Account testAccount = new Account("simon","cat",Role.ADMIN);
        assertEquals("Check that the proper password is output","cat", testAccount.getPassword());
    }
    @Test
    public void checkAccountUsername() {
        Account testAccount = new Account("simon","cat",Role.ADMIN);
        assertEquals("Check that the proper username is output","simon", testAccount.getUsername());
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
}