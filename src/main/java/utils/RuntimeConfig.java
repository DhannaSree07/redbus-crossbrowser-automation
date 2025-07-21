package utils;

public class RuntimeConfig {
	private static final ThreadLocal<String> browser = new ThreadLocal<>();

	public static void setBrowser(String browserName) {
		browser.set(browserName);
	}

	public static String getBrowser() {
		return browser.get() != null ? browser.get() : "chrome";
	}

	public static void clear() {
		browser.remove();
	}
}
