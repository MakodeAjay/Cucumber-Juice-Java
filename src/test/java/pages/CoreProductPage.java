package pages;

import Utils.*;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
@Slf4j
public class CoreProductPage {

    @Inject
    WaitHelper waitHelper;
    @Inject
    TestContext testContext;
    @Inject
    InteractionHelper interactionHelper;
    @Inject
    ActionHelper actionHelper;

    private By closeIcon = By.xpath("//div[@role='dialog']//div[text()='x']");
    private By menuName = By.xpath("//ul[@role='menubar']//span[text()='Shop']");
    private By shopSubMenu = By.xpath("//ul[@role='menubar']//a[@title=\"Men's\"]");
    private By jacketRadioButton = By.xpath("//div[@class='side-nav-facet-items allDepartmentsBoxes']//span[text()='Jackets']");
    private By jacketItem = By.xpath("//div[@class='column']");
    private By nextPageButton = By.xpath("//div[@class='grid-paginator']//a[@aria-label='next page']");
    private By metaBallsMenu = By.xpath("//li[@class='menu-item']//span[text()='...']");
    private By metaBallsSubMenu = By.xpath("//li[@class='menu-item']//a[@title='News & Features']");
    private By newsText = By.xpath("//h3[text()='NEWS']");
    private By videoFeeds = By.xpath("//h3[text()='VIDEOS']/parent::div/following-sibling::div//li");
    String pagetitle = "//span[text()='%s']";
    String jacketTitles = "(//div[@class='product-card-title']/a)[%s]";
    String jacketPrice = "(//div[@class='product-card-title']/a/parent::div/preceding-sibling::div[@class='spacing']//span[@class='price']//span[@class='sr-only'])[%s]";
    String videoDurationOnPage = "(//span[@aria-hidden='true'])[%s]";

    public void getMenPage() {
        interactionHelper.clickElement(closeIcon);
        actionHelper.moveToElement(testContext.getDriver(), testContext.getDriver().findElement(menuName));
        interactionHelper.clickElement(shopSubMenu);
        interactionHelper.switchToWindow();
    }

    public void verifyGOLDENSTATEWARRIORSMENPage(String pageName) {
        Assert.assertTrue(interactionHelper.validateElementIsDisplayed(getxpath(pageName)));
    }

    public void verifyNewAndFeaturePage() {
        Assert.assertTrue(interactionHelper.validateElementIsDisplayed(newsText));
    }
    public void clickOnDepartment() {
        interactionHelper.clickElement(jacketRadioButton);
    }

    public void getNewsPage() {
        interactionHelper.clickElement(closeIcon);
        actionHelper.moveToElement(testContext.getDriver(), testContext.getDriver().findElement(metaBallsMenu));
        interactionHelper.clickElement(metaBallsSubMenu);
        waitHelper.pageLoadTimeOut(testContext.getDriver(), 10);
    }

    public By getxpath(String pagename) {
        return By.xpath(String.format(pagetitle, pagename));
    }

    public void getJacketInfo() {
        try {
            int itemNumber = 1;
            int serialNumber=1;
            File file = new File("target/jackets_info.txt");
            FileWriter writer = new FileWriter(file);
            while (true) {
                List<WebElement> element = testContext.getDriver().findElements(jacketItem);
                for (WebElement jacket : element) {
                    String title = jacket.findElement(By.xpath(String.format(jacketTitles,itemNumber))).getText();
                    String price = jacket.findElement(By.xpath(String.format(jacketPrice,itemNumber))).getText();
                    writer.write(serialNumber + ":" + title + " -- " + price + "\n");
                    serialNumber++;
                    itemNumber++;
                }

                WebElement nextButton = testContext.getDriver().findElement(nextPageButton);
                if (nextButton.getAttribute("aria-disabled").equalsIgnoreCase("false")) {
                    interactionHelper.clickElement(nextButton);
                    itemNumber=1;
                } else {
                    break;
                }
            }
            writer.close();
            // Read file content into a string
            String fileContent = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

            // Embed text file content into the Extent Report
            ReporterFactory.getInstance().getExtentTest().info("Jacket information extracted and saved.").pass("<pre>" + fileContent + "</pre>");;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyVideoFeedCount(){
        waitHelper.pageLoadTimeOut(testContext.getDriver(),20);
        int itemNumber = 1;
        int videoFeedOlderThenThreeDays=0;
        List<WebElement> element = testContext.getDriver().findElements(videoFeeds);

        for (WebElement videoFeed: element) {
            int number = videoFeed.findElement(By.xpath(String.format(videoDurationOnPage,itemNumber))).getText().charAt(0);
            if (number > 3){
                videoFeedOlderThenThreeDays++;
            }
        }
        ReporterFactory.getInstance().getExtentTest().info("Total Video Feed: "+ element.size() +"------ Videos feed older than three days: "+ videoFeedOlderThenThreeDays);
    }
}
