package Utils;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import lombok.Data;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

/**
 * TestContext class
 * @author AjayMakode
 *
 */
@Data
@ScenarioScoped
public class TestContext {

    //code related to config reader
    @Getter
    ConfigUtils configUtils = ConfigFactory.create(ConfigUtils.class);

    @Inject
    DriverFactory driverFactory;

    public WebDriver driver;

    public void invokeDriver(){
        String browser = (System.getProperty("browser")==null) ? configUtils.getBrowser() : System.getProperty("browser");
        String execType = (System.getProperty("execType")==null) ? "local" : System.getProperty("execType");
        this.driver = driverFactory.getBrowser(browser,execType);
        this.driver.manage().window().maximize();
    }
    public void navigateBrowser(String url){
        this.driver.get(url);
    }
    public void quitDriver(){
        this.driver.quit();
    }
}
