package aua.testingfundamentals.pom.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import aua.testingfundamentals.pom.base.BasePage;
import aua.testingfundamentals.pom.locators.CartPageLocators;


public class CartPage extends BasePage {

    public CartPage(final WebDriver driver, final WebDriverWait wait) {
        super(driver, wait);
    }
    public void clickBasketButton() {
        WebElement basketButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(CartPageLocators.SHOPPING_CART));
        basketButton.click();
    }

    public void removeItemFromCart() {
        WebElement removeButton = driver.findElement(CartPageLocators.REMOVE_ONE_ITEM);
        removeButton.click();
    }

    public void addOneMoreItemToCart() {
        WebElement addButton = driver.findElement(CartPageLocators.ADD_ONE_MORE_ITEM);
        addButton.click();
    }

    public String getItemTitle() {
        WebElement titleElement = driver.findElement(CartPageLocators.ITEM_NAME_IN_CART);
        return titleElement.getText();
    }

    public String getItemQuantity() {
        WebElement quantityInput = driver.findElement(CartPageLocators.BASKET_ITEM_QUANTITY);
        return quantityInput.getAttribute("value");
    }
    public String getItemPrice() {
        WebElement priceElement = driver.findElement(CartPageLocators.BASKET_ITEM_PRICE);
        return priceElement.getText();
    }

    public String getEmptyCartMessage() {
        WebElement emptyMessage = driver.findElement(CartPageLocators.EMPTY_CART_MESSAGE);
        return emptyMessage.getText();

    }

}
