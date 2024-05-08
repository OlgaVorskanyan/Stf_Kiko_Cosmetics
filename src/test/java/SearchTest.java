import aua.testingfundamentals.pom.locators.SearchLocators;
import aua.testingfundamentals.pom.pages.SearchPage;
import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SearchTest extends BaseTest {
    private SearchPage searchPage;

    @BeforeMethod
    public void methodSetup() {
        searchPage = new SearchPage(driver, webDriverWait);
    }
    @Test
    public void searchForProduct() {
        searchPage.performSearch();
        searchPage.enterSearchQuery("Mascara");
        searchPage.enter();
        List<String> searchResults = searchPage.getSearchResults();

        Assert.assertFalse(searchResults.isEmpty(), "Search results are empty");

        boolean allContainMascara = searchResults.stream().allMatch(result -> result.contains("Mascara"));
        Assert.assertTrue(allContainMascara, "Not all search results contain 'Mascara'");
    }

    @Test
    public void searchForInvalidProduct() {
        SearchPage searchPage = new SearchPage(driver, webDriverWait);
        searchPage.performSearch();
        searchPage.enterSearchQuery("dcsjbncjnsdc");
        searchPage.enter();
        Duration timeout = Duration.ofSeconds(20);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SearchLocators.NO_RESULTS_MESSAGE));
        Assert.assertEquals(errorMessageElement.getText(), searchPage.getErrorMessage());
    }

    @Test
    public void searchForEmptyProductAnotherVersion() {
        String initialUrl = driver.getCurrentUrl();

        SearchPage searchPage = new SearchPage(driver, webDriverWait);
        searchPage.performSearch();
        searchPage.enterSearchQuery("");
        searchPage.enter();
        Duration timeout = Duration.ofSeconds(10);

        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(initialUrl)));

        String finalUrl = driver.getCurrentUrl();

        Assert.assertEquals(initialUrl, finalUrl, "URL has not changed after searching for an empty product");
    }
    //there is a bug because the url changed without any message.

}
