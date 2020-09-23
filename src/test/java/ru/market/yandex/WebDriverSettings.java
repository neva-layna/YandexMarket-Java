package ru.market.yandex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverSettings {
	protected WebDriver driver;
	protected WebDriverWait wait;

	@BeforeEach
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\79969\\IdeaProjects\\test_task\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 3);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterEach
	public void close() {
		driver.quit();
	}
}
