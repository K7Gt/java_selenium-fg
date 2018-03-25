package stqa.litecart.com;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stqa.litecart.com.Model.Product;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddNewProductTest extends TestBase {

    @Before

    public void preconditions(){
        driver.get("http://localhost/litecart/admin");
        login();

    }
    Product product = new Product(
            true,
            "Test product",
            "Test",
            "Rubber Ducks",
            "Male",
            "7",
            new File("src/test/resources/samurai.jpg"),
            "ACME Corp.",
            "Samurai",
            "Samurai description",
            "Samurai is the military nobility and officer caste of medieval and early-modern Japan.",
            "Samurai",
            "Bushido",
            "777",
            "US Dollars",
            "770",
            "700",
            "550",
            "450"
    );

    @Test
    public void testAddNewProduct(){
        gotoCatalogTab();

//        String language = driver.findElement(By.cssSelector());

        driver.findElement(By.cssSelector("td#content div:nth-child(2) a:nth-child(2)")).click();
        fillGeneralTab(product);
        gotoInfoTab();
        fillInfoTab(product);
        gotoPricesTab();
        fillPricesTab(product);
        submit();


        List<WebElement> categories = driver.findElements(By.cssSelector("tr.row"));
        boolean result = isThereProduct(categories, product.getGeneralName());

        Assert.assertTrue(result);

    }

    private boolean isThereProduct(List<WebElement> categories, String product) {
        categories.remove(0);
        for (WebElement el:categories
             ) {
            if(el.findElement(By.cssSelector("td:nth-child(3) a")).getAttribute("textContent").equals(product)){
                return true;
            }
        }
        return false;
    }

    private void submit() {
        driver.findElement(By.cssSelector("button[name=save]")).click();
    }

    private void fillPricesTab(Product product) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        type(product.getPricesPurchasePrice(),By.cssSelector("input[name=purchase_price]"));
        selectFromPicklist(By.cssSelector("select[name=purchase_price_currency_code]"),product.getPricesPurchaseUnit());
        type(product.getPricesPriceUSD(),By.cssSelector("input[name='prices[USD]']"));
        type(product.getPricesPriceTaxUSD(),By.cssSelector("input[name='gross_prices[USD]']"));
        type(product.getPricesPriceEUR(),By.cssSelector("input[name='prices[EUR]']"));
        type(product.getPricesPriceTaxEUR(),By.cssSelector("input[name='gross_prices[EUR]']"));
    }

    private void fillInfoTab(Product product) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        selectFromPicklist(By.cssSelector("select[name=manufacturer_id]"),product.getInfoManufacture());
        type(product.getInfoKeyword(),By.cssSelector("input[name=keywords]"));
        type(product.getInfoShortDesc(),By.cssSelector("input[name='short_description[en]']"));
        type(product.getInfoDesc(),By.cssSelector("div.trumbowyg-editor"));
        type(product.getInfoHeadTitle(),By.cssSelector("input[name='head_title[en]']"));
        type(product.getInfoMetaDesc(),By.cssSelector("input[name='meta_description[en]']"));
    }

    private void fillGeneralTab(Product product) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        isCheck(By.cssSelector("input[name= status]:first-child"), By.cssSelector("input[name= status]:last-child"),product.isGeneralStatus());
        type(product.getGeneralName(),By.cssSelector("span.input-wrapper input[name='name[en]'"));
        type(product.getGeneralCode(),By.cssSelector("input[name=code"));
        List<WebElement> list1 =  driver.findElements(By.cssSelector("div#tab-general tr:nth-child(4) div.input-wrapper td"));
        checkCheckbox(list1,product.getGeneralCategorie());
        List<WebElement> list2 = driver.findElements(By.cssSelector("div#tab-general tr:nth-child(7) div.input-wrapper td"));
        list2.remove(0);
        checkCheckbox(list2,product.getGeneralGender());
        type(product.getGeneralQuantity(),By.cssSelector("input[name=quantity]"));
        attachFile(product.getGeneralImg(), By.cssSelector("input[name='new_images[]'"));
        fillDate(By.cssSelector("input[name=date_valid_from]"),product.getGeneralDateFrom());
        fillDate(By.cssSelector("input[name=date_valid_to]"),product.getGeneralDateTo());





    }

//    div#tab-general tr:nth-child(4) div.input-wrapper td
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


}
