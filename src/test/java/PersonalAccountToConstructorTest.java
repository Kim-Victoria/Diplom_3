import api.steps.UserApiSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.steps.ConstructorSteps;
import page.steps.LoginSteps;

import static api.steps.UserApiSteps.*;
import static org.junit.Assert.*;

@DisplayName("Тесты на переход из личного кабинета в конструктор")
public class PersonalAccountToConstructorTest extends BaseTest {
    private UserModel user;
    private static String name;
    private static String email;
    private static String password;
    private static String accessToken;

    @Before
    public void createUser() {
        email = generateUniqueEmail();
        password = generateUniquePassword();
        name = generateUniqueName();
        user = new UserModel(email, password, name);
        Response response = UserApiSteps.createUser(user);
        assertEquals("Ошибка при создании пользователя", 200, response.getStatusCode());
        accessToken = response.then().extract().path("accessToken");
        assertNotNull("accessToken не получен", accessToken);
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            UserApiSteps.deleteUser(accessToken);
        }
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    @Description("Тест на переход по клику на «Конструктор»")
    public void transferToConstructorViaConstructorButton() {
        openAndLoginToPersonalAccount();
        ConstructorSteps objConstructorSteps = new ConstructorSteps(driver);
        clickConstructorButton(objConstructorSteps);
        constructorPageVisible(objConstructorSteps);
    }

    @Test
    @DisplayName("Переход из личного кабинета в контруктор по клику на логотип Stellar Burgers")
    @Description("Тест на переход по клику на логотип")
    public void transferToConstructorViaBurgersLogo() {
        openAndLoginToPersonalAccount();
        ConstructorSteps objConstructorSteps = new ConstructorSteps(driver);
        clickConstructorButton(objConstructorSteps);
        constructorPageVisible(objConstructorSteps);
    }
    @Step("Логин пользователя")
    private void openAndLoginToPersonalAccount() {
        LoginSteps objLoginSteps = new LoginSteps(driver);
        objLoginSteps.clickPersonalAccountButton();
        objLoginSteps.login(email, password);
        objLoginSteps.clickPersonalAccountButton();
    }
    @Step("Нажатие на кнопку Конструктор")
    private void clickConstructorButton(ConstructorSteps steps) {
        steps.clickConstructorButton();
    }
    @Step("Проверка отображения раздела Конструктор")
    private void constructorPageVisible(ConstructorSteps steps) {
        assertTrue("Не удалось перейти в личный кабинет", steps.isConstructorPageVisible());
    }
}
