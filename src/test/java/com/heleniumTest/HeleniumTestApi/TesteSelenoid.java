package com.heleniumTest.HeleniumTestApi;

import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TesteSelenoid {

    public SelfHealingDriver driver;
    protected JavascriptExecutor js;

    @BeforeAll
    public void setUp() throws MalformedURLException, URISyntaxException {
        WebDriverManager.chromedriver().setup();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("111.0");
        capabilities.setCapability("enableVideo", true);
        capabilities.setCapability("screenResolution", "1280x1024x24");
        capabilities.setCapability("videoName", "testeSelenoid.mp4");
        capabilities.setCapability("videoCodec", "mpeg4");
        capabilities.setCapability("name", "testeSelenoid");

        RemoteWebDriver deletage = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver = SelfHealingDriver.create(deletage);
    }

    @Test
    public void runTest1() {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://db71e0e6cc6d.ngrok.app/");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.cssSelector(".input-email")).click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.cssSelector(".input-email")).sendKeys("Michel");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.cssSelector(".input-password")).sendKeys("Freitas");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterAll
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }
}
