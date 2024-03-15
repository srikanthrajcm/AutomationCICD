package SrikanthSelenium.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SrikanthSelenium.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{

	
	WebDriver driver;

	public ProductCatalogue(WebDriver driver)
	{
	super(driver);
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}


	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductlist() {
	
		waitforElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
        
		WebElement prod = getProductlist().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
		//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
	}
	
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitforElementToAppear(toastMessage);
		waitforElementToDisappear(spinner);
	}
}
