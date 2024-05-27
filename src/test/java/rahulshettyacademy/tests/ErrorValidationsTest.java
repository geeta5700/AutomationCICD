package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"})
	public void loginErrorValidation() throws IOException {

		landingPage.loginApplication("geeta123@gmail.com", "dgTesting");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";
		String countryName = "India";
		ProductCatalogue productCatalogue = landingPage.loginApplication("geeta123@gmail.com", "dgTesting1*");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductTocart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		// Verify Cart products
		Boolean match = cartPage.verifyCartProductDisplay("Zara Coat 33");
		Assert.assertFalse(match);
	}
		

}
