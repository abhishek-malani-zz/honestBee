package org.honestbee.pages;

import java.util.List;

import org.honestbee.Libraries;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Groceries extends Libraries {
	
	@FindBy (xpath = "//a[contains(@href,'/en/groceries/stores')]")
	private WebElement stores;
	@FindBy (xpath = "//a[contains(@href,'/en/groceries/stores/fresh-by-honestbee')]")
	private WebElement freshStore;
	@FindBy (xpath = "//input[@placeholder='Search Fresh supermarket..']")
	private WebElement searchBox;
	@FindBy (xpath = "//a[contains(@href,'/products/')]")
	private List<WebElement> products;


	public Groceries(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public Groceries clickStores() {
		waitElementToBeClickable(stores);
		clickElement(stores);
		return this;
	}
	
	public Groceries clickFreshStores() {
		waitElementToBeClickable(freshStore);
		clickElement(freshStore);
		return this;
	}
	
	public GroceriesFromFreshStores clickProduct(int index) {
		waitAllElementsToBeVisible(products);
		waitElementToBeClickable(products.get(index));
		clickElement(products.get(index));
		return new GroceriesFromFreshStores(driver);
	}

}
