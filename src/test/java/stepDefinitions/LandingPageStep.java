package stepDefinitions;

import Utils.TestContext;
import com.google.inject.Inject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.CoreProductPage;

public class LandingPageStep {

    @Inject
    TestContext testContext;
    @Inject
    CoreProductPage coreProductPage;

    @Given("User is on {} Page")
    public void userIsOnCoreProductPage(String pageName) {
        testContext.invokeDriver();
        if (pageName.equalsIgnoreCase("Core Product")){
        testContext.navigateBrowser(testContext.getConfigUtils().getCoreProductURL());
        } else if (pageName.equalsIgnoreCase("Dervied Product One")) {
            testContext.navigateBrowser(testContext.getConfigUtils().getDerviedProduct1URL());
        }else if (pageName.equalsIgnoreCase("Dervied Product Two")) {
            testContext.navigateBrowser(testContext.getConfigUtils().getDerviedProduct2URL());
        }
    }

    @When("User click on Mens submenu")
    public void userClickOnMensSubMenu() {
        coreProductPage.getMenPage();
    }

    @When("User click on New & Features under metaBallsMenu")
    public void userClickOnMetaBallsMenuSubmenu() {
        coreProductPage.getNewsPage();
    }
}
