import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import page.steps.RegisterSteps;
import java.util.UUID;
import static org.junit.Assert.assertTrue;

@DisplayName("Тесты на регистрацию пользователя")
public class RegisterTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Тест на успешную регистрацию пользователя с валидными данными")
    public void successfulRegisterTest() {
        RegisterSteps objRegisterSteps = new RegisterSteps(driver);
        String name = generateUsername();
        String email = generateEmail();
        String password = "1234567";

        openRegistrationForm(objRegisterSteps);
        registerWithCredentials(objRegisterSteps, name, email, password);
        checkReturnToLoginForm(objRegisterSteps);
    }

    @Test
    @DisplayName("Регистрация при невалидном пароле")
    @Description("Тест на появление ошибки для некорректного пароля")
    public void unsuccessfulRegisterWithShortPasswordTest() {
        RegisterSteps objRegisterSteps = new RegisterSteps(driver);
        String name = generateUsername();
        String email = generateEmail();
        String invalidPassword = "123";

        openRegistrationForm(objRegisterSteps);
        registerWithCredentials(objRegisterSteps, name, email, invalidPassword);
        checkPasswordValidationError(objRegisterSteps);
    }
    @Step("Генерация уникального имени пользователя")
    private String generateUsername() {
        return "user" + UUID.randomUUID().toString().substring(0, 5);
    }

    @Step("Генерация уникального email")
    private String generateEmail() {
        return "test" + UUID.randomUUID().toString().substring(0, 5) + "@mail.ru";
    }

    @Step("Открытие формы регистрации через личный кабинет")
    private void openRegistrationForm(RegisterSteps steps) {
        steps.clickPersonalAccountButton();
        steps.waitForPersonalAccountForm();
        steps.clickRegisterButton();
        steps.waitForRegistrationForm();
    }
    @Step("Регистрация с данными: имя = {name}, email = {email}")
    private void registerWithCredentials(RegisterSteps steps, String name, String email, String password) {
        steps.registerUser(name, email, password);
    }
    @Step("Проверка возврата на форму входа после успешной регистрации")
    private void checkReturnToLoginForm(RegisterSteps steps) {
        steps.waitForPersonalAccountForm();
    }

    @Step("Проверка отображения ошибки при коротком пароле")
    private void checkPasswordValidationError(RegisterSteps steps) {
        assertTrue("Минимальный пароль — шесть символов", steps.isPasswordErrorDisplayed());
    }
}
