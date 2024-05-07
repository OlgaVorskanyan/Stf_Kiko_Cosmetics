import aua.testingfundamentals.pom.locators.SearchLocators;
import aua.testingfundamentals.pom.pages.SearchPage;
import base.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SearchTest extends BaseTest {

    @Test
    public void searchForProduct() {
        SearchPage searchPage = new SearchPage(driver, webDriverWait);
        searchPage.performSearch();
        searchPage.enterSearchQuery("Mascara");
        searchPage.getDriver().switchTo().activeElement().sendKeys(Keys.ENTER);

        List<String> searchResults = searchPage.getSearchResults();
        Assert.assertFalse(searchResults.isEmpty(), "Search results are empty");
        Assert.assertTrue(searchResults.stream().anyMatch(result -> result.contains("Mascara")),
                "Search results do not contain 'Mascara'");
    }

    @Test
    public void searchForInvalidProduct() {
        SearchPage searchPage = new SearchPage(driver, webDriverWait);
        searchPage.performSearch();
        searchPage.enterSearchQuery("dcsjbncjnsdc");
        searchPage.getDriver().switchTo().activeElement().sendKeys(Keys.ENTER);

        Duration timeout  = Duration.ofSeconds(20);
        // Wait until the error message is visible
        WebDriverWait wait = new WebDriverWait(driver, timeout); // Adjust timeout as needed
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SearchLocators.NO_RESULTS_MESSAGE));

        Assert.assertEquals(searchPage.getErrorMessage(), "No results found");
//use webDriverWait
    }
}
