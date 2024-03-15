package SrikanthSelenium.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SrikanthSelenium.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public boolean VerifyProductDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
