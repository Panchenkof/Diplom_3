package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage {

    @FindBy(how = How.XPATH, using = "(//fieldset//input)")
    private ElementsCollection registrationFields;
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement registrationButton;
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement invalidPasswordError;
    @FindBy(how = How.XPATH, using = "//a[@href='/account']")
    private SelenideElement loginLink;

    @Step("Заполнить поле регистрации")
    public RegistrationPage doFillRegistrationFields(int fieldOrderNumber, String filling) {
        registrationFields.get(fieldOrderNumber).sendKeys(filling);
        return this;
    }// Метод использует нумерацию полей с нуля, по аналогии с массивом

    @Step("Нажать клавишу регистрации")
    public void doRegistrationButtonClick() {
        registrationButton.click();
    }

    @Step("Проверить отображение сообщения о невалидном пароле")
    public boolean isInvalidPasswordErrorDisplayed() {
        return invalidPasswordError.isDisplayed();
    }

    @Step("Нажать клавишу 'Войти'")
    public void doLoginLinkClick() {
        loginLink.click();
    }
}

