package com.transperfect.wahoofitness.webpages;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
	
	WebDriver driver;
	
	@FindBy(id="attribute92")
	WebElement color;
	
	@FindBy(id="attribute231")
	WebElement size;
	
	@FindBy(id="attribute310")
	WebElement plugAndSocketType;
	
	@FindBy(id="attribute303")
	WebElement trainerType;
	
	@FindBy(id="attribute229")
	WebElement sockSize;
	
	@FindBy(id="product-addtocart-button")
	WebElement addToCart;
	
	@FindBy(xpath="//*[@id=\"mini-cart\"]/li")
	List<WebElement> miniCartItems;
	
	@FindBy(id="btn-minicart-close")
	WebElement miniCartClose;
	
	@FindBy(xpath="//*[@id=\"product-view-wrapper\"]/div/div[@class=\"breadcrumbs\"]/ul/li[@class=\"category4\"]/a")
	WebElement productCategoryPageRef;
	
	@FindBy(xpath="//*[@id=\"mini-cart\"]/li[1]/div/div/div[@class=\"product actions\"]/div/a")
	WebElement removeButtonOfItemInMiniCart;
	
	@FindBy(xpath="//*[@class=\"action-primary action-accept\"]")
	WebElement confirmItemRemoval;
	
	@FindBy(xpath="//*[@class=\"action viewcart\"]")
	WebElement viewAndEditCart;
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private void selectFirstOption(WebElement element) {
		try {
			Select selector = new Select(element);
			selector.selectByIndex(1);
		}
		catch (NoSuchElementException e) {
			// The option selector doesn't exist, do apparently the product doesn't have a
			// choice for this element. Hence, we do nothing
		}
	}

	public void selectFirstColorIfPossible() {
		selectFirstOption(color);
	}
	
	public void selectFirstSizeIfPossible() {
		selectFirstOption(size);
	}
	
	public void selectPlugAndSocketTypeIfPossible() {
		selectFirstOption(plugAndSocketType);
	}
	
	public void selectTrainerTypeIfPossible() {
		selectFirstOption(trainerType);
	}
	
	public void selectSockSizeIfPossible() {
		selectFirstOption(sockSize);
	}
	
	public void addProductToCart() {
		addToCart.click();
	}
	
	public boolean checkIfMiniCartContainsItems() {
		return miniCartItems.size() > 0;
	}
	
	public void closeMiniCart() {		
		for (int i = 0; i < 2; i++) {
			try {
				miniCartClose.click();
				break;
			}
			catch (Exception e) {}
		}
	}
	
	public void moveToProductCategoryPage() {
		productCategoryPageRef.click();
	}
	
	public void removeItem() throws InterruptedException {		
		for (int i = 0; i < 2; i++) {
			try {
				removeButtonOfItemInMiniCart.click();
				break;
			}
			catch (Exception e) {}
		}
		Thread.sleep(1000);
		confirmItemRemoval.click();
	}
	
	public void moveToShoppingCartPage() {
		viewAndEditCart.click();
	}
}