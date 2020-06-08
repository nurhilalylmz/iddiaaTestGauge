package methods;

import com.thoughtworks.gauge.Step;
import contants.ContantsLoginPage;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;


public class StepImplementation extends BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public StepImplementation(WebDriver webDriver) {
        this.driver = webDriver;
    }
    ContantsLoginPage contantsLoginPage= PageFactory.initElements(driver, ContantsLoginPage.class);

    protected WebElement findElement(String elementText){
       return contantsLoginPage.getWebElement(elementText);
    }

    protected void logMessage(String text) {
        System.out.println(text);
    }

    @Step("<elementText> elementine <text> değerini yaz.")
    protected void writeText(String elementText, String text) {
        WebElement element = null;
        try {
            element=findElement(elementText);
            if (element.getText().equals("")) {
                element.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
            }
            element.sendKeys(text);

        } catch (Exception e) {
            logMessage("İlgili elemente yazı yazılırken hata oluştu. Element:" + element.getText() + "Hata: " + e.getMessage());
        }
    }

    @Step("<elementText> elementi tıklanabilir olana kadar bekle.")
    protected void waitUntilElementClickable(String elementText) {
        WebElement element = findElement(elementText);
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Step("<elementText> elementine tıkla.")
    protected void clickElement(String elementText) {
        WebElement element = null;
        try {
            element=findElement(elementText);
            element.click();
            waitSeconds(1);
            logMessage(element.getText() + " elementine tıklandı.");
        } catch (Exception e) {
            logMessage("Elemente tıklanamadı. Element: " + element + " Hata: " + e.getMessage());
        }

    }

    protected void waitSeconds(int seconds) {
        try {
            logMessage(seconds + " saniye kadar bekleniyor.");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            logMessage(e.getMessage());
        }
    }

    @Step("<expectedUrl> sayfasında olduğunu kontrol et.")
    protected void controlCurrentPageURL(String expectedUrl) {
        try {
            if (!expectedUrl.isEmpty()) {
                if (expectedUrl.equals(driver.getCurrentUrl())) {
                    Assert.fail("Verilen URL'ler eş değil. Aslında : " + driver.getCurrentUrl() + "/ Beklenen: " + expectedUrl);
                }
            }
        } catch (Exception e) {
            logMessage("Sayfa kontrolü yapılırken hata alındı. Hata : " + e.getMessage());
        }

    }
}
