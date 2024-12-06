package md.tekwill.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import md.tekwill.managers.ConfigReaderManager;
import md.tekwill.managers.DriverManager;
import md.tekwill.managers.ScrollManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GenericSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();

    @Then("the URL contains the following keyword {string}")
    public void theURLContainsTheFollowingKeyword(String collectKeyWord) throws InterruptedException {
        Thread.sleep(2000);

        boolean containsKeyword = driver.getCurrentUrl().contains(collectKeyWord);
        Assertions.assertTrue(containsKeyword, "The URL contains : " + containsKeyword);
    }

    @Given("The {string} endpoint is accessed")
    public void theIsAccessed(String endpoint) {
        String fullLink= ConfigReaderManager.getProperty("baseUrl") + endpoint;
        driver.get(fullLink);
        System.out.println("The accessed link is:" + fullLink);
    }

    @And("a thread sleep of {int} seconds is executed")
    public void aThreadSleepOfSecondsIsExecuted(int timeToBeSlept) {
        try {
            Thread.sleep(timeToBeSlept * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the following list of error messages is displayed:")
    public void theFollowingListOfErrorMessagesIsDisplayed(List<String> listOfErrors) throws InterruptedException {
        Thread.sleep(500);

        listOfErrors.forEach(errorMessage -> {
            boolean messageIsDisplayed = driver.findElement(By.xpath(".//*[contains(text(),'" +
                    errorMessage + "')]")).isDisplayed();
            Assertions.assertTrue(messageIsDisplayed, "The error message is displayed");
        });

    }

    @When("the {string} from {string} is clicked")
    public void theFromIsClicked(String clickableElement, String pageName) throws Exception {
        Class classInstance = Class.forName("md.tekwill.pageobjects." + pageName);
        Field webClickableField = classInstance.getDeclaredField(clickableElement);
        webClickableField.setAccessible(true);
        WebElement webClickableElement = (WebElement) webClickableField.get(classInstance.getConstructor(WebDriver.class).newInstance(driver));
        ScrollManager.scrollToElement(webClickableElement);
        webClickableElement.click();
    }
}
