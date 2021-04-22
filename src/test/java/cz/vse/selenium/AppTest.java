package cz.vse.selenium;

import cz.churchcrm.testframework.*;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private ChromeDriver driver;

    @Before
    public void setup() {
        ChromeOptions cho = new ChromeOptions();

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
//        ChromeDriverService service = new ChromeDriverService()
        driver = new ChromeDriver(cho);
        driver.manage().window().maximize();
    }

    @Test
    public void loginUsingValidCredentials() {
        // GIVEN
        // OrangeHRM demo login website is opened
        driver.get("https://opensource-demo.orangehrmlive.com/");

        // WHEN
        // user enters valid username
        WebElement userNameInput = driver.findElement(By.cssSelector("#txtUsername"));
        userNameInput.sendKeys("Admin");
        // user enters valid password
        WebElement userPasswordInput = driver.findElement(By.cssSelector("#txtPassword"));
        userPasswordInput.sendKeys("admin123");
        // user click Login button
        WebElement loginButton = driver.findElement(By.cssSelector("#btnLogin"));
        loginButton.click();

        //loginButton.isDisplayed()

        // THEN

        //Assert.assertEquals("OrangeHRM", driver.getTitle());


        List<WebElement> list = driver.findElementsByCssSelector("#frmLogin");
        //Assert.assertEquals(0, list.size());
        Assert.assertTrue(list.isEmpty());
    }


    @Test
    public void czcExamples() throws InterruptedException {
        driver.get("https://www.czc.cz");

        WebElement searchText = driver.findElementByCssSelector("#fulltext");
        searchText.sendKeys("sluchatka");
        Thread.sleep(4000);

        List<WebElement> suggestionCategoryItems = driver.findElementsByCssSelector(".autocompleteui-group.category .autocompleteui-item span.ac__content");
        Assert.assertTrue(suggestionCategoryItems.get(0).getText().startsWith("Sluchátka"));



        WebDriverWait wait = new WebDriverWait(driver, 20);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("xxxxxxxx")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#my-button")));

        // tady pokracuje program

//
//
//        WebElement searchButton = driver.findElementByCssSelector(".fulltext__btn");
//        searchButton.click();



//        List<WebElement> menuItemList = driver.findElementsByCssSelector(".main-menu-wrapper .main-menu__dep");
//        menuItemList.get(1).click();

    }



    @Test
    public void case1findByID() {
        loginUsingValidCredentials();

        driver.get(" https://opensource-demo.orangehrmlive.com/index.php/recruitment/viewCandidates");

        // by ID
        // WebElement candidatesMenuItem = driver.findElementById("menu_recruitment_viewCandidates");

        // by attribute's href value
        // WebElement candidatesMenuItem = driver.findElementByCssSelector("a[href='/index.php/recruitment/viewCandidates']");

        // by full xpath
        // WebElement candidatesMenuItem = driver.findElementByXPath("/html/body/div[1]/div[2]/ul/li[5]/ul/li[1]/a");

        // by xpath and id atrribute
        // WebElement candidatesMenuItem = driver.findElementByXPath("//*[@id='menu_recruitment_viewCandidates']");

        // by full path css selector
        WebElement candidatesMenuItem = driver.findElement(By.cssSelector("html body div:nth-child(0) div:nth-child(1) ul li:nth-child(4) ul li:nth-child(0) a"));
        // $("html body div:nth-of-type(1) div:nth-of-type(2) ul li:nth-of-type(5) ul li:nth-child(1)")


        //WebElement candidatesMenuItem = driver.findElement(By.cssSelector("ul>li.selected a"));
        // TODO apson 2 jeste

        // WebElement candidatesMenuItem = driver.findElementByLinkText("Candidates");


        WebElement candidateForm = driver.findElementById("frmSrchCandidates");
        List<WebElement> buttonList = candidateForm.findElements(By.cssSelector("input[type=button]"));
        buttonList.get(0).click();

        driver.findElementsByCssSelector("form");
        //driver.findElementsByCssSelector("input[name=firstName]");
        driver.findElementsByName("firstName");

//                 <input type="text" name="firstName" />
        //driver.findElementByTagName("form")


//        candidatesMenuItem.click();


    }



    @Test
    public void shouldAddCandidate() {
        // GIVEN
        loginUsingValidCredentials();

        // WHEN
        // open Candidates page
        // click add button
        // fill in candidate attributes and click Save

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/recruitment/viewCandidates");
        WebElement addButton = driver.findElementByCssSelector("#btnAdd");
        addButton.click();
        WebElement firstNameInput = driver.findElementByCssSelector("#addCandidate_firstName");
        firstNameInput.sendKeys("Pavel");
        WebElement lastNameInput = driver.findElementByCssSelector("#addCandidate_lastName");
        lastNameInput.sendKeys("Gloss");
        WebElement emailInput = driver.findElementByCssSelector("#addCandidate_email");
        emailInput.sendKeys("pavel@pavel.pavel");
        WebElement saveButton = driver.findElementByCssSelector("#btnSave");
        saveButton.click();


        // THEN
        // no more on page
        // on page https://opensource-demo.orangehrmlive.com/index.php/recruitment/addCandidate/id/XXXX
        // input field with ID "addCandidate_firstName" is disabled

        // TODO fragile, redo
        Assert.assertNotEquals("https://opensource-demo.orangehrmlive.com/index.php/recruitment/addCandidate", driver.getCurrentUrl());

        Assert.assertTrue(driver.getCurrentUrl().contains("/recruitment/addCandidate/id/"));

        WebElement disabledNameInput = driver.findElementByCssSelector("#addCandidate_firstName");
        Assert.assertFalse(disabledNameInput.isEnabled());
    }



    //https://opensource-demo.orangehrmlive.com/index.php/recruitment/viewCandidates

    @After
    public void tearDown() {
//        driver.close();
    }


    @Test
    public void google1_should_pass() {
        // GIVEN
        driver.get("https://www.google.com/");

        // WHEN
        WebElement searchInput = driver.findElementByName("q");
        searchInput.sendKeys("koloběžka");
//        searchInput.sendKeys(Keys.ENTER);
        driver.findElementByName("btnK").click();

        // THEN
        Assert.assertTrue(driver.getTitle().startsWith("koloběžka - "));
        assertThat(driver.getTitle()).startsWith("koloběžka - ");

        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.google.com/search?"));
        Assert.assertTrue(driver.getCurrentUrl().contains("q=kolob%C4%9B%C5%BEka"));


        driver.quit();
    }

    @Test
    public void alzaTest() throws InterruptedException {
        driver.get("https://www.alza.cz/");
        WebElement searchInput = driver.findElement(By.cssSelector("#edtSearch"));
        searchInput.sendKeys("wifi");

        List<WebElement> items = driver.findElements(By.cssSelector("#ui-id-1 li.t3"));
        Assert.assertTrue(items.get(0).getAttribute("title").equals("TP-LINK TL-WN722N"));


        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver input) {
                List<WebElement> items = driver.findElements(By.cssSelector("#ui-id-1 li.t3"));    // idealne 3 kusy
                if (!items.isEmpty() && items.get(0).getAttribute("title").equals("TP-LINK TL-WN722N")) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        wait.until(ExpectedConditions.attributeToBe(By.cssSelector("#ui-id-1 li.t3"), "title", "expected hodnota"));



//        List<WebElement> searchItems = driver.findElements(By.cssSelector("#ui-id-1>li.t3"));
//        Assert.assertEquals(3, searchItems.size());
       // driver.quit();
    }

    @Test
    public void google2_should_fail() {
        driver.get("https://www.google.com/");
       // WebElement searchInputNotExisting = driver.findElement(By.name("kdsfkladsjfas"));
        driver.quit();
    }

    @Test
    public void google3_should_fail() {
        driver.get("https://www.google.com/");
        Assert.assertEquals("one", "one");
        driver.quit();
    }

    public void shouldNotLoginUsingInvalidPassword() {
        // given
        driver.get("https://opensource-demo.orangehrmlive.com/");

        // when
        WebElement usernameInput = driver.findElement(By.id("txtUsername"));
        usernameInput.sendKeys("admin");
        WebElement passwordInput = driver.findElement(By.id("txtPassword"));
        passwordInput.sendKeys("invalidPassssssssword");
        WebElement loginButton = driver.findElement(By.id("btnLogin"));
        loginButton.click();

        // then
        WebElement errorMessageSpan = driver.findElement(By.id("spanMessage"));
        Assert.assertEquals("Invalid credentials", errorMessageSpan.getText());

        // validation error exists
        // url changed to https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials
        // there is no menu
    }


    @Test
    public void shouldLoginUsingValidCredentials() {
        // given
        //driver.get("http://demo.churchcrm.io/master/");
        driver.get("http://digitalnizena.cz/church/");

        // when
        WebElement usernameInput = driver.findElement(By.id("UserBox"));
        usernameInput.sendKeys("church");
        WebElement passwordInput = driver.findElement(By.id("PasswordBox"));
        passwordInput.sendKeys("church12345");
        WebElement loginButton = driver.findElement(By.className("btn-primary"));
        loginButton.click();
    }

    public void shouldCreateNewUser() throws InterruptedException {
        // Given
        shouldLoginUsingValidCredentials();

        // When
        driver.get("http://digitalnizena.cz/church/PersonEditor.php");

        WebElement genderSelectElement = driver.findElement(By.name("Gender"));
        Select genderSelect = new Select(genderSelectElement);
        genderSelect.selectByVisibleText("Male");

        WebElement firstNameInput = driver.findElement(By.id("FirstName"));
        firstNameInput.sendKeys("John");
        WebElement lastNameInput = driver.findElement(By.id("LastName"));
        String uuid = UUID.randomUUID().toString();
        lastNameInput.sendKeys("Doe " + uuid);
        WebElement emailInput = driver.findElement(By.name("Email"));
        emailInput.sendKeys("john.doe@gmail.com");

        WebElement classificationSelectElement = driver.findElement(By.name("Classification"));
        Select classificationSelect = new Select(classificationSelectElement);
        classificationSelect.selectByIndex(4);

        WebElement personSaveButton = driver.findElement(By.id("PersonSaveButton"));
        personSaveButton.click();

        // Then
        driver.get("http://digitalnizena.cz/church/v2/people");

        WebElement searchInput = driver.findElement(By.cssSelector("#members_filter input"));
        searchInput.sendKeys(uuid);
        Thread.sleep(500);

        // to verify if record is shown in table grid, we first filter the whole table to get exactly one data row
        // that row should contain previously generated UUID value (in last name
        // UKOL...opravit, doplnit tak, aby se provedla verifikace ze kontakt, ktery jsme vytvorili opravdu existuje
        //    (jde vyhledat a zobrazi se v tabulce)
        //    doporucuji radek tabulky s danou osobou projit (traverzovat), nebo jinym zpusobem v nem najit retezec UUID, ktery jednoznacne identifikuje pridanou osobu
        List<WebElement> elements = driver.findElements(By.cssSelector("table#members tr"));
        Assert.assertEquals(2, elements.size());

        // data row is at index 0, header row is at index 1  (because in ChurchCRM html code there is tbody before thead)
        WebElement personTableRow = elements.get(0);


        // option1
        Assert.assertTrue(personTableRow.getText().contains(uuid));

        // option2 - traverse all cells in table grid
        List<WebElement> cells = personTableRow.findElements(By.tagName("td"));
        final int EXPECTED_COLUMNS = 9;
        Assert.assertEquals(EXPECTED_COLUMNS, cells.size());
        for (int i = 0; i < cells.size(); i++) {
            WebElement cell = cells.get(i);
            if (cell.getText().contains(uuid)) {
                //
            }

            System.out.println(cells.get(i).getText());
        }
    }



    @Test
    public void given_userIsLoggedIn_when_userAddsNewDeposit_then_depositRecordIsShownInDepositTableGrid() throws InterruptedException {
        // GIVEN user is logged in

        shouldLoginUsingValidCredentials();

        // WHEN user adds deposit comment

        driver.get("http://digitalnizena.cz/church/FindDepositSlip.php");

        WebElement depositCommentInput = driver.findElement(By.cssSelector("#depositComment"));
        String uuid = UUID.randomUUID().toString();
        String depositComment = "deposit-PavelG-" + uuid;
        depositCommentInput.sendKeys(depositComment);

        WebElement depositDateInput = driver.findElement(By.cssSelector("#depositDate"));
        depositDateInput.click();
        depositDateInput.clear();
        depositDateInput.sendKeys("2018-10-30");

        WebElement addDepositButton = driver.findElement(By.cssSelector("#addNewDeposit"));
        addDepositButton.click();

        // THEN newly added deposit should be shown in deposits table grid

        // option1 - wait exactly 2 seconds, blocks the thread ....not recommended
        // Thread.sleep(2000);

        // option2 - use custom "expected condition" of WebDriver framework
        WebDriverWait wait = new WebDriverWait(driver, 2);     // timeout after 2 seconds
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                // each time, we try to get the very first row from table grid and check, if contains the last record

                List<WebElement> depositRows = driver.findElements(By.cssSelector("#depositsTable_wrapper #depositsTable tbody tr"));
                WebElement firstRow = depositRows.get(0);
                String innerHTML = firstRow.getAttribute("innerHTML");

                if (innerHTML.contains(uuid)) {
                    Assert.assertTrue(innerHTML.contains("10-30-18"));    // beware, different date format in table grid vs. input field
                    Assert.assertTrue(innerHTML.contains(depositComment));
                    return true;     // expected condition is met
                } else {
                    return false;    // selenium webdriver will continue polling the DOM each 500ms and check the expected condition by calling method apply(webDriver) again
                }
            }
        });
    }

    public void deleteDeposits() throws InterruptedException {
        shouldLoginUsingValidCredentials();

        driver.get("http://digitalnizena.cz/church/FindDepositSlip.php");

        Thread.sleep(1000);

        List<WebElement> depositRows = driver.findElements(By.cssSelector("#depositsTable tbody tr"));

        for (WebElement row : depositRows) {
            row.click();
        }

//
        WebElement deleteButton = driver.findElement(By.cssSelector("#deleteSelectedRows"));
        deleteButton.click();
//
//        //TODO compare this WebElement confirmDeleteButton = driver.findElement(By.cssSelector(".modal-dialog .btn-primary"));
        WebElement confirmDeleteButton = driver.findElement(By.cssSelector(".modal-content > .modal-footer .btn-primary"));
        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOf(confirmDeleteButton));
        confirmDeleteButton.click();

//        // actually the application behaves incorrect => when delete all rows, Delete button should be disabled
//        // we have our test correct, so it good that test fails!
        Assert.assertFalse(deleteButton.isEnabled());
    }

    @Test
    public void loadingExample() throws InterruptedException {
        driver.get("http://digit107.wwwnlss4.a2hosted.com/priklad/loading3.html");



        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until( ExpectedConditions.elementToBeClickable(By.cssSelector("#my-button")) );


        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver1) {
                try {
                    driver1.findElement(By.cssSelector("#my-button"));

                } catch (Exception e) {
                    System.out.println("element nenalezen...");
                    return false;
                }

                System.out.println("element NALEZEN...");
                return true;
            }
        });



//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#my-button")));

//        WebElement spinner = driver.findElement(By.cssSelector("#spinner"));
//        spinner.click();

        // here in code, we are 100% sure, that button is visible
    }

    @Test
    public void userPepaExistsInSystem_whenUserLogsWithValidPassword_thenUserIsLoggedIntoDashboard() {
        // Given
        driver.get("http://digitalnizena.cz/church/");

        // when
        WebElement usernameInput = driver.findElement(By.cssSelector("#UserBox"));
        usernameInput.sendKeys("church");

        WebElement passwordInput = driver.findElement(By.id("PasswordBox"));
        passwordInput.sendKeys("church12345");

        WebElement loginButton = driver.findElement(By.cssSelector(".btn-primary"));
        loginButton.click();

        // Then
        Assert.assertTrue(driver.getCurrentUrl().equals("https://digitalnizena.cz/church/Menu.php"));
        Assert.assertTrue(driver.getTitle().equals("ChurchCRM: Welcome to"));
        Assert.assertTrue(driver.findElements(By.id("Login")).isEmpty());
    }

    @Test
    public void loginWithInvalidCredentials_userStaysAtLoginPage() {


    }


    @Test
    public void shouldAddDepositRecord() {
        // Given
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();

        DepositPage depositPage = new DepositPage(driver);
        DepositListingPage depositsPage = depositPage.gotoAllDeposits();

        // WHEN

        Grid depositsGrid = new Grid(driver, "#depositsTable_wrapper");
        int rowCount = depositsGrid.getRowCount();

        String depositComment = "PG123" ;  //+ UUID.randomUUID();
        String date = "2021-04-08";
        depositsPage.addDeposit(depositComment, date);

        // THEN

        depositsGrid.shouldContain(depositComment, rowCount);

//
//
//
//        //////////////////
//        List<GridRow> rows = depositsGrid.search(depositComment);
//        rows.get(0).shouldContain(depositComment);
//        rows.get(0).shouldContain("datum");
//        rows.get(0).shouldContain("Bank");
//
//        depositsGrid.getValues("selektorProSloupec");
//
////        depositsGrid.getRows(Column xxx);
////        Assert.assertTrue(rows.get(0).getDepositComment().equals(depositComment));

    }


}
