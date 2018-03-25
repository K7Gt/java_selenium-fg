package stqa.litecart.com.Tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CountriesZonesOrderTest extends TestBase {

    @Before
    public  void preconditions(){
        app.getNavigationHelper().gotoAdminPage();
        app.getAdminPageHelper().login();
    }


    @Test
    public void testCountriesOrder(){
        app.getNavigationHelper().gotoUrl("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<String> actual = app.getAdminPageHelper().getCountriesList(By.cssSelector("td#content tr.row"), By.cssSelector("td:nth-child(5) a"));
        List<String> expected = actual.stream().sorted().collect(Collectors.toList());
        assertThat(actual,is(expected));

        List<WebElement> rows = app.getHelperBase().getListOfElements(By.cssSelector("tr.row"));
        for (int i = 0; i <rows.size() ; i++) {

            if(Integer.valueOf(rows.get(i).findElement(By.cssSelector("td:nth-child(6)")).getText())>0){
                rows.get(i).findElement(By.cssSelector("td:nth-child(5) a")).click();
                List<String> actualZoneList;
                List<String> expectedZoneList;
                actualZoneList = app.getAdminPageHelper().getCountriesList(By.cssSelector("table.dataTable tr:not([class])"), By.cssSelector("td:nth-child(3)"));
                expectedZoneList = actualZoneList.stream().sorted().collect(Collectors.toList());
                assertThat(actualZoneList,is(expectedZoneList));
                app.getHelperBase().click(By.cssSelector("button[name=cancel]"));

            }
            rows = app.getHelperBase().getListOfElements(By.cssSelector("tr.row"));
        }

    }

    @Test
    public void testZonesOrder(){
        app.getNavigationHelper().gotoUrl("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> rows = app.getHelperBase().getListOfElements(By.cssSelector("tr.row"));
        for (int i = 0; i <rows.size() ; i++) {

            rows.get(i).findElement(By.cssSelector("td:nth-child(3) a")).click();
            List<String> actualZoneList;
            List<String> expectedZoneList;
            actualZoneList = app.getAdminPageHelper().getZonesList(By.cssSelector("table.dataTable tr:not([class])"), By.cssSelector("td:nth-child(3)"));
            expectedZoneList = actualZoneList.stream().sorted().collect(Collectors.toList());
            assertThat(actualZoneList,is(expectedZoneList));
            app.getHelperBase().click(By.cssSelector("button[name=cancel]"));

            rows = app.getHelperBase().getListOfElements(By.cssSelector("tr.row"));
        }

    }







}
