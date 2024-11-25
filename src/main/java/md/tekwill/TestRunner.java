package md.tekwill;

import md.tekwill.managers.DriverManager;
import md.tekwill.managers.RandomDataManager;
import md.tekwill.managers.ScrollManager;
import org.openqa.selenium.*;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.get("https://www.google.com/");
        System.out.println("The driver is on page:" + driver.getCurrentUrl());

        String theNameOfTheFirstTab = driver.getWindowHandle();

        // Open a new window and navigate to the Diez page

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://tekwillacademy-opencart.online/");
        System.out.println("The driver is on page:" + driver.getCurrentUrl());

        WebElement myAccountDropDownIcon = driver.findElement(By.xpath("//i[@class='fa-solid fa-user']"));
        myAccountDropDownIcon.click();

        WebElement registerLink = driver.findElement(By.xpath("//a[@class='dropdown-item'][normalize-space()='Register']"));
        registerLink.click();
        System.out.println("The driver is on page:" + driver.getCurrentUrl());

        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        firstNameInput.sendKeys(RandomDataManager.getRandomFirstName());

        WebElement lastNameInput = driver.findElement(By.id("input-lastname"));
        lastNameInput.sendKeys(RandomDataManager.getRandomLastName());

        WebElement emailInput = driver.findElement(By.id("input-email"));
        String emailData = RandomDataManager.getRandomEmail();
        System.out.println("Email: " + emailData);
        emailInput.sendKeys(emailData);

        WebElement passwordInput = driver.findElement(By.cssSelector("#input-password"));
        String passwordData = RandomDataManager.getRandomPassword(3, 21, true, true);
        System.out.println("Password: " + passwordData);
        passwordInput.sendKeys(passwordData);

        WebElement privacyToggleBar = driver.findElement(By.name("agree"));
        ScrollManager.scrollToElement(privacyToggleBar);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", privacyToggleBar);
        Thread.sleep(500);

        privacyToggleBar.click();


        WebElement continueButton = driver.findElement(By.cssSelector("button[type='submit']"));
        continueButton.click();

        Thread.sleep(3000);


        // Close the current tab
        driver.close();

        driver.switchTo().window(theNameOfTheFirstTab);
        System.out.println("The driver is on page:" + driver.getCurrentUrl());


        driver.quit();


    }
}