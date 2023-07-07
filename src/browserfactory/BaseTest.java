package browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;

    public void openBrowser(String baseurl) {

        //launch the Chrome browser
        driver = new ChromeDriver();
        //open the URL in to Browser
        driver.get(baseurl);
        //maximise Browse
        driver.manage().window().maximize();
        //we give implicit wait to Driver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }
    public void closeBrowser(){
        driver.quit();
    }
}
