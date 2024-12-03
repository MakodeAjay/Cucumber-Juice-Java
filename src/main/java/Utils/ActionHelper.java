package Utils;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;

/**
 * ActionHelper class
 *
 * @author AjayMakode
 */
@Slf4j
public class ActionHelper {
    @Inject
    TestContext testContext;

    public void scrollByVisibilityOfElementWebDriver(WebDriver driver, WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", ele);
    }

    public void clickAction(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        act.moveToElement(ele).click().build().perform();
    }

    /**
     * Get driver and element value
     *
     * @param driver and element
     * @return boolean value
     */
    public boolean findElement(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            ele.isDisplayed();
            flag = true;
        } catch (Exception e) {
            flag = false;
        } finally {
            if (flag) {
                log.info("Successfully Found element at");

            } else {
                log.info("Unable to locate element at");
            }
        }
        return flag;
    }

    /**
     * Get driver and element value
     *
     * @param driver and element
     * @return boolean value
     */
    public boolean isDisplayed(WebDriver driver, WebElement ele) {
        boolean flag = false;
        flag = findElement(driver, ele);
        if (flag) {
            flag = ele.isDisplayed();
            if (flag) {
                log.info("The element is Displayed");
            } else {
                log.info("The element is not Displayed");
            }
        } else {
            log.info("Not displayed ");
        }
        return flag;
    }

    /**
     * Get driver and element value
     *
     * @param driver and element
     * @return boolean value
     */
    public boolean isSelected(WebDriver driver, WebElement ele) {
        boolean flag = false;
        flag = findElement(driver, ele);
        if (flag) {
            flag = ele.isSelected();
            if (flag) {
                log.info("The element is Selected");
            } else {
                log.info("The element is not Selected");
            }
        } else {
            log.info("Not selected ");
        }
        return flag;
    }

    /**
     * Get driver and element value
     *
     * @param driver and element
     * @return boolean value
     */
    public boolean isEnabled(WebDriver driver, WebElement ele) {
        boolean flag = false;
        flag = findElement(driver, ele);
        if (flag) {
            flag = ele.isEnabled();
            if (flag) {
                log.info("The element is Enabled");
            } else {
                log.info("The element is not Enabled");
            }
        } else {
            log.info("Not Enabled ");
        }
        return flag;
    }

    /**
     * Get driver and  element.
     *
     * @param driver and element
     * @return boolean value
     */
    public boolean JSClick(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", ele);
            flag = true;
        } catch (Exception e) {
            throw e;
        } finally {
            if (flag) {
                log.info("Click Action is performed");
            } else if (!flag) {
                log.info("Click Action is not performed");
            }
        }
        return flag;
    }

    /**
     * Get driver and string value
     *
     * @param driver and string
     * @return boolean value
     */
    public boolean switchToFrameById(WebDriver driver, String idValue) {
        boolean flag = false;
        try {
            driver.switchTo().frame(idValue);
            flag = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (flag) {
                log.info("Frame with Id \"" + idValue + "\" is selected");
            } else {
                log.info("Frame with Id \"" + idValue + "\" is not selected");
            }
        }
    }

    /**
     * Get driver and string value
     *
     * @param driver and string
     * @return boolean value
     */
    public boolean switchToFrameByName(WebDriver driver, String nameValue) {
        boolean flag = false;
        try {
            driver.switchTo().frame(nameValue);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                log.info("Frame with Name \"" + nameValue + "\" is selected");
            } else if (!flag) {
                log.info("Frame with Name \"" + nameValue + "\" is not selected");
            }
        }
    }

    /**
     * Get driver and element value
     *
     * @param driver and element
     * @return boolean value
     */
    public void mouseOverElement(WebDriver driver, WebElement element) {
        boolean flag = false;
        try {
            new Actions(driver).moveToElement(element).build().perform();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (flag) {
                log.info(" MouserOver Action is performed on ");
            } else {
                log.info("MouseOver action is not performed on");
            }
        }
    }

    /**
     * Get driver and element value
     *
     * @param driver and element
     * @return boolean value
     */
    public boolean moveToElement(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", ele);
            Actions actions = new Actions(driver);
            actions.moveToElement(ele).build().perform();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Get driver and element value
     *
     * @param driver and element
     * @return boolean value
     */
    public boolean mouseover(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            new Actions(driver).moveToElement(ele).build().perform();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get driver ,element and source/destination coordinates
     *
     * @param driver,element and source/destination coordinates
     * @return boolean value
     */
    public boolean draggable(WebDriver driver, WebElement source, int x, int y) {
        boolean flag = false;
        try {
            new Actions(driver).dragAndDropBy(source, x, y).build().perform();
            Thread.sleep(5000);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                log.info("Draggable Action is performed on \"" + source + "\"");
            } else if (!flag) {
                log.info("Draggable action is not performed on \"" + source + "\"");
            }
        }
    }

    /**
     * Get driver and  element.
     *
     * @param driver and source/destination target webelement
     * @return boolean value
     */
    public boolean draganddrop(WebDriver driver, WebElement source, WebElement target) {
        boolean flag = false;
        try {
            new Actions(driver).dragAndDrop(source, target).perform();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                log.info("DragAndDrop Action is performed");
            } else if (!flag) {
                log.info("DragAndDrop Action is not performed");
            }
        }
    }

    /**
     * Get driver and  element.
     *
     * @param driver,element and source/destination coordinates
     * @return boolean value
     */
    public boolean slider(WebDriver driver, WebElement ele, int x, int y) {
        boolean flag = false;
        try {
            new Actions(driver).dragAndDropBy(ele, x, y).build().perform();// 150,0
            Thread.sleep(5000);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                log.info("Slider Action is performed");
            } else {
                log.info("Slider Action is not performed");
            }
        }
    }

    /**
     * Get driver and  element.
     *
     * @param driver and element
     * @return boolean value
     */
    public boolean rightclick(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            Actions clicker = new Actions(driver);
            clicker.contextClick(ele).perform();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                log.info("RightClick Action is performed");
            } else {
                log.info("RightClick Action is not performed");
            }
        }
    }

    /**
     * Get driver and  element.
     *
     * @param driver and element
     * @return boolean value
     */
    public boolean switchWindowByTitle(WebDriver driver, String windowTitle, int count) {
        boolean flag = false;
        try {
            Set<String> windowList = driver.getWindowHandles();

            String[] array = windowList.toArray(new String[0]);

            driver.switchTo().window(array[count - 1]);

            if (driver.getTitle().contains(windowTitle)) {
                flag = true;
            } else {
                flag = false;
            }
            return flag;
        } catch (Exception e) {
            //flag = true;
            return false;
        } finally {
            if (flag) {
                log.info("Navigated to the window with title");
            } else {
                log.info("The Window with title is not Selected");
            }
        }
    }

    /**
     * Get driver value
     *
     * @param driver
     * @return boolean value
     */
    public boolean switchToNewWindow(WebDriver driver) {
        boolean flag = false;
        try {
            Set<String> s = driver.getWindowHandles();
            Object popup[] = s.toArray();
            driver.switchTo().window(popup[1].toString());
            flag = true;
            return flag;
        } catch (Exception e) {
            flag = false;
            return flag;
        } finally {
            if (flag) {
                log.info("Window is Navigated with title");
            } else {
                log.info("The Window with title: is not Selected");
            }
        }
    }

    /**
     * Get driver value
     *
     * @param driver
     * @return boolean value
     */
    public boolean switchToOldWindow(WebDriver driver) {
        boolean flag = false;
        try {
            Set<String> s = driver.getWindowHandles();
            Object popup[] = s.toArray();
            driver.switchTo().window(popup[0].toString());
            flag = true;
            return flag;
        } catch (Exception e) {
            flag = false;
            return flag;
        } finally {
            if (flag) {
                log.info("Focus navigated to the window with title");
            } else {
                log.info("The Window with title: is not Selected");
            }
        }
    }

    /**
     * Verify alert present or not
     *
     * @return: Boolean (True: If alert preset, False: If no alert)
     */
    public boolean Alert(WebDriver driver) {
        boolean presentFlag = false;
        Alert alert = null;
        try {
            // Check the presence of alert
            alert = driver.switchTo().alert();
            // if present consume the alert
            alert.accept();
            presentFlag = true;
        } catch (NoAlertPresentException ex) {
            // Alert not present
            ex.printStackTrace();
        } finally {
            if (!presentFlag) {
                log.info("The Alert is handled successfully");
            } else {
                log.info("There was no alert to handle");
            }
        }
        return presentFlag;
    }
}
