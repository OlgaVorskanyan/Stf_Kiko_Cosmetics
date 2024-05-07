package aua.testingfundamentals.pom.pages;

import aua.testingfundamentals.pom.base.BasePage;
import aua.testingfundamentals.pom.locators.SearchLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends BasePage {
    private WebDriverWait wait;
    public WebDriver getDriver() {
        return driver;
    }


    public SearchPage(final WebDriver driver, final WebDriverWait wait) {
        super(driver, wait);
        this.wait = wait;
    }
    public void performSearch() {
        WebElement enableSearchField = driver.findElement(SearchLocators.ENABLE_SEARCH_FIELD);
        enableSearchField.click();
    }

    public void enterSearchQuery(String query) {
        WebElement searchField = driver.findElement(SearchLocators.SEARCH_FIELD);
        searchField.sendKeys(query);
    }

    public String getErrorMessage(){
        WebElement errorMessage = driver.findElement(SearchLocators.NO_RESULTS_MESSAGE);
        return errorMessage.getText();
    }

    Duration timeout = Duration.ofSeconds(10);


    public List<String> getSearchResults() {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SearchLocators.SEARCH_RESULTS));
        List<WebElement> searchResultElements = driver.findElements(SearchLocators.SEARCH_RESULTS);
        return searchResultElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

}
