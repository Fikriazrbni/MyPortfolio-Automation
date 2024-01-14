package Runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/resources/Features/Dealls.feature"},
        glue = {"Definitions"}
)
public class DeallsRunner {
}
