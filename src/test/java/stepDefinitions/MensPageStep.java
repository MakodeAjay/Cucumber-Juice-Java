package stepDefinitions;

import com.google.inject.Inject;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CoreProductPage;

public class MensPageStep {
	@Inject
	CoreProductPage coreProductPage;

	@Then("User redirect to {} page")
	public void userRedirectToGOLDENSTATEWARRIORSMENPage(String pageName) {
		coreProductPage.verifyGOLDENSTATEWARRIORSMENPage(pageName);
	}

	@When("User select Jackets from all department section")
	public void userSelectJacketsFromAllDepartmentSection() {
		coreProductPage.clickOnDepartment();
	}

	@Then("User is able to see all Jackets on page")
	public void userIsAbleToSeeAllJacketsOnPage() {
		coreProductPage.getJacketInfo();
	}
}
