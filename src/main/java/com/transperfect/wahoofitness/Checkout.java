package com.transperfect.wahoofitness;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.transperfect.wahoofitness.webpages.CheckoutPage;
import com.transperfect.wahoofitness.webpages.HomePage;
import com.transperfect.wahoofitness.webpages.ProductCategoryPage;
import com.transperfect.wahoofitness.webpages.ProductPage;
import com.transperfect.wahoofitness.webpages.ShoppingCartPage;

/**
 * This class provides all the methods for testing the checkout functionality of the WahooFitness website.
 * @author Mark Riezebos
 *
 */
public class Checkout {
	
	String driverPath = "C:\\Users\\markr\\eclipse-workspace\\Transperfect case Mark Riezebos\\chromedriver.exe";
	
	WebDriver driver;
	
	HomePage objHomePage;
	ProductCategoryPage objProductCategoryPage;
	ProductPage objProductPage;
	ShoppingCartPage objShoppingCartPage;
	CheckoutPage objCheckoutPage;

	public void setup() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.get("https://eu.wahoofitness.com/");
		driver.manage().window().maximize();
	}
	
	public void openProductCategory() {
		objHomePage = new HomePage(driver);
		objHomePage.acceptCookiesIfAsked();
		objHomePage.openProductCategory();
	}
	
	public void selectRandomProduct1AndAddToCart() {
		// Select random product 1
		objProductCategoryPage = new ProductCategoryPage(driver);
		objProductCategoryPage.selectNewRandomProduct();

		objProductPage = new ProductPage(driver);
		objProductPage.selectFirstColorIfPossible();
		objProductPage.selectFirstSizeIfPossible();
		objProductPage.selectPlugAndSocketTypeIfPossible();
		objProductPage.selectTrainerTypeIfPossible();
		objProductPage.selectSockSizeIfPossible();
		
		// Add random product 1 to cart
		objProductPage.addProductToCart();
		assertTrue(objProductPage.checkIfMiniCartContainsItems());
	}
	
	public void selectRandomProduct2AndAddToCart() throws InterruptedException {
		Thread.sleep(2000);
		objProductPage.closeMiniCart();
		
		// Select random product 2
		objProductPage.moveToProductCategoryPage();
		objProductCategoryPage.selectNewRandomProduct();
		objProductPage.selectFirstColorIfPossible();
		objProductPage.selectFirstSizeIfPossible();
		objProductPage.selectPlugAndSocketTypeIfPossible();
		objProductPage.selectTrainerTypeIfPossible();
		objProductPage.selectSockSizeIfPossible();
		
		// Add random product 2 to cart
		objProductPage.addProductToCart();
	}
	
	public void removeItem() throws InterruptedException {
		objProductPage.removeItem();
	}
	
	public void moveToShoppingCartPage() throws InterruptedException {
		Thread.sleep(2000);
		objProductPage.moveToShoppingCartPage();
	}
	
	public void changeItemQuantity() throws InterruptedException {
		objShoppingCartPage = new ShoppingCartPage(driver);
		String initialSubtotal = objShoppingCartPage.getSubtotal();
		String initialOrderTotal = objShoppingCartPage.getOrderTotal();
		
		// Change the quantity of the item in the cart to 2
		objShoppingCartPage.changeQuantity(2);
		
		Thread.sleep(2000);
		String updatedSubtotal = objShoppingCartPage.getSubtotal();
		String updatedOrderTotal = objShoppingCartPage.getOrderTotal();
		assertTrue(!initialSubtotal.equals(updatedSubtotal), "Subtotal should have been updated");
		assertTrue(!initialOrderTotal.equals(updatedOrderTotal), "Order total should have been updated");
	}
	
	public void proceedToCheckoutPage() {
		objShoppingCartPage.proceedToCheckout();
		objCheckoutPage = new CheckoutPage(driver);
	}
	
	public void placeOrderWithoutInfo() throws InterruptedException {
		Thread.sleep(3000);
		objCheckoutPage.placeOrder();
		assertTrue(objCheckoutPage.hasErrorMessageRequiredField(), "Error message(s) should appear since no personal details were entered");
	}
	
	public void selectExpressShipping() throws InterruptedException {
		String initialShippingCosts = objCheckoutPage.getShippingCosts();
		boolean isExpressShipping = objCheckoutPage.selectExpressShipping();
		Thread.sleep(2000);
		String updatedShippingCosts = objCheckoutPage.getShippingCosts();
		if (isExpressShipping) {
			assertTrue(!initialShippingCosts.equals(updatedShippingCosts), "Shipping costs should have been updated");
		}
	}
	
	public void enterPersonalDetails() {
		objCheckoutPage.enterEmail("someone@whocares.com");
		objCheckoutPage.enterFirstName("Test");
		objCheckoutPage.enterLastName("Tester");
		objCheckoutPage.enterStreetAddress("Comandante Izarduy");
		objCheckoutPage.enterCity("Barcelona");
		objCheckoutPage.enterCountry("Spain");
		objCheckoutPage.enterZipCode("08940");
		objCheckoutPage.enterPhoneNumber("5555555555");
		objCheckoutPage.switchToCreditCardDetailsFrame();
		objCheckoutPage.enterCreditCardNumber("4111111111111111");
		objCheckoutPage.enterCreditCardExpiryDate("0824");
		objCheckoutPage.enterCreditCardCvc("111");
	}
	
	public void placeOrder() throws InterruptedException {
		Thread.sleep(2000);
		objCheckoutPage.placeOrder();
		assertTrue(objCheckoutPage.isPaymentDeclined(), "Payment is not declined whereas it should have been");
	}
}
