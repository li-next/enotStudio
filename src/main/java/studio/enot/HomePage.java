package studio.enot;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @FindBy(xpath = "//title")
    private WebElement title;

    @FindBy(xpath = "//div[@class='search search--header']//input[@type='search']")
    private WebElement searchForm;

    @FindBy(xpath = "//div[@class='search search--header']//button[@class='search-button']")
    private WebElement searchFormClick;

    @FindBy(xpath = "//div[@class='product_card-title']//a")
    private WebElement findResult;

    @FindBy(xpath = "//p[@class='popup--empty text-center']//strong")
    private WebElement msgFindResultEnd;

    @FindBy(xpath = "//span[@class='search-voice-trigger']")
    private WebElement microphone;

    private ProductName productName = new ProductName();
    //строка для хранения названия продукта по которому будет производиться поиск
    private String searchProductName = "";
    //строка для первого найденного товара
    private String findFirstResult = "";
    //сообщение об отсутствие товаре
    private String msgFindResult = "";

    //обнуление строк хранящий название товаров
    public void allSetNullString() {
        searchProductName = "";
        findFirstResult = "";
        msgFindResult = "";
    }

    //получить случайное название продукта для поиска
    public void setRandomProductName() {
        searchProductName = productName.getRandomName();
    }

    public String getSearchProductName() {
        return searchProductName;
    }

    public String getFindFirstResult() {
        return findFirstResult;
    }

    public String getTitle() {
        return driver.getTitle();
    }
    //первый найденный товар
    public void setFindFirstResult() {
        List<WebElement> findElements = driver.findElements(By.xpath("//div[@class='product_card-title']//a"));
        if(findElements.size() > 0) {
            findFirstResult = findResult.getText().replaceAll("_", " ").replaceAll("#", "");
        }
        else setMsgFindResultEnd();

    }
    //сообщение товар не найден
    public void setMsgFindResultEnd() {
        msgFindResult = msgFindResultEnd.getText();
    }
    public String getMsgFindResult() {
        return msgFindResult;
    }
    //поиск по названию целиком
    public void clickFindProduct() {
        allSetNullString();
        setRandomProductName();
        searchForm.sendKeys(searchProductName);
        searchFormClick.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //первый найденный продукт
        setFindFirstResult();
    }

    //поиск по первым двум буквам
    public void clickFindFirstTwoLetters() {
        allSetNullString();
        setRandomProductName();
        searchForm.sendKeys(searchProductName.substring(0, 2));
        searchFormClick.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        setFindFirstResult();
    }

    //поиск по одному спецсимволу
    public void clickFindOneChar() {
        allSetNullString();
        searchProductName = String.valueOf(productName.getRandomChar());
        searchForm.sendKeys(searchProductName);
        searchFormClick.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        setMsgFindResultEnd();
    }
    //поиск по одному буквы на кириллице
    public void clickOneLetterRu() {
        allSetNullString();
        searchProductName = productName.getRandomLetter();
        searchForm.sendKeys(searchProductName);
        searchFormClick.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        setFindFirstResult();

    }
    //строка для голосового поиска
    public String voiceFindString() {
        allSetNullString();
        setRandomProductName();
        return searchProductName;
    }

    public void findMicrophoneClick() {
        microphone.click();
    }


}
