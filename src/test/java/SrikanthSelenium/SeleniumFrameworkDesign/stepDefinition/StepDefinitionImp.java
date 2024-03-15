package SrikanthSelenium.SeleniumFrameworkDesign.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import SrikanthSelenium.SeleniumFrameworkDesign.TestComponents.BaseTest;
import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce page")	
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and (.+)$")
	public void logged_in_username_and_pwd(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productname) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductlist();
		productCatalogue.addProductToCart(productname);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productname)
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		
		
		Boolean match = cartPage.VerifyProductDisplay(productname);		
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();	
	}
	
	//Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page
	@Then("{string} message is displayed on confirmation page")
	public void msg_displayed_conf_page(String string)
	{
		String confirmMessage = confirmationPage.veriConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));	
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void errormsg_displayed_conf_page(String string)
	{
				
		Assert.assertEquals(string, LandingPage.getErrorMessage());	
		driver.close();
	}
}
