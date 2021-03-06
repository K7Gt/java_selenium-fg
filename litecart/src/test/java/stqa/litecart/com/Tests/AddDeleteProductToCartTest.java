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
        app.getShopPageHelper().addSeveralProducts(3, app.getProductPageHelper());
        app.getShopPageHelper().gotoCart();
        app.getCartPage().removeAllProducts();
    }

}
