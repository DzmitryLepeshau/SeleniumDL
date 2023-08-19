package com.herokuapp.theinternet;

public class NegativeTestsWithParameters {

    /*@Parameters({ "username", "password" ,"expectedErrorMessage" })
    @Test(priority = 1,groups = { "negativeTests", "smokeTests" })
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
        System.out.println("Test finished");*/




    /*@Parameters({ "username", "password" ,"expectedMessage" })
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
    }*/
}
