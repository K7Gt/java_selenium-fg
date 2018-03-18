package stqa.litecart.com;

import org.junit.Test;

public class LiteCartLoginTest extends TestBase {

    @Test
    public void testLogin() {
        driver.get("http://localhost/litecart/admin");
        login();

    }



}
