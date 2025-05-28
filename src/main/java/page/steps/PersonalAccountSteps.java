package page.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountSteps {
    private WebDriver driver;
    private final By personalAccountPage = By.xpath(".//a[@href='/account/profile' and contains(text(), 'Профиль')]");
    private final By exitButton = By.xpath(".//button[contains(text(), 'Выход')]");
    private By logPage = By.xpath(".//h2[contains(text(), 'Вход')]");

    public PersonalAccountSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка активности страницы Личного кабинета")
    public boolean isPersonalAccountPageVisible() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage)).isDisplayed();
        } catch (Exception ignored) {
            System.out.println("Пользователь не авторизован: " + ignored.getMessage());
            return false;
        }
        }
    @Step("Клик по кнопке Выход")
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }
    @Step("Проверка видимости страницы Входа")
    public boolean isLoginPageVisible() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(logPage)).isDisplayed();
        } catch (Exception ignored) {
            System.out.println("Пользователь авторизован: " + ignored.getMessage());
            return false;
        }
    }
}
