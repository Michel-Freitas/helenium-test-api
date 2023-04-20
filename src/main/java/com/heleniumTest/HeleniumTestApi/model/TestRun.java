package com.heleniumTest.HeleniumTestApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestRun {

    private String seleniumCode;
    private String testName;
    private String testNameWithExtension;
    private String testScript;

    public TestRun(String testName, String seleniumCode) {
        setTestName(testName);
        setSeleniumCode(seleniumCode);
    }

    public void validateTest() {
        if (testName.contains(".java")) {
            testNameWithExtension = testName;
            testName = testName.split("\\.")[0];
        } else {
            testNameWithExtension = testName + ".java";
        }
    }

    public void assembleTestScript() {
        testScript = "package com.templateProject.template_project;\n\n"+
                "import com.epam.healenium.SelfHealingDriver;\n"+
                "import org.junit.jupiter.api.AfterAll;\n"+
                "import org.junit.jupiter.api.BeforeAll;\n"+
                "import org.junit.jupiter.api.Test;\n"+
                "import org.junit.jupiter.api.TestInstance;\n"+
                "import org.openqa.selenium.By;\n"+
                "import org.openqa.selenium.JavascriptExecutor;\n"+
                "import org.openqa.selenium.chrome.ChromeDriver;\n"+
                "import org.openqa.selenium.chrome.ChromeOptions;\n\n"+
                "import java.util.concurrent.TimeUnit;\n\n"+
                "@TestInstance(TestInstance.Lifecycle.PER_CLASS)\n"+
                "public class "+ testName + " {\n"+
                "    public SelfHealingDriver driver;\n"+
                "    protected JavascriptExecutor js;\n\n"+
                "    @BeforeAll\n"+
                "    public void setUp() {\n"+
                "        ChromeOptions options = new ChromeOptions();\n"+
                "        options.setHeadless(false);\n options.addArguments(\"--remote-allow-origins=*\");\n"+
                "        System.setProperty(\"webdriver.chrome.driver\", \"C:\\\\Users\\\\michel_freitas\\\\Documents\\\\DriveNavegadorSelenium\\\\chromedriver.exe\");\n"+
                "        driver = SelfHealingDriver.create(new ChromeDriver(options));\n"+
                "        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);\n"+
                "        js = (JavascriptExecutor) this.driver;\n"+
                "    }\n\n"+
                "    @Test\n"+
                "    public void "+ testName +"test() {\n"+
                    seleniumCode +
                "    }\n\n"+
                "    @AfterAll\n"+
                "    public void after() {\n"+
                "       if (driver != null) {\n"+
                "           driver.quit();\n"+
                "       }\n"+
                "    }\n}\n";
    }
}
