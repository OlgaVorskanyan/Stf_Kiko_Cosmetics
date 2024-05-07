package base;

import aua.testingfundamentals.pom.base.BasePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public abstract class BaseTest {

    public static final String PATH = "src/main/resources/";
    public static final String PNG_EXTENSION = ".png";

    protected WebDriver     driver;
    protected WebDriverWait webDriverWait;

    @BeforeMethod
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get(BasePage.BASE_URL);
    }

    @AfterMethod
    public void screenshotAndTerminate(ITestResult result) throws URISyntaxException {
        if (result.getStatus() == ITestResult.FAILURE) {
            final var screenshotMaker = (TakesScreenshot) driver;
            final Path screenShotBytes = screenshotMaker.getScreenshotAs(OutputType.FILE).getAbsoluteFile().toPath();
            final Path destination = Paths.get(PATH + result.getStartMillis() + "-" + "screenshot" + PNG_EXTENSION);
            try {
                Files.move(screenShotBytes, destination);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        driver.close();
    }

}