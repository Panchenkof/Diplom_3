package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PasswordRecoveryPage {

    @FindBy(how = How.XPATH, using = "//a[@href='/login']")
    private SelenideElement loginLink;

    @Step("Нажать клавишу 'Войти'")
    public void doLoginLinkClick() {
        loginLink.click();
    }
}
