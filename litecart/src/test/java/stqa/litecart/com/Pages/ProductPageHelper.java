package stqa.litecart.com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import stqa.litecart.com.Appmanager.HelperBase;

public class ProductPageHelper  extends HelperBase {

    public ProductPageHelper(EventFiringWebDriver driver) {
        super(driver);
    }

    public void addProductToCart(){
        if(isElementPresent(By.cssSelector("select[name='options[Size]']"))){
            selectNthElementInPicklist(By.cssSelector("select[name='options[Size]']"),1);
        }
        String value = getWebElement(By.cssSelector("span.quantity")).getAttribute("textContent");
        click(By.cssSelector("button[name=add_cart_product]"));
        waitUntilTextChanged(By.cssSelector("span.quantity"),value);
        click(By.cssSelector("div#logotype-wrapper"));
    }
}

