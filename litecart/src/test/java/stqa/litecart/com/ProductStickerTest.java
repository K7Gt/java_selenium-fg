package stqa.litecart.com;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductStickerTest extends TestBase {

    @Before
    public void preconditions(){
        driver.get("http://localhost/litecart/");

    }


    @Test
    public void testProductSticker(){
        List<WebElement> productStickers = driver.findElements(By.cssSelector("div.content li.product"));
        for (WebElement el:productStickers) {
            WebElement actualResult1 = el.findElement(By.cssSelector("a:first-child  div.image-wrapper"));
            List<WebElement> actualResult2 = el.findElements(By.cssSelector("a:first-child  div.image-wrapper"));
            Assert.assertNotNull(actualResult1);
            Assert.assertEquals(1,actualResult2.size());
        }
    }
}
