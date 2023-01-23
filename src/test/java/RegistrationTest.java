import com.codeborne.selenide.Configuration;
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
import pageobjects.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

/*
Для прогона в яндекс браузере
System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver.exe");
 */

public class RegistrationTest {

    TestData testData;
    HTTPClient httpClient;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    AccountPage accountPage;


    @Before
    public void initializationData() {
        testData = new TestData();
        httpClient = new HTTPClient(testData);
        System.setProperty("selenide.browser", "Chrome");
        Configuration.browserSize = "1920x1080";
        mainPage = page(MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
        accountPage = page(AccountPage.class);
        open(testData.getMainPageURL(), MainPage.class);
    }


    @DisplayName("Регистрация пользователя")
    @Description("Успешная")
    @Test
    public void registrationTest() throws InterruptedException {
        mainPage.doAccountButtonClick();
        loginPage.doRegistrationLinkClick();
        registrationPage.doFillRegistrationFields(0, testData.getTestName())
                .doFillRegistrationFields(1, testData.getTestEmail())
                .doFillRegistrationFields(2, testData.getTestPassword())
                .doRegistrationButtonClick();
        loginPage.isLoginHeaderVisible()
                .doFillLoginFields(0, testData.getTestEmail())
                .doFillLoginFields(1, testData.getTestPassword())
                .doLoginHoverButtonClick();

        mainPage.isOrderButtonVisible();
        boolean expected = true;
        boolean actual = mainPage.isOrderButtonDisplayed();
        assertEquals(expected, actual);
    }

    @After
    public void deleteData() {
        httpClient.doDeleteUserRequest();
    }
}

