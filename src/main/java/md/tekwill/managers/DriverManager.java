package md.tekwill.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    private static DriverManager instance;
    private WebDriver driver;
    private static final String Web_Driver_Type = "Chrome";

    private DriverManager() {
        switch (Web_Driver_Type.toUpperCase()) {
            case "CHROME":
                driver = new ChromeDriver();
                System.out.println("The Chrome Driver is opened.");
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                System.out.println("The Firefox Driver is opened.");
                break;
            case "EDGE":
                driver = new EdgeDriver();
                System.out.println("The Edge browser is opened.");
                break;
            case "SAFARI":
                driver = new SafariDriver();
                System.out.println("The Safari browser is opened.");
            default:
                System.out.println("The browser type is not defined: " + Web_Driver_Type);

        }
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

}


