import com.codeborne.selenide.Configuration;
import helpers.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjects.MainPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;


/*
Для прогона в яндекс браузере
System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver.exe");
*/

@RunWith(Parameterized.class)
public class BurgerConstructorTest {
    TestData testData;
    MainPage mainPage;
    int ingredientNumber;

    public BurgerConstructorTest(int ingredientNumber) {
        this.ingredientNumber = ingredientNumber;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {0},
                {1},
                {2},
        };
    }

    @Before
    public void initializationData() {
        System.setProperty("selenide.browser", "Chrome");
        Configuration.browserSize = "1920x1080";
        testData = new TestData();
        mainPage = page(MainPage.class);
        open(testData.getMainPageURL(), MainPage.class);

    }


    @DisplayName("Переходим по вкладкам конструктора бургера")
    @Description("Успешно")
    @Test
    public void isBurgerConstructorTubsLeadToIngredientSectionsTest() throws InterruptedException {
        if (ingredientNumber == 0) {
            mainPage.doIngredientTabClick(1);
            mainPage.isIngredientCategoriesHeaderVisible(1);
            TimeUnit.SECONDS.sleep(1);
        }
        mainPage.doIngredientTabClick(ingredientNumber);
        boolean expected = true;
        boolean actual = mainPage.isIngredientCategoriesHeaderVisible(ingredientNumber);
        assertEquals(expected, actual);
    }
}
