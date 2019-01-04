package org.honestbee.tests;

import org.honestbee.DriverManager;
import org.honestbee.TestBaseSetup;
import org.honestbee.pages.Homepage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SanityGorceries extends TestBaseSetup {

	@Test(groups = { "sanityGroceries" }, enabled = true, priority = 2)
	public void addGroceryToCart() throws Exception {
		System.out.println("Test case 2");
		WebDriver driver = DriverManager.getInstance().getDriver();
		driver.get("https://www.honestbee.sg/en/");
		Homepage home = new Homepage(driver);

		home.clickLoginButton().enterEmail("abhishek.malani@treebohotels.com").enterPassword("a82003ps175")
				.clickLoginButton().clickGroceriesBanner().clickStores().clickFreshStores().clickProduct(1).clickAddToCart().closeForm().openCart().closeForm().clickOnMenu().logout();
		Thread.sleep(5000);
	}

}
