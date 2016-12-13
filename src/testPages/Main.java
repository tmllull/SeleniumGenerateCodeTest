package testPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.org.apache.bcel.internal.generic.ConstantPushInstruction;

public class Main {

	public static WebDriver driver;
	public static WebDriverWait wait;
	static List<String> attrs = new ArrayList<String>();
	static List<String> values = new ArrayList<String>();

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized", "-incognito");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 30);
		driver.get(
				"https://accounts.google.com/ServiceLogin?hl=es&passive=true&continue=https://www.google.es/%3Fgws_rd%3Dssl#identifier");
		attrs.add("id");
		attrs.add("class");
		attrs.add("name");
		attrs.add("placeholder");
		attrs.add("type");
		attrs.add("value");
		findElements("dhdgssdg");
	}

	public static void findElements(String text) {

		// List<WebElement> links =
		// driver.findElements(By.xpath("//*[contains(text(), '" + text +
		// "')]"));
		// List<WebElement> links =
		// driver.findElements(By.xpath("//*[contains(text(), '" + text +
		// "')]"));
		List<WebElement> links = driver.findElements(By.cssSelector("*"));
		boolean anything = false;
		System.out.println("Searching attributtes for " + text + "...");
		for (WebElement link : links) {
			for (String a : attrs) {
				if (link.getAttribute(a) != null && !link.getAttribute(a).equals("")) {
					if (link.getAttribute(a).equals(text)) {
						System.out.print(a + ": ");
						System.out.println(link.getAttribute(a));
						anything = true;
					}
				}
			}

		}
		if (anything == false) {
			System.out.println("No matching attributes for " + text + ".");
			System.out.println("Searching elements contains text " + text + ".");
			boolean contains = false;
			links = driver.findElements(By.xpath("//*[contains(text(), '" + text + "')]"));
			for (WebElement link : links) {
				for (String a : attrs) {
					if (link.getAttribute(a) != null && !link.getAttribute(a).equals("")) {
						if (link.getAttribute(a).equals(text)) {
							System.out.print(a + ": ");
							System.out.println(link.getAttribute(a));
							contains = true;
						}
					}
				}

			}
			if (contains == false)
				System.out.println("No matching elements for " + text + ".");
		}

		System.out.println("Process finished");

	}

}
