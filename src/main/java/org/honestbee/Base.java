package org.honestbee;

public abstract class Base {

	/**
	 * The browser name for execution
	 */
	public static String browser = System.getProperty("browser");

	/**
	 * Pass it as 'localhost', when you want to run locally
	 */
	public static String host = System.getProperty("host");

	/**
	 * URL of the server to test
	 */
	public static String url = System.getProperty("url");

	/**
	 * Number of times to retry for stale element exception before actually
	 * throwing the exception
	 */
	public static final int retryCount = 3;

	/**
	 * Time in seconds for explicit wait
	 */
	public static final int explicitWaitTime = 60;

	/**
	 * The boolean variable which says if we need screenshots on all the steps.
	 */
	public static final boolean isScreenshotRequired = Boolean.parseBoolean(System.getProperty("isScreenshotRequired"));

	/**
	 * Implicit wait for the driver in seconds
	 */
	public static final int implicitWait = 10;

}
