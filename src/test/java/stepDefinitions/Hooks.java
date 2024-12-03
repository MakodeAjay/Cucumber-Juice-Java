package stepDefinitions;

import Utils.InteractionHelper;
import Utils.ReporterFactory;
import Utils.RunnerHelper;
import Utils.TestContext;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks {
    @Inject
    InteractionHelper interactionHelper;
    @Inject
    TestContext testContext;

    @Before
    public void beforeScenario(Scenario scenario) {
        RunnerHelper.beforeTestSuit();
        log.info("beforeScenario " + scenario.getName());

        //Create new test for each scenario
        ExtentTest test = RunnerHelper.extent.createTest(scenario.getName());
        ReporterFactory.getInstance().setExtentTestList(test);
    }

    @AfterStep
    public void addScreenShots(Scenario scenario) {

		//Passed step adding screenshot
        if (!scenario.isFailed()) {
            ReporterFactory.getInstance().getExtentTest().pass(MediaEntityBuilder.createScreenCaptureFromBase64String(interactionHelper.takeScreenShotOfWebPage()).build());
        } else if (scenario.isFailed()) {
            ReporterFactory.getInstance().getExtentTest().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(interactionHelper.takeScreenShotOfWebPage()).build());
        }
    }

    @After
    public void AfterScenario(){
        RunnerHelper.tearDown();
        testContext.quitDriver();
    }


}
