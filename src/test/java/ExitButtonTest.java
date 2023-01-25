import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import helpers.HTTPClient;
import helpers.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.AccountPage;
import pageobjects.LoginPage;
import pageobjects.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

/*
Для прогона в яндекс браузере
System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver.exe");
*/

public class ExitButtonTest {
    TestData testData;
    HTTPClient httpClient;
    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Before
    public void initializationData() {
        testData = new TestData();
        httpClient = new HTTPClient(testData);
        System.setProperty("selenide.browser", "Chrome");
        Configuration.browserSize = "1920x1080";
        mainPage = page(MainPage.class);
        loginPage = page(LoginPage.class);
        accountPage = page(AccountPage.class);
        httpClient.doCreateUserRequest();
        open(testData.getMainPageURL(), MainPage.class);
        mainPage.doLoginButtonClick();
        loginPage.loginUsingTestData(testData);
        mainPage.isAccountButtonVisible().doAccountButtonClick();
    }


    @DisplayName("Тест выхода из учетной записи")
    @Description("Успешно")
    @Test
    public void isExitButtonLeadsToExitTest() {
        accountPage.isExitButtonVisible().doExitButtonClick();
        loginPage.waitLoginButton();
        String actualURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedURL = testData.getLoginPageURL();
        assertEquals(expectedURL, actualURL);
    }

    @After
    public void deleteData() {
        httpClient.doDeleteUserRequest();
    }
}