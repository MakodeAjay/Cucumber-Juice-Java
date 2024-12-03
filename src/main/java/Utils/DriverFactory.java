package Utils;

import io.cucumber.guice.ScenarioScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DriverFactory class
 * @author AjayMakode
 *
 */
@ScenarioScoped
@Slf4j
public class DriverFactory {

    @Inject
    TestContext testContext;

    /**
     * Get browserName ad environment value
     *
     * @param string , string
     *
     * @return webdriver instance
     */
    public WebDriver getBrowser(String browserName, String environmentName){
        WebDriver driver = null;
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);

        if(browserName.equalsIgnoreCase("chrome")){
            // Suppress ChromeDriver verbose logging
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
            ChromeOptions choptions = new ChromeOptions();
            choptions.addArguments("--incognito");
            if(environmentName.equalsIgnoreCase("local"))
            {
                driver = new ChromeDriver(choptions);
            }else
            {
                log.info("************************ environmentName not recognised ************************");
            }
        }else if(browserName.equalsIgnoreCase("firefox"))
        {
            FirefoxOptions foptions = new FirefoxOptions();
            foptions.addArguments("-private");
            driver = new FirefoxDriver(foptions);
        }
        return driver;
    }
}
