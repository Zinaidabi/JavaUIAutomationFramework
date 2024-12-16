package md.tekwill;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/java/md/tekwill/features",
        glue = "md.tekwill.stepdefinitions",
        tags = "",
        plugin = {"pretty", "json:target/reports/cucumber.json", "html:target/reports/cucumber.html"}

)
public class CucumberTestRunner {
}
