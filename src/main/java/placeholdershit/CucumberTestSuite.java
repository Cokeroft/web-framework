package placeholdershit;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

/**@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {
                "pretty", "html:target/serenity-reports//serenity-html-report",
                "json:target/serenity-reports/cucumber_report.json",
                "rerun:target/serenity-reports/rerun.txt",
                "timeline:test-output-thread/"
        }
        ,features = "classpath:features"
        ,tags = "@Math"
        ,glue={"tests.stepdefinitions"}
)*/

public class CucumberTestSuite {
}
