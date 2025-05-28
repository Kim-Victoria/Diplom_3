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
import page.steps.PersonalAccountSteps;

import static api.steps.UserApiSteps.*;
import static org.junit.Assert.*;

@DisplayName("Тест на выход пользователя из личного кабинета")
public class LogoutTest extends BaseTest {
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
    @DisplayName("Выход из аккаунта")
    @Description("Тест на выход по кнопке «Выйти» в личном кабинете")
    public void checkSuccessfulExit() {
        openAndLoginToPersonalAccount();
        PersonalAccountSteps objPersonalAccountSteps = new PersonalAccountSteps(driver);
        checkVisibilityOfPersonalAccountPage(objPersonalAccountSteps);
        clickExitButton(objPersonalAccountSteps);
        checkUserLoggedOut(objPersonalAccountSteps,"Страница логина не отображается");
    }

    @Step("Логин пользователя")
    private void openAndLoginToPersonalAccount() {
        LoginSteps objLoginSteps = new LoginSteps(driver);
        objLoginSteps.clickPersonalAccountButton();
        objLoginSteps.login(email, password);
        objLoginSteps.clickPersonalAccountButton();
    }
    @Step("Проверка открытия страницы Личного кабинета")
    private void checkVisibilityOfPersonalAccountPage(PersonalAccountSteps steps) {
        steps.isPersonalAccountPageVisible();
    }
    @Step("Нажатие на кнопку Выход")
    private void clickExitButton(PersonalAccountSteps steps) {
        steps.clickExitButton();
    }
    @Step("Проверка, что пользователь успешно вышел из системы")
    private void checkUserLoggedOut(PersonalAccountSteps steps, String errorMessage) {
        assertTrue(errorMessage, steps.isLoginPageVisible());
    }
}
