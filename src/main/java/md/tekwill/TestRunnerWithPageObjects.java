package md.tekwill;

import md.tekwill.managers.DriverManager;
import md.tekwill.managers.RandomDataManager;
import md.tekwill.pageobjects.AccountPage;
import md.tekwill.pageobjects.HomePage;
import md.tekwill.pageobjects.LoginPage;
import md.tekwill.pageobjects.RegisterPage;
import org.openqa.selenium.WebDriver;

public class TestRunnerWithPageObjects {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.get("https://tekwillacademy-opencart.online/");

        HomePage homePage = new HomePage(driver);
        homePage.navigateToRegisterPage();


        // Generate random data
        String firstName = RandomDataManager.getRandomFirstName();
        String lastName = RandomDataManager.getRandomLastName();
        String password = RandomDataManager.getRandomPassword();
        String email = RandomDataManager.getRandomEmail();

        // Actions on the Register page
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.completeTheRegisterForm(firstName, lastName, email, password);
        registerPage.enableTheToggleBar();
        registerPage.clickOnTheContinueBtn();


        Thread.sleep(2000);

        // Account related actions

        AccountPage accountPage = new AccountPage(driver);
        accountPage.logOutFromTheAccount();

        homePage.navigateToLoginPage();
        Thread.sleep(2000);

        // Login page related actions

        LoginPage loginPage = new LoginPage(driver);
        loginPage.completeLoginForm(email, password);
        loginPage.clickTheLoginBtn();
        Thread.sleep(5000);

        driver.quit();

    }
}
