import aua.testingfundamentals.pom.locators.SearchLocators;
import aua.testingfundamentals.pom.pages.SearchPage;
import base.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

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

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(SearchLocators.NO_RESULTS_MESSAGE));
        searchPage.getErrorMessage();
        Assert.assertEquals(searchPage.getErrorMessage(), "No results found");
    }

    @Test
    public void searchEmpty() {
        SearchPage searchPage = new SearchPage(driver, webDriverWait);
        String initialUrl = driver.getCurrentUrl();
        searchPage.performSearch();
        searchPage.enterSearchQuery("");
        searchPage.enter();

        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(initialUrl)));

        String finalUrl = driver.getCurrentUrl();
        Assert.assertEquals(initialUrl, finalUrl, "URL has not changed after searching for an empty product");
    }

    //it should fail as I have found a bug here

}