package org.honestbee;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager extends Base {

	private static DriverManager driverManager = new DriverManager();

	public static DriverManager getInstance() {
		return driverManager;
	}

	private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>() {
		@Override
		protected RemoteWebDriver initialValue() {

			DesiredCapabilities cap = new DesiredCapabilities();

			if (browser == null || browser.isEmpty()) {
				browser = "chrome";
				System.out.println("No browser provided!");
			}

			if (browser.toLowerCase().equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "/usr/local/share/geckodriver");
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
			} else if (browser.toLowerCase().equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver");
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				Map<String, Object> prefs = new HashMap<String, Object>();
		        prefs.put("profile.default_content_setting_values.notifications", 2);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
		        options.setExperimentalOption("prefs", prefs);
				cap.setCapability(ChromeOptions.CAPABILITY, options);
			}

			URL url = null;
			try {
				url = new URL("http://localhost:4444/wd/hub");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			if (host == null || host.isEmpty()) {
				host = "grid";
			}

			if (host.equals("grid")) {
				return new RemoteWebDriver(url, cap);
			} else if (host.equals("localhost")) {
				if (browser.toLowerCase().equals("firefox")) {
					return new FirefoxDriver(cap);
				} else if (browser.toLowerCase().equals("chrome")) {
					return new ChromeDriver(cap);
				}
			}
			return null;
		}
	};

	public WebDriver getDriver() {
		return driver.get();
	}

	public void removeDriver() {
		driver.get().quit();
		driver.remove();
	}

}
