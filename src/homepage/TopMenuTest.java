package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {
    String basUrl = " http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(basUrl);

    }
    @Test

    public void testNameVerifyProductArrangeInAlphaBaticalOrder() {

        mouseHoverToElementAndClick(By.linkText("Desktops"));
        clickOnElement(By.linkText("Show AllDesktops"));
        String expectedText = "Desktops";
        String actualText = driver.findElement(By.xpath("//h2[contains(text(),'Desktops')]")).getText();
        Assert.assertEquals("text is not visisble", expectedText, actualText);

    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        mouseHoverToElementAndClick(By.linkText("Laptops & Notebooks"));
        clickOnElement(By.xpath("//a[contains(text(),'Show AllLaptops & Notebooks')]"));
        String expectedText = "Laptops & Notebooks";
        String actualText = driver.findElement(By.xpath("//h2[contains(text(),'Laptops & Notebooks')]")).getText();
        Assert.assertEquals("text is not visisble", expectedText, actualText);

    }
    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully(){
        mouseHoverToElementAndClick(By.linkText("Components"));
        clickOnElement(By.xpath("//a[contains(text(),'Show AllComponents')]"));
        String expectedText = "Components";
        String actualText = driver.findElement(By.xpath("//h2[contains(text(),'Components')]")).getText();
        Assert.assertEquals("text is not visisble", expectedText, actualText);


    }

@After
    public void tearDown(){
        driver.close();
}

}






