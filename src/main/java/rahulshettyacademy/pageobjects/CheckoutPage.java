package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By dropdownresults = By.cssSelector(".ta-results");

	// Page Factory

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement placeOrder;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		waitForWebelementToappear(country);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToappear(dropdownresults);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() throws InterruptedException {
		scrollIntoViewButton();
		elementToBeClickable(placeOrder);
		Thread.sleep(5000);
		placeOrder.click();
		return new ConfirmationPage(driver);
	}

}
