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

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

/*
Для прогона в яндекс браузере
System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver.exe");
*/

public class ReachingProfilePageTest {

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
    }

    @DisplayName("Переходим на страницу профиля")
    @Description("Успешно")
    @Test
    public void isReachingProfilePageTest() {
        mainPage.doLoginButtonClick();
        loginPage.loginUsingTestData(testData);
        mainPage.doAccountButtonClick();
        Boolean expected = true;
        assertEquals(expected, accountPage.isProfileTabButtonVisible());
        String actualURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedURL = testData.getAccountPageURL();
        assertEquals(expectedURL, actualURL);
    }

    @After
    public void deleteData() {
        httpClient.doDeleteUserRequest();
        closeWindow();
    }
}