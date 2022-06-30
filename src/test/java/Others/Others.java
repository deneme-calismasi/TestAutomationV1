package Others;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.BasePage;

import java.util.*;
import java.util.List;

public class Others extends BasePage {

    public Others(WebDriver driver) {
        super(driver);
    }


    public void loginToVatan() {
        navigateToURL(configuration.getProperty("vatan_site"));
        clickElementByClassName("btn-group my-account");
        clickElementByXpath("//*[@href='/login?returnUrl=%2F&logtab=signin']");
        fillInputFieldById("email", configuration.getProperty("user_name"));
        fillInputFieldById("pass", configuration.getProperty("password"));
        clickElementById("login-button");
    }

    public void loginToGGidiyor() {
        navigateToURL("https://www.gittigidiyor.com/");
        hoverToElementByName("profile");
        sleep(1000L);
        clickElementByXpath("//*[@data-cy='header-login-button']");
        waitForLoad();
        fillInputFieldById("L-UserNameField", configuration.getProperty("git_user_name"));
        fillInputFieldById("L-PasswordField", configuration.getProperty("git_pass"));
        clickElementById("gg-login-enter");
        sleep(5000L);

        String actualLoginName = findElementByXpath("//*[@class='gekhq4-4 egoSnI']/span").getText();
        String expectedLoginName = configuration.getProperty("git_account_name");
        Assert.assertEquals(actualLoginName, expectedLoginName);
        sleep(5000L);
    }

    public void selectBoxAmazon() {
        navigateToURL(configuration.getProperty("amazon_site"));
        List<WebElement> selectBox = driver.findElements(By.xpath("//*[@id='searchDropdownBox']/option"));
        ArrayList<String> selectBoxText = new ArrayList<>();
        for (int i = 0; i < selectBox.size(); i++) {
            selectBoxText.add(selectBox.get(i).getText());
        }

        Iterator<String> iteratorSelectBoxText = selectBoxText.iterator();

        while (iteratorSelectBoxText.hasNext()) {
            Select sel = new Select(findElementById("searchDropdownBox"));
            sel.selectByVisibleText(iteratorSelectBoxText.next());
            clickElementById("nav-search-submit-button");
            sleep(1000L);
        }
    }

}
