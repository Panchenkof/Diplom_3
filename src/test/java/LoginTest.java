import com.codeborne.selenide.Configuration;
import helpers.HTTPClient;
import helpers.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.PasswordRecoveryPage;
import pageobjects.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

/*
Для прогона в яндекс браузере
System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver.exe");
*/

public class LoginTest {

    TestData testData;
    HTTPClient httpClient;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    PasswordRecoveryPage passwordRecoveryPage;

    @Before
    public void initializationData() {
        testData = new TestData();
        httpClient = new HTTPClient(testData);
        System.setProperty("selenide.browser", "Chrome");
        Configuration.browserSize = "1920x1080";
        mainPage = page(MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
        passwordRecoveryPage = page(PasswordRecoveryPage.class);
        httpClient.doCreateUserRequest();
        open(testData.getMainPageURL(), MainPage.class);
    }

    @DisplayName("Авторизация с помощью кнопки на главной странице")
    @Description("Успешно")
    @Test
    public void isLoginWithMainPageLoginButtonTest() {
        mainPage.isLoginButtonVisible()
                .doLoginButtonClick();
        loginPage.loginUsingTestData(testData);
        mainPage.isOrderButtonVisible();
        boolean expected = true;
        boolean actual = mainPage.isOrderButtonDisplayed();
        assertEquals(expected, actual);
    }


    @DisplayName("Авторизация с помощью кнопки личного кабинета")
    @Description("Успешно")
    @Test
    public void isLoginWithMainPageAccountButtonTest() {
        mainPage.isLoginButtonVisible()
                .doLoginButtonClick();
        loginPage.loginUsingTestData(testData);
        mainPage.isOrderButtonVisible();
        boolean expected = true;
        boolean actual = mainPage.isOrderButtonDisplayed();
        assertEquals(expected, actual);
    }


    @DisplayName("Авторизация с использованием ссылки на странице регистрации")
    @Description("Успешно")
    @Test
    public void isLoginWithRegistrationPageLoginLinkTest() {
        mainPage.isLoginButtonVisible()
                .doLoginButtonClick();
        loginPage.doRegistrationLinkClick();
        registrationPage.doLoginLinkClick();
        loginPage.loginUsingTestData(testData);
        mainPage.isOrderButtonVisible();
        boolean expected = true;
        boolean actual = mainPage.isOrderButtonDisplayed();
        assertEquals(expected, actual);
    }


    @DisplayName("Авторизация с использованием ссылки на странице восстановления пароля")
    @Description("Успешно")
    @Test
    public void isLoginWithPasswordRecoveryPageLoginButtonTest() {
        mainPage.isLoginButtonVisible()
                .doLoginButtonClick();
        loginPage.doPasswordRecoveryLinkClick();
        passwordRecoveryPage.doLoginLinkClick();
        loginPage.loginUsingTestData(testData);
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