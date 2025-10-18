package Runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = {"src/resources/Features/Dealls.feature"},
        glue = {"Definitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml"
        }
)
public class DeallsRunner extends AbstractTestNGCucumberTests {

}
