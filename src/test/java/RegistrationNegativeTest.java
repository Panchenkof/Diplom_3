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

public class RegistrationNegativeTest {

    TestData testData;
    HTTPClient httpClient;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    AccountPage accountPage;
    String fiveCharacterPassword = "24353";
    private String new_email;

    @Before
    public void initializationData() {
        testData = new TestData();
        httpClient = new HTTPClient(testData);
        System.setProperty("selenide.browser", "Chrome");
        Configuration.browserSize = "1920x1080";
        mainPage = open(testData.getMainPageURL(), MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
        accountPage = page(AccountPage.class);
    }

    @DisplayName("Регистрации пользователя при пароле менее 6 символов")
    @Description("Без успешная регистрация")
    @Test
    public void isRegistrationWithFiveCharactersPasswordImpossibleTest() {
        testData.setTestPassword(fiveCharacterPassword);
        mainPage.doAccountButtonClick();
        loginPage.doRegistrationLinkClick();
        new_email = testData.getTestEmail();
        registrationPage.doFillRegistrationFields(0, testData.getTestName())
                .doFillRegistrationFields(1, new_email)
                .doFillRegistrationFields(2, testData.getTestPassword())
                .doRegistrationButtonClick();
        boolean expected = true;
        boolean actual = registrationPage.isInvalidPasswordErrorDisplayed();
        assertEquals(expected, actual);
    }

    @After
    public void deleteData() {
        try {
            httpClient.doDeleteUserRequestForNegativeTest(new_email);
        } catch (Exception exception) {
            System.out.println("Нет пользователя на удаление");
        }
    }
}


