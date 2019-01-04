package org.honestbee.pages;

import org.honestbee.Libraries;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GroceriesFromFreshStores extends Libraries {
	
	
	@FindBy (xpath = "//*[contains(text(),'Add to cart')]//ancestor::button")
	private WebElement addToCart;
	@FindBy (xpath = "//div[contains(text(),'close')]")
	private WebElement closeForm;
	@FindBy (xpath = "//a[contains(@href,'/en/groceries/cart')]")
	private WebElement openCart;
	@FindBy (xpath = "//div[contains(text(),'person')]")
	private WebElement menu;
	@FindBy (xpath = "//a[contains(@href,'/en/logout')]")
	private WebElement logout;

	public GroceriesFromFreshStores(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public GroceriesFromFreshStores clickAddToCart() {
		waitElementToBeClickable(addToCart);
		clickElement(addToCart);
		return this;
	}
	
	public GroceriesFromFreshStores closeForm() {
		waitElementToBeClickable(closeForm);
		clickElement(closeForm);
		return this;
	}
	
	public GroceriesFromFreshStores openCart() {
		waitElementToBeClickable(openCart);
		clickElement(openCart);
		return this;
	}
	
	public GroceriesFromFreshStores clickOnMenu() {
		waitElementToBeClickable(menu);
		clickElement(menu);
		return this;
	}
	
	public GroceriesFromFreshStores logout() {
		waitElementToBeClickable(logout);
		clickElement(logout);
		return this;
	}
}
