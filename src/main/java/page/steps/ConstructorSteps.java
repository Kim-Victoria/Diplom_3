package page.steps;

import io.qameta.allure.Step;
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
    private By activeBunsSection = By.xpath("//div[contains(@class,'tab_tab_type_current__2BEPc')]/span[text()='Булки']");
    private By activeSauceSection = By.xpath("//div[contains(@class,'tab_tab_type_current__2BEPc')]/span[text()='Соусы']");
    private By activeFillingSection = By.xpath("//div[contains(@class,'tab_tab_type_current__2BEPc')]/span[text()='Начинки']");

    public ConstructorSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по логотипу Stellar Burgers")
    public void clickBurgerLogo() {
        driver.findElement(burgersLogo).click();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageCreateOrder));
    }
    @Step("Клик по кнопке Конструктор")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageCreateOrder));
    }
    @Step("Проверка активности страницы Конструктора")
    public boolean isConstructorPageVisible() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(mainPageCreateOrder)).isDisplayed();
        } catch (Exception ignored) {
            System.out.println("Пользователь не авторизован: " + ignored.getMessage());
            return false;
        }
    }
    @Step("Клик по разделу Булки")
    public void clickBunsSection() {
        driver.findElement(bunsSectionButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsSection));
    }
    @Step("Клик по разделу Соусы")
    public void clickSauceSection() {
        driver.findElement(sauceSectionButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(sauceSection));
    }
    @Step("Клик по разделу Начинки")
    public void clickFillingSection() {
        driver.findElement(fillingSectionButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingSection));
    }
    @Step("Проверка активности раздела Булки")
    public boolean isBunSectionActive() {
        return isSectionActive(activeBunsSection, "Булки");
    }
    @Step("Проверка активности раздела Соусы")
    public boolean isSauceSectionActive() {
        return isSectionActive(activeSauceSection, "Соусы");
    }
    @Step("Проверка активности раздела Начинки")
    public boolean isFillingSectionActive() {
        return isSectionActive(activeFillingSection, "Начинки");
    }
    @Step("Проверка активности раздела")
    private boolean isSectionActive(By locator, String sectionName) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            System.out.println("Раздел " + sectionName + " не активен: " + e.getMessage());
            return false;
        }
    }
}


