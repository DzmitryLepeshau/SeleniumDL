package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ExceptionsTests {
    private WebDriver driver;

    @Parameters({"browser"})

    @BeforeMethod(alwaysRun = true)
    private void setUp(String browser) {


        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Do not know how to start " + browser + ",start chrome by default");
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();

    }
    @Test
    public void noSuchElementExceptionTest() {

        driver.get("https://practicetestautomation.com/practice-test-exceptions/");


        WebElement addButton = driver.findElement(By.xpath("/html//button[@id='add_btn']"));
        addButton.click();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        WebElement row2 = driver.findElement(By.xpath("//div[@id='rows']/div[3]/div[@class='row']/input[@type='text']"));
        Assert.assertTrue(row2.isDisplayed(), "row2 input field is not displayed");
    }
    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        driver.quit();
    }

}
