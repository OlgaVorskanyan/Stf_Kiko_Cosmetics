import aua.testingfundamentals.pom.locators.CartPageLocators;
import aua.testingfundamentals.pom.locators.ItemLocators;
import aua.testingfundamentals.pom.locators.SearchLocators;
import aua.testingfundamentals.pom.pages.CartPage;
import aua.testingfundamentals.pom.pages.ItemPage;
import aua.testingfundamentals.pom.pages.SearchPage;
import base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddBasketTest extends BaseTest {

    private static final String ERROR_MESSAGE_TITLE = "The product title does not match";
    private static final String SEARCH_TERM = "Mascara";
    private static final String INCREASE_WRONG = "Quantity did not increase correctly";
    private static final String PRICE_INCREASE_WRONG = "Price did not increase correctly";
    private static final String DECREASE_WRONG = "Quantity did not decrease correctly";
    private static final String PRICE_DECREASE_WRONG = "Price did not decrease correctly";
    private static final String EMPTY = "Your bag is empty!";
    private static final String WRONG_MESSAGE = "Empty cart message does not match";


    private SearchPage searchPage;
    private CartPage cartPage;
    private ItemPage itemPage;

    @BeforeMethod
    public void setupPages() {
        searchPage = new SearchPage(driver, webDriverWait);
        cartPage = new CartPage(driver, webDriverWait);
        itemPage = new ItemPage(driver, webDriverWait);

        webDriverWait.until(ExpectedConditions.elementToBeClickable(SearchLocators.ENABLE_SEARCH_FIELD));
        searchPage.performSearch();
        searchPage.enterSearchQuery(SEARCH_TERM);
        searchPage.getDriver().switchTo().activeElement().sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement descriptionElement = wait.until(ExpectedConditions.presenceOfElementLocated(ItemLocators.ITEM_NAME));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", descriptionElement);

    }

    @Test
    public void testAddProductToBasket() {


        String expectedTitle = itemPage.getItemTitle();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(ItemLocators.ADD_TO_BAG));
        itemPage.clickAddToBagButton();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        cartPage.clickBasketButton();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(CartPageLocators.ITEM_NAME_IN_CART));
        String actualTitle = cartPage.getItemTitle();
        Assert.assertEquals(actualTitle, expectedTitle, ERROR_MESSAGE_TITLE);
    }

    @Test
    public void testAddOneMoreProductToBasket() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(ItemLocators.ADD_TO_BAG));
        itemPage.clickAddToBagButton();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        cartPage.clickBasketButton();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(CartPageLocators.ADD_ONE_MORE_ITEM));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CartPageLocators.BASKET_ITEM_QUANTITY));


        int initialQuantity = Integer.parseInt(cartPage.getItemQuantity());
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(CartPageLocators.BASKET_ITEM_PRICE));

        String initialPriceText = cartPage.getItemPrice();
        double initialPrice = Double.parseDouble(initialPriceText.strip().substring(1));
        double pricePerUnit = initialPrice / initialQuantity;

        cartPage.addOneMoreItemToCart();
        webDriverWait.until(ExpectedConditions.attributeToBe(
                CartPageLocators.BASKET_ITEM_QUANTITY, "value", String.valueOf(initialQuantity + 1)));

        String expectedUpdatedPrice = "$" + String.format("%.2f", ((initialQuantity + 1) * pricePerUnit));
        webDriverWait.until(ExpectedConditions.textToBe(
                CartPageLocators.BASKET_ITEM_PRICE, expectedUpdatedPrice));

        int updatedQuantity = Integer.parseInt(cartPage.getItemQuantity());
        String updatedPriceText = cartPage.getItemPrice();

        Assert.assertEquals(updatedQuantity, initialQuantity + 1, INCREASE_WRONG);
        Assert.assertEquals(updatedPriceText, expectedUpdatedPrice, PRICE_INCREASE_WRONG);

    }

    @Test
    public void testDecreaseItemQuantity() {

        webDriverWait.until(ExpectedConditions.elementToBeClickable(ItemLocators.ADD_TO_BAG));
        itemPage.clickAddToBagButton();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        cartPage.clickBasketButton();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(CartPageLocators.ADD_ONE_MORE_ITEM));

        cartPage.addOneMoreItemToCart();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(CartPageLocators.BASKET_ITEM_QUANTITY));

        int initialQuantity = Integer.parseInt(cartPage.getItemQuantity());

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(CartPageLocators.BASKET_ITEM_PRICE));

        String initialPriceText = cartPage.getItemPrice();

        double initialPrice = Double.parseDouble(initialPriceText.strip().substring(1));
        double pricePerUnit = initialPrice / initialQuantity;


        wait.until(ExpectedConditions.visibilityOfElementLocated(CartPageLocators.REMOVE_ONE_ITEM));

        cartPage.removeItemFromCart();

        webDriverWait.until(ExpectedConditions.attributeToBe(
                CartPageLocators.BASKET_ITEM_QUANTITY, "value", String.valueOf(initialQuantity - 1)));

        String expectedUpdatedPrice = "$" + String.format("%.2f", ((initialQuantity - 1) * pricePerUnit));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(CartPageLocators.BASKET_ITEM_PRICE));

        webDriverWait.until(ExpectedConditions.textToBe(
                CartPageLocators.BASKET_ITEM_PRICE, expectedUpdatedPrice));

        int updatedQuantity = Integer.parseInt(cartPage.getItemQuantity());

        String updatedPriceText = cartPage.getItemPrice();


        Assert.assertEquals(updatedQuantity, initialQuantity - 1, DECREASE_WRONG);
        Assert.assertEquals(updatedPriceText, expectedUpdatedPrice, PRICE_DECREASE_WRONG);
    }

    @Test
    public void testDecreaseItemQuantityToZero() {

        webDriverWait.until(ExpectedConditions.elementToBeClickable(ItemLocators.ADD_TO_BAG));

        itemPage.clickAddToBagButton();

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

        cartPage.clickBasketButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        wait.until(ExpectedConditions.visibilityOfElementLocated(CartPageLocators.BASKET_ITEM_QUANTITY));

        int initialQuantity = Integer.parseInt(cartPage.getItemQuantity());

        wait.until(ExpectedConditions.visibilityOfElementLocated(CartPageLocators.REMOVE_ONE_ITEM));

        cartPage.removeItemFromCart();

        webDriverWait.until(ExpectedConditions.attributeToBe(
                CartPageLocators.BASKET_ITEM_QUANTITY, "value", String.valueOf(initialQuantity - 1)));

        int updatedQuantity = Integer.parseInt(cartPage.getItemQuantity());

        Assert.assertEquals(updatedQuantity, initialQuantity - 1, DECREASE_WRONG);

        webDriverWait.until(ExpectedConditions.textToBe(CartPageLocators.EMPTY_CART_MESSAGE, EMPTY));

        String actualEmptyCartMessage = cartPage.getEmptyCartMessage();

        Assert.assertEquals(actualEmptyCartMessage, EMPTY, WRONG_MESSAGE);

    }

    @AfterMethod
    public void waitBetweenTests() {
        try {
            Thread.sleep(5000); // 5 seconds pause between tests
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

