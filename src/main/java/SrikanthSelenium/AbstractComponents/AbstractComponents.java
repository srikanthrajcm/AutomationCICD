package SrikanthSelenium.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.CartPage;
import SrikanthSelenium.SeleniumFrameworkDesign.pageobjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}


	public void waitforElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitforWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitforElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
	//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	//	wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;		
	}
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage cartPage = new OrderPage(driver);
		return cartPage;		
	}
}
