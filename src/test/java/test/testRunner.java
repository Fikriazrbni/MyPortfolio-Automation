package test;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/resources/Features/apiScenario.feature"},
        glue = {"definitions"}
)
public class testRunner {
}
