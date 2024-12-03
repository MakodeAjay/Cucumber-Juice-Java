package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.inject.Inject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * RunnerHelper class
 * @author AjayMakode
 *
 */
public class RunnerHelper {
    @Inject
    TestContext testContext;

    public static ExtentSparkReporter spark;
    public static ExtentReports extent;

    public static void beforeTestSuit() {
        //code related to report
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportFileName = "Test-Report-" + timeStamp + ".html";

        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Report/" + reportFileName);
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static void tearDown() {
        if (extent != null) {
            extent.flush();
        }
    }

    public void generateRunnerClass(String className, String featureFileName) {
        String runnerClassContent = String.format(
                "package your.package.runners;\n\n" +
                        "import io.cucumber.testng.AbstractTestNGCucumberTests;\n" +
                        "import io.cucumber.testng.CucumberOptions;\n\n" +
                        "@CucumberOptions(\n" +
                        "        features = \"src/test/resources/features/%s\",\n" +
                        "        glue = \"your.package.stepdefinitions\",\n" +
                        "        plugin = {\"pretty\", \"html:target/cucumber-reports/%s.html\"}\n" +
                        ")\n" +
                        "public class %s extends AbstractTestNGCucumberTests {\n" +
                        "}\n",
                featureFileName, className, className);
        try (FileWriter writer = new FileWriter(testContext.getConfigUtils().getRunnerFilePath() + className + ".java")) {
            writer.write(runnerClassContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
