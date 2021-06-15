package studio.enot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class VoiceText {

    private WebDriver driver;
    private WebDriverWait wait;
    public VoiceText(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @FindBy(xpath = "//button[@class='BtnClear']")
    private WebElement clearButton;

    @FindBy(xpath = "//textarea[@class='area']")
    private WebElement textarea;

    @FindBy(xpath = "//button[@id='start']")
    private WebElement startVoice;

    public void voiceText(String str) {
        try {

            clearButton.click();
            Thread.sleep(2000);
            textarea.sendKeys(str);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void voiceClick() {
        startVoice.click();
    }
}
