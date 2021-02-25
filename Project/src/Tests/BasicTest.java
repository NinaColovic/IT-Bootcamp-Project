package Tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import Pages.AuthPage;
import Pages.CartSummaryPage;
import Pages.LocationPopupPage;
import Pages.LoginPage;
import Pages.MealPage;
import Pages.NotificationSistemPage;
import Pages.ProfilePage;
import Pages.SearchResultPage;

public abstract class BasicTest {
	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSistemPage notificationSistemPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	protected SearchResultPage searchResultPage;
	protected String BaseUrl;
	protected String username;
	protected String password;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");

		this.driver = new ChromeDriver();
		this.waiter = new WebDriverWait(driver, 30);
		this.js = (JavascriptExecutor) driver;

		this.locationPopupPage = new LocationPopupPage(driver, js, waiter);
		this.loginPage = new LoginPage(driver, js, waiter);
		this.notificationSistemPage = new NotificationSistemPage(driver, js, waiter);
		this.profilePage = new ProfilePage(driver, js, waiter);
		this.authPage = new AuthPage(driver, js, waiter);
		this.mealPage = new MealPage(driver, js, waiter);
		this.cartSummaryPage = new CartSummaryPage(driver, js, waiter);
		this.searchResultPage = new SearchResultPage(driver, js, waiter);

		this.BaseUrl = "http://demo.yo-meals.com";
		this.username = "customer@dummyid.com";
		this.password = "12345678a";

		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void takeScreenShotOnFailureAndDeleteCookies(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("screenshots"));
		}
		driver.manage().deleteAllCookies();
	}

	@AfterClass
	public void quit() {
		this.driver.quit();
	}

}
