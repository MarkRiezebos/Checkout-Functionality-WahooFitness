package com.transperfect.wahoofitness;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CheckoutFunctionality {
	
	Checkout test;
	
	@BeforeTest
	public void setupTest() {
		test = new Checkout();
		test.setup();
	}
	
	@Test
	public void placeOrder() throws InterruptedException {
		test.openProductCategory();
		test.selectRandomProduct1AndAddToCart();
		test.selectRandomProduct2AndAddToCart();
		test.removeItem();
		test.moveToShoppingCartPage();
		test.changeItemQuantity();
		test.proceedToCheckoutPage();
		test.placeOrderWithoutInfo();
		test.selectExpressShipping();
		test.enterPersonalDetails();
		test.placeOrder();
	}
}
