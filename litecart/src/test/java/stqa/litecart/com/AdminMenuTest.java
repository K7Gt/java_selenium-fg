package stqa.litecart.com;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AdminMenuTest extends TestBase {

    @Before
    public  void precondtions() {
        driver.get("http://localhost/litecart/admin");
        login();
    }

    @Test
    public void testAdminMenu(){

        for (int i = 1; i <= driver.findElements(By.cssSelector("li#app-")).size(); i++) {
            WebElement option = driver.findElement(By.cssSelector("li#app-:nth-child("+i+")"));
            option.findElement(By.cssSelector("a:first-child")).click();
            Assert.assertTrue(isElementPresent(By.cssSelector("td#content h1")));
//            System.out.println("H1 is presented for option: " + i);

            if(driver.findElements(By.cssSelector("li#app-:nth-child("+i+") ul.docs li")).size() > 0) {
                for (int j = 1; j <= driver.findElements(By.cssSelector("li#app-:nth-child("+i+") ul.docs li")).size(); j++) {
                    WebElement subOption = driver.findElement(By.cssSelector("ul.docs li:nth-child("+j+")"));
                    subOption.findElement(By.cssSelector("a:first-child")).click();
                    Assert.assertTrue(isElementPresent(By.cssSelector("td#content h1")));
//                    System.out.println("H1 is presented for subOption: " + i + " - " + j);
                }
            }

        }


    }
}
