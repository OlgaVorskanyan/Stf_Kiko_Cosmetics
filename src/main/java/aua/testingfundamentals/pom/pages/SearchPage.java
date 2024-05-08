package aua.testingfundamentals.pom.pages;

import aua.testingfundamentals.pom.base.BasePage;
import aua.testingfundamentals.pom.locators.SearchLocators;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends BasePage {

    public SearchPage( WebDriver driver,  WebDriverWait wait) {
        super(driver, wait);
    }
    public  void performSearch() {
        WebElement enableSearchField = driver.findElement(SearchLocators.ENABLE_SEARCH_FIELD);
        enableSearchField.click();
    }

    public  void enterSearchQuery(String query) {
        WebElement searchField = driver.findElement(SearchLocators.SEARCH_FIELD);
        searchField.sendKeys(query);
    }

    public String getErrorMessage(){
        WebElement errorMessage = driver.findElement(SearchLocators.NO_RESULTS_MESSAGE);
        return errorMessage.getText();
    }

    public  List<String> getSearchResults() {
        Duration timeout = Duration.ofSeconds(20);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        List<WebElement> searchResultElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SearchLocators.SEARCH_RESULTS));

        return searchResultElements.stream()
                .map(WebElement::getText)
                .toList();
    }

    public void enter(){
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

}
