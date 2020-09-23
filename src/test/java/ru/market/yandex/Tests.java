package ru.market.yandex;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Tests extends Steps{

	@Test
	public void firstTest() {
		openYa();
		openMarket();
		openSection("Компьютеры");
		openSection("Ноутбуки");
		setPrice("10000", "30000");
		setManufacturer("HP");
		setManufacturer("Lenovo");
		changeElemCount();
		checkElemCount();
	}
}
