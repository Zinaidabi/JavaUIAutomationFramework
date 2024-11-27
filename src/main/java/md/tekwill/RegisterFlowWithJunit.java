package md.tekwill;

import md.tekwill.managers.DriverManager;
import md.tekwill.managers.RandomDataManager;
import md.tekwill.pageobjects.HomePage;
import md.tekwill.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class RegisterFlowWithJunit {

    private WebDriver driver;
    private HomePage homePage;
    private RegisterPage registerPage;

    @BeforeAll
    public static void beforeAllTheTest() {
        System.out.println("This method is run before all the tests from this class");
    }

    @BeforeEach
    public void beforeEachTest() {
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://tekwillacademy-opencart.online/");
        homePage = new HomePage(driver);
        homePage.navigateToRegisterPage();
        registerPage = new RegisterPage(driver);
    }

    @Test
    @DisplayName("User is redirected to Account page when registering with valid data")
    public void registerFlowWithValidDataRedirectsUserToAccountPage() throws InterruptedException {

        // Generate random data
        String firstName = RandomDataManager.getRandomFirstName();
        String lastName = RandomDataManager.getRandomLastName();
        String password = RandomDataManager.getRandomPassword();
        String email = RandomDataManager.getRandomEmail();

        // Actions on the Register page
        registerPage.completeTheRegisterForm(firstName, lastName, email, password);
        registerPage.enableTheToggleBar();
        registerPage.clickOnTheContinueBtn();


        Thread.sleep(2000);

        boolean urlContainSuccessKeyword = driver.getCurrentUrl().contains("success");
        Assertions.assertTrue(urlContainSuccessKeyword, "The URL of the page contain the Success keyword");
    }


    @Test
    @DisplayName("The user remains on the Register page when registering without accepting the terms and conditions")
    public void userRemainsOnTheRegisterPageWhenRegisteringWithoutAcceptingPrivacyRules() throws InterruptedException {

        // Generate random data
        String firstName = RandomDataManager.getRandomFirstName();
        String lastName = RandomDataManager.getRandomLastName();
        String password = RandomDataManager.getRandomPassword();
        String email = RandomDataManager.getRandomEmail();

        // Actions on the Register page
        registerPage.completeTheRegisterForm(firstName, lastName, email, password);
        registerPage.clickOnTheContinueBtn();

        Assertions.assertTrue(driver.getCurrentUrl().contains("register"), "The page url has the keyword register ");
    }

    @Test
    @DisplayName("Navigate to Login page from Register Page")
    public void navigateToLoginPageFromRegisterPage() {
        registerPage.navigateToLoginPage();
        Assertions.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @AfterEach
    public void afterEachTest() {
        DriverManager.getInstance().quitTheDriver();
    }

    @AfterAll
    public static void afterAllTheTests() {
        System.out.println("This method is executed after all the tests");
    }

}
