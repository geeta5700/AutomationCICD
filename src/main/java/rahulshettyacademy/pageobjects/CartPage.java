package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//Page Factory

	@FindBy(css = ".totalRow button.btn-primary")
	WebElement checkOut;
	
	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	

	public Boolean verifyCartProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		scrollIntoViewButton();
		elementToBeClickable(checkOut);
		checkOut.click();
		return new CheckoutPage(driver);
		
	}
	
}
