package Definitions;

import LibKeyword.BaseAction;
import Utilities.Screenshoot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import static Const.GlobalConst.*;
import static DataTools.EncryptData.decrypt;
import static DataTools.ExcelTools.readData;
import static ElementPath.DeallsPath.*;

public class DeallStepDef extends BaseAction {
    public static WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private Scenario scenario;
//    private Screenshoot screenshoot;
    public void YourStepDefinitions(Scenario scenario) {
        this.scenario = scenario;
    }


    public void setupDriver() {
        extent = new ExtentReports();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("extent-reports/report.html");
        extent.attachReporter(extentSparkReporter);

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        } else {
            options.addArguments("--start-maximized");
        }


        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
    }

    @After
    public void afterScenario() {
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }


    @Given("open url")
    public void open_url() {
        setupDriver();
        driver.get(urlDealls);
    }

    @When("login with invalid credential")
    public void input_invalid_credential() {
        click(driver, menuLogin);
        writeText(driver, fieldEmail, wrongMail);
        writeText(driver, fieldPassword, wrongPass);
        click(driver, btnSignIn);
    }

    @Then("failed to login with error message")
    public void failed_to_login_with_error_message() throws IOException {
        String step = "failed to login with error message";
        test = extent.createTest(step, "Step of ->" + step);
        String m = getText(driver, modalWrongEmail);
        System.out.println(m);
        String actual = m.substring(0,15);
        Assert.assertEquals(actual, "Email Not found");
        test.log(Status.INFO, actual);
//        screenshoot.capture(driver);
    }

    @When("login with valid credential")
    public void loginWithValidCredential() {
        click(driver, menuLogin);
        writeText(driver, fieldEmail, decrypt(readData("Email Deal")));
        writeText(driver, fieldPassword, decrypt(readData("Password Deal")));
        click(driver, btnSignIn);
    }

    @Then("login success with valid information")
    public void loginSuccessWithValidInformation() {
        click(driver, dropProfile);
        click(driver, btnMyProfile);
        String m = getText(driver, dtlProfile_name);
        System.out.println(m);
        Assert.assertEquals(m, "Fikri Azhari Rabbani");
    }
}
