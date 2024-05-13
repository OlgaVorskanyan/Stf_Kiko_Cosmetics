package aua.testingfundamentals.pom.locators;

import org.openqa.selenium.By;

public class SearchLocators {
    public static final By ENABLE_SEARCH_FIELD = By.cssSelector(".menu-mobile_button__S7e1L.menu-mobile_searchButton__gj6DX");

    public static final By SEARCH_FIELD = By.className("menu-mobile_searchInput__sjmP6");

    public static final By SEARCH_RESULTS = By.className("search-product-grid_grid__VkhiG");

    public static final By NO_RESULTS_MESSAGE = By.xpath("//div[@class='search-product-grid_noResults__8CG6h']/h2");
}