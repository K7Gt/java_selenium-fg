import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GoogleTest {

    private WebDriver driver;
    private WebDriverWait wait;
    public BrowserMobProxy proxy;

    public static class MyListener extends AbstractWebDriverEventListener{
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

    @Before
    public void start(){
        proxy = new BrowserMobProxyServer();
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

//        driver = new EventFiringWebDriver(new ChromeDriver(capabilities));
        driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);




    }

    @Test
    public void getLogs(){
        proxy.newHar();
        driver.get("http://www.selenium2.ru");
        Har har = proxy.endHar();
        har.getLog().getEntries().forEach(l -> System.out.println(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));


    }

//    @Test
//    public void testGoogle(){
//        driver.get("http://www.google.com/");
//        driver.findElement(By.name("q")).sendKeys("webdriver");
//        driver.findElement(By.name("btnK")).click();
//        wait.until(titleIs("webdriver - Поиск в Google"));
//
//    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }


    public int gettingLogs(){
        return driver.manage().logs().get("browser").getAll().size();
    }

}
