package com.heleniumTest.HeleniumTestApi;

import com.epam.healenium.SelfHealingDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Teste1 {

    public SelfHealingDriver driver;
    protected JavascriptExecutor js;

    @BeforeAll
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\michel_freitas\\Documents\\DriveNavegadorSelenium\\chromedriver.exe");
        driver = SelfHealingDriver.create(new ChromeDriver(options), );
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        js = (JavascriptExecutor) this.driver;
    }

    @Test
    public void runTest1() {
        driver.get("http://localhost:5173/");
        driver.findElement(By.cssSelector(".input-email")).click();
        driver.findElement(By.cssSelector(".input-email")).sendKeys("Michel");
        driver.findElement(By.cssSelector(".input-password")).sendKeys("Freitas");
    }

    @AfterAll
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }
}
