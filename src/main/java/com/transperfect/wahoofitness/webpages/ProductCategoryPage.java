package com.transperfect.wahoofitness.webpages;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCategoryPage {

	WebDriver driver;
	Set<Integer> selectedProducts; // keeps track of products that are already selected
	
	@FindBy(xpath="//*[@id=\"section-4\"]/div/div/ul/li" +
					"[not(div[@class=\"text\"]/div[@class=\"actions show\"]/a/span[text()[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'out of stock')]])" +
					" and " +
					"not(div[@class=\"text\"]/div[@class=\"actions show\"]/a/span[text()[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'coming soon')]])]" +
					"/div[@class=\"amlabel-div\"]/a")
	List<WebElement> allProducts;
	
	public ProductCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		selectedProducts = new HashSet<Integer>();
	}
	
	public void selectNewRandomProduct() {		
		Random rand = new Random();
		int randomProduct;
		do {
			randomProduct = rand.nextInt(allProducts.size());
		} while (selectedProducts.contains(randomProduct));
		
		selectedProducts.add(randomProduct);
		allProducts.get(randomProduct).click();
	}
}
