package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //src/test/resources/MyappFeatures/MyAppautomation.feature
        features = "src\\test\\resources\\MyappFeatures\\MyAppautomation.feature",
        glue = "StepDefinitions",
        tags = "@db",
        dryRun = false
)
public class MyAppRunner {
}
