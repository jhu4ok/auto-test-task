package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@CucumberOptions(
        tags = "@api",
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks"},
        plugin = {"pretty", "html:target/site/cucumber-pretty",
                "json:target/jsonreports/cucumber.json"})
public class TestRunner extends AbstractTestNGCucumberTests {

    @AfterSuite()
    public void afterAllCleanUp() {
        generateReport();
    }

    public void generateReport() {
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/jsonreports/cucumber.json");
        String projectName = "API Test Task";
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

}
