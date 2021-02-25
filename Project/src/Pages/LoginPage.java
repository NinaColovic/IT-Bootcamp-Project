package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getLoginButton() {
		return this.driver.findElement(By.linkText("Login"));
	}

	public WebElement getUsername() {
		return this.driver.findElement(By.name("username"));
	}

	public WebElement getPassword() {
		return this.driver.findElement(By.name("password"));
	}

	public void LogInCustomer(String username, String password) {
		this.getLoginButton().click();
		this.getUsername().sendKeys(Keys.CONTROL + "a");
		this.getUsername().sendKeys(Keys.DELETE);
		this.getUsername().sendKeys(username);
		this.getPassword().sendKeys(Keys.CONTROL + "a");
		this.getPassword().sendKeys(Keys.DELETE);
		this.getPassword().sendKeys(password);
		this.driver.findElement(By.name("btn_submit")).click();
	}

}
