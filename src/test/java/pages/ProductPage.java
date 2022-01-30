package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ProductPage {
    public ProductPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }



    @FindBy(id = "add-to-wishlist-button-submit")
    public WebElement addToWishlistBtn;

    @FindBy(id = "WLHUC_result_action_add")
    public WebElement surayaEklendi;


}
