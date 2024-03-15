package SrikanthSelenium.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SrikanthSelenium.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{

	
	WebDriver driver;

	public CartPage(WebDriver driver)
	{
	super(driver);
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}


	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
	@FindBy(css=".cartSection h3")
	private List<WebElement> productTitles;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public boolean VerifyProductDisplay(String productName) {
		Boolean match = productTitles.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductlist() {
	
		waitforElementToAppear(productsBy);
		return productTitles;
	}
	
	public WebElement getProductByName(String productName) {
        
		WebElement prod = getProductlist().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
		//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitforElementToAppear(toastMessage);
	//	waitforElementToDisappear(spinner);
	}
	
	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
}
