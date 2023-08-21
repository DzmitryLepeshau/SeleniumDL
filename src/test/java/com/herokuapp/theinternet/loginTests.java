package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class loginTests {
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


        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test(priority = 1, groups = {"positiveTests", "smokeTests"})
    public void positiveLoginTest() {
        System.out.println("Test started");

        sleep(1);
        System.out.println("Page is opened");
//  Enter username
        WebElement username = driver.findElement(By.xpath("/html//input[@id='username']"));
        username.sendKeys("tomsmith");

//  Enter password
        WebElement password = driver.findElement(By.xpath("/html//input[@id='password']"));
        password.sendKeys("SuperSecretPassword!");
//  Click login button
        WebElement logInButton = driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']"));
        logInButton.click();

//  Verifications
//   New URL
        String expectedUrl = "http://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual page Url is not the same as expected");

//   Logout button is  visible
        WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logOutButton.isDisplayed(), "Logout button is not displayed");
//   Successful login message
        WebElement successMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = successMessage.getText();
//        Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not contain expected. \nActual Message: " + actualMessage);

        tearDown();


    }


    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(priority = 2, groups = {"negativeTests", "smokeTests"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {

        System.out.println("Starting negativeLoginTest with" + username + " and " + password);

        System.out.println("Page is opened");
        //  Enter username
        WebElement usernameElement = driver.findElement(By.xpath("/html//input[@id='username']"));
        usernameElement.sendKeys(username);
        //  Enter password
        WebElement passwordElement = driver.findElement(By.xpath("/html//input[@id='password']"));
        passwordElement.sendKeys(password);
        //  Click login button
        WebElement logInButton = driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']"));
        logInButton.click();

        //  Verifications
        //   Error message
        WebElement errorMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
        String actualErrorMessage = errorMessage.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Actual error message does not contain expected");

        tearDown();
        System.out.println("Test finished");
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        driver.quit();
    }
}
