package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SignInPage {

    public SignInPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(id = "ap_email")
    public WebElement email;

    @FindBy(id = "ap_password")
    public WebElement password;
}
