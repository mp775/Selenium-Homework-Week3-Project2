package laptopsandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);

    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        //1.1 Mouse hover on Laptops & Notebooks Tab.and click
        mouseHoverToElementAndClick(By.linkText("Laptops & Notebooks"));
        clickOnElement(By.linkText("Show AllLaptops & Notebooks"));
        List<WebElement> products = driver.findElements(By.xpath("//p[@class ='price']"));
        List<Double> originalProductsPrice = new ArrayList<>();
        for (WebElement e : products) {
            System.out.println(e.getText());
            String[] arr = e.getText().split("Ex Tax:");
            originalProductsPrice.add(Double.valueOf(arr[0].substring(1).replaceAll(",", "")));
        }
        System.out.println(originalProductsPrice);
        // Sort in Reverse order
        Collections.sort(originalProductsPrice, Collections.reverseOrder());
        System.out.println(originalProductsPrice);
        //Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Price (High > Low)");
        // After filter Price (High > Low) Get all the products price and stored into array list
        products = driver.findElements(By.xpath("//p[@class ='price']"));
        ArrayList<Double> afterSortByPrice = new ArrayList<>();
        for (WebElement e : products) {
            String[] arr = e.getText().split("Ex Tax:");
            afterSortByPrice.add(Double.valueOf(arr[0].substring(1).replaceAll(",", "")));
        }
        System.out.println(afterSortByPrice);
        Assert.assertEquals("Product not sorted by price High to Low",
                originalProductsPrice, afterSortByPrice);
    }


    @Test
    public void verifyThatUserPlaceOrderSuccessfully() {
        mouseHoverToElementAndClick(By.linkText("Laptops & Notebooks"));
        clickOnElement(By.xpath("//a[contains(text(),'Show AllLaptops & Notebooks')]"));
        clickOnElement(By.xpath("//select[@id='input-sort']"));
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Price (High > Low)");
//"Price (High > Low)"
        clickOnElement(By.xpath("Price (High > Low)"));
//Select Product “MacBook”
        clickOnElement(By.xpath("//a[normalize-space()='MacBook']"));
//Verify the text “MacBook”
        String expectedText = "MacBook";
        String actualText = driver.findElement(By.xpath("//h1[contains(text(),'MacBook')]")).getText();
        Assert.assertEquals("macbook textis displayed ", expectedText, actualText);

        //Click on ‘Add To Cart’ button
        clickOnElement(By.id("button-cart"));
        //Verify the message
        String expectedMessage = "Success: You have added MacBook to your shopping cart!";
        String actualMessage = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText();
        Assert.assertEquals(" Message not Displayed", expectedMessage, actualMessage);
        //Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        // Verify the text "Shopping Cart"
        String expectedMessage2 = "Shopping Cart";
        String actualMessage2 = driver.findElement(By.cssSelector("div[id='content'] h1")).getText();
        Assert.assertEquals(" Message not Displayed", expectedMessage2, actualMessage2);
        //Verify the Product name "MacBook"
        String expectedProductName = "Shopping Cart";
        String actualProductName = driver.findElement(By.xpath("(//a[contains(text(),'MacBook')])[2]")).getText();
        Assert.assertEquals(" Message not Displayed", expectedProductName, actualProductName);
        driver.findElement(By.cssSelector(" input[value='1']")).clear();
//Change Quantity "2"
        sendTextToElement(By.cssSelector(" //input[@name='quantity"), "2");
//Click on “Update” Tab
        clickOnElement(By.xpath("//button[contains(@data-original-title, 'Update')]"));
//Verify the message “Success: You have modified your shopping cart!”
        Assert.assertTrue("Cart not modified", getTextFromElement(By.xpath("//div[@id='checkout-cart']/div[1]")).contains("Success: You have modified your shopping cart!"));
        Assert.assertEquals("Total not matched", "£737.45", getTextFromElement(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[6]")));

    }
    @After
    public void tearDown(){
        driver.close();
    }
}