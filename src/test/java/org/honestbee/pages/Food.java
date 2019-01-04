package org.honestbee.pages;

import java.util.List;
import org.honestbee.Libraries;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Food extends Libraries {
	
	@FindBy (xpath = "//input[@placeholder='What are you hungry for ?']")
	private WebElement search_input;
	
	@FindBy (xpath = "//p[contains(text(),'Island Creamery')]")
	private WebElement suggestion;
	
	@FindBy (xpath = "//a[contains(@href,'/products/')]")
	private List<WebElement> products;
	
	@FindBy (xpath = "//*[contains(text(),'Add to cart')]//ancestor::button")
	private WebElement addToCart;
	
	@FindBy (xpath = "//a[contains(@href,'/en/food/cart')]")
	private WebElement openCart;
	
	public Food(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public Food enterSearch(String keyword) throws InterruptedException {
		waitElementToBeClickable(search_input);
		sendkeysElement(search_input, keyword);
		Thread.sleep(3000);
		return this;
	}
	
	public Food clickFirstSuggestion() {
		waitElementToBeClickable(search_input);
		waitElementToBeClickable(suggestion);
		clickElement(suggestion);
		return this;
	}
	
	public Food clickProduct(int index) {
		waitAllElementsToBeVisible(products);
		waitElementToBeClickable(products.get(index));
		clickElement(products.get(index));
		return this;
	}

	public Food clickAddToCart() {
		waitElementToBeClickable(addToCart);
		clickElement(addToCart);
		return this;
	}
	
	public FoodCart clickOpenCart() {
		waitElementToBeClickable(openCart);
		clickElement(openCart);
		return new FoodCart(driver);
	}
	
}
