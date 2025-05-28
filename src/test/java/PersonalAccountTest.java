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

@DisplayName("Тест на переход по клику на «Личный кабинет»")
public class PersonalAccountTest extends BaseTest {
    private UserModel user;
    private static String name;
    private static String email;
    private static String password;
    private static String accessToken;

    @Before
    public void createUser() {
        email = generateUniqueEmail();
        name = generateUniqueName();
        password = generateUniquePassword();
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
    @DisplayName("Переход в личный кабинет")
    @Description("Тест на переход по клику на «Личный кабинет»")
    public void personalAccountPage() {
        openAndLoginToPersonalAccount();
        PersonalAccountSteps objPersonalAccountSteps = new PersonalAccountSteps(driver);
        personalAccountPageVisible(objPersonalAccountSteps);
    }

    @Step("Логин пользователя")
    private void openAndLoginToPersonalAccount() {
        LoginSteps objLoginSteps = new LoginSteps(driver);
        objLoginSteps.clickPersonalAccountButton();
        objLoginSteps.login(email, password);
        objLoginSteps.clickPersonalAccountButton();
    }
    @Step("Проверка отображения раздела Личный кабинет")
    private void personalAccountPageVisible(PersonalAccountSteps steps) {
        assertTrue("Не удалось перейти в личный кабинет", steps.isPersonalAccountPageVisible());
    }
}
