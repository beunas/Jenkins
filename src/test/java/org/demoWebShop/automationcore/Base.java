package org.demoWebShop.automationcore;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.demoWebShop.constants.Constants;
import org.demoWebShop.extentreport.ExtentManager;
import org.demoWebShop.listeners.TestListener;
import org.demoWebShop.utilities.WaitUtility;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {
    public WebDriver driver;
    public Properties prop;
    public FileInputStream fs;
    public Base()  {
        prop=new Properties();
        try {
            fs =new FileInputStream(System.getProperty("user.dir")+ Constants.CONFIG_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @BeforeSuite
    public void setExtent(final ITestContext testContext){
        ExtentManager.createInstance().createTest(testContext.getName(), "TEST FAILED");
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(String browserName) {
        String baseUrl= prop.getProperty("url");
        driver = DriverFactory.testInitializa(browserName);
        driver.get(baseUrl);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WaitUtility.PAGE_LOAD_WAIT));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenShot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShot, new File("./Screenshots/" + result.getName() + ".png"));
           attachScreenshot("\\Screenshots\\"+ result.getName() + ".png");
        }
        driver.close();
    }

    public void attachScreenshot(String path){
        String p = System.getProperty("user.dir")+path;
        ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
        extentTest.get().log(Status.FAIL,"Screenshot find here:       " +p);

    }

}
