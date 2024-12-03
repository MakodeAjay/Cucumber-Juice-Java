package Utils;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.*;
/**
 * InteractionHelper class
 * @author AjayMakode
 *
 */
@Slf4j
public class InteractionHelper {

    @Inject
    TestContext testContext;

    /**
     * Get element and string value
     *
     * @param by and String
     *
     * @return Webelement
     */
    public WebElement typeElement(By by, String input) {

        log.info("typeElement "+by.toString());
        WebElement element = null;
        try
        {
            WebDriverWait wait = new WebDriverWait(testContext.getDriver(), Duration.ofSeconds(10));
            element = wait.until(ExpectedConditions.elementToBeClickable(by));
            element.clear();
            element.sendKeys(input);
            return element;
        }
        catch (Exception ex)
        {
            Assert.fail("***** Error Occured *****" + ex);
            log.error("typeElement "+ex.toString());
            return element;
        }
        //the return element statement can be use to rerun the current element to the called function so that the
        // called function can again manipulate the element or pass it to any other functions
    }

    /**
     * Get element value
     *
     * @param by
     *
     * @return Webelement
     */
    public WebElement clickElement(By by) {

        log.info("clickElement "+by.toString());
        WebElement element = null;
        try
        {
            WebDriverWait wait = new WebDriverWait(testContext.getDriver(), Duration.ofSeconds(10));
            element = wait.until(ExpectedConditions.elementToBeClickable(by));
            element.click();
            return element;
        }
        catch (Exception ex)
        {
            log.error("***** Error Occured ***** "+ex);
            Assert.fail("***** Error Occured *****" + ex);
            return element;
        }
    }

    /**
     * Get element value
     *
     * @param webelement
     *
     * @return Webelement
     */
    public WebElement clickElement(WebElement element) {

        log.info("clickElement "+element.toString());
        try
        {
            WebDriverWait wait = new WebDriverWait(testContext.getDriver(), Duration.ofSeconds(10));
            element = wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            return element;
        }
        catch (Exception ex)
        {
            log.error("***** Error Occured ***** "+ex);
            Assert.fail("***** Error Occured *****" + ex);
            return element;
        }
    }

    /**
     * Get element and string value
     *
     * @param by and String
     *
     * @return Webelement
     */
    public WebElement selectElementByText(By by, String textToBeSelected) {

        log.info("selectElementByText "+by.toString());
        WebElement element = null;
        try
        {
            WebDriverWait wait = new WebDriverWait(testContext.getDriver(), Duration.ofSeconds(10));
            element = wait.until(ExpectedConditions.elementToBeClickable(by));
            Select sel = new Select(element);
            sel.selectByVisibleText(textToBeSelected);
            return element;
        }
        catch (Exception ex)
        {
            Assert.fail("***** Error Occured *****" + ex);
            log.error("selectElementByText "+ex.toString());
            return element;
        }
    }

    /**
     * Get element and int value
     *
     * @param by and String
     *
     * @return Webelement
     */
    public WebElement selectElementByIndex(By by, int index) {

        log.info("selectElementByIndex "+by.toString());
        WebElement element = null;
        try
        {
            WebDriverWait wait = new WebDriverWait(testContext.getDriver(), Duration.ofSeconds(10));
            element = wait.until(ExpectedConditions.elementToBeClickable(by));
            Select sel = new Select(element);
            sel.selectByIndex(index);
            return element;
        }
        catch (Exception ex)
        {
            Assert.fail("***** Error Occured *****" + ex);
            log.error("selectElementByIndex "+ex.toString());
            return element;
        }

    }

    /**
     * Get element value
     *
     * @param by
     *
     * @return String
     */
    public String getText(By by) {

        log.info("getText "+by.toString());
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(testContext.getDriver(), Duration.ofSeconds(10));
            element = wait.until(ExpectedConditions.elementToBeClickable(by));
            return element.getText();
        } catch (Exception ex) {
            Assert.fail("***** Error Occured *****" + ex);
            log.error("getText "+ex.toString());
            return "";
        }
    }

    /**
     * Get element and string value
     *
     * @param by and String
     *
     * @return String
     */
    public String getAttribute(By by, String attName) {

        log.info("getAttribute "+by.toString());
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(testContext.getDriver(), Duration.ofSeconds(10));
            element = wait.until(ExpectedConditions.elementToBeClickable(by));
            String value = element.getAttribute(attName);
            return value;
        } catch (Exception ex) {
            Assert.fail("***** Error Occured *****" + ex);
            log.error("getAttribute "+ex.toString());
            return "";
        }
    }

    public String getTitle() {
        WebElement element = null;
        try {
            String value = testContext.getDriver().getTitle();
            return value;
        } catch (Exception ex) {
            Assert.fail("***** Error Occured *****" + ex);
            return "";
        }
    }

    /**
     * Get element value
     *
     * @param by
     *
     * @return list of Webelement
     */
    public List<WebElement> getListOfWebElements(By by){
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(testContext.getDriver(), Duration.ofSeconds(10));
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return element.findElements(by);
        } catch (Exception ex) {
            Assert.fail("***** Error Occured *****" + ex);
            return new ArrayList<>();
        }


    }

    /**
     * Get element value
     *
     * @param by
     *
     * @return boolean
     */
    public boolean validateElementIsDisplayed(By by) {
        boolean b = false;
        try {
            WebDriverWait wait = new WebDriverWait(testContext.getDriver(), Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            b = element.isDisplayed();
            return b;
        } catch (Exception ex) {
            Assert.fail("***** Error Occured *****" + ex);
            return b;
        }
    }

    public void switchToSecondWindowTab() {
        WebElement element = null;
        try {
            String [] handles = (String[]) testContext.getDriver().getWindowHandles().toArray();
            System.out.println("***************************"+handles);
            testContext.getDriver().switchTo().window(handles[1]);
        } catch (Exception ex) {
            Assert.fail("***** Error Occured *****" + ex);
        }

    }

    public void switchToWindow(){
        String mainWindow = testContext.getDriver().getWindowHandle();
        Set<String> windows = testContext.getDriver().getWindowHandles();
        Iterator<String> iterator = windows.iterator();
        while (iterator.hasNext()){
            String childWindow = iterator.next();
            if(!mainWindow.equals(childWindow)){
                testContext.getDriver().switchTo().window(childWindow);
            }
        }
    }

    public void switchToDefaultWindowTab() {
        WebElement element = null;
        try {
            String [] handles = (String[]) testContext.getDriver().getWindowHandles().toArray();
            testContext.getDriver().switchTo().window(handles[0]);
        } catch (Exception ex) {
            Assert.fail("***** Error Occured *****" + ex);
        }
    }

    /**
     * Get element value
     *
     * @param by
     *
     * @return String
     */
    public String takeScreenShotOfElement(By by) {

        String base64="";
        File srcfile = testContext.getDriver().findElement(by).getScreenshotAs(OutputType.FILE);
        try {
            InputStream in = new FileInputStream(srcfile);
            byte[] imageBytes = in.readAllBytes();
            base64 = Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }

    /**
     * Taking webpage screenshot
     * @return string
     */
    public String takeScreenShotOfWebPage() {

        log.info("takeScreenShotOfWebPage");
        String base64="";
        File srcfile = ((TakesScreenshot) testContext.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            InputStream in = new FileInputStream(srcfile);
            byte[] imageBytes = in.readAllBytes();
            base64 = Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }
}
