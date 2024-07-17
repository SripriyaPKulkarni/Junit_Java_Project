package com.test.junitdemo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class JunitDemoFirefox {


    public String username = "username";
    public String accesskey = "access_key";

    public static RemoteWebDriver driver;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
    static String URL = "https://ecommerce-playground.lambdatest.io/";

    @Before
    public void setup() throws MalformedURLException {

        String hub = "@hub.lambdatest.com/wd/hub";

        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("127");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "sripriyapkulkarni");
        ltOptions.put("accessKey", "NMt43ZDK7n5MrTZkp8NjsTXjiveSA4JPIGjUqvt3uwSbSwLAjo");
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);
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
    public void test_() throws InterruptedException {
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
