package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

    @Test(priority = 1,groups = { "negativeTests", "smokeTests" })
    public void incorrectUsername() {

        System.out.println("Test started");
        WebDriver driver = new FirefoxDriver();
        System.out.println("Browser started");

        //  Open test page
        String url = "http://the-internet.herokuapp.com/secure";
        driver.get(url);
        driver.manage().window().maximize();
        System.out.println("Page is opened");
        //  Enter username
        WebElement username = driver.findElement(By.xpath("/html//input[@id='username']"));
        username.sendKeys("incorrectUsername");
        //  Enter password
        WebElement password = driver.findElement(By.xpath("/html//input[@id='password']"));
        password.sendKeys("SuperSecretPassword!");
        //  Click login button
        WebElement logInButton = driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']"));
        logInButton.click();

        //  Verifications
//   Error message
        WebElement errorMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
        String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMessage = errorMessage.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Actual error message does not contain expected");

        driver.close();
        System.out.println("Test finished");


    }

    @Test(priority = 2,enabled = true, groups = { "negativeTests" })
    public void incorrectPassword() {

        System.out.println("Test started");
        WebDriver driver = new ChromeDriver();
        System.out.println("Browser started");

        //  Open test page
        String url = "http://the-internet.herokuapp.com/secure";
        driver.get(url);
        driver.manage().window().maximize();
        System.out.println("Page is opened");
        //  Enter username
        WebElement username = driver.findElement(By.xpath("/html//input[@id='username']"));
        username.sendKeys("tomsmith");
        //  Enter password
        WebElement password = driver.findElement(By.xpath("/html//input[@id='password']"));
        password.sendKeys("IncorrectPassword!");
        //  Click login button
        WebElement logInButton = driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']"));
        logInButton.click();

        //  Verifications
//   Error message
        WebElement errorMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
        String expectedErrorMessage = "Your password is invalid!";
        String actualErrorMessage = errorMessage.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Actual error message does not contain expected");

        driver.close();
        System.out.println("Test finished");
    }
}
