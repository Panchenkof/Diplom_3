package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class AccountPage {
    @FindBy(how = How.XPATH, using = "//a[text()='Профиль']")
    private SelenideElement profileTabButton;
    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton;
    @FindBy(how = How.XPATH, using = "//div[@class='AppHeader_header__logo__2D0X2']/a")
    private SelenideElement headerLogo;
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement exitButton;

    @Step("Проверить видимость переключателя вкладок 'Профиль'")
    public boolean isProfileTabButtonVisible() {
        try {
            profileTabButton.shouldBe(visible);
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    @Step("Нажать клавишу 'Конструктор'")
    public void doConstructorButtonClick() {
        constructorButton.click();
    }

    @Step("Нажать клавишу на логотип")
    public void doHeaderLogoClick() {
        headerLogo.click();
    }

    @Step("Проверка видимости клавиши 'Выход'")
    public AccountPage isExitButtonVisible() {
        exitButton.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    @Step("Нажать клавишу 'Выход'")
    public void doExitButtonClick() {
        exitButton.click();
    }
}
