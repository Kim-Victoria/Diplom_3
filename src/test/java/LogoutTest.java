import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import page.steps.LoginSteps;
import page.steps.PersonalAccountSteps;

import java.util.UUID;

import static org.junit.Assert.*;

@DisplayName("Тест на выход пользователя из личного кабинета")
public class LogoutTest extends BaseTest {
    private static String name;
    private static String email;
    private static String password;
    private static String accessToken;

    @BeforeClass
    public static void createUser() {
        email ="user"+ UUID.randomUUID().toString().substring(0,5) +"@mail.ru";
        password ="123456";
        name ="TestUser";
        Response response = LoginSteps.createUser(name, email, password);
        assertEquals("Ошибка при создании пользователя", 200, response.getStatusCode());
        accessToken = response.then().extract().path("accessToken");
        assertNotNull("accessToken не получен", accessToken);
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            LoginSteps.deleteUser(accessToken);
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
