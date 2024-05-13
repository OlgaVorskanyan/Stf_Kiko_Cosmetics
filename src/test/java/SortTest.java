import aua.testingfundamentals.pom.locators.SortLocators;
import aua.testingfundamentals.pom.pages.SearchPage;
import aua.testingfundamentals.pom.pages.SortPage;
import base.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SortTest extends BaseTest {

    private static final String SEARCH_TERM = "Mascara";
    private SortPage sortPage;

    @BeforeMethod
    public void setupSortPage() {
        SearchPage searchPage = new SearchPage(driver, webDriverWait);
        searchPage.performSearch();
        searchPage.enterSearchQuery(SEARCH_TERM);
        searchPage.getDriver().switchTo().activeElement().sendKeys(Keys.ENTER);
        sortPage = new SortPage(driver, webDriverWait);
    }

    @Test
    public void testSortByPriceLowToHigh() {
        webDriverWait.withTimeout(Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(SortLocators.SORT_BUTTON));
        sortPage.sortByPriceLowToHigh();
        List<Double> prices = sortPage.getProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1),
                    "Prices are not sorted from low to high. Found " + prices.get(i) + " before " + prices.get(i + 1));
        }
    }

    @Test
    public void testSortByPriceHighToLow() {
        webDriverWait.withTimeout(Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(SortLocators.SORT_BUTTON));

        sortPage.sortByPriceHighToLow();

        List<Double> prices = sortPage.getProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i + 1),

                    "Prices are not sorted from high to low. Found " + prices.get(i) + " before " + prices.get(i + 1));
        }
    }

    //found more bugs here as the prices are not sorted properly

}