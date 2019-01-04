package org.honestbee;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

@SuppressWarnings("deprecation")
public class Libraries extends Base {

	protected WebDriver driver;
	

	public Libraries(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * It is used to retry clicking on a web element ignoring stale element
	 * exception for 3 times. It also tries to click by actions class and scrolling
	 * to the element by javascript executor if webdriver exception is encountered.
	 * 
	 * @param element
	 *            The web element upon whom the click action needs to be performed.
	 * 
	 */
	public void clickElement(WebElement element) {
		int i = 0;
		while (i < retryCount) {
			try {
				waitForSeconds(1000);
				element.click();
				return;
			} catch (StaleElementReferenceException e) {
				i++;
				waitForSeconds(1000);
				if (i == retryCount) {
					System.out.println("Attempted : " + i + " time, but not successfull. So throwing exception");
					throw e;
				}
			} catch (WebDriverException e) {
				waitForSeconds(1000);
				try {
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", element);
					return;
				} catch (WebDriverException ex) {
					i++;
					if (i == retryCount) {
						System.out.println("Attempted : " + i + " time, but not successfull. So throwing exception");
						throw ex;
					}
				}
			}
		}
	}

	/**
	 * It is used to retry clearing a web element ignoring stale element exception
	 * for 3 times.
	 * 
	 * @param element
	 *            The web element upon whom the clear action needs to be performed.
	 */
	public void clearElement(WebElement element) {
		int i = 0;
		while (i < retryCount) {
			try {
				element.clear();
				waitForSeconds(500);
				element.clear();
				return;
			} catch (StaleElementReferenceException e) {
				i++;
				waitForSeconds(1000);
				if (i == retryCount) {
					throw e;
				}
			}
		}
	}

	/**
	 * It is used to retry send keys on a web element ignoring stale element
	 * exception for 3 times.
	 * 
	 * @param element
	 *            The web element upon whom the send keys action needs to be
	 *            performed.
	 * @param stringKey
	 *            The string that needs to be typed
	 */
	public void sendkeysElement(WebElement element, String stringKey) {
		int i = 0;
		while (i < retryCount) {
			try {
				element.sendKeys(stringKey);
				return;
			} catch (StaleElementReferenceException e) {
				i++;
				waitForSeconds(1000);
				if (i == retryCount) {
					throw e;
				}
			} catch (WebDriverException e) {
				waitForSeconds(1000);
				try {
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", element);
					continue;
				} catch (WebDriverException ex) {
					i++;
					if (i == retryCount) {
						System.out.println("Attempted : " + i + " time, but not successfull. So throwing exception");
						throw ex;
					}
				}
			}
		}
	}

	public void sendkeysElement(WebElement element, Keys key) {
		int i = 0;
		while (i < retryCount) {
			try {
				waitForSeconds(1000);
				element.sendKeys(key);
				return;
			} catch (StaleElementReferenceException e) {
				i++;
				waitForSeconds(1000);
				if (i == retryCount) {
					throw e;
				}
			} catch (WebDriverException e) {
				waitForSeconds(1000);
				try {
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", element);
					continue;
				} catch (WebDriverException ex) {
					i++;
					if (i == retryCount) {
						System.out.println("Attempted : " + i + " time, but not successfull. So throwing exception");
						throw ex;
					}
				}
			}
		}
	}

	/**
	 * It is used to retry selecting option of a drop down ignoring stale element
	 * exception for 3 times.
	 * 
	 * @param element
	 *            The web element upon whom the send keys action needs to be
	 *            performed.
	 * @param option
	 *            The string option that will be selected
	 */
	public void selectDropdown(WebElement element, String option) {
		int i = 0;
		while (i < retryCount) {
			try {
				Select select = new Select(element);
				select.selectByVisibleText(option);
				return;
			} catch (StaleElementReferenceException e) {
				i++;
				waitForSeconds(1000);
				if (i == retryCount) {
					throw e;
				}
			}
		}
	}

	/**
	 * It is used to retry selecting option of a drop down ignoring stale element
	 * exception for 3 times.
	 * 
	 * @param element
	 *            The web element upon whom the send keys action needs to be
	 *            performed.
	 * @param index
	 *            Index on the dropdown to be selected
	 */
	public void selectDropdown(WebElement element, int index) {
		int i = 0;
		while (i < retryCount) {
			try {
				Select select = new Select(element);
				select.selectByIndex(index);
				return;
			} catch (StaleElementReferenceException e) {
				i++;
				waitForSeconds(1000);
				if (i == retryCount) {
					throw e;
				}
			}
		}
	}

	/**
	 * 
	 * @param element
	 *            Web element for which we need to check the presence checked
	 * @return <code>true</code> if the element is Displayed in the DOM.
	 *         <code>false</code> otherwise
	 */
	public boolean isElementPresent(WebElement element) {
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @param elements
	 *            List of web elements for which we need to check the presence
	 * @return <code>true</code> if the size of the list of web elements is greater
	 *         than 0 <code>false</code> otherwise
	 */
	public boolean isElementPresent(List<WebElement> elements) {
		try {
			// new WebDriverWait(driver,
			// 5).until(ExpectedConditions.visibilityOfAllElements(elements));
			if (elements.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * It will switch the focus of driver to the new window.
	 * 
	 * @param mainWins
	 *            Set of string with window handle identities before opening a new
	 *            window
	 */
	public void switchWin(Set<String> mainWins) {
		Set<String> getWinIds = driver.getWindowHandles();
		System.out.println("Windows open are : " + getWinIds.size());
		System.out.println("Main window size : " + mainWins.size());
		for (String str : mainWins) {
			System.out.println("Main win : " + str);
		}
		for (String newWinId : getWinIds) {
			System.out.println("Win id : " + newWinId);
			if (!isStringPresentInSet(mainWins, newWinId)) {
				driver.switchTo().window(newWinId);
				System.out.println("Switched to : " + newWinId);
				return;
			}
		}
	}

	/**
	 * To check if a String is present in a set of Strings
	 * 
	 * @param stringSet
	 *            A set of String with whom to match
	 * @param stringToMatch
	 *            A String that will be checked in the set of string
	 * @return <code>true</code> If the string is present in the given set of
	 *         String. <code>false</code> otherwise
	 */
	private boolean isStringPresentInSet(Set<String> stringSet, String stringToMatch) {
		for (String str : stringSet) {
			if (stringToMatch.equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * It will get the window ids for all the tabs/browsers associated with the
	 * driver.
	 * 
	 * @param driver
	 *            Instance of web driver upon whom this action needs to be
	 *            performed.
	 * @return Set of string with window handles for each open tab/browser.
	 */
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	

	/**
	 * Used to generate a random number between the specified range
	 * 
	 * @param min
	 *            Minimum value of the random number
	 * @param max
	 *            Maximum value of the random number
	 * @return A random number in the given range
	 */
	public int getRandomNumberInRange(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}

	

	/**
	 * To wait for a certain short period.
	 * 
	 * @param timeInMilliSeconds
	 *            Duration to wait in milliseconds.
	 */
	public void waitForSeconds(int timeInMilliSeconds) {
		try {
			Thread.sleep(timeInMilliSeconds);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * It will scroll the browser so that the web element comes to view.
	 * 
	 * @param element
	 */
	public void scrollIntoView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * To check if a number lies between its maximum and minimum bound
	 * 
	 * @param number
	 *            The number which should lie between the max and min value
	 * @param max
	 *            The maximum value of the number
	 * @param min
	 *            The minimum value of the number
	 * @return <code>true</code> if the number lies between the max and minimum
	 *         value <code>false</code> otherwise
	 */
	public boolean isNumberBetweenSpecifiedRange(int number, int max, int min) {
		if (number >= min) {
			if (number <= max) {
				return true;
			}
		}
		return false;
	}

	/**
	 * To check if a number lies between its maximum and minimum bound
	 * 
	 * @param number
	 *            The number which should lie between the max and min value
	 * @param max
	 *            The maximum value of the number
	 * @param min
	 *            The minimum value of the number
	 * @return <code>true</code> if the number lies between the max and minimum
	 *         value <code>false</code> otherwise
	 */
	public boolean isNumberBetweenSpecifiedRange(double number, double max, double min) {
		if (number >= min) {
			if (number <= max) {
				return true;
			}
		}
		return false;
	}

	/**
	 * It will close all the window tabs with the given window ids
	 * 
	 * @param winIds
	 *            The window ids of the tabs that needs to be closed
	 * @param driver
	 *            the instance of webdriver
	 */
	public void closeWindows(Set<String> winIds) {
		for (String str : winIds) {
			driver.switchTo().window(str);
			driver.close();
		}
	}

	/**
	 * It is used to retry waiting for an element to be click able on a web element
	 * ignoring stale element exception for 3 times.
	 * 
	 * @param element
	 *            The web element which needs to be click able
	 */
	public void waitElementToBeClickable(WebElement element) {
		int i = 0;
		while (i < retryCount) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, explicitWaitTime);
				wait.until(ExpectedConditions.elementToBeClickable(element));
				return;
			} catch (StaleElementReferenceException e) {
				i++;
				waitForSeconds(1000);
				if (i == retryCount) {
					System.out.println("Attempted : " + i + " time, but not successfull. So throwing exception");
					throw e;
				}
			}
		}
	}

	/**
	 * It is used to retry waiting for an element to be visible on a web element
	 * ignoring stale element exception for 3 times.
	 * 
	 * @param element
	 *            The web element which needs to be visible
	 */
	public void waitElementToBeVisible(WebElement element) {
		int i = 0;
		while (i < retryCount) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, explicitWaitTime);
				wait.until(ExpectedConditions.visibilityOf(element));
				return;
			} catch (StaleElementReferenceException e) {
				i++;
				waitForSeconds(1000);
				if (i == retryCount) {
					System.out.println("Attempted : " + i + " time, but not successfull. So throwing exception");
					throw e;
				}
			}
		}
	}

	/**
	 * It is used to retry waiting for visibility of all the web elements in a list
	 * ignoring stale element exception for 3 times.
	 * 
	 * @param elements
	 *            The list of web elements which needs to be visible
	 */
	public void waitAllElementsToBeVisible(List<WebElement> elements) {
		int i = 0;
		while (i < retryCount) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, explicitWaitTime);
				wait.until(ExpectedConditions.visibilityOfAllElements(elements));
				return;
			} catch (StaleElementReferenceException e) {
				i++;
				waitForSeconds(1000);
				if (i == retryCount) {
					System.out.println("Attempted : " + i + " time, but not successfull. So throwing exception");
					throw e;
				}
			}
		}
	}

	/**
	 * It is used to retry fetching text from a web element ignoring stale element
	 * exception for 3 times.
	 * 
	 * @param element
	 *            The web element from which text needs to be fetched
	 * @return The text of the web element
	 */
	public String getTextFromElement(WebElement element) {
		int i = 0;
		String text = null;
		while (i < retryCount) {
			try {
				text = element.getText();
				return text;
			} catch (StaleElementReferenceException e) {
				i++;
				waitForSeconds(1000);
				if (i == retryCount) {
					System.out.println("Attempted : " + i + " time, but not successfull. So throwing exception");
					throw e;
				}
			}
		}
		return text;
	}

	/**
	 * It is used to retry fetching attribute value from a web element ignoring
	 * stale element exception for 3 times.
	 * 
	 * @param element
	 *            The web element from which text needs to be fetched
	 * @param attribute
	 *            The attribute whose value needs to be fetched
	 * @return The value of the attribute of the given web element.
	 */
	public String getAttributeFromElement(WebElement element, String attribute) {
		int i = 0;
		String text = null;
		while (i < retryCount) {
			try {
				text = element.getAttribute(attribute);
				return text;
			} catch (StaleElementReferenceException e) {
				i++;
				waitForSeconds(1000);
				if (i == retryCount) {
					System.out.println("Attempted : " + i + " time, but not successfull. So throwing exception");
					throw e;
				}
			}
		}
		return text;
	}

	/**
	 * It is used to retry send keys on a web element ignoring stale element
	 * exception for 3 times.
	 * 
	 * @param element
	 *            The web element upon whom the send keys action needs to be
	 *            performed.
	 * @param sendkeys
	 *            The string that needs to be typed
	 * @param timeBetweenCharacter
	 *            time to wait between typing character
	 */
	public void sendkeysElementByCharacter(WebElement element, String sendkeys, int timeBetweenCharacter) {
		int i = 0;
		while (i < retryCount) {
			try {
				for (int j = 0; j < sendkeys.length(); j++) {
					String str = Character.toString(sendkeys.charAt(j));
					element.sendKeys(str);
					waitForSeconds(timeBetweenCharacter);
				}
				return;
			} catch (StaleElementReferenceException e) {
				i++;
				waitForSeconds(1000);
				if (i == retryCount) {
					throw e;
				}
			}
		}
	}

	/**
	 * Will scroll down till 1000 units
	 * 
	 * @param driver
	 */
	public void scrollToBottom() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1000)", "");
	}

	/**
	 * Will scroll up till 1000 units
	 * 
	 * @param driver
	 */
	public void scrollToTop() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1000)", "");
	}

	

	public void moveToElement(WebElement element) {
		int i = 0;
		while (i < retryCount) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(element).build().perform();
				return;
			} catch (StaleElementReferenceException e) {
				i++;
				waitForSeconds(1000);
				if (i == retryCount) {
					System.out.println("Attempted : " + i + " time, but not successfull. So throwing exception");
					throw e;
				}
			}
		}

	}

	public void refreshBrowser() {
		driver.navigate().refresh();
	}

}
