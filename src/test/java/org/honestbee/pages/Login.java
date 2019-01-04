package org.honestbee.pages;

import org.honestbee.Libraries;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends Libraries {
	
	@FindBy (name = "email")
	private WebElement email;
	
	@FindBy (name = "password")
	private WebElement password;
	
	@FindBy (xpath = "//*[@name='password']/../following-sibling::button[1]")
	private WebElement loginButton;
	
	public Login(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public Login enterEmail(String email_str) {
		waitElementToBeVisible(email);
		sendkeysElement(email, email_str);
		return this;
	}
	
	public Login enterPassword(String password_str) {
		waitElementToBeVisible(password);
		sendkeysElement(password, password_str);
		return this;
	}
	
	public Homepage clickLoginButton() {
		waitElementToBeClickable(loginButton);
		clickElement(loginButton);
		return new Homepage(driver);
	}
	
	

}
