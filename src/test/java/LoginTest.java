import api.steps.UserApiSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.steps.LoginSteps;

import static api.steps.UserApiSteps.*;
import static org.junit.Assert.*;

@DisplayName("Тесты на вход пользователя")
public class LoginTest extends BaseTest {
    private static String email;
    private static String password;
    private static String name;
    private static String accessToken;
    private UserModel user;

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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Тест на проверку входа по кнопке «Войти в аккаунт»")
    public void loginFromMainPage() {
        LoginSteps objLoginSteps = new LoginSteps(driver);
        performLoginFromMainPage(objLoginSteps);
        checkUserLoggedIn(objLoginSteps, "Не удалось войти с главной страницы");
    }
    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Тест на проверку входа через кнопку «Личный кабинет»")
    public void loginFromPersonalAccountButton() {
        LoginSteps objLoginSteps = new LoginSteps(driver);
        performLoginFromPersonalAccount(objLoginSteps);
        checkUserLoggedIn(objLoginSteps, "Не удалось войти через кнопку Личный кабинет");
    }
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Тест на проверку входа через кнопку в форме регистрации")
    public void loginFromRegistrationFormButton() {
        LoginSteps objLoginSteps = new LoginSteps(driver);
        performLoginFromRegistrationPage(objLoginSteps);
        checkUserLoggedIn(objLoginSteps, "Не удалось войти через кнопку в форме регистрации");
    }
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Тест на проверку входа через кнопку в форме восстановления пароля")
    public void loginFromRestorePasswordPage() {
        LoginSteps objLoginSteps = new LoginSteps(driver);
        performLoginFromPasswordRestorePage(objLoginSteps);
        checkUserLoggedIn(objLoginSteps, "Не удалось войти через страницу восстановления пароля");
    }

    @Step("Логин с главной страницы")
    private void performLoginFromMainPage(LoginSteps steps) {
        steps.loginFromMainPage(email, password);
    }

    @Step("Логин через кнопку 'Личный кабинет'")
    private void performLoginFromPersonalAccount(LoginSteps steps) {
        steps.loginFromPersonalAccount(email, password);
    }

    @Step("Логин через форму регистрации")
    private void performLoginFromRegistrationPage(LoginSteps steps) {
        steps.loginFromRegistrationPage(email, password);
    }

    @Step("Логин через восстановление пароля")
    private void performLoginFromPasswordRestorePage(LoginSteps steps) {
        steps.loginFromPasswordRestorePage(email, password);
    }

    @Step("Проверка, что пользователь успешно вошел в систему")
    private void checkUserLoggedIn(LoginSteps steps, String errorMessage) {
        assertTrue(errorMessage, steps.isUserLoggedIn());
    }
}
