package com.transperfect.wahoofitness.webpages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage {

	WebDriver driver;
	
	@FindBy(xpath="//button[span[text()[contains(.,'Place Order')]]]")
	WebElement placeOrder;
	
	@FindBy(xpath="//div[@class=\"field-error\"]")
	WebElement errorMessageRequiredField;
	
	@FindBy(xpath="//*[@id=\"s_method_amstrates_amstrates22\"]")
	WebElement expressShipping;
	
	@FindBy(xpath="//span[@data-th=\"Shipping\"]")
	WebElement shippingCosts;
	
	@FindBy(id="customer-email")
	WebElement email;
	
	@FindBy(xpath="//input[@name=\"firstname\"]")
	WebElement firstName;
	
	@FindBy(xpath="//input[@name=\"lastname\"]")
	WebElement lastName;
	
	@FindBy(xpath="//input[@name=\"street[0]\"]")
	WebElement streetAddress;
	
	@FindBy(xpath="//input[@name=\"city\"]")
	WebElement city;

	@FindBy(xpath="//select[@name=\"country_id\"]")
	WebElement country;
	
	@FindBy(xpath="//input[@name=\"postcode\"]")
	WebElement zipCode;
	
	@FindBy(xpath="//input[@name=\"telephone\"]")
	WebElement phoneNumber;
	
	@FindBy(xpath="//*[@id=\"amasty_stripe_card_data\"]/div/iframe")
	WebElement creditCardDetailsFrame;
	
	@FindBy(xpath="//input[@name=\"cardnumber\"]")
	WebElement creditCardNumber;
	
	@FindBy(xpath="//input[@name=\"exp-date\"]")
	WebElement creditCardExpiryDate;
	
	@FindBy(xpath="//input[@name=\"cvc\"]")
	WebElement creditCardCvc;
	
	@FindBy(xpath="//div[@data-ui-id=\"checkout-cart-validationmessages-message-error\"]")
	WebElement errorMessage;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void placeOrder() {
		for (int i = 0; i < 2; i++) {
			try {
				placeOrder.click();
				break;
			}
			catch (Exception e) {}
		}
	}
	
	public boolean hasErrorMessageRequiredField() {
		return errorMessageRequiredField != null;
	}
	
	/**
	 * Selects express shipping if possible, and returns whether express shipping
	 * was selected or not.
	 * @return Boolean that indicates if express shipping was selected.
	 */
	public boolean selectExpressShipping() {
		try {
			expressShipping.click();
			return true;
		}
		catch (NoSuchElementException e) {
			// Sometimes express shipping is not available
			return false;
		}
	}
	
	public String getShippingCosts() {
		return shippingCosts.getText();
	}
	
	public void enterEmail(String str) {
		email.clear();
		email.sendKeys(str);
	}
	
	public void enterFirstName(String str) {
		firstName.clear();
		firstName.sendKeys(str);
	}
	
	public void enterLastName(String str) {
		lastName.clear();
		lastName.sendKeys(str);
	}
	
	public void enterStreetAddress(String str) {
		streetAddress.clear();
		streetAddress.sendKeys(str);
	}
	
	public void enterCity(String str) {
		city.clear();
		city.sendKeys(str);
	}
	
	public void enterCountry(String str) {
		Select selector = new Select(country);
		selector.selectByVisibleText(str);
	}
	
	public void enterZipCode(String str) {
		zipCode.clear();
		zipCode.sendKeys(str);
	}
	
	public void enterPhoneNumber(String str) {
		phoneNumber.clear();
		phoneNumber.sendKeys(str);
	}
	
	public void switchToCreditCardDetailsFrame() {
		driver.switchTo().frame(creditCardDetailsFrame);
	}
	
	public void enterCreditCardNumber(String str) {
		creditCardNumber.clear();
		creditCardNumber.sendKeys(str);
	}
	
	public void enterCreditCardExpiryDate(String str) {
		creditCardExpiryDate.clear();
		creditCardExpiryDate.sendKeys(str);
	}
	
	public void enterCreditCardCvc(String str) {
		creditCardCvc.clear();
		creditCardCvc.sendKeys(str);
	}
	
	public boolean isPaymentDeclined() {
		return errorMessage != null;
	}
}
