package utilities.BaseMethods;


import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.BasePage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BaseMethods extends BasePage {

    public BaseMethods(WebDriver driver) {
        super(driver);
    }

    Random random = new Random();


    boolean xpathElement = true;

    public void addAddress() {
        clickElementByXpath("//*[@id='txtAdresBasligi']");
        fillInputFieldByXpath("//*[@id='txtAdresBasligi']", configuration.getProperty("address_head"));
        clickElementByXpath("//*[@id='txtAdresAdSoyad']");
        fillInputFieldByXpath("//*[@id='txtAdresAdSoyad']", configuration.getProperty("name_surname"));
        clickElementByXpath("//*[@id='txtAdresAliciTelefon']");
        fillInputFieldByXpath("//*[@id='txtAdresAliciTelefon']", configuration.getProperty("phone_num"));
        clickElementByXpath("//*[@id='txtAdres']");
        fillInputFieldByXpath("//*[@id='txtAdres']", configuration.getProperty("main_address"));
        Select cities = new Select(findElementById("slctCity"));
        cities.selectByVisibleText("İstanbul");
        Select districts = new Select(findElementById("slctDistrict"));
        districts.selectByVisibleText("Fatih");
        clickElementById("txtPostaKodu");
        fillInputFieldByXpath("//*[@id='txtPostaKodu']", configuration.getProperty("post_code"));
        clickElementById("txtTCKimlik");
        fillInputFieldByXpath("//*[@id='txtTCKimlik']", configuration.getProperty("id_number"));
        scrollToElementById("saveAddressButton");
        clickElementById("saveAddressButton");
    }

    public void randomCargoMethod() {
        ArrayList<WebElement> spaceList = new ArrayList<>();
        List<WebElement> cargoList = driver.findElements(By.xpath("//*[@class='cargoName']/span[2]"));
        for (int i = 0; i < cargoList.size(); i++) {
            String cargoLength = cargoList.get(i).getText();
            if (Double.parseDouble(cargoList.get(i).getText().substring(1, cargoLength.length()).replaceAll(",", ".")) > 0.00)
                spaceList.add(cargoList.get(i));
        }
        int randomIndex = random.nextInt(spaceList.size());
        spaceList.get(randomIndex).click();
        clickElementByXpath("//*[@id='lnkBtnSiparisKaydet2']");
    }


    protected String nowDate(String dateFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime now = LocalDateTime.now();
        return now.format(df);
    }


    public void dragAndDrop(String dragFromXpath, String dropToXpath) {
        WebElement From = driver.findElement(By.xpath(dragFromXpath));
        WebElement To = driver.findElement(By.xpath(dropToXpath));
        Actions act = new Actions(driver);
        act.dragAndDrop(From, To).build().perform();
    }

    public void switchToFrame(String frameId) {
        navigateToURL(configuration.getProperty("frame_site"));
//        driver.switchTo().frame("frame1");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(frameId)));
    }


    public void switchToNewTabFromXpath(String xpath) {
        waitForNewWindow(driver, 10);
        driver.findElement(By.xpath(xpath)).click();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0)).close();
        driver.switchTo().window(tabs.get(1));
    }

    public void switchToNewWindow() {
        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.switchTo().window(winHandleBefore).close();
    }

    public void switchToAlertWindow() {
        driver.switchTo().alert().accept();
    }


    public boolean existsElementXpath(String Xpath) {
        try {
            driver.findElement(By.xpath(Xpath));
        } catch (NoSuchElementException e) {
            xpathElement = false;
        }
        return xpathElement;
    }


    public void UploadTest(String URL, String uploadElement, String filePath) {
        navigateToURL(URL);
        sleep(1000L);
        WebElement uploadFile = findElementById(uploadElement);
        uploadFile.sendKeys(System.getProperty("user.dir") + filePath);
        sleep(3000L);
    }

    public void DownLoadTest(String URL, String downloadElement) {
        navigateToURL(URL);
        sleep(1000L);
        clickElementById(downloadElement);
        sleep(3000L);
    }

    public void smallModalTest() {
        navigateToURL("https://demoqa.com/modal-dialogs");
        clickElementByXpath("//*[@class='mr-4 mt-2 btn btn-primary']");
        String headText = driver.findElement(By.id("example-modal-sizes-title-sm")).getText();
        System.out.println(headText);
        String paraphText = driver.findElement(By.className("modal-body")).getText();
        System.out.println(paraphText);
        driver.findElement(By.id("closeSmallModal")).click();

    }

    public void largeModalTest() {
        navigateToURL("https://demoqa.com/modal-dialogs");
        clickElementByXpath("//*[@class='mt-2 btn btn-primary']");
        String headText = driver.findElement(By.id("example-modal-sizes-title-lg")).getText();
        System.out.println(headText);
        String paraphText = driver.findElement(By.className("modal-body")).getText();
        System.out.println(paraphText);
        driver.findElement(By.id("closeLargeModal")).click();
    }

    public void readTextFile(String pathFile) throws IOException {

        List<String> listOfStrings = new ArrayList<>();

        BufferedReader bf = new BufferedReader(new FileReader(System.getProperty("user.dir") + pathFile));

        String line = bf.readLine();

        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }

        bf.close();

        for (String str : listOfStrings) {
            System.out.println(str);
            navigateToURL(str);
        }

    }


}
