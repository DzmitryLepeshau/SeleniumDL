package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class loginTests {

    @Test(priority = 1,groups = { "positiveTests", "smokeTests" })
    public void positiveLoginTest() {
        System.out.println("Test started");
//  Create driver
        WebDriver driver = new ChromeDriver();
        System.out.println("Browser started");
        sleep(1);

//  Open test page
        String url = "http://the-internet.herokuapp.com/secure";
        driver.get(url);
        sleep(1);
        driver.manage().window().maximize();
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

        driver.close();
        System.out.println("Test finished");

    }
    @Parameters({ "username", "password" ,"expectedErrorMessage" })
    @Test(priority = 2,groups = { "negativeTests", "smokeTests" })
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {

        System.out.println("Starting negativeLoginTest with" + username + " and " + password);
        WebDriver driver = new ChromeDriver();
        System.out.println("Browser started");

        //  Open test page
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        driver.manage().window().maximize();
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

        driver.close();
        System.out.println("Test finished");
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
