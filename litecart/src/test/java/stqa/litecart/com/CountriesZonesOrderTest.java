package stqa.litecart.com;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CountriesZonesOrderTest extends TestBase {

    @Before
    public  void preconditions(){
        driver.get("http://localhost/litecart/admin");
        login();

    }


    @Test
    public void testCountriesOrder(){
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<String> actual = getCountriesList(By.cssSelector("td#content tr.row"), By.cssSelector("td:nth-child(5) a"));
        List<String> expected = actual.stream().sorted().collect(Collectors.toList());
        assertThat(actual,is(expected));

        List<WebElement> rows = driver.findElements(By.cssSelector("tr.row"));
        for (int i = 0; i <rows.size() ; i++) {

            if(Integer.valueOf(rows.get(i).findElement(By.cssSelector("td:nth-child(6)")).getText())>0){
                rows.get(i).findElement(By.cssSelector("td:nth-child(5) a")).click();
                List<String> actualZoneList;
                List<String> expectedZoneList;
                actualZoneList = getCountriesList(By.cssSelector("table.dataTable tr:not([class])"), By.cssSelector("td:nth-child(3)"));
                expectedZoneList = actualZoneList.stream().sorted().collect(Collectors.toList());
                assertThat(actualZoneList,is(expectedZoneList));
                driver.findElement(By.cssSelector("button[name=cancel]")).click();

            }
            rows = driver.findElements(By.cssSelector("tr.row"));
        }

    }

    @Test
    public void testZonesOrder(){
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> rows = driver.findElements(By.cssSelector("tr.row"));
        for (int i = 0; i <rows.size() ; i++) {

            rows.get(i).findElement(By.cssSelector("td:nth-child(3) a")).click();
            List<String> actualZoneList;
            List<String> expectedZoneList;
            actualZoneList = getZonesList(By.cssSelector("table.dataTable tr:not([class])"), By.cssSelector("td:nth-child(3)"));
            expectedZoneList = actualZoneList.stream().sorted().collect(Collectors.toList());
            assertThat(actualZoneList,is(expectedZoneList));
            driver.findElement(By.cssSelector("button[name=cancel]")).click();

            rows = driver.findElements(By.cssSelector("tr.row"));
        }

    }



    private List<String> getCountriesList(By rowsLocator, By cellLocator) {
        List<WebElement> rows = driver.findElements(rowsLocator);
        List<String> countries = new ArrayList<>();
        for (WebElement el:rows
             ) {
            if(!el.findElement(cellLocator).getAttribute("textContent").equals(""))
            countries.add(el.findElement(cellLocator).getText());
        }
        return countries;
    }


    private List<String> getZonesList(By rowsLocator, By cellLocator) {
        List<WebElement> rows = driver.findElements(rowsLocator);
        rows.remove(rows.size()-1);
        List<String> zones = new ArrayList<>();
        for (WebElement el:rows
                ) {
            WebElement cell =  el.findElement(cellLocator);
            zones.add(new Select(cell.findElement(By.cssSelector("select"))).getFirstSelectedOption().getText());
        }
        return zones;
    }
}
