package stepDefinitions;

import com.google.inject.Inject;
import io.cucumber.java.en.Then;
import pages.CoreProductPage;

public class NewAndFeaturePageStep {
    @Inject
    CoreProductPage coreProductPage;

    @Then("User is able to see news and videos on page")
    public void userIsAbleToSeeNewsAndVideosOnPage() {
        coreProductPage.verifyNewAndFeaturePage();
    }

    @Then("User count total number of video on page")
    public void userCountTotalNumberOfVideoOnPage() {
        coreProductPage.verifyVideoFeedCount();
    }
}
