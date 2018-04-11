package stqa.litecart.com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import stqa.litecart.com.Appmanager.HelperBase;
import stqa.litecart.com.Model.User;

public class ShopPageHelper extends HelperBase {


    public ShopPageHelper(EventFiringWebDriver driver) {
        super(driver);
    }

    public void logOut() {
        click(By.cssSelector("div#box-account ul li:last-child a"));
    }

    public void initRegistration(){
        click(By.cssSelector("div#box-account-login tr:nth-child(5) a"));
    }

    public void submitRegistration(){
        click(By.cssSelector("button[name=create_account]"));
    }

    public void fillRegistrationForm(User user){
        type(user.getTaxId(), By.cssSelector("input[name=tax_id]"));
        type(user.getCompanyName(), By.cssSelector("input[name=company]"));

        type(user.getFirstName(), By.cssSelector("input[name=firstname]"));
        type(user.getLastName(), By.cssSelector("input[name=lastname]"));

        type(user.getAddres1(), By.cssSelector("input[name=address1]"));
        type(user.getAddres2(), By.cssSelector("input[name=address2]"));

        type(user.getPostCode(), By.cssSelector("input[name=postcode]"));
        type(user.getCity(), By.cssSelector("input[name=city]"));

        selectFromPicklist(By.cssSelector("select[name=country_code]"), user.getCountry());
        selectFromPicklist(By.cssSelector("select[name=zone_code]"), user.getState());

        type(user.getEmail(), By.cssSelector("input[name=email]"));
        type(user.getPhone(), By.cssSelector("input[name=phone]"));

        isCheck(By.cssSelector("input[name=newsletter]"));

        type(user.getDesiredPassword(), By.cssSelector("input[name=password]"));
        type(user.getDesiredPassword(), By.cssSelector("input[name=confirmed_password]"));

    }

    public void loginUser(User newUser) {
        type(newUser.getEmail(),By.cssSelector("input[name=email]"));
        type(newUser.getDesiredPassword(),By.cssSelector("input[name=password]"));
        WebElement button = getWebElement(By.cssSelector("button[name=login]"));
        executeJS("arguments[0].scrollIntoView(true);",button);
        executeJS("arguments[0].click();",button);
    }


    public void gotoProductPage(ProductPageHelper page) {
        click(By.cssSelector("div#box-most-popular li:nth-child(1)"));
        page.addProductToCart();

    }

    public void addSeveralProducts(int numberOfProducts, ProductPageHelper page){
        for (int i = 0; i < numberOfProducts; i++) {
            gotoProductPage(page);
        }
    }

//    public void removeProduct(By locator){
//        waitAndClick(locator);
//    }

    public void gotoCart() {
        click(By.cssSelector("div#cart"));
    }


}
