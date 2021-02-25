package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getUserAcc() {
		return this.driver.findElement(By.className("after-arrow"));
	}

	public WebElement getLogout() {
		return this.driver.findElement(By.linkText("Logout"));
	}

	public void Logout() {
		this.getUserAcc().click();
		this.getLogout().click();
	}

}
