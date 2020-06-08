package contants;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.Arrays;

public class ContantsLoginPage {

    ArrayList<WebElement> elementList=new ArrayList<WebElement>();
    WebElement expectedElement;

    @FindBy(how = How.ID,using = "10000")
    public WebElement phoneNumber;
    @FindBy(how = How.ID,using = "20000")
    public WebElement password;
    @FindBy(how = How.CSS,using = "button")
    public WebElement buttonLogin;
    @FindBy(how = How.CSS,using = ".col-copy")
    public WebElement copyrightText;
    @FindBy(how = How.CLASS_NAME,using = "policy-popup__close")
    public WebElement buttonClosePopup;

    public ArrayList<WebElement> getElementList(){
        elementList.add(phoneNumber);
        elementList.add(password);
        elementList.add(buttonLogin);
        elementList.add(copyrightText);
        elementList.add(buttonClosePopup);
        return elementList;
    }

    public WebElement getWebElement(String elementText){
        for(int i=0;i<getElementList().size();i++){
            if(getElementList().get(i).getText().contains(elementText)){
              expectedElement=getElementList().get(i);
            }
        }
        return expectedElement;
    }
}
