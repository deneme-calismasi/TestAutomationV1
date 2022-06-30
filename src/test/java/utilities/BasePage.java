package utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DecimalFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {


    Random random = new Random();

    private static final Logger LOGGER = Logger.getLogger(BasePage.class.getName());


    protected WebDriver driver;
    protected WebDriverWait wait;
    public Configuration configuration;

    protected Select select;
    protected Actions action;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 60);
        this.configuration = new Configuration();
        this.action = new Actions(driver);
    }


    protected void navigateToURL(String URL) {
        try {
            waitForLoad();
            driver.navigate().to(URL);
        } catch (Exception e) {
            refresh();
            waitForLoad();
            driver.navigate().to(URL);
        }
    }

    public void getSiteUrl() {
        sleep(2);
        navigateToURL(configuration.getProperty("site_url"));
    }

    public void navigateToSiteUrl() {
        sleep(1);
        navigateToURL(configuration.getProperty("site_url"));
    }

    protected void refresh() {
        driver.navigate().refresh();
    }


    public void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        wait.until(pageLoadCondition);

    }


    public void sleep(long millis) {
        try {
            Thread.sleep(millis);

        } catch (InterruptedException e) {
            LOGGER.log(Level.WARNING, "Interrupted!", e);
            Thread.currentThread().interrupt();
        }
    }

    public void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000L);

        } catch (InterruptedException e) {
            LOGGER.log(Level.WARNING, "Interrupted!", e);
            Thread.currentThread().interrupt();
        }
    }

    public int randBetweenTwoNumber(int max, int min) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    protected void clickElementById(String id) {
        waitUntilJSIsReady();
        waitUntilElementIsClickableId(id);
        highlightElement(findElementById(id));
        findElementById(id).click();
    }

    protected void clickElementByName(String name) {
        waitUntilJSIsReady();
        waitUntilElementIsClickableName(name);
        highlightElement(findElementByName(name));
        findElementByName(name).click();
    }


    protected void clickElementByClassName(String className) {
        waitForLoad();
        waitUntilElementIsClickableClass(className);
        highlightElement(findElementByClassName(className));
        findElementByClassName(className).click();
    }

    protected void clickElementByXpath(String xpath) {
        waitForLoad();
        waitUntilElementIsClickableXpath(xpath);
        highlightElement(findElementByXpath(xpath));
        findElementByXpath(xpath).click();
    }


    protected void clickElement(WebElement webElement) {
        waitForLoad();
        waitUntilElementIsClickable(webElement);
        highlightElement(webElement);
        webElement.click();
    }


    protected void staleClickElementByXpath(String xpath) {
//        waitUntilJSIsReady();
        waitForLoad();
        waitUntilElementIsClickableXpath(xpath);
        highlightElement(findElementByXpath(xpath));
        try {
            findElementByXpath(xpath).click();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            findElementByXpath(xpath).click();
        }
    }

    protected void clickElementByCSS(String css) {
        waitUntilJSIsReady();
        waitUntilElementIsClickableCSS(css);
        highlightElement(findElementByXpath(css));
        findElementByCss(css).click();
    }

    protected void clickElementsByClassName(String className, int index) {
        waitUntilJSIsReady();
        waitUntilElementsIsClickableClass(className, index);
        highlightElement(findElementsByClassName(className, index));
        findElementsByClassName(className, index).click();
    }

    protected void clickElementsByXpath(String xpath, int index) {
        waitUntilJSIsReady();
        waitUntilElementsIsClickableXpath(xpath, index);
        highlightElement(findElementsByXpath(xpath, index));
        findElementsByXpath(xpath, index).click();
    }


    protected WebElement findElementById(String id) {
        waitForLoad();
//        waitUntilPresenceOfElementId(id);
        return driver.findElement(By.id(id));
    }

    protected WebElement findElementByName(String name) {
        waitUntilPresenceOfElementName(name);
        return driver.findElement(By.name(name));
    }

    protected WebElement findElementByClassName(String className) {
        waitUntilPresenceOfElementClass(className);
        return driver.findElement(By.className(className));
    }

    protected WebElement findElementByXpath(String xpath) {
        waitUntilPresenceOfElementXpath(xpath);
        return driver.findElement(By.xpath(xpath));

    }

    protected WebElement findElementByCss(String css) {
        waitUntilPresenceOfElementCSS(css);
        return driver.findElement(By.cssSelector(css));

    }

    protected WebElement findElementsByClassName(String className, int index) {

        waitUntilPresenceOfElementClass(className);
        return driver.findElements(By.className(className)).get(index);

    }

    protected List<WebElement> findListElementsByClassName(String className) {
        waitUntilPresenceOfElementClass(className);
        return driver.findElements(By.className(className));


    }

    protected List<WebElement> findListElementsByXpath(String xpath) {
        waitUntilPresenceOfElementXpath(xpath);
        return driver.findElements(By.xpath(xpath));
    }

    protected WebElement findElementsByXpath(String xpath, int index) {
        waitUntilPresenceOfElementXpath(xpath);
        return driver.findElements(By.xpath(xpath)).get(index);
    }


    protected String randomEmail() {
        String mail = RandomStringUtils.randomAlphabetic(5);
        return mail.toLowerCase() + "@gmail.com";
    }

    protected String randomName() {
        String name = RandomStringUtils.randomAlphabetic(7);
        return name.toLowerCase();
    }

    protected String randomNumber() {
        int r = random.nextInt(9);
        DecimalFormat decimalFormat = new DecimalFormat("#");
        return decimalFormat.format(r);
    }

    protected String randomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        return RandomStringUtils.random(15, characters);
    }

    protected String getTextWithSubString(WebElement element, int x, int y) {
        return element.getText().substring(x, y);
    }

    protected String getText(WebElement element) {
        return element.getText();
    }

    protected String getTitleText() {
        return driver.getTitle();
    }


    public void scrollMoveToElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToElementById(String id) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id(id)));
    }

    public void scrollToElementByXpath(String xpath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", findElementByXpath(xpath));
    }

    public void scrollToElementByName(String name) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", findElementByName(name));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
    }

    public void scrollToElementByCSS(String css) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.cssSelector(css)));

    }


    //Wait Method
    protected void waitUntilElementIsClickableXpath(String xpath) {
        wait.until(ExpectedConditions.elementToBeClickable(findElementByXpath(xpath)));
    }


    protected void waitUntilElementIsClickableCSS(String css) {
        wait.until(ExpectedConditions.elementToBeClickable(findElementByCss(css)));
    }

    protected void waitUntilElementsIsClickableXpath(String xpath, int index) {
        wait.until(ExpectedConditions.elementToBeClickable(findElementsByXpath(xpath, index)));
    }

    protected void waitUntilElementIsClickableClass(String className) {
        wait.until(ExpectedConditions.elementToBeClickable(findElementByClassName(className)));
    }

    protected void waitUntilElementsIsClickableClass(String className, int index) {
        wait.until(ExpectedConditions.elementToBeClickable(findElementsByClassName(className, index)));
    }

    protected void waitUntilElementIsClickableId(String id) {
        wait.until(ExpectedConditions.elementToBeClickable(findElementById(id)));
    }

    protected void waitUntilElementIsClickableName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(findElementByName(name)));
    }

    protected void waitUntilElementIsClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }


    protected void waitUntilElementIsVisibleXpath(String xpath, int index) {
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath(xpath)).get(index)));
        } catch (Exception e) {
            refresh();
            wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath(xpath)).get(index)));
        }
    }

    protected void waitUntilPresenceOfElementXpath(String xpath) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            refresh();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

        }
    }

    protected void waitUntilPresenceOfElementId(String id) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        } catch (Exception e) {
            refresh();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        }
    }

    protected void waitUntilPresenceOfElementName(String name) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
        } catch (Exception e) {
            refresh();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
        }
    }

    protected void waitUntilPresenceOfElementClass(String className) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
        } catch (Exception e) {
            refresh();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
        }
    }

    protected void waitUntilPresenceOfElementCSS(String css) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
        } catch (Exception e) {
            refresh();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
        }
    }

    protected void waitUntilJSIsReady() {
//     try-catch was deleted for refreshing
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        sleep(200L);
        wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        sleep(200L);
    }

    protected void highlightElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: red; border: 1px dashed red;");
        } catch (Exception e) {
            refresh();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: red; border: 1px dashed red;");
        }
    }

    protected void checkTextWithXpath(String xpath, String alertText) {
        wait.until(ExpectedConditions.textToBe(By.xpath(xpath), alertText));
    }

    protected void assertEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected);
    }

    protected void assertEquals(int actual, int expected) {
        Assert.assertEquals(actual, expected);
    }

    protected void assertEquals(Double actual, Double expected) {
        Assert.assertEquals(actual, expected);
    }

    protected void assertEquals(Float actual, Float expected) {
        Assert.assertEquals(actual, expected);
    }

    protected void assertTrue(Boolean bl) {
        Assert.assertTrue(bl);
    }

    protected void assertFalse(Boolean bl) {
        Assert.assertFalse(bl);
    }

    public void fillInTheBlankById(String id, String keys) {
        scrollToElementById(id);
        driver.findElement(By.id(id)).sendKeys(keys);
    }

    public void fillInTheBlankByXpath(String xpath, String keys) {
        scrollToElementByXpath(xpath);
        driver.findElement(By.xpath(xpath)).sendKeys(keys);
    }

    protected String getDay() {
        Calendar d = Calendar.getInstance();
        int today = d.get(Calendar.DAY_OF_MONTH); // 26
        return String.valueOf(today);  // string 26
    }

    protected String getMonth() {
        Calendar m = Calendar.getInstance();
        int thisMonth = m.get(Calendar.MONTH);
        return String.valueOf(thisMonth + 1);
    }


    protected void selectComboBoxByClassName(String data, int index, String dataValue) {
        select = new Select(findElementsByClassName(data, index));
        select.selectByValue(dataValue);
    }


    protected void selectComboBoxByClassNameWithText(String data, int index, String text) {
        select = new Select(findElementsByClassName(data, index));
        select.selectByVisibleText(text);
    }


    protected void selectComboBox(WebElement element, String dataValue) {
        select = new Select(element);
        select.selectByValue(dataValue);
    }

    protected void selectComboBoxByText(WebElement element, String text) {
        select = new Select(element);
        select.selectByVisibleText(text);
    }

    protected void selectComboBoxByXpathValue(WebElement element, String dataValue) {
        select = new Select(element);
        select.selectByValue(dataValue);
    }

    protected void selectComboBoxByIdValue(String id, String dataValue) {
        select = new Select(findElementById(id));
        select.selectByValue(dataValue);
    }

    protected void selectComboBoxWithText(WebElement element, String Text) {

        select = new Select(element);
        select.selectByVisibleText(Text);
    }

    protected void selectComboBoxId(String id, String text) {
        select = new Select(findElementById(id));
        select.selectByVisibleText(text);
    }

    protected void selectComboBoxName(String name, String text) {
        select = new Select(findElementByName(name));
        select.selectByVisibleText(text);
    }


    protected void selectComboBoxNameIndex(String name, int index) {
        select = new Select(findElementByName(name));
        List<WebElement> cheques = driver.findElements(By.xpath("//*[@name='ctl00$mainHolder$ucSepetim$ddlHediyeCekleri']/option"));
        int l = cheques.size();
        select.selectByIndex(l - 1);
    }


    protected void selectComboBoxId(String id, int index) {
        select = new Select(findElementById(id));
        select.selectByIndex(index);
    }


    protected void selectComboBoxById(String id, String text) {
        select = new Select(findElementById(id));
        select.selectByVisibleText(text);
    }


    public void selectComboBoxByName(String name, String text) {
        select = new Select(findElementByName(name));
        select.selectByVisibleText(text);
    }

    public void selectComboBoxByName(String name, int i) {
        select = new Select(findElementByName(name));
        select.selectByIndex(i);
    }


    protected void selectComboBoxByXpath(String xpath, String text) {
        select = new Select(findElementByXpath(xpath));
        select.selectByVisibleText(text);
    }


    protected void selectComboBoxIdIndex(String id, int index) {
        select = new Select(findElementById(id));
        select.selectByIndex(index);
    }


    protected void selectComboBoxIdLastChild(String id) {
        select = new Select(findElementById(id));
        List<WebElement> l = select.getOptions();
        select.selectByIndex(l.size() - 1);
    }

    protected void fillInputFieldById(String id, String charSequence) {
        waitUntilJSIsReady();
        findElementById(id).sendKeys(charSequence);
    }


    protected void fillInputFieldByName(String name, String charSequence) {
        waitUntilJSIsReady();
        findElementByName(name).sendKeys(charSequence);
    }

    protected void fillInputFieldByClassName(String className, String charSequence) {
        waitUntilJSIsReady();
        findElementByClassName(className).sendKeys(charSequence);
    }

    protected void fillInputFieldsByClassName(String className, int index, String charSequence) {
        findElementsByClassName(className, index).sendKeys(charSequence);
    }

    protected void fillInputFieldByXpath(String xpath, String charSequence) {
        waitUntilJSIsReady();
        findElementByXpath(xpath).sendKeys(charSequence);
    }

    protected void fillInputFieldsByXpath(String className, int index, String charSequence) {
        waitUntilJSIsReady();
        findElementsByXpath(className, index).sendKeys(charSequence);
    }

    protected void hoverToElementsByClassName(String className, int index) {
        try {
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
            action.moveToElement(findElementsByClassName(className, index)).build().perform();
        } catch (Exception e) {
            refresh();
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
            action.moveToElement(findElementsByClassName(className, index)).build().perform();
        }


    }

    protected void hoverToElementByClassName(String className) {
        try {
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
            action.moveToElement(findElementByClassName(className)).build().perform();
        } catch (Exception e) {
            refresh();
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
            action.moveToElement(findElementByClassName(className)).build().perform();
        }
    }

    protected void hoverToElementByXpath(String xpath) {
        try {
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            action.moveToElement(findElementByXpath(xpath)).build().perform();
        } catch (Exception e) {
            refresh();
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            action.moveToElement(findElementByXpath(xpath)).build().perform();
        }
    }

    protected void hoverToElementByName(String name) {
        try {
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
            action.moveToElement(findElementByName(name)).build().perform();
        } catch (Exception e) {
            refresh();
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
            action.moveToElement(findElementByName(name)).build().perform();
        }
    }

    protected void hoverToElementById(String id) {
        try {
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
            action.moveToElement(findElementById(id)).build().perform();
        } catch (Exception e) {
            refresh();
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(id)));
            action.moveToElement(findElementById(id)).build().perform();
        }
    }


    protected void hoverToElementAndClickById(String id) {
        try {
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
            action.moveToElement(findElementById(id)).build().perform();
            action.click(findElementById(id)).build().perform();
        } catch (Exception e) {
            refresh();
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(id)));
            action.moveToElement(findElementById(id)).build().perform();
            action.click(findElementById(id)).build().perform();
        }
    }

    protected void hoverToElementsByXpath(String xpath, int index) {
        try {
            waitUntilJSIsReady();
            action.moveToElement(findElementsByXpath(xpath, index)).build().perform();
        } catch (Exception e) {
            refresh();
            waitUntilJSIsReady();
            action.moveToElement(findElementsByXpath(xpath, index)).build().perform();
        }

    }

    protected void hoverAndClick(WebElement element, Keys charSequence) {
        action.sendKeys(element, charSequence);
    }

    protected void HoverAndClick(WebElement element) {
        try {
            waitUntilJSIsReady();
            action.moveToElement(element).click().build().perform();
        } catch (Exception e) {
            refresh();
            waitUntilJSIsReady();
            action.moveToElement(element).click().build().perform();
        }

    }

    public void waitForNewWindow(WebDriver driver, int timeout) {
        try {
            boolean flag = false;
            int counter = 0;

            while (!flag) {

                try {
                    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

                    if (tabs.size() > 1) {
                        flag = true;
                        return;
                    }

                    sleep(1);

                    counter++;
                    if (counter > timeout) {
                        return;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
        } catch (Exception e) {
            refresh();

            boolean flag = false;
            int counter = 0;

            while (!flag) {

                try {
                    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                    if (tabs.size() > 1) {
                        flag = true;
                        return;
                    }
                    sleep(1);
                    counter++;
                    if (counter > timeout) {
                        return;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    return;
                }
            }
        }
    }


}
