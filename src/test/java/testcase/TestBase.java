package testcase;

import Core.Listener.TestAllureListener;
import Core.Wrapper.Driver;
import Utils.Constant;
import org.testng.annotations.*;

@Listeners({TestAllureListener.class})
public class TestBase {

    @Parameters({"browser", "remote"})
    @BeforeMethod(alwaysRun = true)
    public void LaunchApplication(@Optional(Constant.DEFAULT_BROWSER) String browser, @Optional(Constant.DEFAULT_REMOTE_STATE) boolean remote) {
        Driver.setDriver(browser, remote);
        Driver.setPageLoadTimeOut();
        Driver.get(Constant.AUT_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Driver.closeBrowser();
    }
}
