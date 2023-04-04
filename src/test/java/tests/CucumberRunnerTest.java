package tests;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = {}, features = "classpath:features"
        , glue = "tests.stepdefinitions")

public class CucumberRunnerTest {
}