import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class RemoteTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() throws MalformedURLException {

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setPlatform(Platform.VISTA);
        driver = new RemoteWebDriver(new URL("http://192.168.1.214:4444/wd/hub"), capabilities);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }



    @Test
    public void testGoogle(){
        driver.get("http://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
//        wait.until(titleIs("webdriver - Поиск в Google"));

    }


}
