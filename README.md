# Automated test for testing checkout functionality on WahooFitness website #

The checkout functionality of the WahooFitness website is tested according to the following steps:

1. Load https://eu.wahoofitness.com/ and open product category
2. Select random product and add it to the cart (Verify that side-bar cart appears with added product).
    - random product means: create product collection and select the one with random index
    - "Coming soon" and "Out of stock" product are not available for ordering. Find a way to avoid such products and select available ones.
    - if product has color option, select the first available color from the select-box (eg. https://eu.wahoofitness.com/devices/bike-computers/gps-elemnt-bolt)
3. Go back to product category and select another random product and add it to the cart, too.
    - random product means: create product collection and select the one with random index
    - "Coming soon" and "Out of stock" product are not available for ordering. Find a way to avoid such products and select available ones.
    - if product has color option, select the first available color from the select-box (eg. https://eu.wahoofitness.com/devices/bike-computers/gps-elemnt-bolt)
4. After the side-bar slides out again, click the removal button under one of the items, then confirm with the following pop-up. The item should be successfully removed from the cart.
5. At the bottom of the cart side-bar, click on the edit cart link - should be taken to cart page.
6. Change the quantity of the item in the cart and click the update cart button. Prices should update to reflect the change.
7. Click the blue proceed to checkout button. Should be taken to the checkout details page.
8. Click the blue place order button without filling in any info. Error messages should appear.
9. Switch the shipping method to express shipping. Shipping method price should update.
10. Enter any email, name, address, phone, credit card
    - Email: someone@whocares.com
    - Name: Test Tester
    - Address: Comandante Izarduy 67, Barcelona  08940
    - Phone: 5555555555
    - Credit Card: 4111111111111111, Exp. 08/24, Cv. 111
11. Click the blue place order button.

Expected result: payment is declined.


# Instructions for running #

+ Open the project in Eclipse IDE.
+ Make sure that the TestNG plugin for Eclipse is downloaded. If not yet, click on "Help" and go to the Eclipse marketplace to download the TestNG plugin.
+ Configure the variable *driverPath* in *Checkout.java* to the location where the ChromeDriver is stored.
+ Right-click the file "testng.xml", and select "Run As" > "TestNG Suite".