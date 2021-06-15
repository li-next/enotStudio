package studio.enot;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnotStudioTest extends Base{

    @Test//открыть главную страницу
    public void open() {
        homePage = new HomePage(driver);
        assertEquals(ReadProperties.getProperty("titleHomePage"), homePage.getTitle());
    }

    @Test//поиск по названию целиком
    public void findProduct() {
        open();
        homePage.clickFindProduct();
        assertEquals(homePage.getSearchProductName(), homePage.getFindFirstResult());
    }

    @Test//поиск по первым двум буквам
    public void findFirstTwoLetters() {
        open();
        homePage.clickFindFirstTwoLetters();
        assertEquals(homePage.getSearchProductName().substring(0, 2), homePage.getFindFirstResult().substring(0, 2));
    }

    @Test//поиск по одному спецсимволу
    public void findOneSpecialCharacter() {
        open();
        homePage.clickFindOneChar();
        assertEquals(homePage.getSearchProductName(), homePage.getMsgFindResult());

    }

    @Test//поиск по одному буквы на кириллице
    public void findOneLetterRu() {
        open();
        homePage.clickOneLetterRu();
        if(homePage.getFindFirstResult().length() > 0) {
            assertTrue(homePage.getFindFirstResult().toLowerCase().split(homePage.getSearchProductName()).length > 1);
        }
        else assertEquals(homePage.getSearchProductName(), homePage.getMsgFindResult());
    }

    @Test//голосовой поиск по названию
    public void findVoiceProduct() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        open();
        String enotWindow = driver.getWindowHandle();
        js.executeScript("window.open()");
        Set<String> allWindow = driver.getWindowHandles();
        String voiceWindow = "";

        for(String tmpWindow : allWindow) {
            if(!tmpWindow.equals(enotWindow)) {
                voiceWindow = tmpWindow;
                break;
            }
        }

        String textForVoice = homePage.voiceFindString();

        driver.switchTo().window(voiceWindow);
        driver.get(ReadProperties.getProperty("voiceUrl"));
        VoiceText voiceText = new VoiceText(driver);
        voiceText.voiceText(textForVoice);
        driver.switchTo().window(enotWindow);
        homePage.findMicrophoneClick();
        driver.switchTo().window(voiceWindow);
        voiceText.voiceClick();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.switchTo().window(enotWindow);

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        homePage.setFindFirstResult();
        if(homePage.getFindFirstResult().length() > 0) {
            assertEquals(homePage.getSearchProductName(), homePage.getFindFirstResult());
            System.out.println(homePage.getSearchProductName()+ " " + homePage.getMsgFindResult());
        }
        else assertEquals(homePage.getSearchProductName(), homePage.getMsgFindResult());
        //если происходит ошибка воспроизведения текста или записи воспроизведённого текста
        System.out.println(homePage.getSearchProductName()+ " " + homePage.getMsgFindResult());

    }



}
