package org.honestbee.tests;

import org.honestbee.DriverManager;
import org.honestbee.TestBaseSetup;
import org.honestbee.pages.Homepage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SanityFood extends TestBaseSetup {

	@Test(groups = { "sanityFood" }, enabled = true, priority = 1)
	public void addFoodToCart() throws Exception {
		System.out.println("Test case 1");
		WebDriver driver = DriverManager.getInstance().getDriver();
		driver.get("https://www.honestbee.sg/en/");
		Homepage home = new Homepage(driver);

		home.clickLoginButton().enterEmail("abhishek.malani@treebohotels.com").enterPassword("a82003ps175")
				.clickLoginButton().clickFoodBanner().enterSearch("Island Creamery").clickFirstSuggestion()
				.clickProduct(0).clickAddToCart().clickOpenCart().clearCart().clickOnMenu().logout();
		Thread.sleep(5000);
	}

}
