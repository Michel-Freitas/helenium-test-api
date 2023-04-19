package com.heleniumTest.HeleniumTestApi;

import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HeleniumTest {

//    public SelfHealingDriver driver;
    protected JavascriptExecutor js;

    @BeforeAll
    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//
//        WebDriver delegate = new ChromeDriver(options);
//        this.driver = SelfHealingDriver.create(delegate);
//        this.driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
//        this.driver.manage().window().setSize(new Dimension(1200, 800));
//        js = (JavascriptExecutor) this.driver;
    }

    @Test
    public void runTest1() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\michel_freitas\\Documents\\DriveNavegadorSelenium\\chromedriver.exe");
        SelfHealingDriver driver = SelfHealingDriver.create(new ChromeDriver(options));
//        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get("http://localhost:5173/");
        driver.findElement(By.cssSelector(".input-email")).sendKeys("Michel");
    }

    @AfterAll
    public void after() {
//        if (this.driver != null) {
//            this.driver.quit();
//        }
    }

}
