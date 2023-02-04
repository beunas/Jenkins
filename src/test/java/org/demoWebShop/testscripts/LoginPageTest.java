package org.demoWebShop.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.demoWebShop.automationcore.Base;
import org.demoWebShop.dataprovider.DataProviders;
import org.demoWebShop.listeners.TestListener;
import org.demoWebShop.pages.HomePage;
import org.demoWebShop.pages.LoginPage;
import org.demoWebShop.pages.UserAccountPage;
import org.demoWebShop.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LoginPageTest extends Base {
    HomePage home;
    LoginPage login;
    UserAccountPage userAccountPage;
    ExcelUtility excel = new ExcelUtility();
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 1,description = "TC_002_Verification of user login ",groups = {"sanity" })
    public void verifyUserValidLogin() {
        extentTest.get().assignCategory("sanity");
        home = new HomePage(driver);
        login = home.clickOnLoginMenu();
        List<List<String>> data = excel.excelDataReader("LoginPage");
        String uname = data.get(1).get(0);
        login.enterUserName(uname);
        extentTest.get().log(Status.PASS, "User name enetred successfully");
        String pswd = data.get(1).get(1);
        login.enterPassword(pswd);
        extentTest.get().log(Status.PASS, "Password enetred successfully");
        userAccountPage = login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "clicked on login button successfully");
        String actualEmailId = userAccountPage.getUserAccountEmail();
        Assert.assertEquals(actualEmailId, uname, "ERROR::Invalid Email ID");
        extentTest.get().log(Status.PASS, "user logged in successfully");
    }
    @Test(priority = 2,dataProvider = "InvalidUserCredentials", dataProviderClass = DataProviders.class,groups = {"sanity" })
    public void verifyInValidLogin(String uname, String pword) {
        extentTest.get().assignCategory("sanity");
        home = new HomePage(driver);
        login = home.clickOnLoginMenu();
        List<List<String>> data = excel.excelDataReader("LoginPage");
        login.enterUserName(uname);
        extentTest.get().log(Status.PASS, "User name enetred successfully");
        login.enterPassword(pword);
        extentTest.get().log(Status.PASS, "Password enetred successfully");
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "clicked on login button successfully");
        String actMessage = login.getLoginErrorMessage();
        String expMessage = data.get(3).get(1);
        Assert.assertEquals(actMessage, expMessage, "Inavlid Error Message");
        extentTest.get().log(Status.PASS, "user logged in successfully");
    }





}
