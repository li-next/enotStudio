package studio.enot;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Base {

    protected static WebDriver driver;
    protected HomePage homePage;
    protected VoiceText voiceText;



    @BeforeClass
    public static void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(Arrays.asList("disable-infobars", "ignore-certificate-errors", "start-maximized","use-fake-ui-for-media-stream"));
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(ReadProperties.getProperty("siteUrl"));

    }



    @AfterClass
    public static void end() {
        System.out.println("end");
        //driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        //driver.quit();
    }
}
