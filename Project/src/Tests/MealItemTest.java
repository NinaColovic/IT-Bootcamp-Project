package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MealItemTest extends BasicTest {

	@Test(priority = 1)
	public void AddMealToCartTesta() throws InterruptedException {

		this.driver.navigate().to(this.BaseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		this.locationPopupPage.ClosePopupDialog();
		this.mealPage.AddMealToCart("2");
		Thread.sleep(2000);
		String message = this.notificationSistemPage.Message();
		Assert.assertTrue(message.contains("The Following Errors Occurred:"), "[Error - message doesn't exist]");
		Assert.assertTrue(message.contains("Please Select Location"), "[Error - message doesn't exist]");

		this.notificationSistemPage.Wait();
		this.locationPopupPage.OpenPopupContent();
		this.locationPopupPage.SetLocation("City Center - Albany");
		Thread.sleep(2000);
		this.mealPage.AddMealToCart("2");
		Assert.assertEquals(this.notificationSistemPage.Message(), "Meal Added To Cart",
				"[Error - message doesn't exist]");
	}

	@Test(priority = 5)
	public void AddMealToFavorite() throws InterruptedException {
		this.driver.navigate().to(this.BaseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		this.locationPopupPage.ClosePopupDialog();
		this.mealPage.AddMealToFavorite();
		Thread.sleep(2000);
		Assert.assertEquals(this.notificationSistemPage.Message(), "Please login first!",
				"[Error - message doesn't exist]");

		this.notificationSistemPage.Wait();
		this.driver.navigate().to(this.BaseUrl + "/guest-user/login-form");
		this.loginPage.LogInCustomer(this.username, this.password);
		this.driver.navigate().to(this.BaseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(2000);
		this.mealPage.AddMealToFavorite();
		Assert.assertEquals(this.notificationSistemPage.Message(), "Product has been added to your favorites.",
				"[Error - message doesn't exist]");
	}

	@Test(priority = 8)
	public void ClearCartTesta() throws IOException, InterruptedException {
		this.driver.navigate().to(this.BaseUrl + "/meals");
		this.locationPopupPage.getSelectLocation();
		this.locationPopupPage.SetLocation("City Center - Albany");

		Thread.sleep(2000);
		File file = new File("data/Data.xlsx");
		FileInputStream fos = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fos);
		XSSFSheet sheet = wb.getSheet("Meals");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);

			String mealUrl = row.getCell(0).getStringCellValue();
			String quantity = NumberToTextConverter.toText(row.getCell(1).getNumericCellValue());

			this.driver.navigate().to(mealUrl);
			this.mealPage.AddMealToCart(quantity);

			Assert.assertEquals(this.notificationSistemPage.Message(), "Meal Added To Cart",
					"Error - message doesn't exist");
		}
		this.notificationSistemPage.Wait();
		this.cartSummaryPage.ClearAll();
		Assert.assertEquals(this.notificationSistemPage.Message(), "All meals removed from Cart successfully",
				"Error - message doesn't exist");
	}

}
