package endUser;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utilities.BaseMethods.BaseMethods;

import java.util.*;

public class StorePage extends BaseMethods {

    public StorePage(WebDriver driver) {
        super(driver);
    }

    BaseMethods baseMethods = new BaseMethods(driver);

    HomePage homePage = new HomePage(driver);

    Random random = new Random();


    public void basketPopUpAndNonPopUp() {
        if (!baseMethods.existsElementXpath("//*[@class='fancybox-iframe']")) {
            hoverToElementByXpath("//*[@class='sepetTecxt']");

        } else {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("fancybox-iframe")));
            clickElementById("ucSepetim_aSiparisTamamlaLink2");
            driver.switchTo().defaultContent();
            clickElementById("lnkBtnSiparisKaydet2");
            sleep(3000L);
        }

    }

    public void sortToAlphabeticallyAtoZ() {
        navigateToURL("https://release811.ticimax.dev/feyza-deneme-kategori");
        clickElementByXpath("//*[@class='sortingButton']//*[text() = 'Ürün Adına Göre (A>Z)']");
        findElementByXpath("//*[@class='selected']//*[text() = 'Ürün Adına Göre (A>Z)']");
        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(By.xpath("//*[@class='productName detailUrl']//*[@title]"));

        for (WebElement we : elementList) {
            obtainedList.add(we.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>(obtainedList);
        Collections.sort(sortedList);
        Assert.assertEquals(obtainedList, sortedList);

    }


    public void sortToAlphabeticallyZtoA() {
        navigateToURL("https://release811.ticimax.dev/feyza-deneme-kategori");
        clickElementByXpath("//*[@class='sortingButton']//*[text() = 'Ürün Adına Göre (Z<A)']");
        findElementByXpath("//*[@class='selected']//*[text() = 'Ürün Adına Göre (Z<A)']");
        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(By.xpath("//*[@class='productName detailUrl']//*[@title]"));

        for (WebElement we : elementList) {
            obtainedList.add(we.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>(obtainedList);
        sortedList.sort(Comparator.reverseOrder());
        System.out.println(obtainedList);
        System.out.println("--------------");
        System.out.println(sortedList);
        Assert.assertEquals(obtainedList, sortedList);

    }

    public void sortByPriceUp() {
        navigateToURL("https://release811.ticimax.dev/feyza-deneme-kategori");
        clickElementByXpath("//*[@class='sortingButton']//*[text() = 'Fiyata Göre (Artan)']");
        findElementByXpath("//*[@class='selected']//*[text() = 'Fiyata Göre (Artan)']");
        ArrayList<Double> obtainedList = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(By.xpath("//*[@class='discountPrice']/span[1]"));

        for (WebElement webElement : elementList) {
            String cost = webElement.getText();
            Double elementDoubleList = Double.parseDouble(cost.substring(1).replace(",", "."));
            obtainedList.add(elementDoubleList);
        }

        Collections.sort(obtainedList);
        for (int i = 1; i < obtainedList.size(); i++) {
            Double elementDoubleSmall = obtainedList.get(i - 1);
            Double elementDoubleBig = obtainedList.get(i);
            Assert.assertTrue(elementDoubleSmall <= elementDoubleBig);
        }
    }


    public void productAmount() {
        navigateToSiteUrl();
        waitForLoad();
        sleep(1);
        homePage.login();
        waitForLoad();
        clickElementByXpath("//*[@class='navigation']//*[@href='/magaza-87']");
        waitForLoad();
        clickElementByXpath("//*[@id='ProductPageProductList']//*[text()='İstanbul Ümraniye Akyaka Park']");
        clickElementByXpath("//*[@id='btnHemenAl']");
        sleep(5000L);
        int randomNum = random.nextInt((10 - 1) + 1) + 1;
        String stringRand = Integer.toString(randomNum);
        driver.findElement(By.xpath("//*[@id='txtbxAdet_21568']")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.xpath("//*[@id='txtbxAdet_21568']")).sendKeys(Keys.BACK_SPACE);
        waitForLoad();
        findElementByXpath("//*[@class='textbox txtSepetAdet']").sendKeys(stringRand);
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//*[@class='textbox txtSepetAdet']"), "value", stringRand));
//        Assert.assertEquals(stringRand, findElementByXpath("//*[@class='textbox txtSepetAdet']").getAttribute("value"));

    }

}
