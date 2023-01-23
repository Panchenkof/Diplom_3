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
import pageobjects.PasswordRecoveryPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

/*
Для прогона в яндекс браузере
System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver.exe");
*/

public class AccountPageToConstructorPageTest {
    TestData testData;
    HTTPClient httpClient;
    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;
    PasswordRecoveryPage passwordRecoveryPage;

    @Before
    public void initializationData() {
        testData = new TestData();
        httpClient = new HTTPClient(testData);
        System.setProperty("selenide.browser", "Chrome");
        Configuration.browserSize = "1920x1080";
        mainPage = page(MainPage.class);
        loginPage = page(LoginPage.class);
        passwordRecoveryPage = page(PasswordRecoveryPage.class);
        accountPage = page(AccountPage.class);
        httpClient.doCreateUserRequest();
        open(testData.getMainPageURL(), MainPage.class);
        mainPage.doLoginButtonClick();
        loginPage.loginUsingTestData(testData);
        mainPage.doAccountButtonClick();
    }


    @DisplayName("Переходим на страницу конструктора по кнопке")
    @Description("Успешно")
    @Test
    public void isConstructorReachableViaConstructorButtonTest() {
        accountPage.doConstructorButtonClick();
        String actualURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedURL = testData.getMainPageURL();
        assertEquals(expectedURL, actualURL);
    }

    @DisplayName("Переходим на страницу конструктора при нажатии на логотип")
    @Description("Успешно")
    @Test
    public void isConstructorReachableViaHeaderLogoButtonTest() {
        accountPage.doHeaderLogoClick();
        String actualURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedURL = testData.getMainPageURL();
        assertEquals(expectedURL, actualURL);
    }

    @After
    public void deleteData() {
        httpClient.doDeleteUserRequest();
    }
}