package aua.testingfundamentals.pom.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import aua.testingfundamentals.pom.base.BasePage;
import aua.testingfundamentals.pom.locators.CartPageLocators;


public class CartPage extends BasePage {

    public CartPage(final WebDriver driver, final WebDriverWait wait) {
        super(driver, wait);
    }

    public void removeItemFromCart() {
        WebElement removeButton = driver.findElement(CartPageLocators.REMOVE_ITEM_FROM_CART);
        removeButton.click();
    }

    public void addOneMoreItemToCart() {
        WebElement addButton = driver.findElement(CartPageLocators.ADD_ONE_MORE_ITEM);
        addButton.click();
    }


}
