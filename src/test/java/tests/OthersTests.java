package tests;

import org.testng.annotations.Test;
import utilities.BaseMethods.BaseMethods;
import utilities.BaseTest;
import Others.Others;


public class OthersTests extends BaseTest {


    BaseMethods baseMethods;
    Others otherTestsMethods;


    @Test
    public void loginToVatanSite() {
        otherTestsMethods = new Others(driver);
        otherTestsMethods.loginToVatan();
    }

    @Test
    public void loginToGGidiyor() {
        otherTestsMethods = new Others(driver);
        otherTestsMethods.loginToGGidiyor();
    }

    @Test
    public void selectBoxAmazonSite() {
        otherTestsMethods = new Others(driver);
        otherTestsMethods.selectBoxAmazon();
    }

    @Test
    public void dragAndDropSite() {
        baseMethods = new BaseMethods(driver);
        baseMethods.dragAndDrop("//*[@id='draggable']", "//*[@id='simpleDropContainer']//*[@id='droppable']");
    }

    @Test
    public void switchToFrameSite() {
        baseMethods = new BaseMethods(driver);
        baseMethods.switchToFrame("frame1");
    }

    @Test
    public void switchToNewTabSite() {
        baseMethods = new BaseMethods(driver);
        baseMethods.switchToNewTabFromXpath("//*[@id='tabButton']");
    }

    @Test
    public void switchToNewWindowSite() {
        baseMethods = new BaseMethods(driver);
        baseMethods.switchToNewWindow();
    }

    @Test
    public void switchToAlertWindowSite() {
        baseMethods = new BaseMethods(driver);
        baseMethods.switchToAlertWindow();
    }


}
