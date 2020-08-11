package org.Utility;

import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import org.Listeners.ExtentManager;
import org.Listeners.ExtentTestManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.restassured.RestAssured.given;

public class ReadConfig {

    Properties config;
    Properties obj;
    public static String startDate;
    public static String Resturl;
    public static io.restassured.specification.RequestSpecification httpRequest;
    public static Response response;
    public static String wl_id;

    public ReadConfig() {
        File src = new File("./config.properties");
        config = new Properties();
        try {
            FileInputStream fis = new FileInputStream(src);
            config = new Properties();
            config.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }

    public void controleExecutor() {
        obj = new Properties();
        try {
            obj.load(new FileInputStream(new File("./ObjectRepository/OR.properties")));
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
    }

    public String getWeatherMapURL() {
        String weathermapURL = config.getProperty("weathermapBaseURI");
        return weathermapURL;
    }

    @BeforeMethod
    public void beforeMethod(java.lang.reflect.Method method) {
        ExtentTestManager.startTest(method.getName());
    }

    @AfterMethod
    protected void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
        } else {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
        }

        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter().flush();
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;

    }
}


