package org.nikita.api;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:",
    plugin = { "pretty", "html:target/cucumber-report.html" }
)
public class RunCucumberTest {
}
