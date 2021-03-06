package stqa.litecart.com.Appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import stqa.litecart.com.Pages.CartPageHelper;
import stqa.litecart.com.Pages.ProductPageHelper;
import stqa.litecart.com.Pages.ShopPageHelper;

public class ApplicationManager {
    private HelperBase helperBase;
    private NavigationHelper navigationHelper;
    private ShopPageHelper shopPageHelper ;
    private AdminPageHelper adminPageHelper;
    private CartPageHelper cartPageHelper;
    private ProductPageHelper productPageHelper;



    EventFiringWebDriver driver;
    WebDriverWait wait;



    public  static  class MyListener extends AbstractWebDriverEventListener{
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by +  " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
//            File tmp =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            File screen = new File ("screen" + System.currentTimeMillis() + ".png");
//            try {
//                Files.copy(tmp,screen);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println(screen);
        }
    }


    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("unexpectedAlertBehaviour","dismiss");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("unexpectedAlertBehavior","dismiss");
        driver =  new EventFiringWebDriver(new ChromeDriver(options));
        driver.register(new MyListener());
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
        adminPageHelper = new AdminPageHelper(driver);
        shopPageHelper = new ShopPageHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        cartPageHelper = new CartPageHelper(driver);
        productPageHelper = new ProductPageHelper(driver);
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
    public CartPageHelper getCartPage() {  return cartPageHelper;    }
    public ProductPageHelper getProductPageHelper() {
        return productPageHelper;
    }
}
