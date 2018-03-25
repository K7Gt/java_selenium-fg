package stqa.litecart.com.Appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import stqa.litecart.com.Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AdminPageHelper extends HelperBase{

    public AdminPageHelper(WebDriver driver) {
        super(driver);
    }

    public void gotoPricesTab(){
        click(By.cssSelector("ul.index li:nth-child(4)"));
    }
    public void gotoInfoTab(){
        click(By.cssSelector("ul.index li:nth-child(2)"));
    }
    public void gotoCatalogTab() {
        click(By.cssSelector("li#app-:nth-child(2)"));
    }

    public List<String> getCountriesList(By rowsLocator, By cellLocator) {
        List<WebElement> rows = getListOfElements(rowsLocator);
        List<String> countries = new ArrayList<>();
        for (WebElement el:rows
                ) {
            if(!el.findElement(cellLocator).getAttribute("textContent").equals(""))
                countries.add(el.findElement(cellLocator).getText());
        }
        return countries;
    }

    public List<String> getZonesList(By rowsLocator, By cellLocator) {
        List<WebElement> rows = getListOfElements(rowsLocator);
        rows.remove(rows.size()-1);
        List<String> zones = new ArrayList<>();
        for (WebElement el:rows
                ) {
            WebElement cell =  el.findElement(cellLocator);
            zones.add(new Select(cell.findElement(By.cssSelector("select"))).getFirstSelectedOption().getText());
        }
        return zones;
    }


    public void fillPricesTab(Product product) {
        setImplicityWait(10);
        type(product.getPricesPurchasePrice(),By.cssSelector("input[name=purchase_price]"));
        selectFromPicklist(By.cssSelector("select[name=purchase_price_currency_code]"),product.getPricesPurchaseUnit());
        type(product.getPricesPriceUSD(),By.cssSelector("input[name='prices[USD]']"));
        type(product.getPricesPriceTaxUSD(),By.cssSelector("input[name='gross_prices[USD]']"));
        type(product.getPricesPriceEUR(),By.cssSelector("input[name='prices[EUR]']"));
        type(product.getPricesPriceTaxEUR(),By.cssSelector("input[name='gross_prices[EUR]']"));
    }

    public void fillInfoTab(Product product) {
        setImplicityWait(10);
        selectFromPicklist(By.cssSelector("select[name=manufacturer_id]"),product.getInfoManufacture());
        type(product.getInfoKeyword(),By.cssSelector("input[name=keywords]"));
        type(product.getInfoShortDesc(),By.cssSelector("input[name='short_description[en]']"));
        type(product.getInfoDesc(),By.cssSelector("div.trumbowyg-editor"));
        type(product.getInfoHeadTitle(),By.cssSelector("input[name='head_title[en]']"));
        type(product.getInfoMetaDesc(),By.cssSelector("input[name='meta_description[en]']"));
    }

    public void fillGeneralTab(Product product) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        isCheck(By.cssSelector("input[name= status]:first-child"), By.cssSelector("input[name= status]:last-child"),product.isGeneralStatus());
        type(product.getGeneralName(),By.cssSelector("span.input-wrapper input[name='name[en]'"));
        type(product.getGeneralCode(),By.cssSelector("input[name=code"));
        List<WebElement> list1 =  getListOfElements(By.cssSelector("div#tab-general tr:nth-child(4) div.input-wrapper td"));
        checkCheckbox(list1,product.getGeneralCategorie());
        List<WebElement> list2 = getListOfElements(By.cssSelector("div#tab-general tr:nth-child(7) div.input-wrapper td"));
        list2.remove(0);
        checkCheckbox(list2,product.getGeneralGender());
        type(product.getGeneralQuantity(),By.cssSelector("input[name=quantity]"));
        attachFile(product.getGeneralImg(), By.cssSelector("input[name='new_images[]'"));
        fillDate(By.cssSelector("input[name=date_valid_from]"),product.getGeneralDateFrom());
        fillDate(By.cssSelector("input[name=date_valid_to]"),product.getGeneralDateTo());
    }


}
