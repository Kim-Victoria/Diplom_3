package page.steps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class LoginSteps {
    private WebDriver driver;
    private final LoginPage loginPage;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public void login(String email, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getEmailField())).sendKeys(email);
        driver.findElement(loginPage.getPasswordField()).sendKeys(password);
        driver.findElement(loginPage.getLoginConfirmationButton()).click();
    }

    public void clickPersonalAccountButton() {
        driver.findElement(loginPage.getAccountButtonOnTop()).click();
    }

    public void loginFromMainPage(String email, String password) {
        driver.findElement(loginPage.getLoginButtonMain()).click();
        new WebDriverWait(driver, Duration.ofSeconds(25))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLogPage()));
        driver.findElement(loginPage.getEmailField()).sendKeys(email);
        driver.findElement(loginPage.getPasswordField()).sendKeys(password);
        driver.findElement(loginPage.getLoginConfirmationButton()).click();
    }

    public void loginFromPersonalAccount(String email, String password) {
        driver.findElement(loginPage.getAccountButtonOnTop()).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLogPage()));
        driver.findElement(loginPage.getEmailField()).sendKeys(email);
        driver.findElement(loginPage.getPasswordField()).sendKeys(password);
        driver.findElement(loginPage.getLoginConfirmationButton()).click();
    }

    public void loginFromRegistrationPage(String email, String password) {
        driver.findElement(loginPage.getAccountButtonOnTop()).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLogPage()));
        driver.findElement(loginPage.getRegisterButtonOnBottom()).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getRegistrationPage()));
        driver.findElement(loginPage.getLoginButtonInRegistrationForm()).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLogPage()));
        driver.findElement(loginPage.getEmailField()).sendKeys(email);
        driver.findElement(loginPage.getPasswordField()).sendKeys(password);
        driver.findElement(loginPage.getLoginConfirmationButton()).click();
    }

    public void loginFromPasswordRestorePage(String email, String password) {
        driver.findElement(loginPage.getLoginButtonMain()).click();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLogPage()));
        driver.findElement(loginPage.getRestorePasswordButton()).click();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getRestorePasswordPage()));
        driver.findElement(loginPage.getLoginButtonInRestorationPasswordForm()).click();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLogPage()));
        driver.findElement(loginPage.getEmailField()).sendKeys(email);
        driver.findElement(loginPage.getPasswordField()).sendKeys(password);
        driver.findElement(loginPage.getLoginConfirmationButton()).click();
    }

    public boolean isUserLoggedIn() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getMainPageCreateOrder())).isDisplayed();
        } catch (Exception ignored) {
            System.out.println("Пользователь не авторизован: " + ignored.getMessage());
            return false;
        }
    }
}

