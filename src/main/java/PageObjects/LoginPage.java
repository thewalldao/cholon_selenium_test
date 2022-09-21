package PageObjects;

import Core.Wrapper.Element;
import Enums.Accounts;
import Models.Account;
import Utils.TestResult;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginPage extends BasePage {
    private Element loginPanelTitle = new Element("//div/h3[normalize-space(.)='Đăng nhập']/following-sibling::p[normalize-space(.)='Chào bạn quay lại']");
    private Element errorMessage = new Element("//div[div[h3[normalize-space(.)='Đăng nhập']]]/following-sibling::div[contains(@class,'error')]");
    private Element userField = new Element("//div/input[@placeholder='Nhập SĐT của bạn']");
    private Element passwordField = new Element("//div/input[@placeholder='Nhập mật khẩu của bạn']");
    private Element loginButton = new Element("//button[.='Đăng nhập']");
    Faker faker = new Faker();
    private static Logger logger = LogManager.getLogger(LoginPage.class);


    public TestResult isLoginPageDisplay() {
        TestResult result = new TestResult();
        result.checkEqual(true, loginPanelTitle.isDisplayed(), "Login Page is not Displayed");
        return result;
    }

    @Step("Login")
    public void login(Account account) {
        userField.enter(account.getUsername());
        passwordField.enter(account.getPassword());
        loginButton.click();
    }

    @Step("Checking errors messages correctly when login with invalid account")
    public TestResult areErrorsMessagesCorrectWhenLoginWithInvalidAccount() {
        TestResult result = new TestResult();
        String phoneLessThanNine = faker.number().digits(8);
        String phoneMoreThanEleven = faker.number().digits(12);
        String passWordLessThanFive = faker.number().digits(4);
        String validPhone = Accounts.VALID_USERNAME.getValue();
        String validPassWord = Accounts.VALID_PASSWORD.getValue();

        Account accountWithPhoneLessThanNine = new Account(phoneLessThanNine, validPassWord);
        Account accountWithPhoneMoreThanEleven = new Account(phoneMoreThanEleven, validPassWord);
        Account accountWithPassWordLessThanFive = new Account(validPhone, passWordLessThanFive);

        logger.info("Validate Login unsuccessfully with phone less than nine letter");
        login(accountWithPhoneLessThanNine);
        result.mergeIfAny(isErrorMessageCorrect("Key: 'AuthLoginRequest.phone' Error:Field validation for 'phone' failed on the 'min' tag"));

        logger.info("Validate Login unsuccessfully with phone more than eleven letter");
        login(accountWithPhoneMoreThanEleven);
        result.mergeIfAny(isErrorMessageCorrect("Key: 'AuthLoginRequest.phone' Error:Field validation for 'phone' failed on the 'max' tag"));

        logger.info("Validate Login unsuccessfully with password less than five letter");
        login(accountWithPassWordLessThanFive);
        result.mergeIfAny(isErrorMessageCorrect("Key: 'AuthLoginRequest.password' Error:Field validation for 'password' failed on the 'min' tag"));

        return result;
    }

    public TestResult isErrorMessageCorrect(String expectedMessage) {
        TestResult result = new TestResult();
        String actualErrorMessage = errorMessage.getText();
        result.checkEqual(actualErrorMessage, expectedMessage, "Error message is not correct");
        return result;
    }
}
