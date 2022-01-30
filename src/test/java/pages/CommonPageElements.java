package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class CommonPageElements {
    public CommonPageElements(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "sp-cc-accept")
    public WebElement cerezleriKabulEt;

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchtxtbox;



    @FindBy(id = "nav-link-accountList-nav-line-1")
    public WebElement navUser;

    @FindBy(id = "searchDropdownBox")
    public WebElement searchDropdownBox;

    @FindBy(id = "nav-search-label-id")
    public WebElement searchNavLabel;



    @FindBy(partialLinkText = " Listesi")
    public WebElement alisverisListesi;

    @FindBy(id = "nav-item-signout")
    public WebElement cikisYap;

    @FindBy(id = "nav-logo-sprites")
    public WebElement homePageLogo;

    @FindBy(id = "WLHUC_continue")
    public WebElement alisveriseDevamEtBtn;



}
