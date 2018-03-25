package stqa.litecart.com.Tests;

import org.junit.*;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

public class PageCorrectnessTest extends TestBase {


    @Before

    public void preconditions(){
        app.getNavigationHelper().gotoShopMainPage();
    }

    @Test
    public void testProductName(){
        String mainPageName = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-campaigns div.name")).
                getAttribute("textContent");
        app.getHelperBase().click(By.cssSelector("div#box-campaigns a:first-child"));
        String productPageName = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-product h1")).
                getAttribute("textContent");
        Assert.assertEquals(mainPageName,productPageName);

    }

    @Test
    public void testProductPrice(){
        String mainPagePrice = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-campaigns div.price-wrapper s")).
                getAttribute("textContent");
        String mainPageSalesPrice = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-campaigns div.price-wrapper strong")).
                getAttribute("textContent");

        app.getHelperBase().click(By.cssSelector("div#box-campaigns a:first-child"));

        String productPagePrice = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-product div.price-wrapper s")).
                getAttribute("textContent");
        String productPageSalesPrice = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-product div.price-wrapper strong")).
                getAttribute("textContent");

        Assert.assertEquals(mainPagePrice,productPagePrice);
        Assert.assertEquals(mainPageSalesPrice,productPageSalesPrice);

    }


    @Test
    public  void testCommonPriceColorsDecorate(){
        String mainPagePriceColor = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-campaigns div.price-wrapper s")).
                getCssValue("color");
        String mainPagePriceDecorate = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-campaigns div.price-wrapper s")).
                getCssValue("text-decoration-line");

        Assert.assertTrue(isGray(getColorsSet(mainPagePriceColor)));
        Assert.assertEquals("line-through",mainPagePriceDecorate);

        app.getHelperBase().click(By.cssSelector("div#box-campaigns a:first-child"));

        String productPagePriceColor = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-product div.price-wrapper s")).
                getCssValue("color");
        String productPagePriceDecorate = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-product div.price-wrapper s")).
                getCssValue("text-decoration-line");

        Assert.assertTrue(isGray(getColorsSet(productPagePriceColor)));
        Assert.assertEquals("line-through",productPagePriceDecorate);
    }

    @Test
    public  void testSalesPriceColorsDecorate(){

        String mainPageSalesPriceColor = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-campaigns div.price-wrapper strong")).getCssValue("color");
        String mainPageSalesPriceWeight = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-campaigns div.price-wrapper strong")).getCssValue("font-weight");

        Assert.assertTrue(isRed(getColorsSet(mainPageSalesPriceColor)));
        Assert.assertTrue(isBold(mainPageSalesPriceWeight));

        app.getHelperBase().click(By.cssSelector("div#box-campaigns a:first-child"));

        String productPageSalesPrice = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-product div.price-wrapper strong")).getCssValue("color");
        String productPageSalesWeight = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-product div.price-wrapper strong")).getCssValue("font-weight");

        Assert.assertTrue(isRed(getColorsSet(productPageSalesPrice)));
        Assert.assertTrue(isBold(productPageSalesWeight));

    }

    @Test
    public void testCommonSalesPriceSize(){
        String mainPagePriceFontSize = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-campaigns div.price-wrapper s")).getCssValue("font-size");
        String mainPageSalesPriceFontSize = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-campaigns div.price-wrapper strong")).getCssValue("font-size");

        Assert.assertTrue((fontSize(mainPagePriceFontSize) < fontSize(mainPageSalesPriceFontSize)));

        app.getHelperBase().click(By.cssSelector("div#box-campaigns a:first-child"));

        String productPagePriceFontSize = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-product div.price-wrapper s")).getCssValue("font-size");
        String productPageSalesFontSize = app.getHelperBase().
                getWebElement(By.cssSelector("div#box-product div.price-wrapper strong")).getCssValue("font-size");

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
