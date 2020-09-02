package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //src/test/resources/APIFeature/
        features = "src\\test\\resources\\APIFeature\\BreakingBad.feature",
        glue ="API\\StepDefs",
        dryRun = false,
        tags = "@BreakingBad"
)
public class APIRunner {
}
