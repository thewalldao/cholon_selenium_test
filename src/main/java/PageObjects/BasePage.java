package PageObjects;

import Core.Wrapper.Element;
import Utils.TestResult;
import io.qameta.allure.Step;

public class BasePage {
    protected Element userLoggedInField = new Element("//div[div[span[a[normalize-space(.)='ĐĂNG TIN']]]]/preceding-sibling::*[not(self::div[normalize-space(.)='Đăng nhập']) and not(self::div//input)]");
    protected Element moreButton = new Element("//a//span[normalize-space(.)='Thêm']");
    protected Element logOutButton = new Element("//a/div[.='Đăng xuất']");
    protected Element loginField = new Element("//span[b[text()='Đăng nhập']]");

    @Step("Checking User Log In Successfully")
    public TestResult isUserLoginSuccessfully() {
        MainPage mainPage = new MainPage();
        TestResult result = new TestResult();
        result.mergeIfAny(mainPage.isMainPageDisplay());
        result.checkEqual(true, userLoggedInField.isDisplayed(), "User login unsuccessfully");
        return result;
    }

    @Step("Log out")
    public void logOut() {
        moreButton.click();
        logOutButton.click();
    }

    @Step("Checking User Logout Successfully")
    public TestResult isUserLogoutSuccessfully() {
        MainPage mainPage = new MainPage();
        TestResult result = new TestResult();
        result.mergeIfAny(mainPage.isMainPageDisplay());
        result.checkEqual(true, loginField.isDisplayed(), "User logout unsuccessfully");
        return result;
    }
}
