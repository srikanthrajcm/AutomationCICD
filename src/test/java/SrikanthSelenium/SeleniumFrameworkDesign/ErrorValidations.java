package SrikanthSelenium.SeleniumFrameworkDesign;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import SrikanthSelenium.SeleniumFrameworkDesign.TestComponents.BaseTest;
import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.CartPage;
import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.CheckoutPage;
import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.LandingPage;
import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends BaseTest{

	@Test(groups= {"ErrorHandling"})
	public void submitOrder() throws IOException {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = LandingPage.loginApplication("rajkumar@gmail.com", "Srinivasam78");
		Assert.assertEquals("Incorrect email or password.", LandingPage.getErrorMessage());		
	}
}
