package Core.Listener;

import Core.Wrapper.Driver;
import io.qameta.allure.Attachment;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestAllureListener implements ITestListener {
    private static Logger logger = LogManager.getLogger(TestAllureListener.class);

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG() {
        return Driver.takeScreenshot();
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        logger.info("Start - " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", Driver.getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger.info("Finish " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info(getTestMethodName(iTestResult) + " - Start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info(getTestMethodName(iTestResult) + " - Succeed");
    }

    private void logError(Throwable exception) {
        logger.error(exception.getLocalizedMessage());
        logger.error(ExceptionUtils.getStackTrace(exception));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.warn(getTestMethodName(iTestResult) + " - Failed");
        // Allure ScreenShotRobot and SaveTestLog
        logger.warn("Screenshot captured for test case: " + getTestMethodName(iTestResult));
        saveScreenshotPNG();
        // Save a log on allure.
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
        logError(iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.warn(getTestMethodName(iTestResult) + " - Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.warn("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}

