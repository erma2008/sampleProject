package Runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src\\test\\resources\\com.cucumber.features\\WebOrder\\CreateOrder.feature"},
        glue = "StepDefinitions\\WebOrderStepDefinitions",
        dryRun = false,
        monochrome = true
)
public class cukesRunner {
}

//src/test/resources/com.cucumber.features/WebOrder

