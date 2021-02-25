package Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test(priority = 0)
	public void EditProfileTest() throws InterruptedException {
		this.driver.navigate().to(this.BaseUrl + "/guest-user/login-form");
		this.locationPopupPage.ClosePopupDialog();
		this.loginPage.LogInCustomer(this.username, this.password);
		Assert.assertEquals(this.notificationSistemPage.Message(), "Login Successfull",
				"[Error - message doesn't exist]");

		this.driver.navigate().to(this.BaseUrl + "/member/profile");
		this.profilePage.EditPersonalInformatio("Marta", "Smit", "street", "245-8967-85", "12458", "India", "Delhi",
				"New Delhi");
		Assert.assertEquals(this.notificationSistemPage.Message(), "Setup Successful",
				"[Error - message does not exist]");

		Thread.sleep(2000);

		this.authPage.Logout();
		Assert.assertEquals(this.notificationSistemPage.Message(), "Logout Successfull!",
				"[Error - message doesn't exist]");
	}

	@Test(priority = 3)
	public void ChangeProfileImageTesta() throws IOException, InterruptedException {
		this.driver.navigate().to(this.BaseUrl + "/guest-user/login-form");
		this.locationPopupPage.ClosePopupDialog();
		this.loginPage.LogInCustomer(this.username, this.password);
		Assert.assertEquals(this.notificationSistemPage.Message(), "Login Successfull",
				"[Error - message doesn't exist]");

		this.driver.navigate().to(this.BaseUrl + "/member/profile");
		this.profilePage.UploadProfilePicture("images/images.jpg");
		Thread.sleep(2000);
		Assert.assertEquals(this.notificationSistemPage.Message(), "Profile Image Uploaded Successfully",
				"[Error - message doesn't exist]");

		this.notificationSistemPage.Wait();
		this.profilePage.RemovePicture();
		Assert.assertEquals(this.notificationSistemPage.Message(), "Profile Image Deleted Successfully",
				"[Error - message doesn't exist]");

		this.notificationSistemPage.Wait();
		this.authPage.Logout();
		Assert.assertEquals(this.notificationSistemPage.Message(), "Logout Successfull!",
				"[Error - message doesn't exist]");
	}

}
