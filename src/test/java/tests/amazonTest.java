package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import pages.*;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JSUtils;
import utilities.ReusableMethods;
import utilities.logs.Log;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("Amazon End to End Wishlist")
@Feature("Search Item and Add to Wishlist Positive")
public class amazonTest {
    CommonPageElements commonPage;
    SearchResultPage searchResultPage;
    WhishlistPage alisverisListesi;
    ProductPage productPage;
    SignInPage signInPage;

    @BeforeAll
    public void setUp() {
        Log.info("Setting up the tests");

        commonPage = new CommonPageElements();
        searchResultPage = new SearchResultPage();
        alisverisListesi = new WhishlistPage();
        productPage = new ProductPage();
        signInPage = new SignInPage();
    }

    @Test
    @Story("User is on the Main Page ")
    @Description("Asserting if user is on the correct page")
    @Order(1)
    public void testMainPage() {
        Driver.getDriver().get(ConfigReader.getProperty("app_url"));
        Log.info("Opening the amazon website");
        JSUtils.highlightElement(commonPage.cerezleriKabulEt);
        commonPage.cerezleriKabulEt.click();
        assertEquals("https://www.amazon.com.tr/", Driver.getDriver().getCurrentUrl());

    }

    @Test
    @Story("User logs in with valid credentials ")
    @Description("Asserting if log in is successful")
    @Order(2)
    public void loginTest() {
        Log.info("signing in to amazon");

        //Login islemleri
        JSUtils.highlightElement(commonPage.navUser);
        JSUtils.clickElementByJS(commonPage.navUser);
        JSUtils.highlightElement(signInPage.email);
        signInPage.email.sendKeys(ConfigReader.getProperty("email") + Keys.ENTER);
        JSUtils.highlightElement(signInPage.password);
        signInPage.password.sendKeys(ConfigReader.getProperty("password") + Keys.ENTER);
        //login oldugunun kontrolu
        JSUtils.highlightElement(commonPage.navUser);
        assertFalse(commonPage.navUser.getText().toLowerCase().contains("sign in"));
        JSUtils.highlightElement(commonPage.searchDropdownBox);

    }

    @Test
    @Story("User search for the Item in Given category ")
    @Description("Asserting the search results")
    @Order(3)
    public void searchTest() {
        Log.info("searching the item in amazon");

        //Arama kismindan bilgisayarlarin secilmesi
        Select select = new Select(commonPage.searchDropdownBox);
        select.selectByVisibleText("Bilgisayarlar");
        assertEquals("Bilgisayarlar", commonPage.searchNavLabel.getText());
        //msi keyword arama
        commonPage.searchtxtbox.sendKeys("msi" + Keys.ENTER);


    }

    @Test
    @Story("User goes to the second page")
    @Description("Going to the second page and asserting")
    @Order(4)
    public void navigateSearchTest() {
        Log.info("going to the second page in amazon");
        //sonuclardan 2.sayfaya gitme işlemi
        JSUtils.scrollIntoViewJS(searchResultPage.paginationList.get(0));
        JSUtils.highlightElement(ReusableMethods.getDesiredPagination(searchResultPage.paginationList, "2"));
        ReusableMethods.getDesiredPagination(searchResultPage.paginationList, "2").click();
        //ikinci sayfada oldugu kontrolu
        JSUtils.highlightElement(searchResultPage.paginationSelected);
        JSUtils.scrollIntoViewJS(searchResultPage.paginationSelected);
        assertEquals("2", searchResultPage.paginationSelected.getText(), "ikinci sayfada değilsiniz");

    }


    @Test
    @Story("User adds second item to the wishList")
    @Description("in the second page, add the second item to the list")
    @Order(5)
    public void wishlistTest() {
        Log.info("going to the second item in amazon");
        JSUtils.scrollIntoViewJS(searchResultPage.searchResultList.get(1));
        JSUtils.highlightElement(searchResultPage.searchResultList.get(1), 2);
        searchResultPage.searchResultList.get(1).click();
        //urun alisveris listesine eklenir
        Log.info("adding to the second item in whislist");
        productPage.addToWishlistBtn.click();
        commonPage.alisveriseDevamEtBtn.click();
        ReusableMethods.waitFor(1);
        ReusableMethods.pageUp(1);

        //alisveris listesine gidilir
        Log.info("navigating to the wishlist");
        ReusableMethods.hover(commonPage.navUser);
        JSUtils.highlightElement(commonPage.alisverisListesi, 2);
        commonPage.alisverisListesi.click();
        JSUtils.highlightElement(alisverisListesi.listHeading, 1);
        assertTrue(alisverisListesi.listHeading.getText().contains("Listelerim"));


    }

    @Test
    @Story("User removes the item from list")
    @Description("added all items in the wishlist to a container in order to asserting if removing item is successful")
    @Order(6)
    public void removeItem() {
        Log.info("removing the item in whislist");
        //urunun kaldirildigini kontrol etmek icin istek listesindekilerin sayisi kaydedilir
        int listSizeBeforeDelete = alisverisListesi.kaldırBtnList.size();
        JSUtils.highlightElement(alisverisListesi.kaldırBtnList.get(0), 2);
        alisverisListesi.kaldırBtnList.get(0).click();
        Driver.getDriver().navigate().refresh();
        int listSizeAfterDelete = alisverisListesi.kaldırBtnList.size();
        assertNotEquals(listSizeBeforeDelete, listSizeAfterDelete);
    }

    @Test
    @Story("User logs out from the page")
    @Description("in the second page, add the item to the list")
    @Order(7)
    public void signOutTest() {
        Log.info("sign out from amazon");
        //sign out islemleri
        ReusableMethods.hover(commonPage.navUser);
        commonPage.cikisYap.click();
        assertEquals("Amazon Giriş Yap", Driver.getDriver().getTitle(), "cikis islemi basarisiz");

    }


    @AfterAll
    public void tearDown() throws InterruptedException {
        Log.info("tearing down and deleting all cookies");
        Driver.getDriver().manage().deleteAllCookies();
        Driver.closeDriver();
    }


}
