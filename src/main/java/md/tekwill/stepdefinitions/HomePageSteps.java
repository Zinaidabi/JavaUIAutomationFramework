package md.tekwill.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import md.tekwill.managers.DriverManager;
import md.tekwill.pageobjects.HomePage;
import org.openqa.selenium.WebDriver;

public class HomePageSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    HomePage homePage = new HomePage(driver);


    @Given("The home Page is displayed")
    public void theHomePageIsDisplayed() {
        driver.get("https://tekwillacademy-opencart.online/");

    }

    @And("Register Page is accessed from the Home Page button")
    public void registerPageIsAccessedFromTheHomePageButton() {
        homePage.navigateToRegisterPage();
        System.out.println("Code from step 2");
    }
}
