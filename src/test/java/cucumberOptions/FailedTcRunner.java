package cucumberOptions;

import Utils.RunnerHelper;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

/**
 * Runner Class to run failed scenario
 * @author AjayMakode
 *
 */
@CucumberOptions(features = "@target/failed_scenario.txt",
        glue = "stepDefinitions", monochrome = true,
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json", "rerun:target/failed_scenario.txt"})
public class FailedTcRunner extends AbstractTestNGCucumberTests {
    /**
     Test can be executed parallel or sequentially;
     Set the parallel = true to execute test parallel,
     false to execute test sequentially
     * */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeClass
    public void beforeClass() { RunnerHelper.beforeTestSuit();}

    @AfterClass
    public void afterClass()
    {
        RunnerHelper.tearDown();
    }
}
