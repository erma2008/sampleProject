package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target\\cucumber-html-report",
        "json:target\\cucumber.json","junit:target\\cucumber.xml"},
        features = "src\\test\\resources",
        glue = "StepDefinitions",
        monochrome = true,
        dryRun = false,
        tags = "@TC-15"
)
public class smokeRunner {
}
