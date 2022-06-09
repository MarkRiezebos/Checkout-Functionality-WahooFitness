package com.transperfect.wahoofitness.webpages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(id="onetrust-accept-btn-handler")
	WebElement acceptCookies;
	
	@FindBy(xpath="//*[@id=\"top_nav\"]/ul/li/a[span[text()[contains(.,\"SHOP\")]]]")
	WebElement shop;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void acceptCookiesIfAsked() {
		try {
			acceptCookies.click();
		}
		catch (NoSuchElementException e) {
			// The "accept cookies" button doesn't exist, so apparently we are not asked about
			// cookies. Hence, we do nothing
		}
	}
	
	public void openProductCategory() {
		try {
			shop.click();
		}
		catch (WebDriverException e) {
			e.printStackTrace();
		}
	}
}
