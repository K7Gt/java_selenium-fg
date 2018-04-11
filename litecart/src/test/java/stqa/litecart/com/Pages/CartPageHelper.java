package stqa.litecart.com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import stqa.litecart.com.Appmanager.HelperBase;

public class CartPageHelper extends HelperBase {

    public CartPageHelper(EventFiringWebDriver driver) {
        super(driver);
    }

    public void removeAllProducts(){
        int i = getListOfElements(By.cssSelector("ul.shortcuts li")).size();
        while (i != 0){
            WebElement element = getWebElement(By.cssSelector("div#order_confirmation-wrapper table"));
            waitAndClick(By.cssSelector("button[name=remove_cart_item]"));
            stalenessWaitOf(element);
            i--;
        }
    }
}
