package stqa.litecart.com;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class PageCorrectnessTest{

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

    @Test
    public void testProductName(){
        String mainPageName = driver.findElement(By.cssSelector("div#box-campaigns div.name")).getAttribute("textContent");
        driver.findElement(By.cssSelector("div#box-campaigns a:first-child")).click();
        String productPageName = driver.findElement(By.cssSelector("div#box-product h1")).getAttribute("textContent");
        Assert.assertEquals(mainPageName,productPageName);

    }

    @Test
    public void testProductPrice(){
        String mainPagePrice = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper s")).getAttribute("textContent");
        String mainPageSalesPrice = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper strong")).getAttribute("textContent");

        driver.findElement(By.cssSelector("div#box-campaigns a:first-child")).click();

        String productPagePrice = driver.findElement(By.cssSelector("div#box-product div.price-wrapper s")).getAttribute("textContent");
        String productPageSalesPrice = driver.findElement(By.cssSelector("div#box-product div.price-wrapper strong")).getAttribute("textContent");

        Assert.assertEquals(mainPagePrice,productPagePrice);
        Assert.assertEquals(mainPageSalesPrice,productPageSalesPrice);

    }


    @Test
    public  void testCommonPriceColorsDecorate(){
        String mainPagePriceColor = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper s")).getCssValue("color");
        String mainPagePriceDecorate = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper s")).getCssValue("text-decoration-line");

        Assert.assertTrue(isGray(getColorsSet(mainPagePriceColor)));
        Assert.assertEquals("line-through",mainPagePriceDecorate);

        driver.findElement(By.cssSelector("div#box-campaigns a:first-child")).click();

        String productPagePriceColor = driver.findElement(By.cssSelector("div#box-product div.price-wrapper s")).getCssValue("color");
        String productPagePriceDecorate = driver.findElement(By.cssSelector("div#box-product div.price-wrapper s")).getCssValue("text-decoration-line");

        Assert.assertTrue(isGray(getColorsSet(productPagePriceColor)));
        Assert.assertEquals("line-through",productPagePriceDecorate);
    }

    @Test
    public  void testSalesPriceColorsDecorate(){

        String mainPageSalesPriceColor = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper strong")).getCssValue("color");
        String mainPageSalesPriceWeight = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper strong")).getCssValue("font-weight");

        Assert.assertTrue(isRed(getColorsSet(mainPageSalesPriceColor)));
        Assert.assertTrue(isBold(mainPageSalesPriceWeight));

        driver.findElement(By.cssSelector("div#box-campaigns a:first-child")).click();

        String productPageSalesPrice = driver.findElement(By.cssSelector("div#box-product div.price-wrapper strong")).getCssValue("color");
        String productPageSalesWeight = driver.findElement(By.cssSelector("div#box-product div.price-wrapper strong")).getCssValue("font-weight");

        Assert.assertTrue(isRed(getColorsSet(productPageSalesPrice)));
        Assert.assertTrue(isBold(productPageSalesWeight));

    }

    @Test
    public void testCommonSalesPriceSize(){
        String mainPagePriceFontSize = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper s")).getCssValue("font-size");
        String mainPageSalesPriceFontSize = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper strong")).getCssValue("font-size");

        Assert.assertTrue((fontSize(mainPagePriceFontSize) < fontSize(mainPageSalesPriceFontSize)));

        driver.findElement(By.cssSelector("div#box-campaigns a:first-child")).click();

        String productPagePriceFontSize = driver.findElement(By.cssSelector("div#box-product div.price-wrapper s")).getCssValue("font-size");
        String productPageSalesFontSize = driver.findElement(By.cssSelector("div#box-product div.price-wrapper strong")).getCssValue("font-size");

        Assert.assertTrue((fontSize(productPagePriceFontSize) < fontSize(productPageSalesFontSize)));

    }



    private List<String> getColorsSet(String rawColor){
        return Arrays.asList(rawColor.trim()
                .replaceAll("[/[^0-9,]/]","")
                .split(","));
    }

    private boolean isGray(List<String> rgbColor){
        return (rgbColor.get(0).equals(rgbColor.get(1)) && rgbColor.get(0).equals(rgbColor.get(2)));
    }

    private boolean isRed(List<String> rgbColor){
        return rgbColor.get(1).equals(rgbColor.get(2)) && rgbColor.get(1).equals("0");
    }

    private boolean isBold(String weight){
        return Integer.parseInt(weight)>=700?true:false;
    }

    private double fontSize(String weight){
        String result = weight.replaceAll("px","");
        return Double.parseDouble(result);
    }

}
