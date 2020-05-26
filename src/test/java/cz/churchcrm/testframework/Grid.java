package cz.churchcrm.testframework;

import org.assertj.core.api.Assertions;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Grid {
    private ChromeDriver driver;
    private String cssSelector;

    public Grid(ChromeDriver driver, String cssSelector) {
        this.driver = driver;
        this.cssSelector = cssSelector;

//        driver.findElements()
        HashMap<String, List<String>> map;
    }

    public List<GridRow> search(String query) {
        WebElement gridWrapper = driver.findElement(By.cssSelector(cssSelector));
        WebElement searchInput = gridWrapper.findElement(By.cssSelector(".dataTables_filter input"));
        searchInput.sendKeys(query);



        return null;
    }

    public void getValues(String selektorProSloupec) {
        //driver.findElement(By.cssSelector("#" + cssSelector  + "thead tr " + selektorProSloupec))    // "#depositsTable_wrapper thead tr"
    }



    public void getCellValue(String columnKey, int rowNumber) {

    }

    public void shouldContain(String str) {
        WebElement gridWrapper = driver.findElement(By.cssSelector(cssSelector));
//        WebElement searchInput = gridWrapper.findElement(By.cssSelector(".dataTables_filter input"));
//        searchInput.sendKeys(str);

        WebElement tableElement = gridWrapper.findElement(By.cssSelector("table tbody"));

        assertThat(tableElement.getText()).contains(str);


//
//
//
//        WebDriverWait wait = new WebDriverWait(driver, 1);
//        wait.until(new ExpectedCondition<Boolean>() {
//
//            public Boolean apply(WebDriver input) {
//                WebElement gridWrapper = driver.findElement(By.cssSelector(cssSelector));
//                WebElement searchInput = gridWrapper.findElement(By.cssSelector(".dataTables_filter input"));
//                searchInput.sendKeys(str);
//
//                WebElement tableElement = gridWrapper.findElement(By.cssSelector("table tbody"));
//
//                return tableElement.getText().contains(str);
//            }
//        });
    }
}
