package base;


import aua.testingfundamentals.pom.locators.ItemLocators;
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

    private SortPage sortPage;
    private static final String SEARCH_TERM = "Mascara";

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
        // Test to verify that products can be sorted by price in ascending order.
        // Check that each price is less than or equal to the next price in the list, ensuring correct sorting.
        webDriverWait.withTimeout(Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(SortLocators.SORT_BUTTON));


        sortPage.openSortDropdown();
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
        // Test to verify that products can be sorted by price in descending order.
        // Validate that each price is greater than or equal to the next price in the list, confirming correct sorting.
        webDriverWait.withTimeout(Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(SortLocators.SORT_BUTTON));
        sortPage.openSortDropdown();
        webDriverWait.withTimeout(Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(SortLocators.SORT_BUTTON));

        sortPage.sortByPriceHighToLow();
        List<Double> prices = sortPage.getProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i + 1),
                    "Prices are not sorted from high to low. Found " + prices.get(i) + " before " + prices.get(i + 1));
        }
    }
}