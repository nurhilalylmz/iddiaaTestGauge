package pages;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.junit.Assert;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BasePage {
    public static WebDriver driver;

    @BeforeScenario
    public void setUp() {
        setBrowser("chrome");
        driver.navigate().to("https://test.iddaa.com/giris-yap");
    }

    @AfterScenario
    public void tearDown() {
        if(driver!=null){
            driver.quit();
        }
    }

    public  void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
    public  void setBrowser(String browser) {
        if(browser.equals("chrome")){
            //chrome driver dizinini belirttik.
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
            //Browser ayarları
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //Chrome ayarlarını yapmak için yeni bir obje nesnesi oluşturulur.
            ChromeOptions chromeOptions = new ChromeOptions();
            //Browser tam ekranda gösterilir.
            chromeOptions.addArguments("start-maximized");
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            //Driver'ımızı setliyoruz.
            setDriver(new ChromeDriver(chromeOptions));
        }
        else if(browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
            //Browser ayarları
            setDriver(new FirefoxDriver());

        }
        else{
            Assert.fail("Driver bulunamadı.Ayarları kontrol ediniz.");
        }
    }
}