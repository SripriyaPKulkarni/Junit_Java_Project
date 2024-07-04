package com.test.junitdemo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

import java.net.MalformedURLException;

public class JunitDemo {


    public String username = "username";
    public String accesskey = "access_key";

    public static RemoteWebDriver driver;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
    static String URL = "https://lambdatest.github.io/sample-todo-app/";

    @Before
    public void setup() throws MalformedURLException {

        String hub = "@hub.lambdatest.com/wd/hub";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("124.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "sripriyapkulkarni");
        ltOptions.put("accessKey", "NMt43ZDK7n5MrTZkp8NjsTXjiveSA4JPIGjUqvt3uwSbSwLAjo");
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
    public void test_LT_ToDoApp() throws InterruptedException {
        driver.navigate().to(URL);
        driver.manage().window().maximize();


        try {
            /* Let's mark done first two items in the list. */
            driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();


            /* Let's add an item in the list. */
            driver.findElement(By.id("sampletodotext")).sendKeys("Happy Testing at LambdaTest");
            driver.findElement(By.id("addbutton")).click();


            /* Let's check that the item we added is added in the list. */
            String enteredText = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[6]/span")).getText();
            Assert.assertEquals(enteredText, "LT - Happy Testing at LambdaTest");
            if (enteredText.equals("Happy Testing at LambdaTest")) {
                System.out.println("Demonstration of running JUnit tests from Eclipse is complete");
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
