package PageObjects;

import Core.Wrapper.Element;
import Utils.TestResult;
import io.qameta.allure.Step;

public class MainPage extends BasePage {
    private Element categoryList = new Element("//div/p[normalize-space(.)='Khám phá danh mục']");

    public LoginPage gotoLoginPage() {
        loginField.click();
        return new LoginPage();
    }

    @Step("Checking Main Page display")
    public TestResult isMainPageDisplay() {
        TestResult result = new TestResult();
        result.checkEqual(true, categoryList.isDisplayed(), "Main page is not Display");
        return result;
    }
}
