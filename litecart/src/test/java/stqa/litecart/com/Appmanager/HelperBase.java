package stqa.litecart.com.Appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class HelperBase {
    WebDriver driver;


    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    public  void login() {
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

    public void selectNthElementInPicklist(By locator, int position){
        Select picklist = new Select(driver.findElement(locator));
        picklist.selectByIndex(position);
    }

    public void waitUntilTextChanged(By locator, String text){
        new WebDriverWait(driver, 5).until(not(ExpectedConditions.textToBePresentInElementLocated(locator,text)));
    }

    public void type(String text, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void gotoUrl(String urlAddress){
        driver.get(urlAddress);
    }

    public void click(By locator){
        driver.findElement(locator).click();
    }

    public List<WebElement> getListOfElements(By locator) {
        return driver.findElements(locator);
    }

    public WebElement getWebElement(By locator){
        return driver.findElement(locator);
    }

    public void executeJS(String s, WebElement button) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(s,button);
    }
    public void setImplicityWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public void attachFile(File file, By locator){

        driver.findElement(locator).sendKeys(file.getAbsolutePath());
    }

    public void fillDate(By locator, Date date){
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        String[] dateValue = formater.format(date.getTime()).split("-") ;
        String dd= dateValue[0];
        String mm= dateValue[1];
        String yyyy= dateValue[2];
        WebElement datePicker = driver.findElement(locator);
        datePicker.sendKeys(dd);
        datePicker.sendKeys(mm);
        datePicker.sendKeys(yyyy);

    }

    public void checkCheckbox(List<WebElement> elements, String value){

        for (int i = 1; i <= elements.size() ; i++) {
            if(i%2 !=0){
                if(elements.get(i-1).findElement(By.cssSelector("input")).isSelected()){
                    elements.get(i-1).findElement(By.cssSelector("input")).click();
                }
            }else if(i%2 == 0 && elements.get(i-1).getAttribute("textContent").trim().equals(value)){
                elements.get(i-2).findElement(By.cssSelector("input")).click();
            }
        }

    }
    public void waitAndClick(By locator){
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void stalenessWaitOf(WebElement element){
        new WebDriverWait(driver,5).until(ExpectedConditions.stalenessOf(element));
    }
}
