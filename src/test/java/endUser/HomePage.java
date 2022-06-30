package endUser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utilities.BasePage;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToSiteUrl() {
        sleep(1);
        navigateToURL(configuration.getProperty("site_url"));
        String checkUrl = driver.getCurrentUrl();
        Assert.assertEquals(checkUrl, configuration.getProperty("site_url"));
    }

    public void login() {
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='headerUyeGiris']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='headerUyeGiris']//*[@href='javascript:void(0);']")));
        clickElementByXpath("//*[@class='headerUyeGiris']//*[@href='javascript:void(0);']");
        fillInTheBlankById("txtUyeGirisEmail", configuration.getProperty("user_name"));
        fillInTheBlankById("txtUyeGirisPassword", configuration.getProperty("password"));
        clickElementByXpath("//*[@class='userLoginBtn button']");
    }

    public void loginWithPopUp() {
        clickElementByClassName("//*[@class='headerUyeGiris']");
        fillInputFieldByXpath("//*[@id='txtUyeGirisEmail']", configuration.getProperty("user_name"));
        fillInputFieldByXpath("//*[@id='txtUyeGirisPassword']", configuration.getProperty("password"));
        clickElementByXpath("//*[@class='userLoginBtn button']");
    }

    public void logout() {
        waitForLoad();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='headerCikis']")));
        clickElementByXpath("//*[@class='headerCikis']");
    }

    public void goToHomePage() {
        waitForLoad();
        sleep(1000L);
        clickElementByXpath("//*[@class='navigation']//*[@title='Anasayfa']");
    }

    public void goToStorePage() {
        waitForLoad();
        sleep(1000L);
        clickElementByXpath("//*[@class='navigation']//*[@title='Mağaza']");
    }

}
