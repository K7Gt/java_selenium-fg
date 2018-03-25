package stqa.litecart.com;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void start(){
        ChromeOptions options = new ChromeOptions();
        options.setCapability("unexpectedAlertBehaviour","dismiss");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("unexpectedAlertBehavior","dismiss");
        driver =  new ChromeDriver(options);
        driver.manage().window().maximize();
//        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver,10);
    }

    @After
    public  void stop(){
        driver.quit();
        driver = null;
    }

    protected  void login() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    public void isCheck(By locator){
        WebElement checkbox = driver.findElement(locator);
        if (checkbox.isSelected()){
            checkbox.click();
        }

    }

    public void isCheck(By locatorOn, By locatorOff, boolean status){
        WebElement checkboxOn = driver.findElement(locatorOn);
        WebElement checkboxOff = driver.findElement(locatorOff);
        if (status==true){
            checkboxOn.click();
        }else checkboxOff.click();

    }

    public void selectFromPicklist(By locator, String option){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new Select(driver.findElement(locator)).selectByVisibleText(option);

    }

    public void type(String text, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void gotoPricesTab(){
        driver.findElement(By.cssSelector("ul.index li:nth-child(4)")).click();
    }
    public void gotoInfoTab(){
        driver.findElement(By.cssSelector("ul.index li:nth-child(2)")).click();
    }
    public void gotoCatalogTab() {
        driver.findElement(By.cssSelector("li#app-:nth-child(2)")).click();
    }


}
