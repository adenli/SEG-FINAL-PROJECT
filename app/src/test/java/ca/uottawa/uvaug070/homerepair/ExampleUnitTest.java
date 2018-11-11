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
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void checkAccountUserAndPass() {
        Account testAccount = new Account("simon","cat",Role.ADMIN);
        assertEquals("Check that the proper username is output","simon", testAccount.getUsername());
        assertEquals("Check that the proper password is output","cat", testAccount.getPassword());
    }
}