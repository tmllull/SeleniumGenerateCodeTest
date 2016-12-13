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
		driver.get("https://www.google.es/");
		attrs.add("id");
		attrs.add("class");
		attrs.add("name");
		attrs.add("placeholder");
		attrs.add("type");
		attrs.add("value");
		// values.add("email");
		// values.add("Email");
		values.add("buscar");
		// values.add("iniciar");
		// values.add("Iniciar");
		// values.add("login");
		// values.add("Login");
		// values.add("registro");
		// values.add("Registro");
		findElements(values);
	}

	/**
	 * Funció que cerca a tota la pàgina els elements que contenen el text
	 * "text" o que el tenen com atribut. En cas de trobar-ne algun, retorna
	 * quin atribut conté aquest text i el seu id, si en té.
	 * 
	 * @param text
	 */
	public static void findElements(List<String> values) {

		for (String v : values) {
			List<WebElement> links = driver.findElements(By.cssSelector("*"));
			//List<WebElement> links2 = driver.findElements(By.xpath("//*[contains(text(), '"+v+"')]"));
		    List<WebElement> links2 = driver.findElements(By.xpath("//*[text()[contains(translate(.,'B','b'),'"+v+"')]]"));
			// System.out.println("Elements on links2 = " + links2.size());
			boolean anything = false;
			System.out.println("Searching attributtes for '" + v + "'...");

			for (WebElement link : links) {
				for (String a : attrs) {
					if (link.getAttribute(a) != null && !link.getAttribute(a).equals("")) {
						if (link.getAttribute(a).toLowerCase().contains(v.toLowerCase())) {
							System.out.print("      " + a + ": ");
							System.out.print(link.getAttribute(a));
							System.out.print(" --> Id: ");
							if (link.getAttribute("id") != null)
								System.out.println(link.getAttribute("id"));
							anything = true;
						}
					}
				}

			}

			// if (anything == false) {
			// System.out.println("No matching attributes for '" + v + "'.");
			System.out.println("Searching elements contains text '" + v + "'.");
			boolean contains = false;
			int cont = 1;
			for (WebElement link : links2) {
				//System.out.println("Element " + cont);
				for (String a : attrs) {
					if (link.getAttribute(a) != null && !link.getAttribute(a).equals("")) {
						System.out.print("      " + a + ": ");
						System.out.println(link.getAttribute(a));
						contains = true;

					}
				}
				++cont;
			}
			if (contains == false && anything == false)
				System.out.println("No matching elements for '" + v + "'.");
			// }

			System.out.println("---------------------------------------");

		}
		System.out.println("Process finished");
	}
}
