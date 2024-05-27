package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in__username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When ("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName )
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductTocart(productName);
	}
	
	@When ("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		// Verify Cart products
		Boolean match = cartPage.verifyCartProductDisplay(productName);
		Assert.assertTrue(match);
		// Checkout page
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then ("{string} message is displayed on Confirmation")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmmsg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase(string));
		driver.close();

	}
	
	@Then ("{string} message is displayed")
	public void invalid_message_displayed(String strArg)
	{
		Assert.assertEquals(strArg, landingPage.getErrorMessage());
		driver.close();
	}
}
