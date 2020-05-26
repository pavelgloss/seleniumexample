package cz.churchcrm.testframework;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.UUID;

public class DepositListingPage {
    private ChromeDriver driver;

    public DepositListingPage(ChromeDriver driver) {

        this.driver = driver;
    }

    public void addDeposit(String depositComment, String date) {
        WebElement commentInput = driver.findElementByCssSelector("#depositComment");
        commentInput.sendKeys(depositComment);

        WebElement dateInput = driver.findElementByCssSelector("#depositDate");
        dateInput.sendKeys(date);

        WebElement depositTypeSelect = driver.findElementByCssSelector("#depositType");
        Select depositType = new Select(depositTypeSelect);
        depositType.selectByValue("Blabla");

        WebElement addDepositButton = driver.findElementByCssSelector("#addNewDeposit");
        addDepositButton.click();
    }
}
