import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class GoogleTest {

    private EventFiringWebDriver driver;
    private WebDriverWait wait;

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
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);

    }

    @Test
    public void getLogs(){
        driver.get("https://onliner.by");
        System.out.println(driver.manage().logs().getAvailableLogTypes());
        List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
        System.out.println(logs.size());
        System.out.println(logs.size());

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
