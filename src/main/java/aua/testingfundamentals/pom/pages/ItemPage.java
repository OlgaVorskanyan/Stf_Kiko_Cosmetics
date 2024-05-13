package aua.testingfundamentals.pom.pages;

import aua.testingfundamentals.pom.base.BasePage;
import aua.testingfundamentals.pom.locators.ItemLocators;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ItemPage extends BasePage {


    public ItemPage(WebDriver driver, WebDriverWait wait) {

        super(driver, wait);
    }

    public void clickAddToBagButton() {
        WebElement addToBagButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(ItemLocators.ADD_TO_BAG));
        scrollToElement(addToBagButton);
        addToBagButton.click();
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public String getItemTitle() {
        WebElement titleElement = driver.findElement(ItemLocators.ITEM_NAME);
        return titleElement.getText();
    }


}
