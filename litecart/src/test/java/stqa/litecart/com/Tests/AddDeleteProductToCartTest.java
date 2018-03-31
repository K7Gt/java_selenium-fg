package stqa.litecart.com.Tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class AddDeleteProductToCartTest extends TestBase {

    @Before
    public void preconditions(){
        app.getNavigationHelper().gotoShopMainPage();
    }

    @Test
    public void testAddDeleteProductToCart(){
        app.getShopPageHelper().addSeveralProducts(10);
        app.getNavigationHelper().gotoCart();
        app.getShopPageHelper().removeAllProducts(By.cssSelector("ul.shortcuts li"));
    }

}
