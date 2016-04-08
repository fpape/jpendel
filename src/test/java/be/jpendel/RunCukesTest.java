package be.jpendel;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber"}
        , features = "src/cucumber/resources", glue = "be.jpendel"
)
public class RunCukesTest {

}