package cz.churchcrm.testframework;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {
    public static final String BASE_URL = "http://digit107.wwwnlss4.a2hosted.com/church2/";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage login(String username, String password) {
        driver.findElement(By.cssSelector("#UserBox"))
                .sendKeys(username);
        driver.findElement(By.cssSelector("#PasswordBox"))
                .sendKeys(password);
        driver.findElement(By.cssSelector(".btn-primary"))
                .click();

        return new DashboardPage(driver);
    }

    public DashboardPage login() {
        driver.get(BASE_URL);
        return this.login("church", "church12345");
    }

    public void shouldNotBeOpen() {
        Assert.assertEquals(driver.getCurrentUrl(), "xxxx");
        Assert.assertNotEquals(driver.getCurrentUrl(), "");
 //       Assert.assertEquals(driver.findElements(By.cssSelector(".login-box"))).isEmpty();
    }

    public void shouldBeOpen() {
//        assertThat(driver.getCurrentUrl()).isEqualTo(URL_LOGIN);
//        assertThat(driver.findElements(By.cssSelector(".login-box"))).isNotEmpty();
    }

    public void shouldBeErrorMessage() {
//        WebElement errorDiv = driver.findElement(By.cssSelector("#Login .alert.alert-error"));    // there are in fact two .alert .alert-error boxes in page, we want that for #Login div
//        assertThat(errorDiv.getText()).isEqualTo("Invalid login or password");
    }


}
