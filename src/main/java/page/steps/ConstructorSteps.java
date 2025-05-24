package page.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorSteps {
    private WebDriver driver;
    private By burgersLogo = By.xpath(".//a[@href='/']");
    private By mainPageCreateOrder = By.xpath(".//h1[contains(text(), 'Соберите бургер')]");
    private By constructorButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and contains(text(), 'Конструктор')]");
    private By bunsSectionButton = By.xpath(".//span[text()='Булки']");
    private By sauceSectionButton = By.xpath(".//span[text()='Соусы']");
    private By fillingSectionButton = By.xpath(".//span[text()='Начинки']");
    private By bunsSection = By.xpath(".//h2[text()='Булки']");
    private By sauceSection = By.xpath(".//h2[text()='Соусы']");
    private By fillingSection = By.xpath(".//h2[text()='Начинки']");

    public ConstructorSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void clickBurgerLogo() {
        driver.findElement(burgersLogo).click();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageCreateOrder));
    }
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageCreateOrder));
    }
    public boolean isConstructorPageVisible() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(mainPageCreateOrder)).isDisplayed();
        } catch (Exception ignored) {
            System.out.println("Пользователь не авторизован: " + ignored.getMessage());
            return false;
        }
    }
    public void clickBunsSection() {
        driver.findElement(bunsSectionButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsSection));
    }
    public void clickSauceSection() {
        driver.findElement(sauceSectionButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(sauceSection));
    }
    public void clickFillingSection() {
        driver.findElement(fillingSectionButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingSection));
    }
    public boolean isBunsSectionVisible() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(bunsSection)).isDisplayed();
        } catch (Exception ignored) {
            System.out.println("Раздел Булки не открыт: " + ignored.getMessage());
            return false;
        }
    }
    public boolean isSauceSectionVisible() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(sauceSection)).isDisplayed();
        } catch (Exception ignored) {
            System.out.println("Раздел Соусы не открыт: " + ignored.getMessage());
            return false;
        }
    }
    public boolean isFillingSectionVisible() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(fillingSection)).isDisplayed();
        } catch (Exception ignored) {
            System.out.println("Раздел Начинки не открыт: " + ignored.getMessage());
            return false;
        }
    }
}
