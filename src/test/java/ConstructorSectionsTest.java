import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import page.steps.ConstructorSteps;
import static org.junit.Assert.assertTrue;

@DisplayName("Тесты на раздел «Конструктор»")
public class ConstructorSectionsTest extends BaseTest {

    @Test
    @DisplayName("Проверка перехода к разделу Булки")
    @Description("Тест на переход к разделу Булки в конструкторе")
    public void checkBunsSectionMove() {
        ConstructorSteps objConstructionSteps = new ConstructorSteps(driver);
        moveToSauceSection(objConstructionSteps);
        moveToBunsSection(objConstructionSteps);
        checkBunsSectionActive(objConstructionSteps);
    }
    @Test
    @DisplayName("Проверка перехода к разделу Соусы")
    @Description("Тест на переход к разделу Соусы в конструкторе")
    public void checkSauceSectionMove() {
        ConstructorSteps objConstructionSteps = new ConstructorSteps(driver);
        moveToFillingSection(objConstructionSteps);
        moveToSauceSection(objConstructionSteps);
        checkSauceSectionActive(objConstructionSteps);
    }
    @Test
    @DisplayName("Проверка перехода к разделу Начинки")
    @Description("Тест на переход к разделу Начинки в конструкторе")
    public void checkFillingSectionMove() {
        ConstructorSteps objConstructionSteps = new ConstructorSteps(driver);
        moveToFillingSection(objConstructionSteps);
        checkFillingSectionActive(objConstructionSteps);
    }
    @Step("Переход к разделу Соусы")
    private void moveToSauceSection(ConstructorSteps steps) {
        steps.clickSauceSection();
    }

    @Step("Переход к разделу Булки")
    private void moveToBunsSection(ConstructorSteps steps) {
        steps.clickBunsSection();
    }

    @Step("Переход к разделу Начинки")
    private void moveToFillingSection(ConstructorSteps steps) {
        steps.clickFillingSection();
    }

    @Step("Проверка отображения раздела Булки")
    private void checkBunsSectionActive(ConstructorSteps steps) {
        assertTrue("Раздел Булки не отображается", steps.isBunSectionActive());
    }

    @Step("Проверка отображения раздела Соусы")
    private void checkSauceSectionActive(ConstructorSteps steps) {
        assertTrue("Раздел Соусы не отображается", steps.isSauceSectionActive());
    }

    @Step("Проверка отображения раздела Начинки")
    private void checkFillingSectionActive(ConstructorSteps steps) {
        assertTrue("Раздел Начинки не отображается", steps.isFillingSectionActive());
    }
}
