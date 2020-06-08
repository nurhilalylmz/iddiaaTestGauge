package pages;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BasePage {
    public static WebDriver driver;

    @Before
    public void beginTest() {
        setBrowser("chrome");
        driver.navigate().to("https://test.iddaa.com/giris-yap");
    }

    @After
    public void afterTest() {
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
            System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
            //Browser ayarları
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //Chrome ayarlarını yapmak için yeni bir obje nesnesi oluşturulur.
            ChromeOptions chromeOptions = new ChromeOptions();
            //Browser'ı test modunda çalıştırma.
            chromeOptions.addArguments("test-type");
            //Dil çevirme penceresini kapattırma.
            chromeOptions.addArguments("disable-translate");
            //Browser tam ekranda gösterilir.
            chromeOptions.addArguments("start-maximized");
            //Pop-uplar bloklanır.
            chromeOptions.addArguments("disable-popup-blocking");
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            //Driver'ımızı setliyoruz.
            setDriver(new ChromeDriver(chromeOptions));
        }
        else if(browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
            //Browser ayarları
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //Chrome ayarlarını yapmak için yeni bir obje nesnesi oluşturulur.
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            //Browser'ı test modunda çalıştırma.
            firefoxOptions.addArguments("test-type");
//        //Dil çevirme penceresini kapattırma.
            firefoxOptions.addArguments("disable-translate");
            //Browser tam ekranda gösterilir.
            firefoxOptions.addArguments("start-maximized");
            //Pop-uplar bloklanır.
            firefoxOptions.addArguments("disable-popup-blocking");
            firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
            //Driver'ımızı setliyoruz.
            setDriver(new FirefoxDriver(firefoxOptions));
        }
        else{
            Assert.fail("Driver bulunamadı.Ayarları kontrol ediniz.");
        }
    }
}