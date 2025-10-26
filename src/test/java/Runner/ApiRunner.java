package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/resources/Features/Api.feature"},
        tags = "@apiPetStore",
        glue = {"Definitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml"
        }
)
public class ApiRunner extends AbstractTestNGCucumberTests {
}