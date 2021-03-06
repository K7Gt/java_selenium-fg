package stqa.litecart.com.Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductStickerTest extends TestBase {

    @Before
    public void preconditions(){
        app.getNavigationHelper().gotoShopMainPage();

    }


    @Test
    public void testProductSticker(){
        List<WebElement> products = app.getHelperBase().getListOfElements(By.cssSelector("div.content li.product"));

        for (WebElement el:products) {
            List<WebElement> listOfStickers = el.findElements(By.cssSelector("a:link  div.image-wrapper div.sticker"));
            Assert.assertEquals(1,listOfStickers.size());
        }
    }
}
