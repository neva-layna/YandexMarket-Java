package ru.market.yandex;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Steps extends WebDriverSettings{

	private final By ITEMS = By.xpath(".//article[@data-zone-name='snippet-card']/div[4]/div/h3/a/span");

	public WebElement waitFor(By xpath) {
		return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(xpath)));
	}

	@Step("Открываем Яндекс")
	public void openYa() {
		driver.get("https://yandex.ru");
	}

	@Step("Переходим в Яндекс.Маркет")
	public void openMarket() {
		waitFor(By.xpath(".//*[text()='Маркет']")).click();
		List<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}

	@Step("Открываем нужный раздел {section}")
	public void openSection(String section) {
		waitFor(By.xpath(".//*[text()='" + section +"']")).click();
	}

	@Step("Установка цены от {from} до {to}")
	public void setPrice(String from, String to) {
		waitFor(By.id("glpricefrom")).sendKeys("10000");
		waitFor(By.id("glpriceto")).sendKeys("30000");
	}

	@Step("Установка производителя {manafacurer}")
	public void setManufacturer(String manafacurer) {
		waitFor(By.xpath(".//span[text()='" + manafacurer +"']")).click();
	}

	@Step("Изменения количества отображаемых элементов на странице")
	public void changeElemCount() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("page_loader")));
		waitFor(By.xpath(".//button[@class='vLDMfabyVq']")).click();
		waitFor(By.xpath(".//button[text()='Показывать по 12']")).click();
	}

	@Step("Проверка количества элементов на странице")
	public void checkElemCount() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("page_loader")));
		ArrayList<WebElement> list = new ArrayList<WebElement>(driver.findElements(ITEMS));
		assertEquals(12, list.size());
		findFirstItem(list);
	}

	@Step("Поиск первого элемента из списка {list}")
	public void findFirstItem(ArrayList<WebElement> list) {
		String firstItem = list.get(0).getText();
		waitFor(By.id("header-search")).sendKeys(firstItem);
		waitFor(By.xpath(".//button[@type='submit']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("page_loader")));
		WebElement elem = driver.findElement(By.xpath(".//h3/a[@title='"+ firstItem +"']"));
		String findItem = elem.getText();
		assertEquals(firstItem, findItem);
	}
}
