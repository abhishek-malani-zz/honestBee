package org.honestbee.pages;

import org.honestbee.Libraries;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage extends Libraries {

	@FindBy(xpath = "//span[contains(text(),'Log in')]/../../..")
	private WebElement login_button;
	
	@FindBy (xpath = "//a[@href='/en/food']")
	private WebElement foodBanner;
	
	@FindBy (xpath = "//a[@href='/en/groceries']")
	private WebElement groceriesBanner;

	public Homepage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public Login clickLoginButton() {
		waitElementToBeClickable(login_button);
		clickElement(login_button);
		return new Login(driver);
	}
	
	public Food clickFoodBanner() {
		waitElementToBeClickable(foodBanner);
		clickElement(foodBanner);
		return new Food(driver);
	}
	
	public Groceries clickGroceriesBanner() {
		waitElementToBeClickable(groceriesBanner);
		clickElement(groceriesBanner);
		return new Groceries(driver);
	}

}
