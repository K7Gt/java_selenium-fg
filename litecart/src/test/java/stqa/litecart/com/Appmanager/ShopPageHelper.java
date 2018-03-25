package stqa.litecart.com.Appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import stqa.litecart.com.Model.User;

public class ShopPageHelper extends HelperBase {


    public ShopPageHelper(WebDriver driver) {
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
        type(user.getTaxId(), By.cssSelector("div#create-account tr:nth-child(1) td:first-child input"));
        type(user.getCompanyName(), By.cssSelector("div#create-account tr:nth-child(1) td:last-child input"));

        type(user.getFirstName(), By.cssSelector("div#create-account tr:nth-child(2) td:first-child input"));
        type(user.getLastName(), By.cssSelector("div#create-account tr:nth-child(2) td:last-child input"));

        type(user.getAddres1(), By.cssSelector("div#create-account tr:nth-child(3) td:first-child input"));
        type(user.getAddres2(), By.cssSelector("div#create-account tr:nth-child(3) td:last-child input"));

        type(user.getPostCode(), By.cssSelector("div#create-account tr:nth-child(4) td:first-child input"));
        type(user.getCity(), By.cssSelector("div#create-account tr:nth-child(4) td:last-child input"));

        selectFromPicklist(By.cssSelector("select[name=country_code]"), user.getCountry());
        selectFromPicklist(By.cssSelector("select[name=zone_code]"), user.getState());

        type(user.getEmail(), By.cssSelector("div#create-account tr:nth-child(6) td:first-child input"));
        type(user.getPhone(), By.cssSelector("div#create-account tr:nth-child(6) td:last-child input"));

        isCheck(By.cssSelector("input[name=newsletter]"));

        type(user.getDesiredPassword(), By.cssSelector("div#create-account tr:nth-child(8) td:first-child input"));
        type(user.getDesiredPassword(), By.cssSelector("div#create-account tr:nth-child(8) td:last-child input"));

    }

    public void loginUser(User newUser) {
        type(newUser.getEmail(),By.cssSelector("input[name=email]"));
        type(newUser.getDesiredPassword(),By.cssSelector("input[name=password]"));
        WebElement button = getWebElement(By.cssSelector("button[name=login]"));
        executeJS("arguments[0].scrollIntoView(true);",button);
        executeJS("arguments[0].click();",button);
//        executor.executeScript("arguments[0].scrollIntoView(true);",button);
//        executor.executeScript("arguments[0].click();",button);

    }



}
