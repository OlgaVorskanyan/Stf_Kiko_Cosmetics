package aua.testingfundamentals.pom.locators;

import org.openqa.selenium.By;

public class CartPageLocators {

    public static final By SHOPPING_CART = By.cssSelector("a.header_headerElement__5VRsS[href='/en-us/cart/']");
    public static final By REMOVE_ITEM_FROM_CART = By.className("cart-product-tile_button__5ihAk");

    public static final By ADD_ONE_MORE_ITEM = By.cssSelector("button[aria-label='Increase quantity']");
    public static final By ITEM_NAME_IN_CART = By.className("cart-product-tile_productName__05kZq");



}