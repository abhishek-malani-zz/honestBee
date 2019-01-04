package org.honestbee.pages;

import org.honestbee.Libraries;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class FoodCart extends Libraries{
	
	public FoodCart(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//div[contains(text(),'close')]")
	private WebElement removeFromCart;
	@FindBy (xpath = "//span[contains(text(),'Your cart is empty')]")
	private WebElement emptyCartMessage;
	@FindBy (xpath = "//div[contains(text(),'person')]")
	private WebElement menu;
	@FindBy (xpath = "//a[contains(@href,'/en/logout')]")
	private WebElement logout;
	
	public FoodCart clearCart() {
		waitElementToBeClickable(removeFromCart);
		clickElement(removeFromCart);
		return this;
	}
	
	public FoodCart validateEmptyCart() {
		Assert.assertTrue(checkMessage());
		return this;
	}
	
	public FoodCart clickOnMenu() {
		waitElementToBeClickable(menu);
		clickElement(menu);
		return this;
	}
	
	public FoodCart logout() {
		waitElementToBeClickable(logout);
		clickElement(logout);
		return this;
	}
	
	public boolean checkMessage() {
		waitElementToBeVisible(emptyCartMessage);
		String expected = "Your cart is empty";
		System.out.println("Expected : "+expected.trim().toLowerCase());
		String actual = emptyCartMessage.getText().trim().toLowerCase();
		System.out.println("Actual : "+actual);
		if(actual == expected.trim().toLowerCase()) {
			return true;
		}
		else {
			return false;
		}
	}

}
