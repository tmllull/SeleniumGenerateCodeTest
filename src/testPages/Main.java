package testPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

	public static WebDriver driver;
	public static WebDriverWait wait;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized", "-incognito");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 30);
		driver.get("http://www.google.es");
		findElements();
	}

	public static void findElements() {

		List<WebElement> links = driver.findElements(By.xpath("//*[contains(text(), 'a')]"));
		System.out.println(links.size());
		for(WebElement link : links)
			if (!link.getText().equals(""))
		    System.out.println(link.getText());
		
	}

}
