package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/resources/Features/Api.feature"},
        tags = "@apiWithAuth",
        glue = {"Definitions"}
)
public class apiTestRunner extends AbstractTestNGCucumberTests {
}