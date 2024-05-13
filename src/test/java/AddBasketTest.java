package base;

import aua.testingfundamentals.pom.locators.CartPageLocators;
import aua.testingfundamentals.pom.locators.ItemLocators;
import aua.testingfundamentals.pom.locators.SearchLocators;
import aua.testingfundamentals.pom.pages.CartPage;
import aua.testingfundamentals.pom.pages.ItemPage;
import aua.testingfundamentals.pom.pages.SearchPage;
import base.BaseTest;
import org.openqa.selenium.devtools.v85.domstorage.model.Item;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddBasketTest extends BaseTest {

    private static final String ERROR_MESSAGE_TITLE = "The product title does not match";
    private static final String SEARCH_TERM = "Mascara";

    private SearchPage searchPage;
    private CartPage cartPage;
    private ItemPage itemPage;

    @BeforeMethod
    public void setupPages() {
        searchPage = new SearchPage(driver, webDriverWait);
        cartPage = new CartPage(driver, webDriverWait);
        itemPage = new ItemPage(driver, webDriverWait);

    }

    @Test
    public void testAddBookToBasket() {
        // Ensuring the search button is clickable before proceeding
        webDriverWait.until(ExpectedConditions.elementToBeClickable(SearchLocators.SEARCH_FIELD));
        // Performing a search to find books
        searchPage.performSearch();
        searchPage.enterSearchQuery(SEARCH_TERM);

        // Storing expected values for title, author, and price to verify later
        String expectedTitle = itemPage.getItemTitle();


        // Adding the book to the basket
        webDriverWait.until(ExpectedConditions.elementToBeClickable(ItemLocators.ADD_TO_BAG));
        itemPage.clickAddToBagButton();
        // Confirming basket operation
        webDriverWait.until(ExpectedConditions.elementToBeClickable(CartPageLocators.SHOPPING_CART));
        cartPage.clickBasketButton();

        // Ensuring all elements are visible before verifying
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(CartPageLocators.ITEM_NAME_IN_CART));

        // Retrieving and comparing the actual title, author, and price with the expected values
        String actualTitle = cartPage.getItemTitle();


        Assert.assertEquals(actualTitle, expectedTitle, ERROR_MESSAGE_TITLE);

    }
}