package testcase;

import Enums.Accounts;
import Models.Account;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import Utils.TestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends TestBase {
    private static Logger logger = LogManager.getLogger(LoginTest.class);
    MainPage mainPage;
    LoginPage loginPage;

    @Test(description = "Login Fail")
    public void LoginFailTest() {
        logger.info("Go to Login page");
        mainPage = new MainPage();
        loginPage = mainPage.gotoLoginPage();

        logger.info("Validate Login Page is display correctly");
        TestResult result = loginPage.isLoginPageDisplay();
        Assert.assertTrue(result.ok(), result.getError());

        logger.info("Validate Error messages with invalid accounts");
        result = loginPage.areErrorsMessagesCorrectWhenLoginWithInvalidAccount();
        Assert.assertTrue(result.ok(), result.getError());
    }

    @Test(description = "Login Successfully")
    public void LoginSuccessfullyTest() {
        Account validAccount = new Account(Accounts.VALID_USERNAME.getValue(), Accounts.VALID_PASSWORD.getValue());

        logger.info("Go to Login page");
        mainPage = new MainPage();
        loginPage = mainPage.gotoLoginPage();

        logger.info("Validate Login Page is display correctly");
        TestResult result = loginPage.isLoginPageDisplay();
        Assert.assertTrue(result.ok(), result.getError());

        logger.info("Login with valid account");
        loginPage.login(validAccount);

        logger.info("Validate User Login Successfully");
        result = mainPage.isUserLoginSuccessfully();
        Assert.assertTrue(result.ok(), result.getError());

        logger.info("Log out");
        mainPage.logOut();

        logger.info("Validate User Log out Successfully");
        result = mainPage.isUserLogoutSuccessfully();
        Assert.assertTrue(result.ok(), result.getError());
    }

}
