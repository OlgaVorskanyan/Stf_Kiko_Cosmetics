package aua.testingfundamentals.pom.locators;

import org.openqa.selenium.By;

public class SortLocators {
    public static final By SORT_BUTTON = By.cssSelector("button.category-filters_sort__tRXap");

    public static final By PRICE_LOW_TO_HIGH = By.cssSelector("div.category-sort-dialog_grid__gbSYf button:nth-of-type(2)");
    public static final By PRICE_HIGH_TO_LOW = By.cssSelector("div.category-sort-dialog_grid__gbSYf button:nth-of-type(1)");


    public static final By PRODUCT_PRICE = By.cssSelector(".product-price_displayPrice__yGkaG");

}
