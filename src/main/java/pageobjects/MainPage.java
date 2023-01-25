package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class MainPage {

    @FindBy(how = How.XPATH, using = "//div[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement logoBlock;
    @FindBy(how = How.XPATH, using = "//nav/a[@class='AppHeader_header__link__3D_hX']")
    private SelenideElement accountButton;
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;
    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    private SelenideElement orderButton;
    @FindBy(how = How.XPATH, using = "(//span[@class='text text_type_main-default'])")
    private ElementsCollection ingredientTabs;
    @FindBy(how = How.XPATH, using = "(//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/h2)")
    private ElementsCollection ingredientCategoriesHeaders;

    @FindBy(how = How.XPATH, using = "//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']//span[text()='Булки']")
    private SelenideElement ingredientCategoriesHeader1Active;
    @FindBy(how = How.XPATH, using = "//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']//span[text()='Соусы']")
    private SelenideElement ingredientCategoriesHeader2Active;
    @FindBy(how = How.XPATH, using = "//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']//span[text()='Начинки']")
    private SelenideElement ingredientCategoriesHeader3Active;

    @Step("Дождаться видимости клавиши 'Личный кабинет'")
    public MainPage isAccountButtonVisible() {
        accountButton.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    @Step("Нажать на клавишу 'Личный кабинет'")
    public void doAccountButtonClick() {
        accountButton.click();
    }

    @Step("Нажать клавишу 'Войти'")
    public void doLoginButtonClick() {
        loginButton.click();
    }

    @Step("Дождаться видимости клавиши 'Войти'")
    public MainPage isLoginButtonVisible() {
        loginButton.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }


    @Step("Дождаться видимости клавиши 'Оформить заказ'")
    public MainPage isOrderButtonVisible() {
        orderButton.shouldBe(visible, Duration.ofSeconds(30));
        return this;
    }

    @Step("Проверить видимость клавиши 'Оформить заказ'")
    public boolean isOrderButtonDisplayed() {
        return orderButton.isDisplayed();
    }

    @Step("Дождаться видимости переключателя вкладки ингредиентов и нажать на вкладку")
    public void doIngredientTabClick(int elementNumber) {
        ingredientTabs.get(elementNumber)
                .shouldBe(visible, Duration.ofSeconds(20)).shouldBe(visible).click();
    }

    @Step("Проверить видимость клавиши переключателей категорий ингредиентов")
    public boolean isIngredientCategoriesHeaderVisible(int categoryNumber) {
        if (categoryNumber == 0) {
            return ingredientCategoriesHeader1Active.shouldBe(visible, Duration.ofSeconds(3)).is(visible);
        } else if (categoryNumber == 1) {
            return ingredientCategoriesHeader2Active.shouldBe(visible, Duration.ofSeconds(3)).is(visible);
        } else if (categoryNumber == 2) {
            return ingredientCategoriesHeader3Active.shouldBe(visible, Duration.ofSeconds(3)).is(visible);
        } else {
            return false;
        }
    }
}