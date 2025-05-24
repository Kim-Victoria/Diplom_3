package page.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterSteps {
    private WebDriver driver;
    private By accountButtonOnTop = By.xpath("//a[@href='/account' and .//p[contains(text(),'Личный Кабинет')]]");
    private By logPage = By.xpath(".//h2[contains(text(), 'Вход')]");
    private By registerButtonOnBottom = By.xpath("//a[contains(text(),'Зарегистрироваться')]");
    private By registrationPage = By.xpath(".//div[@class='Auth_login__3hAey']//h2[contains(text(), 'Регистрация')]");
    private By nameField = By.xpath(".//label[contains(text(), 'Имя')]/../input");
    private By emailField = By.xpath(".//label[contains(text(), 'Email')]/../input");
    private By passwordField = By.xpath(".//label[contains(text(), 'Пароль')]/../input");
    private By registerConfirmationButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and contains(text(), 'Зарегистрироваться')]");
    private By wrongPasswordMessage = By.xpath(".//p[contains(text(), 'Некорректный пароль')]");

    public RegisterSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void clickPersonalAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(accountButtonOnTop))
                .click();
    }
    public void waitForPersonalAccountForm() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(logPage));
    }
    public void clickRegisterButton() {
        driver.findElement(registerButtonOnBottom).click();
    }
    public void waitForRegistrationForm() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationPage));
    }
    public void registerUser(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registerConfirmationButton).click();
    }
    public boolean isPasswordErrorDisplayed() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(wrongPasswordMessage))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
