package page.steps;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps {
    private WebDriver driver;
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


    public LoginSteps(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Логин пользователя")
    public void login(String email, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginConfirmationButton).click();
    }
    @Step("Клик по кнопке Личный кабинет")
    public void clickPersonalAccountButton() {
        driver.findElement(accountButtonOnTop).click();
    }
    @Step("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void loginFromMainPage(String email, String password) {
        driver.findElement(loginButtonMain).click();
        new WebDriverWait(driver, Duration.ofSeconds(25))
                .until(ExpectedConditions.visibilityOfElementLocated(logPage));
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginConfirmationButton).click();
    }
    @Step("Вход через кнопку «Личный кабинет»")
    public void loginFromPersonalAccount(String email, String password) {
        driver.findElement(accountButtonOnTop).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(logPage));
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginConfirmationButton).click();
    }
    @Step("Вход через кнопку в форме регистрации")
    public void loginFromRegistrationPage(String email, String password) {
        driver.findElement(accountButtonOnTop).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(logPage));
        driver.findElement(registerButtonOnBottom).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationPage));
        driver.findElement(loginButtonInRegistrationForm).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(logPage));
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginConfirmationButton).click();
    }
    @Step("Вход через кнопку в форме восстановления пароля")
    public void loginFromPasswordRestorePage(String email, String password) {
        driver.findElement(loginButtonMain).click();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(logPage));
        driver.findElement(restorePasswordButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(restorePasswordPage));
        driver.findElement(loginButtonInRestorationPasswordForm).click();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(logPage));
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginConfirmationButton).click();
    }
    @Step("Проверка, что пользователь авторизован")
    public boolean isUserLoggedIn() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(mainPageCreateOrder)).isDisplayed();
        } catch (Exception ignored) {
            System.out.println("Пользователь не авторизован: " + ignored.getMessage());
            return false;
        }
    }
}

