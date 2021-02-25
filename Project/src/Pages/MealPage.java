package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getMealPage() {
		return this.driver.findElement(By.linkText("Meals"));
	}

	public void AddMealToCart(String quantity) throws InterruptedException {
		this.driver.findElement(By.xpath("//input[@name='product_qty']")).click();
		this.driver.findElement(By.xpath("//input[@name='product_qty']")).sendKeys(Keys.CONTROL + "a");
		this.driver.findElement(By.xpath("//input[@name='product_qty']")).sendKeys(Keys.DELETE);
		this.driver.findElement(By.xpath("//input[@name='product_qty']")).sendKeys(quantity);
		this.driver.findElement(By.className("js-proceedtoAddInCart")).click();
	}

	public void AddMealToFavorite() {
		this.driver.findElement(By.className("favourite")).click();
	}

}
