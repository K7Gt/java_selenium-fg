package stqa.litecart.com.Appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplicationManager {
    private HelperBase helperBase;
    private NavigationHelper navigationHelper;
    private ShopPageHelper shopPageHelper ;
    private AdminPageHelper adminPageHelper;
    WebDriver driver;
    WebDriverWait wait;


    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("unexpectedAlertBehaviour","dismiss");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("unexpectedAlertBehavior","dismiss");
        driver =  new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
        adminPageHelper = new AdminPageHelper(driver);
        shopPageHelper = new ShopPageHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        helperBase = new HelperBase(driver);
    }

    public void stop() {
        driver.quit();
        driver = null;

    }


    public AdminPageHelper getAdminPageHelper() {
        return adminPageHelper;
    }

    public ShopPageHelper getShopPageHelper() {
        return shopPageHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public HelperBase getHelperBase() {
        return helperBase;
    }
}
