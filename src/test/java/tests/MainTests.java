package tests;


import endUser.AccountPage;
import endUser.StorePage;
import org.testng.annotations.Test;
import utilities.BaseTest;
import endUser.HomePage;
import endUser.OrderPage;


public class MainTests extends BaseTest {

    HomePage homeMethods;

    AccountPage accountMethods;
    OrderPage orderMethods;

    StorePage storeMethods;


    @Test
    public void navigateSite() {
        homeMethods = new HomePage(driver);
        homeMethods.navigateToSiteUrl();
    }

    @Test
    public void loginSite() {
        homeMethods = new HomePage(driver);
        homeMethods.login();
    }

    @Test
    public void loginWithPopUpSite() {
        homeMethods = new HomePage(driver);
        homeMethods.loginWithPopUp();
    }

    @Test
    public void logoutSite() {
        homeMethods = new HomePage(driver);
        homeMethods.logout();
    }

    @Test
    public void addressAssertSite() {
        accountMethods = new AccountPage(driver);
        accountMethods.addressAssert();
    }

    @Test
    public void birthdayTestSite() {
        accountMethods = new AccountPage(driver);
        accountMethods.birthdayTest();
    }

    @Test
    public void productReturnTestSite() {
        accountMethods = new AccountPage(driver);
        accountMethods.productReturnTest();
    }

    @Test
    public void commentToProductSite() {
        orderMethods = new OrderPage(driver);
        orderMethods.commentToProduct();
    }


    @Test
    public void purchaseNumberOrderSite() {
        orderMethods = new OrderPage(driver);
        orderMethods.purchaseNumberOrder();
    }

    @Test
    public void orderAddBasketSite() {
        orderMethods = new OrderPage(driver);
        orderMethods.orderAddBasket();
    }

    @Test
    public void orderDateSite() {
        orderMethods = new OrderPage(driver);
        orderMethods.orderDate();
    }

    @Test
    public void sortToAlphabeticallyAtoZSite() {
        storeMethods = new StorePage(driver);
        storeMethods.sortToAlphabeticallyAtoZ();
    }

    @Test
    public void sortToAlphabeticallyZtoASite() {
        storeMethods = new StorePage(driver);
        storeMethods.sortToAlphabeticallyZtoA();
    }

    @Test
    public void sortByPriceUpSite() {
        storeMethods = new StorePage(driver);
        storeMethods.sortByPriceUp();
    }

    @Test
    public void productAmountSite() {
        storeMethods = new StorePage(driver);
        storeMethods.productAmount();
    }

}
