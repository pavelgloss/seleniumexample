package cz.churchcrm.testframework;

import org.openqa.selenium.chrome.ChromeDriver;

public class DepositPage {
    private ChromeDriver driver;

    public DepositPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public DepositListingPage gotoAllDeposits() {
        driver.get(LoginPage.BASE_URL + "FindDepositSlip.php");
        return new DepositListingPage(driver);
    }
}
