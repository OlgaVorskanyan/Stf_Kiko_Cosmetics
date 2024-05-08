package aua.testingfundamentals.pom.pages;

import aua.testingfundamentals.pom.locators.ItemLocators;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ItemPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ItemPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickAddToBagButton() {
        WebElement addToBagButton = wait.until(ExpectedConditions.elementToBeClickable(ItemLocators.ADD_TO_BAG));
        scrollToElement(addToBagButton);
        addToBagButton.click();
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


}
