package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/resources/Features/Api.feature"},
        tags = "@apiPetStore",
        glue = {"Definitions"}
)
public class ApiRunner extends AbstractTestNGCucumberTests {
}