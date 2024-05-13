package aua.testingfundamentals.pom.pages;
import aua.testingfundamentals.pom.base.BasePage;
import aua.testingfundamentals.pom.locators.SortLocators;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.stream.Collectors;


public class SortPage extends BasePage {

    public SortPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openSortDropdown() {
        WebElement sortButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(SortLocators.SORT_BUTTON));
        sortButton.click();
    }

    public void sortByPriceLowToHigh() {
        openSortDropdown();
        WebElement sortByLowToHigh = webDriverWait.until(ExpectedConditions.elementToBeClickable(SortLocators.PRICE_LOW_TO_HIGH));
        sortByLowToHigh.click();
    }

    public void sortByPriceHighToLow() {
        openSortDropdown();
        WebElement sortByHighToLow = webDriverWait.until(ExpectedConditions.elementToBeClickable(SortLocators.PRICE_HIGH_TO_LOW));
        sortByHighToLow.click();
    }


    public List<Double> getProductPrices() {
        WebElement descriptionElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(SortLocators.PRODUCT_PRICE));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", descriptionElement);
        return webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SortLocators.PRODUCT_PRICE))
                .stream()
                .map(WebElement::getText)
                .map(priceText -> priceText.replaceAll("[^\\d.]", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }
}