package stqa.litecart.com;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import stqa.litecart.com.Model.User;

import java.util.concurrent.TimeUnit;

public class RegistrationLoginTest {

    static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void start(){
        driver =  new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
    }

    @AfterClass
    public static void stop(){
        driver.quit();
        driver = null;
    }


    @Before

    public void preconditions(){
        driver.get("http://localhost/litecart/");

    }

    User newUser = new User(
            "123",
            "testCompany",
            "testName",
            "testLastname",
            "testaddress1",
            "testaddress2",
            "12345",
            "New York",
            "United States",
            "Arizona",
            "test@test.com",
            "123456789",
            false,
            "testpass"
    );

    @Test
    public void testRegistration(){

        driver.findElement(By.cssSelector("div#box-account-login tr:nth-child(5) a")).click();
        fillRegistrationForm(newUser);
        driver.findElement(By.cssSelector("button[name=create_account]")).click();
        logOut();
        loginUser(newUser);
        logOut();


    }

    private void logOut() {
        driver.findElement(By.cssSelector("div#box-account ul li:last-child a")).click();
    }

    private void loginUser(User newUser) {
        type(newUser.getEmail(),By.cssSelector("input[name=email]"));
        type(newUser.getDesiredPassword(),By.cssSelector("input[name=password]"));
        WebElement button = driver.findElement(By.cssSelector("button[name=login]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);",button);
        executor.executeScript("arguments[0].click();",button);

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

    public void isCheck(By locator){
        WebElement checkbox = driver.findElement(locator);
        if (checkbox.isSelected()){
            checkbox.click();
        }

    }

    public void selectFromPicklist(By locator, String option){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new Select(driver.findElement(locator)).selectByVisibleText(option);

    }

    private void type(String text, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }


}
