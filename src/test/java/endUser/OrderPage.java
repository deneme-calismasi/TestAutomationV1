package endUser;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.BaseMethods.BaseMethods;

import java.util.List;

public class OrderPage extends BaseMethods {


    public OrderPage(WebDriver driver) {
        super(driver);
    }

    HomePage homePage = new HomePage(driver);

    StorePage storePage = new StorePage(driver);


    public void commentToProduct() {
        navigateToSiteUrl();
        homePage.login();
        waitForLoad();
        sleep(1000L);
        clickElementByXpath("//*[@class='navigation']//*[@title='Mağaza']");
        scrollToElementByXpath("//*[@id='ProductPageProductList']//*[text()='test2Product']");
        clickElementByXpath("//*[@id='ProductPageProductList']//*[text()='test2Product']");
        waitForLoad();
        Select productCount = new Select(findElementById("ddlUrunSiparisAdedi"));
        productCount.selectByVisibleText("3");
        waitForLoad();
        clickElementByXpath("//*[@class='hidden-phone button']/span[1]");

        String starElement = "//*[@class='rating']//*[@class='star-" + randBetweenTwoNumber(5, 1) + "']";
        clickElementByXpath(starElement);
        clickElementByXpath("//*[@id='yorumYaz']//*[@for='rdTavsiye2']");
        clickElementByXpath("//*[@name='txtbxYorumBaslik']");
        fillInputFieldByXpath("//*[@name='txtbxYorumBaslik']", configuration.getProperty("comment_head"));
        fillInputFieldByXpath("//*[@id='txtbxYorumMesaj']", configuration.getProperty("comment"));
        clickElementByXpath("//*[@id='yorumYaz']//*[@for='rdYas2']");
        clickElementByXpath("//*[@id='btnYorumKaydet']");
        waitForLoad();
        clickElementByXpath("//*[@id='yorumYaz']//*[@class='modal-close']");
        clickElementByXpath("//*[@id='divSatinAl']//*[@type='button']");
        hoverToElementByXpath("//*[@class='sepetTecxt']");
        clickElementByXpath("//*[@class='button headerCartBtn']");
        List<WebElement> purchaseList = driver.findElements(By.xpath("//*[@class='button basketCompletebtn']"));
        purchaseList.get(1).click();
        homePage.logout();
    }


    public void payScreenProduct() {
        clickElementById("mainHolder_ucOdeme_liMenuKapidaOdeme");
        waitForLoad();
        clickElementById("cbMesafeliSatisSozlesme");
        waitForLoad();
        clickElementById("lnkBtnSiparisiTamamlaTop");
        waitForLoad();
    }


    public void purchaseNumberOrder() {
        navigateToSiteUrl();
        homePage.login();
        clickElementByXpath("//*[@class='navigation']//*[@title='Mağaza']");
        scrollToElementByXpath("//*[@id='ProductPageProductList']//*[text()='test2Product']");
        clickElementByXpath("//*[@id='ProductPageProductList']//*[text()='test2Product']");
        selectComboBoxByText(findElementById("ddlUrunSiparisAdedi"), "3");

        storePage.basketPopUpAndNonPopUp();

        clickElementByXpath("//*[@id='divSatinAl']//*[@type='button']");
        hoverToElementByXpath("//*[@class='sepetTecxt']");
        clickElementByXpath("//*[@class='button headerCartBtn']");
        clickElementById("mainHolder_ucSepetim_aSiparisTamamlaLink2");

        randomCargoMethod();
        payScreenProduct();
        waitForLoad();
        String orderNo = findElementByClassName("SiparisNoLabel").getText();
        clickElementByClassName("headerHesabim");
        clickElementByClassName("menuSiparislerim"); // click my orders
        String lastOrderNo = findElementByXpath("//*[@ng-repeat='order in orderList'][1]//*[@class='order-no']/span").getText();
        Assert.assertTrue(lastOrderNo.contains(orderNo));
        homePage.logout();
    }

    public void orderAddBasket() {
        navigateToSiteUrl();
        homePage.login();
        waitForLoad();
        clickElementByXpath("//*[@class='navigation']//*[@title='Mağaza']");
        scrollToElementByXpath("//*[@id='ProductPageProductList']//*[text()='test2Product']");
        clickElementByXpath("//*[@id='ProductPageProductList']//*[text()='test2Product']");
        selectComboBoxByText(findElementById("ddlUrunSiparisAdedi"), "3");
        clickElementByXpath("//*[@class = 'Addtobasket button btnAddBasketOnDetail']");
        storePage.basketPopUpAndNonPopUp();
        clickElementByXpath("//*[@class='button headerCartBtn']");
        clickElementById("mainHolder_ucSepetim_aSiparisTamamlaLink2");
        addressExistingOrNot();
        payScreenProduct();
        homePage.logout();
    }

    public void orderDate() {
        navigateToURL(configuration.getProperty("site_url"));
        homePage.login();
        clickElementByClassName("headerHesabim");
        clickElementByClassName("menuSiparislerim");
        String orderDate = findElementByXpath("//*[@ng-repeat='order in orderList'][1]//*[@class='order-date ng-binding']").getText();
        Assert.assertTrue(orderDate.contains(nowDate("dd-MM-yyyy")));
        homePage.logout();
    }


    public void addressExistingOrNot() {
        if (existsElementXpath("//*[@id='txtAdresBasligi']")) {
            BaseMethods baseMethods = new BaseMethods(driver);
            baseMethods.addAddress();
            waitForLoad();
            clickElementById("lnkBtnSiparisKaydet2");

        } else {
            clickElementById("lnkBtnSiparisKaydet2");
            sleep(3000L);
        }

    }

}
