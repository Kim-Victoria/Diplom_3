package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    private By loginButtonMain = By.xpath(".//button[contains(text(), 'Войти в аккаунт')]");
    private By accountButtonOnTop = By.xpath("//a[@href='/account' and .//p[contains(text(),'Личный Кабинет')]]");
    private By logPage = By.xpath(".//h2[contains(text(), 'Вход')]");
    private By registerButtonOnBottom = By.xpath("//a[contains(text(),'Зарегистрироваться')]");
    private By registrationPage = By.xpath(".//div[@class='Auth_login__3hAey']//h2[contains(text(), 'Регистрация')]");
    private By loginButtonInRegistrationForm = By.xpath(".//a[contains(text(), 'Войти')]");
    private By restorePasswordButton = By.xpath(".//a[contains(text(), 'Восстановить пароль')]");
    private By restorePasswordPage = By.xpath(".//h2[contains(text(), 'Восстановление пароля')]");
    private By loginButtonInRestorationPasswordForm = By.xpath(".//a[@href='/login' and contains(text(), 'Войти')]");
    private By emailField = By.xpath("//input[@name='name' or @type='text']");
    private By passwordField = By.xpath("//input[@type='password']");
    private By loginConfirmationButton = By.xpath(".//button[contains(text(), 'Войти')]");
    private By mainPageCreateOrder = By.xpath(".//h1[contains(text(), 'Соберите бургер')]");

    public By getLoginButtonMain() {
        return loginButtonMain;
    }
    public By getAccountButtonOnTop() {
        return accountButtonOnTop;
    }
    public By getLogPage() {
        return logPage;
    }
    public By getRegisterButtonOnBottom() {
        return registerButtonOnBottom;
    }
    public By getRegistrationPage() {
        return registrationPage;
    }
    public By getLoginButtonInRegistrationForm() {
        return loginButtonInRegistrationForm;
    }
    public By getRestorePasswordButton() {
        return restorePasswordButton;
    }
    public By getRestorePasswordPage() {
        return restorePasswordPage;
    }
    public By getLoginButtonInRestorationPasswordForm() {
        return loginButtonInRestorationPasswordForm;
    }
    public By getEmailField() {
        return emailField;
    }
    public By getPasswordField() {
        return passwordField;
    }
    public By getLoginConfirmationButton() {
        return loginConfirmationButton;
    }
    public By getMainPageCreateOrder() {
        return mainPageCreateOrder;
    }
}
