package com.test.junitdemo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class JUnitDemoClass {


    public String username = "LT_USERNAME";
    public String accesskey = "LT_ACCESS_KEY";
    
    public static RemoteWebDriver driver;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
    static String URL = "https://ecommerce-playground.lambdatest.io/";

    @Before
    public void setup() throws MalformedURLException {

        String hub = "@hub.lambdatest.com/wd/hub";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("126.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "username");
        ltOptions.put("accessKey", "accesskey");
        ltOptions.put("project", "Junit Test");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), browserOptions);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }



    @Test
    public void test_chrome() throws InterruptedException {

        System.out.println(Thread.currentThread().getName());
        driver.navigate().to(URL);
        driver.manage().window().maximize();


        try {

            driver.findElement(By.xpath("(//input[@placeholder='Search For Products'])[1]")).sendKeys("iPhone");
            driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, "Search IPhone");
            if (actualTitle.equals("Search-iphone")) {
                System.out.println("Demonstration of running JUnit tests is complete");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    @Test
    public void test_firefox() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        driver.navigate().to(URL);
        driver.manage().window().maximize();


        try {

            driver.findElement(By.xpath("(//input[@placeholder='Search For Products'])[1]")).sendKeys("iPhone");
            driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, "Search IPhone");
            if (actualTitle.equals("Search-iphone")) {
                System.out.println("Demonstration of running JUnit tests is complete");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @After
    public void tearDown() {

        driver.quit();
    }

}
