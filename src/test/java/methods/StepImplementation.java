package methods;

import com.thoughtworks.gauge.Step;
import contants.ContantsLoginPage;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;


public class StepImplementation extends BasePage {

    ContantsLoginPage contantsLoginPage = PageFactory.initElements(driver, ContantsLoginPage.class);


    public WebElement findElement(String elementText) {
        return contantsLoginPage.getWebElement(elementText);
    }

    public void logMessage(String text) {
        System.out.println(text);
    }

    @Step("<elementText> elementi tıklanabilir olana kadar bekle.")
    public void waitUntilElementClickable(String elementText) {
        WebElement element = findElement(elementText);
        webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Step("<elementText> elementine tıkla.")
    public void clickElement(String elementText) {
        WebElement element = null;
        try {
            element = findElement(elementText);
            element.click();
            waitSeconds(1);
            logMessage(element.getText() + " elementine tıklandı.");
        } catch (Exception e) {
            logMessage("Elemente tıklanamadı. Element: " + element + " Hata: " + e.getMessage());
        }

    }

    public void waitSeconds(int seconds) {
        try {
            logMessage(seconds + " saniye kadar bekleniyor.");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            logMessage(e.getMessage());
        }
    }

    @Step("<expectedUrl> sayfasında olduğunu kontrol et.")
    public void controlCurrentPageURL(String expectedUrl) {
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

    @Step("<elementText> elementine <text> değerini yaz.")
    public void writeText(String elementText, String text) {
        WebElement element = null;
        try {
            element = findElement(elementText);
            if (element.getText().equals("")) {
                element.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
            }
            element.sendKeys(text);

        } catch (Exception e) {
            logMessage("İlgili elemente yazı yazılırken hata oluştu. Element:" + element.getText() + "Hata: " + e.getMessage());
        }
    }
}
