package md.tekwill.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverManager {
    private static DriverManager instance;
    private WebDriver driver;
    private static final String Web_Driver_Type = ConfigReaderManager.getProperty("browserType");


    private DriverManager() {
        switch (Web_Driver_Type.toUpperCase()) {
            case "CHROME":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--incognito");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
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
        int implicitWaitTime = Integer.valueOf(ConfigReaderManager.getProperty("implicitWaitTime"));
        int pageLoadTime = Integer.valueOf(ConfigReaderManager.getProperty("pageLoadTime"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
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

    public void quitTheDriver() {
        driver.quit();
        driver = null;
        instance = null;
    }

}


