package aua.testingfundamentals.pom.locators;

import org.openqa.selenium.By;

public class CartPageLocators {

    public static final By SHOPPING_CART = By.cssSelector("a.header_headerElement__5VRsS[href='/en-us/cart/']");

    public static final By ADD_ONE_MORE_ITEM = By.cssSelector("button[aria-label='Increase quantity']");

    public static final By ITEM_NAME_IN_CART = By.cssSelector("p.cart-product-tile_productName__05kZq");

    public static final By REMOVE_ONE_ITEM = By.cssSelector("button.beacon-cart-mod-btn[aria-label='Decrease quantity']");

    public static final By BASKET_ITEM_QUANTITY = By.cssSelector("input[name='Quantity'][aria-label='Quantity']");

    public static final By BASKET_ITEM_PRICE = By.cssSelector("p.cart-product-price_displayPrice__UuT4c");

    public static final By EMPTY_CART_MESSAGE = By.cssSelector("h2.cart-empty_title__SjpzM");


}