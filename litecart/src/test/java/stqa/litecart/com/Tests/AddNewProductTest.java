package stqa.litecart.com.Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stqa.litecart.com.Model.Product;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddNewProductTest extends TestBase {

    @Before

    public void preconditions(){
        app.getNavigationHelper().gotoAdminPage();
        app.getAdminPageHelper().login();
    }

    Product product = new Product(
            true,
            "Test product",
            "Test",
            "Rubber Ducks",
            "Male",
            "7",
            new File("src/test/resources/samurai.jpg"),
            "ACME Corp.",
            "Samurai",
            "Samurai description",
            "Samurai is the military nobility and officer caste of medieval and early-modern Japan.",
            "Samurai",
            "Bushido",
            "777",
            "US Dollars",
            "770",
            "700",
            "550",
            "450"
    );

    @Test
    public void testAddNewProduct(){
        app.getAdminPageHelper().gotoCatalogTab();
//        String language = driver.findElement(By.cssSelector());
        app.getHelperBase().click(By.cssSelector("td#content div:nth-child(2) a:nth-child(2)"));
        app.getAdminPageHelper().fillGeneralTab(product);
        app.getAdminPageHelper().gotoInfoTab();
        app.getAdminPageHelper().fillInfoTab(product);
        app.getAdminPageHelper().gotoPricesTab();
        app.getAdminPageHelper().fillPricesTab(product);
        app.getHelperBase().click(By.cssSelector("button[name=save]"));


        List<WebElement> categories = app.getHelperBase().getListOfElements(By.cssSelector("tr.row"));
        boolean result = isThereProduct(categories, product.getGeneralName());

        Assert.assertTrue(result);

    }

    private boolean isThereProduct(List<WebElement> categories, String product) {
        categories.remove(0);
        for (WebElement el:categories
             ) {
            if(el.findElement(By.cssSelector("td:nth-child(3) a")).getAttribute("textContent").equals(product)){
                return true;
            }
        }
        return false;
    }

}
