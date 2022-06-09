package com.transperfect.wahoofitness.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//*[@class=\"input-text qty\"]")
	WebElement quantity;
	
	@FindBy(xpath="//button[@title=\"Update Cart\"]")
	WebElement updateCart;
	
	@FindBy(xpath="//span[@data-th=\"Subtotal\"]")
	WebElement subtotal;
	
	@FindBy(xpath="//td[@data-th=\"Order Total\"]/strong/span")
	WebElement orderTotal;
	
	@FindBy(xpath="//button[@title=\"Proceed to Checkout\"]")
	WebElement proceedToCheckout;
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Changes the quantity of the product in the shopping cart to the value of qty.
	 * @param qty The quantity the product should get updated to
	 */
	public void changeQuantity(int qty) {
		quantity.clear();
		quantity.sendKeys(Integer.toString(qty));
		updateCart.click();	
	}
	
	public String getSubtotal() {
		return subtotal.getText();
	}
	
	public String getOrderTotal() {
		return orderTotal.getText();
	}
	
	public void proceedToCheckout() {
		proceedToCheckout.click();
	}

}
