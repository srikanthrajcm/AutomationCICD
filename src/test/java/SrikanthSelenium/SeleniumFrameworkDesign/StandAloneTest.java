package SrikanthSelenium.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SrikanthSelenium.SeleniumFrameworkDesign.TestComponents.BaseTest;
import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.CartPage;
import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.CheckoutPage;
import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.LandingPage;
import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.OrderPage;
import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest extends BaseTest{

	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

	//	String productName = "ZARA COAT 3";
	
		ProductCatalogue productCatalogue = LandingPage.loginApplication(input.get("email"),input.get("password"));
		
	
		List<WebElement> products = productCatalogue.getProductlist();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
	
		Boolean match = cartPage.VerifyProductDisplay(productName);		
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();		
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	
		String confirmMessage = confirmationPage.veriConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = LandingPage.loginApplication("rajkumar1@gmail.com", "Srinivasam77");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyProductDisplay(productName));
	}

	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
	//	HashMap<String,String> map = new HashMap<String,String>();
	//	map.put("email","rajkumar1@gmail.com");
	//	map.put("password", "Srinivasam77");
	//	map.put("product", "ZARA COAT 3");
		
		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\main\\java\\SrikanthSelenium\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}};
	}
	
}
