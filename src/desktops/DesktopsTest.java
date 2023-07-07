package desktops;

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

public class DesktopsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test

    public void testNameVerifyProductArrangeInAlphaBaticalOrder() {
//Mouse hover on Desktops Tab.and click
        mouseHoverToElementAndClick(By.linkText("Desktops"));
        //Click on “Show All Desktops”
        clickOnElement(By.linkText("Show AllDesktops"));
        //Select Sort By position "Name: Z to A"
        List<WebElement> products = (List<WebElement>) driver.findElement(By.xpath("//h4/a"));
        ArrayList<String> originalProductsName = new ArrayList<>();
        for (WebElement p : products) {
            originalProductsName.add(p.getText());
        }
        System.out.println(originalProductsName);
        Collections.reverse(originalProductsName);
        System.out.println(originalProductsName);

        selectByVisibleTextFromDropDown(By.id("input-sort"), "Name (Z - A)");
        // After filter Z -A Get all the products name and stored into array list
        products = driver.findElements(By.xpath("//h4/a"));
        ArrayList<String> afterSortByZToAProductsName = new ArrayList<>();
        for (WebElement e : products) {
            afterSortByZToAProductsName.add(e.getText());
        }
        System.out.println(afterSortByZToAProductsName);
        //1.4 Verify the Product will arrange in Descending order.
        Assert.assertEquals("Product not sorted into Z to A order",
                originalProductsName, afterSortByZToAProductsName);
    }


    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() {
        //Mouse hover on Desktops Tab. and click
        mouseHoverToElementAndClick(By.linkText("Desktops"));
        //Click on “Show All Desktops”
        clickOnElement(By.linkText("Show AllDesktops"));
        //Select Sort By position "Name: A to Z"
        clickOnElement(By.xpath("//select[@id='input-sort']"));
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Name (A - Z)");
        //Select product “HP LP3065”
        clickOnElement(By.xpath("//a[contains(text(),'HP LP3065')]"));
        //Verify the Text "HP LP3065"
        String expectedText = "HP LP3065";
        String actualText = driver.findElement(By.xpath("//a[contains(text(),'HP LP3065')]")).getText();
        Assert.assertEquals("text not found", expectedText);

//Select Delivery Date "2022-11-30"
        sendTextToElement(By.xpath("//input[@id='input-option225']"), "2022-11-30");
//Enter Qty 1 using Select class.
        sendTextToElement(By.id("input-quantity"), "1");
// Click on “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
// Verify the Message
        String expectedMessage = "Success: You have added HP LP3065 to your shopping cart!";
        String actualMessage = driver.findElement(By.xpath("//body/div[@id='product-product']/div[1]")).getText();
        Assert.assertEquals("Message not found", expectedMessage, actualMessage);
    }
    @After
    public void tearDown(){
        driver.close();
    }
}
