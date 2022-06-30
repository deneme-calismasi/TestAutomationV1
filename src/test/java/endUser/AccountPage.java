package endUser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.BaseMethods.BaseMethods;

public class AccountPage extends BaseMethods {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    HomePage homePage = new HomePage(driver);

    OrderPage orderPage = new OrderPage(driver);

    public void addressAssert() {
        navigateToSiteUrl();
        homePage.login();
        waitForLoad();
        clickElementByClassName("headerHesabim");
        clickElementByXpath("//*[@class='solAnaMenuAlt menuHesapAyarlarim']");
        clickElementByXpath("//*[@href='#/AdresDefterim']");
        String accountAddress = findElementByXpath("//*[@class='address ng-binding']").getText();
        waitForLoad();
        clickElementByXpath("//*[@class='navigation']//*[@title='Mağaza']");
        clickElementByXpath("//*[@class='productName detailUrl']//*[@title='İstanbul Ümraniye Akyaka Park']");
        scrollToElementByXpath("//*[@id='btnHemenAl']");
        clickElementByXpath("//*[@id='btnHemenAl']");
        clickElementById("mainHolder_ucSepetim_aSiparisTamamlaLink2");
        clickElementByXpath("//*[@id='lnkBtnSiparisKaydet2']");
        orderPage.payScreenProduct();
        scrollToElementById("divTeslimatAdresi");

        String orderAddress = findElementByXpath("//*[@id='siparisOzetFaturaAdresi']//*[@class='basketComplateAdres']/div[3]").getText();

        boolean addressCompare = orderAddress.contains(accountAddress);
        Assert.assertTrue(addressCompare);
        homePage.logout();
    }


    public void birthdayTest() {
        navigateToSiteUrl();
        waitForLoad();
        homePage.login();
        sleep(1);
        clickElementByXpath("//*[@class='solAnaMenuAlt menuHesapAyarlarim']");
        clickElementByXpath("//*[@href='#/Uyelik-Bilgilerim']");
        waitForLoad();
        selectComboBoxById("slctDay", getDay());
        selectComboBoxById("slctMonth", getMonth());
        clickElementByXpath("//*[@ng-click='SaveMembershipInfo(frmuyelikBilgilerim.$valid)']");
        clickElementByXpath("//*[@class='confirm']");
        waitForLoad();
        findElementByXpath("//*[@class='day hsbmSelect ng-valid ng-valid-required ng-dirty ng-touched']");
        homePage.logout();
    }

    public void productReturnTest() {
        navigateToSiteUrl();
        waitForLoad();
        homePage.login();
        waitForLoad();
        homePage.goToStorePage();
        clickElementByXpath("//*[@class='productName detailUrl']//*[@title='İstanbul Ümraniye Akyaka Park']");
        scrollToElementByXpath("//*[@id='btnHemenAl']");
        clickElementByXpath("//*[@id='btnHemenAl']");
        clickElementById("mainHolder_ucSepetim_aSiparisTamamlaLink2");
        clickElementByXpath("//*[@id='lnkBtnSiparisKaydet2']");
        orderPage.payScreenProduct();
        waitForLoad();
        clickElementByClassName("headerHesabim");
        clickElementByClassName("menuIadeTaleplerim");
        clickElementByXpath("//*[@ng-click='ShowHideCreateClaim(false)']");
        findElementByXpath("//*[@id='slctOrder']/option[2]").click();
        sleep(2);

    }


}
