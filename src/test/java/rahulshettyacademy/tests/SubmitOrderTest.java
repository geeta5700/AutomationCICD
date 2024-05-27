package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = {"Purchase"}, retryAnalyzer=Retry.class)
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		String countryName = "India";
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductTocart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		// Verify Cart products
		Boolean match = cartPage.verifyCartProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		// Checkout page
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		// Thank you page
		String confirmmsg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		landingPage.loginApplication("geeta123@gmail.com", "dgTesting1*");
		OrdersPage ordersPage = landingPage.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderProductDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data =getJsonDataMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)} };
		
		// return new Object[] [] { {"geeta@gmail.com", "dgTesting1*", "ZARA COAT 3"},{"geeta123@gmail.com", "dgTesting1*", "ADIDAS ORIGINAL"}}
	}
	//HashMap<String,String> map =new HashMap<String, String>();
			//map.put("email", "geeta@gmail.com");
			//map.put("password", "dgTesting1*");
			//map.put("productName", "ZARA COAT 3");
			
			//HashMap<String,String> map1 =new HashMap<String, String>();
			//map1.put("email", "geeta123@gmail.com");
			//map1.put("password", "dgTesting1*");
			//map1.put("productName", "ADIDAS ORIGINAL");
}
