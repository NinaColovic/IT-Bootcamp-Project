package Pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhoneNo() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}

	public Select getCountry() {
		WebElement select = this.driver.findElement(By.id("user_country_id"));
		Select select1 = new Select(select);
		return select1;
	}

	public Select getState() {
		WebElement select = this.driver.findElement(By.id("user_state_id"));
		Select select1 = new Select(select);
		return select1;
	}

	public Select getCity() {
		WebElement select = this.driver.findElement(By.id("user_city"));
		Select select1 = new Select(select);
		return select1;
	}

	public WebElement getSave() {
		return this.driver.findElement(By.name("btn_submit"));
	}

	public void getElementUploadPicture() {
		WebElement upload = this.driver.findElement(By.className("uploadFile-Js"));
		this.js.executeScript("arguments[0].click()", upload);
	}

	public void UploadProfilePicture(String picture) throws IOException {
		this.getElementUploadPicture();
		String imgPath = new File(picture).getCanonicalPath();
		this.driver.findElement(By.xpath("//*[@id='form-upload']/input")).sendKeys(imgPath);
	}

	public void RemovePicture() {
		WebElement remove = this.driver.findElement(By.className("remove"));
		this.js.executeScript("arguments[0].click()", remove);
	}

	public void EditPersonalInformatio(String firstName, String lastName, String address, String phoneNo,
			String zipCode, String countryName, String stateName, String cityName) throws InterruptedException {
		this.getFirstName().sendKeys(Keys.CONTROL + "a");
		this.getFirstName().sendKeys(Keys.DELETE);
		this.getFirstName().sendKeys(firstName);
		this.getLastName().sendKeys(Keys.CONTROL + "a");
		this.getLastName().sendKeys(Keys.DELETE);
		this.getLastName().sendKeys(lastName);
		this.getAddress().sendKeys(Keys.CONTROL + "a");
		this.getAddress().sendKeys(Keys.DELETE);
		this.getAddress().sendKeys(address);
		this.getPhoneNo().sendKeys(Keys.CONTROL + "a");
		this.getPhoneNo().sendKeys(Keys.DELETE);
		this.getPhoneNo().sendKeys(phoneNo);
		this.getZipCode().sendKeys(Keys.CONTROL + "a");
		this.getZipCode().sendKeys(Keys.DELETE);
		this.getZipCode().sendKeys(zipCode);
		this.getCountry().selectByVisibleText(countryName);
		Thread.sleep(2000);
		this.getState().selectByVisibleText(stateName);
		Thread.sleep(2000);
		this.getCity().selectByVisibleText(cityName);
		this.getSave().sendKeys(Keys.ENTER);
	}

}
